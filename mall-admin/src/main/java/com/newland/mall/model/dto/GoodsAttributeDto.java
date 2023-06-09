package com.newland.mall.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.validator.IntOptions;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


/**
 * 商品属性参数
 * @author leell
 */
@Schema(description = "属性")
@Data
public class GoodsAttributeDto {
    @Schema(description ="属性分类ID")
    @NotEmpty(message = "属性分类不能为空")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long goodsAttributeCategoryId;

    @Schema(description ="属性名称")
    @NotEmpty(message = "属性名称不能为空")
    private String name;

    @Schema(description ="属性选择类型：0->唯一；1->单选；2->多选")
    @IntOptions(options = {0,1,2},message = "参数不正确")
    private Integer selectType;

    @Schema(description ="属性录入方式：0->手工录入；1->从列表中选取")
    @IntOptions(options = {0,1},message = "参数不正确")
    private Integer inputType;

    @Schema(description ="可选值列表，以逗号隔开")
    private String inputList;

    @Schema(description ="排序")
    private Integer attrSort;

    @Schema(description ="分类筛选样式：0->普通；1->多选")
    @IntOptions(options = {0,1},message = "参数不正确")
    private Integer filterType;

    @Schema(description ="检索类型；0->不需要进行检索；1->关键字检索；2->范围检索")
    @IntOptions(options = {0,1,2},message = "参数不正确")
    private Integer searchType;

    @Schema(description ="相同属性产品是否关联；0->不关联；1->关联")
    @IntOptions(options = {0,1},message = "参数不正确")
    private Integer relatedStatus;

    @Schema(description ="是否支持手动新增；0->不支持；1->支持")
    @IntOptions(options = {0,1},message = "参数不正确")
    private Integer handAddStatus;

    @Schema(description ="属性的类型；0->规格；1->参数")
    @IntOptions(options = {0,1},message = "参数不正确")
    private Integer type;
}
