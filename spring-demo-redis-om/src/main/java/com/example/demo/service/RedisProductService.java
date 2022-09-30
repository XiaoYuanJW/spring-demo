package com.example.demo.service;

import com.example.demo.domain.Product;

import java.util.List;
import java.util.Optional;

/**
 * 商品缓存管理Service
 * Created by YuanJW on 2022/9/28.
 */
public interface RedisProductService {
    /**
     * 从数据库中导入所有商品到redis
     * @return
     */
    void importAll();

    /**
     * 创建商品到redis
     * @param product
     */
    void create(Product product);

    /**
     * 从redis分页查询商品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Product> findByPage(Integer pageNum, Integer pageSize);

    /**
     * 从redis中根据id查询单个商品详情
     * @param id
     * @return
     */
    Optional<Product> findById(Long id);

    /**
     * 从redis中根据ids删除商品列表
     * @param ids
     */
    void deleteAllById(List<Long> ids);
}
