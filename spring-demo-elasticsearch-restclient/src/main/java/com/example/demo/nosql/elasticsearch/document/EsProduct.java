package com.example.demo.nosql.elasticsearch.document;

import com.example.demo.entity.PmsProduct;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by YuanJW on 2022/11/8.
 */
@Data
@NoArgsConstructor
public class EsProduct implements Serializable {
    private static final long serialVersionUID = -1L;

    private Long id;

    private String productSn;

    private Long brandId;

    private String brandName;

    private Long productCategoryId;

    private String productCategoryName;

    private String pic;

    private String name;

    private String subTitle;

    private String keywords;

    private BigDecimal price;

    private Integer sale;

    private Integer newStatus;

    private Integer recommandStatus;

    private Integer stock;

    private Integer promotionType;

    private Integer sort;

    public EsProduct(PmsProduct pmsProduct) {
        this.id = pmsProduct.getId();
        this.productSn = pmsProduct.getProductSn();
        this.brandId = pmsProduct.getBrandId();
        this.brandName = pmsProduct.getBrandName();
        this.productCategoryId = pmsProduct.getProductCategoryId();
        this.productCategoryName = pmsProduct.getProductCategoryName();
        this.pic = pmsProduct.getPic();
        this.name = pmsProduct.getName();
        this.subTitle = pmsProduct.getSubTitle();
        this.keywords =  pmsProduct.getSubTitle();;
        this.price =  pmsProduct.getPrice();;
        this.sale =  pmsProduct.getSale();;
        this.newStatus =  pmsProduct.getNewStatus();;
        this.recommandStatus =  pmsProduct.getRecommandStatus();;
        this.stock =  pmsProduct.getStock();;
        this.promotionType =  pmsProduct.getPromotionType();;
        this.sort =  pmsProduct.getSort();
    }
}
