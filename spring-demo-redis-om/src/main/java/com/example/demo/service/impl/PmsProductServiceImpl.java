package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.PmsProductMapper;
import com.example.demo.entity.PmsProduct;
import com.example.demo.service.PmsProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* PmsProductServiceImpl实现类
* Created by YuanJW on 2022-09-28 16:44:42
*/
@Slf4j
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService{
    @Resource
    private PmsProductMapper mapper;

    @Override
    public int insertPmsProduct(PmsProduct record){
        int count = mapper.insert(record);
        return count;
    }

    @Override
    public PmsProduct getPmsProductById(Long id){
        return mapper.selectById(id);
    }

    @Override
    public List<PmsProduct> getPmsProducts(PmsProduct record){
        List<PmsProduct> list = mapper.getPmsProducts(record);
        return list;
    }

    @Override
    public int updatePmsProduct(PmsProduct record){
        int count = mapper.updatePmsProduct(record);
        return count;
    }

    @Override
    public int deletePmsProductByIds(List<Long> ids){
        return mapper.deleteBatchIds(ids);
    }

    @Override
    public int countPmsProduct(PmsProduct record) {
        return mapper.countPmsProduct(record);
    }
}