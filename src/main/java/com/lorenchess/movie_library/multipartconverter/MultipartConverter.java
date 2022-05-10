package com.lorenchess.movie_library.multipartconverter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MultipartConverter implements Converter<MultipartFile, String> {

    @Override
    public String convert(MultipartFile source) {
        try {
            return new String(source.getBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Fail to convert multipart file to string", e);
        }
    }
}
