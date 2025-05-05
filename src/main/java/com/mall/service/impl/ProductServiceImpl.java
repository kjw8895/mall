package com.mall.service.impl;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.mall.application.dto.ProductCreateDto;
import com.mall.application.dto.ProductDto;
import com.mall.application.dto.UserInfo;
import com.mall.application.search.ProductSearchDto;
import com.mall.application.vo.ProductUserVo;
import com.mall.code.PointType;
import com.mall.config.property.AwsS3Properties;
import com.mall.domain.ProductEntity;
import com.mall.domain.UserEntity;
import com.mall.repository.ProductEntityRepository;
import com.mall.repository.UserEntityRepository;
import com.mall.service.AwsS3Service;
import com.mall.service.ProductPurchaseService;
import com.mall.service.ProductService;
import com.mall.service.UserPointService;
import com.mall.utils.DefaultDateTimeFormatUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties({AwsS3Properties.class})
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductEntityRepository productEntityRepository;
    private final ProductPurchaseService productPurchaseService;
    private final UserEntityRepository userEntityRepository;
    private final UserPointService userPointService;
    private final AwsS3Service awsS3Service;
    private final AwsS3Properties awsS3Properties;

    @Override
    public Page<ProductDto> page(Pageable pageable, ProductSearchDto searchDto) {
        Page<ProductUserVo> page = productEntityRepository.page(pageable, searchDto);
        return page.map(vo -> ProductDto.toDto(vo.getProduct(), vo.getUser()));
    }

    @Override
    public Page<ProductDto> mySelling(UserInfo userInfo, Pageable pageable) {
        Page<ProductUserVo> page = productEntityRepository.mySelling(pageable, userInfo.getId());
        return page.map(vo -> ProductDto.toDto(vo.getProduct(), vo.getUser()));
    }

    @Override
    public ProductDto fetchById(Long id) {
        ProductEntity product = productEntityRepository.findById(id).orElseThrow();
        return ProductDto.toDto(product, product.getUser());
    }

    @Override
    public ProductDto save(UserInfo userInfo, ProductCreateDto dto, MultipartFile file) {
        try {
            String timestamp = LocalDateTime.now().format(DefaultDateTimeFormatUtils.DATE_TIME_FILE_NAME_FORMAT);
            String fileName = String.format("%s_%s", timestamp, file.getOriginalFilename());

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());
            awsS3Service.putObject(awsS3Properties.getBucket(), awsS3Properties.getPath(fileName), file.getInputStream(), metadata);

            UserEntity user = userEntityRepository.findById(userInfo.getId()).orElseThrow(RuntimeException::new);
            ProductEntity product = ProductEntity.of(dto.getName(), dto.getPrice(), user, awsS3Properties.getFullPath(fileName), dto.getType());
            productEntityRepository.save(product);
            return ProductDto.toDto(product, user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductDto update(UserInfo userInfo, ProductCreateDto dto, MultipartFile file, Long id) {
        try {
            ProductEntity product = productEntityRepository.findById(id).orElseThrow();
            product.update(dto);

            if (file != null) {
                String timestamp = LocalDateTime.now().format(DefaultDateTimeFormatUtils.DATE_TIME_FILE_NAME_FORMAT);
                String fileName = String.format("%s_%s", timestamp, file.getOriginalFilename());

                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(file.getSize());
                metadata.setContentType(file.getContentType());
                awsS3Service.putObject(awsS3Properties.getBucket(), awsS3Properties.getPath(fileName), file.getInputStream(), metadata);
                product.updateImageUrl(awsS3Properties.getFullPath(fileName));
            }

            productEntityRepository.save(product);

            return ProductDto.toDto(product, product.getUser());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductDto pay(UserInfo userInfo, Long id) {
        ProductEntity product = productEntityRepository.findById(id).orElseThrow();

        Long totalPoint = userPointService.totalPoint(userInfo.getId());
        if (totalPoint < product.getPrice().longValue()) {
            throw new RuntimeException("포인트가 부족합니다.");
        }
        userPointService.updatePoint(userInfo.getId(), product.getPrice().longValue(), PointType.LOSE);
        product.pay();
        productEntityRepository.save(product);
        productPurchaseService.save(product.getPrice(), product.getId(), userInfo.getId());

        return ProductDto.toDto(product, product.getUser());
    }

    @Override
    public ProductDto complete(UserInfo userInfo, Long id) {
        ProductEntity product = productEntityRepository.findById(id).orElseThrow();
        product.complete();
        productEntityRepository.save(product);
        return ProductDto.toDto(product, product.getUser());
    }

    @Override
    public void deleteById(Long id) {
        ProductEntity product = productEntityRepository.findById(id).orElseThrow(RuntimeException::new);
        productEntityRepository.delete(product);
    }
}
