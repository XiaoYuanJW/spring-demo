package com.example.demo.domain;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

/**
 * 商品缓存实体
 * Created by YuanJW on 2022/9/28.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Document(language = "chinese")
public class Product {
    @Id
    private Long id;
    @Searchable
    private String name;
    @Searchable
    private String subTitle;
    @Searchable
    private String description;
    @Indexed
    private String brandName;
    @Indexed
    private String productSn;
    @Indexed
    private BigDecimal price;
    @Indexed
    private Integer sale;
}
