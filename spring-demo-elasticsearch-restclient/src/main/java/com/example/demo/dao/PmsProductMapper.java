package com.example.demo.dao;

import com.example.demo.entity.PmsProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* PmsProductMapper
* Created by YuanJW on 2022-11-07 14:02:34
*/
@Mapper
public interface PmsProductMapper extends BaseMapper<PmsProduct> {
    /**
    * 新增PmsProduct对象
    * @param record
    * @return id
    */
    Long savePmsProduct(PmsProduct record);
    /**
    * 根据id查询PmsProduct对象
    * @param id
    * @return PmsProduct
    */
    PmsProduct getPmsProductById(Long id);
    /**
    * 根据搜索条件获取PmsProduct列表
    * @param record
    * @return
    */
    List<PmsProduct> getPmsProducts(PmsProduct record);
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
    int deletePmsProducts(List<Long> ids);
    /**
    * 统计PmsProduct
    * @param record
    * @return
    */
    int countPmsProduct(PmsProduct record);
}