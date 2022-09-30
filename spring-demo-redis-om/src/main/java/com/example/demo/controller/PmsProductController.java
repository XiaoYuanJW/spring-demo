package com.example.demo.controller;

import com.example.demo.common.CommonPage;
import com.example.demo.common.CommonResult;
import com.example.demo.entity.PmsProduct;
import com.example.demo.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "PmsProductController", description = "申报信息管理")
@RequestMapping("/product")
public class PmsProductController {
    @Autowired
    private PmsProductService service;

    @ApiOperation("新增PmsProductController信息")
    @PostMapping(value = "/insert", produces = "application/json;charset=UTF-8")
    public CommonResult savePmsProductController(@RequestBody PmsProduct record) {
        int count = service.insertPmsProduct(record);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改PmsProductController信息")
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public CommonResult updatePmsProductController(@RequestBody PmsProduct record) {
        int count = service.updatePmsProduct(record);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("分页获取PmsProductController信息")
    @PostMapping(value = "/list",produces = "application/json;charset=UTF-8")
    public CommonResult<CommonPage<PmsProduct>> listPmsProductControllers(PmsProduct record) {
        List<PmsProduct> list = service.getPmsProducts(record);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("根据id查询PmsProductController权限")
    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public CommonResult<PmsProduct> getPmsProductController(@PathVariable Long id) {
        PmsProduct PmsProduct = service.getPmsProductById(id);
        return CommonResult.success(PmsProduct);
    }

    @ApiOperation("批量删除PmsProductController信息")
    @PostMapping(value = "/remove",produces = "application/json;charset=UTF-8")
    public CommonResult removePmsProductControllers(@RequestParam("ids") List<Long> ids) {
        int count = service.deletePmsProductByIds(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("统计PmsProductController数量")
    @GetMapping(value = "/count",produces = "application/json;charset=UTF-8")
    public CommonResult countPmsProductController(PmsProduct record) {
        int count = service.countPmsProduct(record);
        return CommonResult.success(count);
    }
}