package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* 商品信息实体类 : pms_product
* Created by YuanJW on 2022-09-28 16:44:42
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value ="pms_product")
@ApiModel(value = "PmsProduct", description = "商品信息")
public class PmsProduct implements Serializable {
    @NotNull(message="[商品id]不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "商品id")
    private Long id;
    
    @ApiModelProperty(value = "商品品牌分类id")
    private Long brandId;
    
    @ApiModelProperty(value = "商品种类分类id")
    private Long productCategoryId;
    
    @ApiModelProperty(value = "商品运费模板id")
    private Long feightTemplateId;
    
    @ApiModelProperty(value = "商品属性分类id")
    private Long productAttributeCategoryId;
    
    @NotBlank(message="[商品名称]不能为空")
    @Size(max= 64,message="编码长度不能超过64")
    @Length(max= 64,message="编码长度不能超过64")
    @ApiModelProperty(value = "商品名称")
    private String name;
    
    
    @Size(max= 255,message="编码长度不能超过255")
    @Length(max= 255,message="编码长度不能超过255")
    @ApiModelProperty(value = "商品图片地址")
    private String pic;
    
    @NotBlank(message="[商品货号]不能为空")
    @Size(max= 64,message="编码长度不能超过64")
    @Length(max= 64,message="编码长度不能超过64")
    @ApiModelProperty(value = "商品货号")
    private String productSn;
    
    @ApiModelProperty(value = "商品删除状态：0->未删除；1->已删除")
    private Integer deleteStatus;
    
    @ApiModelProperty(value = "商品上架状态：0->下架；1->上架")
    private Integer publishStatus;
    
    @NotNull(message="[商品新品状态:0->不是新品；1->新品]不能为空")
    @ApiModelProperty(value = "商品新品状态:0->不是新品；1->新品")
    private Integer newStatus;
    
    @ApiModelProperty(value = "商品推荐状态；0->不推荐；1->推荐")
    private Integer recommandStatus;
    
    @ApiModelProperty(value = "商品审核状态：0->未审核；1->审核通过")
    private Integer verifyStatus;
    
    @ApiModelProperty(value = "商品排序")
    private Integer sort;
    
    @ApiModelProperty(value = "商品销量")
    private Integer sale;
    
    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;
    
    @ApiModelProperty(value = "商品促销价格")
    private BigDecimal promotionPrice;
    
    @ApiModelProperty(value = "商品赠送的成长值")
    private Integer giftGrowth;
    
    @ApiModelProperty(value = "商品赠送的积分")
    private Integer giftPoint;
    
    @ApiModelProperty(value = "商品限制使用的积分数")
    private Integer usePointLimit;
    
    
    @Size(max= 255,message="编码长度不能超过255")
    @Length(max= 255,message="编码长度不能超过255")
    @ApiModelProperty(value = "商品副标题")
    private String subTitle;
    
    
    @Size(max= -1,message="编码长度不能超过-1")
    @Length(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty(value = "商品描述")
    private String description;
    
    @ApiModelProperty(value = "商品市场价")
    private BigDecimal originalPrice;
    
    @ApiModelProperty(value = "商品库存")
    private Integer stock;
    
    @ApiModelProperty(value = "商品库存预警值")
    private Integer lowStock;
    
    
    @Size(max= 16,message="编码长度不能超过16")
    @Length(max= 16,message="编码长度不能超过16")
    @ApiModelProperty(value = "商品单位")
    private String unit;
    
    @ApiModelProperty(value = "商品重量，默认为克")
    private BigDecimal weight;
    
    @ApiModelProperty(value = "商品是否为预告商品：0->不是；1->是")
    private Integer previewStatus;
    
    
    @Size(max= 64,message="编码长度不能超过64")
    @Length(max= 64,message="编码长度不能超过64")
    @ApiModelProperty(value = "商品以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮")
    private String serviceIds;
    
    
    @Size(max= 255,message="编码长度不能超过255")
    @Length(max= 255,message="编码长度不能超过255")
    @ApiModelProperty(value = "商品关键字")
    private String keywords;
    
    
    @Size(max= 255,message="编码长度不能超过255")
    @Length(max= 255,message="编码长度不能超过255")
    @ApiModelProperty(value = "商品备注")
    private String note;
    
    
    @Size(max= 255,message="编码长度不能超过255")
    @Length(max= 255,message="编码长度不能超过255")
    @ApiModelProperty(value = "商品画册图片，连产品图片限制为5张，以逗号分割")
    private String albumPics;
    
    
    @Size(max= 255,message="编码长度不能超过255")
    @Length(max= 255,message="编码长度不能超过255")
    @ApiModelProperty(value = "商品详细标题")
    private String detailTitle;
    
    
    @Size(max= -1,message="编码长度不能超过-1")
    @Length(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty(value = "商品详细描述")
    private String detailDesc;
    
    
    @Size(max= -1,message="编码长度不能超过-1")
    @Length(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty(value = "商品详情网页内容")
    private String detailHtml;
    
    
    @Size(max= -1,message="编码长度不能超过-1")
    @Length(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty(value = "商品移动端网页详情")
    private String detailMobileHtml;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "商品促销开始时间")
    private Date promotionStartTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "商品促销结束时间")
    private Date promotionEndTime;
    
    @ApiModelProperty(value = "商品活动限购数量")
    private Integer promotionPerLimit;
    
    @ApiModelProperty(value = "商品促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购")
    private Integer promotionType;
    
    
    @Size(max= 255,message="编码长度不能超过255")
    @Length(max= 255,message="编码长度不能超过255")
    @ApiModelProperty(value = "商品品牌名称")
    private String brandName;
    
    
    @Size(max= 255,message="编码长度不能超过255")
    @Length(max= 255,message="编码长度不能超过255")
    @ApiModelProperty(value = "商品分类名称")
    private String productCategoryName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
