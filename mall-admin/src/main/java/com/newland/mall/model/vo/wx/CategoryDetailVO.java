package com.newland.mall.model.vo.wx;

import com.newland.mall.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 分类详情
 *
 * @author liulun
 * @date 2023/4/4 11:12
 */
@Schema(name = "分类详情")
@Data
public class CategoryDetailVO {
    @Schema(name = "一级分类")
    private List<Category> categoryList;
    @Schema(name = "二级分类")
    private List<Category> currentSubCategory;
    @Schema(name = "当前分类")
    private Category currentCategory;
}
