package com.mall.utils;

import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public class DefaultDateTimeFormatUtils {
    public static final DateTimeFormatter DATE_TIME_FILE_NAME_FORMAT = ofPattern("yyyyMMdd_HHmmssSSS");
}
