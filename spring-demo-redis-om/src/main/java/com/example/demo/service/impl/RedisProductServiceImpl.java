package com.example.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.demo.dao.PmsProductMapper;
import com.example.demo.domain.Product;
import com.example.demo.entity.PmsProduct;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.RedisProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 商品缓存管理Service实现类
 * Created by YuanJW on 2022/9/28.
 */
@Service
public class RedisProductServiceImpl implements RedisProductService {
    @Autowired
    private PmsProductMapper pmsProductMapper;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void importAll() {
        List<PmsProduct> pmsProducts = pmsProductMapper.getPmsProducts(new PmsProduct());
        List<Product> products = new ArrayList<>();
        for (PmsProduct pmsProduct : pmsProducts) {
            Product product = new Product();
            BeanUtil.copyProperties(pmsProduct, product);
            products.add(product);
        }
        productRepository.deleteAll();
        productRepository.saveAll(products);
    }

    @Override
    public void create(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findByPage(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Product> pageResult = productRepository.findAll(pageable);
        return pageResult.getContent();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteAllById(List<Long> ids) {
        productRepository.deleteAllById(ids);
    }
}
