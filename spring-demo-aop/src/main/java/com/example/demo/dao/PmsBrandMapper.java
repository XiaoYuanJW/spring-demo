package com.example.demo.dao;

import com.example.demo.entity.PmsBrand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * PmsBrandMapper
 * Created by YuanJW on 2022-09-23 09:33:57
 */
@Mapper
public interface PmsBrandMapper extends BaseMapper<PmsBrand> {
    /**
     * 新增PmsBrand对象
     * @param record
     * @return id
     */
    Long savePmsBrand(PmsBrand record);
    /**
     * 根据id查询PmsBrand对象
     * @param id
     * @return PmsBrand
     */
    PmsBrand getPmsBrandById(Long id);
    /**
     * 根据搜索条件获取PmsBrand列表
     * @param record
     * @return
     */
    List<PmsBrand> getPmsBrands(PmsBrand record);
    /**
     * 修改PmsBrand对象
     * @param record
     * @return
     */
    int updatePmsBrand(PmsBrand record);
    /**
     * 批量删除PmsBrand
     * @param ids
     * @return
     */
    int deletePmsBrands(List<Long> ids);
    /**
     * 统计PmsBrand
     * @param record
     * @return
     */
    int countPmsBrand(PmsBrand record);
}
