package com.newland.mall.model.vo.wx;

import com.newland.mall.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 分类all
 * @author liulun
 * @date 2023/4/4 11:18
 */
@Data
public class CategoryAllVO {
    @Schema(name = "一级分类")
    private List<Category> categoryList;
    @Schema(name = "二级分类")
    private List<Category> currentSubCategory;
    @Schema(name = "当前分类")
    private Category currentCategory;
    @Schema(name = "全部分类")
    private Map<Long, List<Category>> allList;
}
