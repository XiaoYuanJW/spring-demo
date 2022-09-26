package com.example.demo.controller;

import com.example.demo.common.CommonPage;
import com.example.demo.common.CommonResult;
import com.example.demo.entity.PmsBrand;
import com.example.demo.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "PmsBrandController", description = "品牌信息管理")
@RequestMapping("/brand")
public class PmsBrandController {
    @Resource
    private PmsBrandService service;

    @ApiOperation("新增PmsBrandController信息")
    @PostMapping(value = "/insert", produces = "application/json;charset=UTF-8")
    public CommonResult savePmsBrandController(@RequestBody PmsBrand record) {
        int count = service.insertPmsBrand(record);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改PmsBrandController信息")
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public CommonResult updatePmsBrandController(@RequestBody PmsBrand record) {
        int count = service.updatePmsBrand(record);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("分页获取PmsBrandController信息")
    @PostMapping(value = "/list",produces = "application/json;charset=UTF-8")
    public CommonResult<CommonPage<PmsBrand>> listPmsBrandControllers(PmsBrand record) {
        List<PmsBrand> list = service.getPmsBrands(record);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("根据id查询PmsBrandController权限")
    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public CommonResult<PmsBrand> getPmsBrandController(@PathVariable Long id) {
        PmsBrand PmsBrand = service.getPmsBrandById(id);
        return CommonResult.success(PmsBrand);
    }

    @ApiOperation("批量删除PmsBrandController信息")
    @PostMapping(value = "/remove",produces = "application/json;charset=UTF-8")
    public CommonResult removePmsBrandControllers(@RequestParam("ids") List<Long> ids) {
        int count = service.deletePmsBrandByIds(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("统计PmsBrandController数量")
    @GetMapping(value = "/count",produces = "application/json;charset=UTF-8")
    public CommonResult countPmsBrandController(PmsBrand record) {
        int count = service.countPmsBrand(record);
        return CommonResult.success(count);
    }
}