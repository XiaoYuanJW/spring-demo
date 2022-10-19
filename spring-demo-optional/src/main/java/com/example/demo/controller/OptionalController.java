package com.example.demo.controller;

import com.example.demo.common.CommonResult;
import com.example.demo.entity.PmsBrand;
import com.example.demo.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Created by YuanJW on 2022/10/1.
 */
@Slf4j
@RestController
@Api(tags = "OptionalController", description = "Optional控制器")
@RequestMapping("/optional")
public class OptionalController {
    @Resource
    private PmsBrandService pmsBrandService;
    
    @ApiOperation("返回空对象")
    @GetMapping(value = "/empty", produces = "application/json;charset=UTF-8")
    public CommonResult empty() {
        Optional<Object> optional = Optional.empty();
        return CommonResult.success(optional);
    }

    @ApiOperation("传入对象")
    @GetMapping(value = "/of/{id}", produces = "application/json;charset=UTF-8")
    public CommonResult of(@PathVariable Long id) {
        PmsBrand pmsBrand = pmsBrandService.getPmsBrandById(id);
        Optional<PmsBrand> brand = Optional.of(pmsBrand);
        return CommonResult.success(brand);
    }

    @ApiOperation("传入可空对象")
    @GetMapping(value = "/ofNullable/{id}", produces = "application/json;charset=UTF-8")
    public CommonResult ofNullable(@PathVariable Long id) {
        PmsBrand pmsBrand = pmsBrandService.getPmsBrandById(id);
        Optional<PmsBrand> brand = Optional.ofNullable(pmsBrand);
        PmsBrand getBrand = brand.get();
        return CommonResult.success(getBrand);
    }

    @ApiOperation("判断是否为空")
    @GetMapping(value = "/ifPresent/{id}", produces = "application/json;charset=UTF-8")
    public CommonResult ifPresent(@PathVariable Long id) {
        PmsBrand pmsBrand = pmsBrandService.getPmsBrandById(id);
        Optional<PmsBrand> brand = Optional.ofNullable(pmsBrand);
        brand.ifPresent(b -> {
            log.info(b.toString());
        });
        return CommonResult.success(brand);
    }

    @ApiOperation("条件筛选")
    @GetMapping(value = "/filter/{id}", produces = "application/json;charset=UTF-8")
    public CommonResult filter(@PathVariable Long id) {
        PmsBrand pmsBrand = pmsBrandService.getPmsBrandById(id);
        Optional<PmsBrand> brand = Optional.ofNullable(pmsBrand);
        Optional<PmsBrand> optional = brand.filter(b -> "小米".equals(b.getName()));
        return CommonResult.success(optional);
    }

    @ApiOperation("判空选择")
    @GetMapping(value = "/orElse/{id}", produces = "application/json;charset=UTF-8")
    public CommonResult orElse(@PathVariable Long id) {
        PmsBrand pmsBrand = pmsBrandService.getPmsBrandById(id);
        PmsBrand brand = Optional.ofNullable(pmsBrand).orElse(new PmsBrand().setName("DefaultName"));
        return CommonResult.success(brand);
    }

    @ApiOperation("判空异常")
    @GetMapping(value = "/orElseThrow/{id}", produces = "application/json;charset=UTF-8")
    public CommonResult orElseThrow(@PathVariable Long id) {
        PmsBrand pmsBrand = pmsBrandService.getPmsBrandById(id);
        PmsBrand brand = Optional.ofNullable(pmsBrand).orElseThrow(() -> new RuntimeException("品牌为空"));
        return CommonResult.success(brand);
    }
}
