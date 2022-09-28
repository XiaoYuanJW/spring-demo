package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import com.example.demo.common.CommonResult;
import com.example.demo.entity.PmsBrand;
import com.example.demo.service.PmsBrandService;
import com.example.demo.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis测试Controller
 * Created by YuanJW on 2022/9/28.
 */
@Slf4j
@Api(tags = "RedisController", description = "redis测试")
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Resource
    private RedisService redisService;
    @Resource
    private PmsBrandService pmsBrandService;

    @ApiOperation("测试String缓存")
    @GetMapping(value = "/stringTest", produces = "application/json;charset=UTF-8")
    public CommonResult<PmsBrand> stringTest() {
        List<PmsBrand> pmsBrands = pmsBrandService.getPmsBrands(new PmsBrand(), 0, 5);
        PmsBrand pmsBrand = pmsBrands.get(0);
        String key = "redis:string:" + pmsBrand.getId();
        redisService.set(key, pmsBrand, 120);
        PmsBrand cacheBrand = (PmsBrand) redisService.get(key);
        return CommonResult.success(cacheBrand);
    }

    @ApiOperation("测试Hash结构中的缓存")
    @GetMapping(value = "/hashTest", produces = "application/json;charset=UTF-8")
    public CommonResult<PmsBrand> hashTest() {
        List<PmsBrand> pmsBrands = pmsBrandService.getPmsBrands(new PmsBrand(), 0, 5);
        PmsBrand pmsBrand = pmsBrands.get(0);
        String key = "redis:hash:" + pmsBrand.getId();
        Map<String, Object> value = BeanUtil.beanToMap(pmsBrand);
        redisService.hSetAll(key, value, 120);
        Map<Object, Object> cacheValue = redisService.hGetAll(key);
        PmsBrand cacheBrand = BeanUtil.mapToBean(cacheValue, PmsBrand.class, true);
        return CommonResult.success(cacheBrand);
    }

    @ApiOperation("测试Set结构中的缓存")
    @GetMapping(value = "/setTest", produces = "application/json;charset=UTF-8")
    public CommonResult<Set<Object>> setTest() {
        List<PmsBrand> pmsBrands = pmsBrandService.getPmsBrands(new PmsBrand(), 0, 5);
        String key = "redis:set:all";
        redisService.sAdd(key, 120, (Object[]) ArrayUtil.toArray(pmsBrands, PmsBrand.class));
        redisService.sRemove(key, pmsBrands.get(0));
        Set<Object> cachedBrandList = redisService.sMembers(key);
        return CommonResult.success(cachedBrandList);
    }

    @ApiOperation("测试List结构中的缓存")
    @GetMapping(value = "/listTest", produces = "application/json;charset=UTF-8")
    public CommonResult<List<Object>> listTest() {
        List<PmsBrand> pmsBrands = pmsBrandService.getPmsBrands(new PmsBrand(), 0, 5);
        String key = "redis:list:all";
        redisService.lPushAll(key, 120, (Object[]) ArrayUtil.toArray(pmsBrands, PmsBrand.class));
        List<Object> cachedBrandList = redisService.lRange(key, 0, 3);
        return CommonResult.success(cachedBrandList);
    }
}

