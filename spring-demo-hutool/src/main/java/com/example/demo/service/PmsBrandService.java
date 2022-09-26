package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.PmsBrand;

import java.util.List;

/**
* PmsBrandService
* Created by YuanJW on 2022-09-23 09:33:57
*/
public interface PmsBrandService extends IService<PmsBrand> {
    /**
    * 新增PmsBrand对象
    * @param object
    * @return
    */
    int insertPmsBrand(PmsBrand object);
    /**
    * 获取PmsBrand列表
    * @param object
    * @return
    */
    List<PmsBrand> getPmsBrands(PmsBrand object);
    /**
    * 根据id获取PmsBrand对象
    * @param id
    * @return
    */
    PmsBrand getPmsBrandById(Long id);
    /**
    * 修改PmsBrand对象
    * @param object
    * @return
    */
    int updatePmsBrand(PmsBrand object);
    /**
    * 批量删除PmsBrand
    * @param ids
    * @return
    */
    int deletePmsBrandByIds(List<Long> ids);
    /**
    * 统计PmsBrand
    * @param record
    * @return
    */
    int countPmsBrand(PmsBrand record);
}