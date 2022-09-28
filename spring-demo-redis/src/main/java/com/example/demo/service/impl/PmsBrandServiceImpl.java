package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.config.RedisConfig;
import com.example.demo.dao.PmsBrandMapper;
import com.example.demo.entity.PmsBrand;
import com.example.demo.service.PmsBrandService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = RedisConfig.REDIS_KEY_DATABASE, key = "'pms:brand:'+#id", unless = "#result==null")
    @Override
    public PmsBrand getPmsBrandById(Long id){
        return mapper.selectById(id);
    }

    @Override
    public List<PmsBrand> getPmsBrands(PmsBrand record, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<PmsBrand> list = mapper.getPmsBrands(record);
        return list;
    }

    @CacheEvict(value = RedisConfig.REDIS_KEY_DATABASE, key = "'pms:brand:'+#record.getId()")
    @Override
    public int updatePmsBrand(PmsBrand record){
        int count = mapper.updatePmsBrand(record);
        return count;
    }

    @CacheEvict(value = RedisConfig.REDIS_KEY_DATABASE, allEntries = true)
    @Override
    public int deletePmsBrandByIds(List<Long> ids){
        return mapper.deleteBatchIds(ids);
    }

    @Override
    public int countPmsBrand(PmsBrand record) {
        return mapper.countPmsBrand(record);
    }
}