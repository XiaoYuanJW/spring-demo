package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
* 品牌表实体类 : pms_brand
* Created by YuanJW on 2022-09-23 09:33:57
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value ="pms_brand")
@ApiModel(value = "PmsBrand", description = "品牌表")
public class PmsBrand implements Serializable {
    @NotNull(message="[商品品牌id]不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "商品品牌id")
    private Long id;

    @Size(max= 64,message="编码长度不能超过64")
    @Length(max= 64,message="编码长度不能超过64")
    @ApiModelProperty(value = "商品品牌名称")
    private String name;

    @Size(max= 8,message="编码长度不能超过8")
    @Length(max= 8,message="编码长度不能超过8")
    @ApiModelProperty(value = "商品品牌首字母")
    private String firstLetter;
    
    @ApiModelProperty(value = "商品品牌排序")
    private Integer sort;
    
    @ApiModelProperty(value = "是否为品牌制造商：0->不是；1->是")
    private Integer factoryStatus;
    
    @ApiModelProperty(value = "是否显示")
    private Integer showStatus;
    
    @ApiModelProperty(value = "产品数量")
    private Integer productCount;
    
    @ApiModelProperty(value = "产品评论数量")
    private Integer productCommentCount;

    @Size(max= 255,message="编码长度不能超过255")
    @Length(max= 255,message="编码长度不能超过255")
    @ApiModelProperty(value = "品牌logo")
    private String logo;

    @Size(max= 255,message="编码长度不能超过255")
    @Length(max= 255,message="编码长度不能超过255")
    @ApiModelProperty(value = "专区大图")
    private String bigPic;

    @Size(max= -1,message="编码长度不能超过-1")
    @Length(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty(value = "品牌故事")
    private String brandStory;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
