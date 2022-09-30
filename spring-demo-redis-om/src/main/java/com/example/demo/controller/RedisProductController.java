package com.example.demo.controller;

import com.example.demo.common.CommonResult;
import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.RedisProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * RedisOmController
 * Created by YuanJW on 2022/9/28.
 */
@Slf4j
@RestController
@Api(tags = "RedisProductController", description = "RedisOm控制器")
@RequestMapping("/redisProduct")
public class RedisProductController {
    @Autowired
    private RedisProductService redisProductService;
    @Autowired
    private ProductRepository productRepository;

    @ApiOperation("导入商品")
    @GetMapping(value = "/import", produces = "application/json;charset=UTF-8")
    public CommonResult importList() {
        redisProductService.importAll();
        return CommonResult.success(null);
    }

    @ApiOperation("创建商品")
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public CommonResult create(@RequestBody Product product) {
        redisProductService.create(product);
        return CommonResult.success(null);
    }

    @ApiOperation("分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public CommonResult<List<Product>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "5") Integer pageSize) {
        List<Product> products = redisProductService.findByPage(pageNum, pageSize);
        return CommonResult.success(products);
    }

    @ApiOperation("查看详情")
    @GetMapping(value = "/detail/{id}", produces = "application/json;charset=UTF-8")
    public CommonResult<Product> detail(@PathVariable Long id) {
        Optional<Product> result = redisProductService.findById(id);
        Product product = result.orElse(null);
        return CommonResult.success(product);
    }

    @ApiOperation("删除商品列表")
    @PostMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    public CommonResult delete(@RequestBody List<Long> ids) {
        redisProductService.deleteAllById(ids);
        return CommonResult.success(null);
    }

    @ApiOperation("根据品牌名称查询商品")
    @GetMapping(value = "/findByBrandName", produces = "application/json;charset=UTF-8")
    public CommonResult<List<Product>> findByBrandName(String brandName) {
        List<Product> products = productRepository.findByBrandName(brandName);
        return CommonResult.success(products);
    }

    @ApiOperation("根据商品名称或副标题查询商品")
    @GetMapping(value = "/findByNameOrSubTitle", produces = "application/json;charset=UTF-8")
    public CommonResult<List<Product>> findByNameOrSubTitle(String name, String subTitle) {
        List<Product> products = productRepository.findByNameOrSubTitle(name, subTitle);
        return CommonResult.success(products);
    }
}
