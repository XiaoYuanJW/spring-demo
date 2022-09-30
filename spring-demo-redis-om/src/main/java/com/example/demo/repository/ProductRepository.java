package com.example.demo.repository;

import com.example.demo.domain.Product;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商品Repository
 * Created by YuanJW on 2022/9/28.
 */
@Component
public interface ProductRepository extends RedisDocumentRepository<Product, Long> {
    /**
     * 根据品牌名称查询商品
     * @param brandName
     * @return
     */
    List<Product> findByBrandName(String brandName);

    /**
     * 根据商品名称或副标题查询商品
     * @param name
     * @param subTitle
     * @return
     */
    List<Product> findByNameOrSubTitle(String name, String subTitle);
}
