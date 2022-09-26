package com.example.demo.service.impl;

import com.example.demo.dao.PmsBrandMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.PmsBrand;
import com.example.demo.service.PmsBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* PmsBrandServiceImpl实现类
* Created by YuanJW on 2022-09-23 09:33:57
*/
@Slf4j
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService{
    @Resource
    private PmsBrandMapper mapper;

    @Override
    public int insertPmsBrand(PmsBrand record){
        int count = mapper.insert(record);
        return count;
    }

    @Override
    public PmsBrand getPmsBrandById(Long id){
        return mapper.selectById(id);
    }

    @Override
    public List<PmsBrand> getPmsBrands(PmsBrand record){
        List<PmsBrand> list = mapper.getPmsBrands(record);
        return list;
    }

    @Override
    public int updatePmsBrand(PmsBrand record){
        int count = mapper.updatePmsBrand(record);
        return count;
    }

    @Override
    public int deletePmsBrandByIds(List<Long> ids){
        return mapper.deleteBatchIds(ids);
    }

    @Override
    public int countPmsBrand(PmsBrand record) {
        return mapper.countPmsBrand(record);
    }
}