package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 文件上传返回结果
 * Created by YuanJW on 2022/9/26.
 */
@Data
@Builder
public class MinioUploadDto {
    private String url;

    private String name;
}
