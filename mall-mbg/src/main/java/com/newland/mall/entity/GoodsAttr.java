package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品属性参数表
 * mall_goods_attr
 * @author leell
 * @date 2023-06-08 12:54:03
 */
@Data
@Schema(name ="商品属性参数表")
public class GoodsAttr extends BaseEntity implements Serializable {
    /**
     * 商品属性参数id
     */
    @Schema(name ="商品属性参数id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 属性名
     */
    @Schema(name ="属性名")
    private String name;

    /**
     * 属性选择类型：0->唯一；1->单选；2->多选
     */
    @Schema(name ="属性选择类型：0->唯一；1->单选；2->多选")
    private Integer selectType;

    /**
     * 属性录入方式：0->手工录入；1->从列表中选取
     */
    @Schema(name ="属性录入方式：0->手工录入；1->从列表中选取")
    private Integer inputType;

    /**
     * 可选值列表，以逗号隔开
     */
    @Schema(name ="可选值列表，以逗号隔开")
    private String inputList;

    /**
     * 排序字段：最高的可以单独上传图片
     */
    @Schema(name ="排序字段：最高的可以单独上传图片")
    private Integer attrSort;

    /**
     * 分类筛选样式：1->普通；1->多选
     */
    @Schema(name ="分类筛选样式：1->普通；1->多选")
    private Integer filterType;

    /**
     * 检索类型；0->不需要进行检索；1->关键字检索；2->范围检索
     */
    @Schema(name ="检索类型；0->不需要进行检索；1->关键字检索；2->范围检索")
    private Integer searchType;

    /**
     * 相同属性产品是否关联；0->不关联；1->关联
     */
    @Schema(name ="相同属性产品是否关联；0->不关联；1->关联")
    private Integer relatedStatus;

    /**
     * 是否支持手动新增；0->不支持；1->支持
     */
    @Schema(name ="是否支持手动新增；0->不支持；1->支持")
    private Integer handAddStatus;

    /**
     * 属性的类型；0->规格；1->参数
     */
    @Schema(name ="属性的类型；0->规格；1->参数")
    private Integer type;

    /**
     * 是否有图片
     */
    @Schema(name ="是否有图片")
    private Integer hasPic;

    private static final long serialVersionUID = 1L;
}