package com.example.demo.controller;

import com.example.demo.common.CommonResult;
import com.example.demo.dto.MinioUploadDto;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * MinIO文件上传Controller
 * Created by YuanJW on 2022/9/26.
 */
@Slf4j
@RestController
@RequestMapping("/minio")
@Api(tags = "MinioController", description = "MinIO对象存储管理")
public class MinioController {
    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.bucket}")
    private String BUCKET_NAME;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public CommonResult upload(@RequestParam("file") MultipartFile file) {
        // 创建一个MinIO的Java客户端
        try {
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            boolean isExists = minioClient.bucketExists(BUCKET_NAME);
            if (isExists) {
                log.info("存储桶已经存在！");
            } else {
                // 创建存储桶并设置只读权限
                minioClient.makeBucket(BUCKET_NAME);
                minioClient.setBucketPolicy(BUCKET_NAME, "*.*",  PolicyType.READ_ONLY);
            }
            // 获取文件名称
            String filename = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            // 设置存储对象名称
            String objectName = sdf.format(new Date()) + "/" + filename;
            // 使用putObject上传一个文件到存储桶中
            minioClient.putObject(BUCKET_NAME, objectName, file.getInputStream(), file.getContentType());
            log.info("文件上传成功！");
            MinioUploadDto minioUploadDto = MinioUploadDto.builder()
                    .name(filename)
                    .url(ENDPOINT + '/' + BUCKET_NAME + "/" + objectName)
                    .build();
            return CommonResult.success(minioUploadDto);
        } catch (Exception e) {
           log.info("上传发送错误：{}！", e.getMessage()); 
        }
        return CommonResult.failed();
    }

    @ApiOperation("文件删除")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("objectName") String objectName) {
        try {
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            minioClient.removeObject(BUCKET_NAME, objectName);
            return CommonResult.success(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResult.failed();
    }
}
