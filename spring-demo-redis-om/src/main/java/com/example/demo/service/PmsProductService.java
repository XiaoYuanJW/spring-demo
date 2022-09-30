package com.example.demo.service;

import com.example.demo.entity.PmsProduct;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* PmsProductService
* Created by YuanJW on 2022-09-28 16:44:42
*/
public interface PmsProductService extends IService<PmsProduct> {
    /**
    * 新增PmsProduct对象
    * @param record
    * @return
    */
    int insertPmsProduct(PmsProduct record);
    /**
    * 获取PmsProduct列表
    * @param record
    * @return
    */
    List<PmsProduct> getPmsProducts(PmsProduct record);
    /**
    * 根据id获取PmsProduct对象
    * @param id
    * @return
    */
    PmsProduct getPmsProductById(Long id);
    /**
    * 修改PmsProduct对象
    * @param record
    * @return
    */
    int updatePmsProduct(PmsProduct record);
    /**
    * 批量删除PmsProduct
    * @param ids
    * @return
    */
    int deletePmsProductByIds(List<Long> ids);
    /**
    * 统计PmsProduct
    * @param record
    * @return
    */
    int countPmsProduct(PmsProduct record);
}