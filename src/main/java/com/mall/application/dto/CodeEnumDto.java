package com.mall.application.dto;

import com.mall.code.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CodeEnumDto {
    private String code;
    private String text;

    public static CodeEnumDto toDto(CodeEnum codeEnum) {
        return new CodeEnumDto(codeEnum.getCode(), codeEnum.getText());
    }
}
