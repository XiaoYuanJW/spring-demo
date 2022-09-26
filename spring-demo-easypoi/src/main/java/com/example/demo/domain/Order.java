package com.example.demo.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 订单
 * Created by YuanJW on 2022/9/20.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Order {
    @Excel(name = "ID", width = 10, needMerge = true)
    private Long id;
    @Excel(name = "订单号", width = 20, needMerge = true)
    private String orderSn;
    @Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss", needMerge = true)
    private Date createTime;
    @Excel(name = "收货地址", width = 20, needMerge = true)
    private String receiverAddress;
    /**
     * @ExcelEntity: 订单和会员的一对一关系
     */
    @ExcelEntity(name = "会员信息")
    private Member member;
    /**
     * @ExcelCollection：订单和商品的一对多关系
     */
    @ExcelCollection(name = "商品列表")
    private List<Product> products;
}
