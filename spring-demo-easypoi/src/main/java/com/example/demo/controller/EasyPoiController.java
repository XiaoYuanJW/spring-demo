package com.example.demo.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import cn.afterturn.easypoi.word.WordExportUtil;
import cn.hutool.core.bean.BeanUtil;
import com.example.demo.common.CommonResult;
import com.example.demo.domain.Order;
import com.example.demo.domain.Product;
import com.example.demo.handler.ExportDataHandler;
import com.example.demo.util.LocalJsonUtil;
import com.example.demo.domain.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * EasyPoiController-导入导出控制器控制器
 * Created by YuanJW on 2022/9/20.
 */
@Slf4j
@RestController
@Api(tags = "EasyPoiController", description = "导入导出控制器")
@RequestMapping("/easypoi")
public class EasyPoiController {
    @ApiModelProperty(value = "导出会员列表Excel")
    @GetMapping(value = "/exportMembers")
    public void exportMembers(ModelMap modelMap,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        // 读取资源json数据转换为会员对象数组
        List<Member> members = LocalJsonUtil.getListFromJson("json/members.json", Member.class);
        // 构建ExportParams对象
        ExportParams exportParams = new ExportParams("会员列表", "会员列表", ExcelType.XSSF);
        // 对导出结果自定义处理
        ExportDataHandler exportDataHandler = new ExportDataHandler();
        exportDataHandler.setNeedHandlerFields(new String[]{"昵称"});
        exportParams.setDataHandler(exportDataHandler);
        // ModelMap模型对象添加信息
        modelMap.put(NormalExcelConstants.FILE_NAME, "members");
        modelMap.put(NormalExcelConstants.PARAMS, exportParams);
        modelMap.put(NormalExcelConstants.DATA_LIST, members);
        modelMap.put(NormalExcelConstants.CLASS, Member.class);
        // 导出内容到Excel文件中
        PoiBaseView.render(modelMap, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    @ApiModelProperty(value = "导入会员列表Excel")
    @PostMapping("/importMembers")
    public CommonResult importMembers(@RequestPart("file") MultipartFile file) {
        // 构建ImportParams对象
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);
        // 导入会员列表
        try {
            List<Member> members = ExcelImportUtil.importExcel(file.getInputStream(), Member.class, importParams);
            return CommonResult.success(members);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("导出失败");
        }
    }

    @ApiModelProperty(value = "导出订单列表Excel")
    @GetMapping("/exportOrders")
    public void exportOrders(ModelMap modelMap,
                             HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse) {
        // 获取订单列表
        List<Order> orders = this.getOrders();
        // 构建ExportParams对象
        ExportParams exportParams = new ExportParams("订单列表", "订单列表", ExcelType.XSSF);
        exportParams.setExclusions(new String[]{"ID", "生日", "性别"});
        // ModelMap模型对象添加信息
        modelMap.put(NormalExcelConstants.FILE_NAME, "orders");
        modelMap.put(NormalExcelConstants.PARAMS, exportParams);
        modelMap.put(NormalExcelConstants.DATA_LIST, orders);
        modelMap.put(NormalExcelConstants.CLASS, Order.class);
        // 导出内容到Excel文件中
        PoiBaseView.render(modelMap, httpServletRequest, httpServletResponse, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    public List<Order> getOrders() {
        List<Order> orders = LocalJsonUtil.getListFromJson("json/orders.json", Order.class);
        List<Product> products = LocalJsonUtil.getListFromJson("json/products.json", Product.class);
        List<Member> members = LocalJsonUtil.getListFromJson("json/members.json", Member.class);
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            order.setMember(members.get(i));
            order.setProducts(products);
        }
        return orders;
    }

    @ApiModelProperty(value = "导出会员文档")
    @GetMapping("/wordExport")
    public void exportWord(HttpServletResponse httpServletResponse) {
        List<Member> members = LocalJsonUtil.getListFromJson("json/members.json", Member.class);
        List<Order> orders = LocalJsonUtil.getListFromJson("json/orders.json", Order.class);
        List<Map<String, Object>> orderMaps = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            Map<String, Object> orderMap = BeanUtil.beanToMap(orders.get(i));
            orderMap.put("index", i + 1);
            orderMaps.add(orderMap);
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        members.forEach(member -> {
            Map<String, Object> map = BeanUtil.beanToMap(member);
            map.put("orders", orderMaps);
            maps.add(map);
        });
        try {
            // 使用设定模板导出文档
            XWPFDocument xwpfDocument = WordExportUtil.exportWord07("template/member.docx", maps);
            // 设置编码格式
            httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
            // 设置内容类型
            httpServletResponse.setContentType("application/octet-stream");
            // 设置头和文件命名
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("会员信息表.docx", StandardCharsets.UTF_8.name()));
            // 导出word
            xwpfDocument.write(httpServletResponse.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
