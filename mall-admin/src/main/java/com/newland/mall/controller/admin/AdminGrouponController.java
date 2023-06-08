package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.GrouponRules;
import com.newland.mall.model.RestResponse;
import com.newland.mall.model.vo.GrouponListItemVO;
import com.newland.mall.service.GrouponRulesService;
import com.newland.mall.service.GrouponService;
import com.newland.mall.validator.Sort;
import com.newland.mybatis.page.PageEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author leell
 */
@Tag(name = "团购管理")
@RestController
@RequestMapping("/admin/groupon")
@Validated
public class AdminGrouponController {
    @Autowired
    private GrouponRulesService rulesService;
    @Autowired
    private GrouponService grouponService;

    @Operation(summary = "详情")
    @GetMapping("/listRecord")
    public RestResponse<PageInfo<GrouponListItemVO> > listRecord(Long grouponRuleId,
                                   @RequestParam(defaultValue = "1") Integer pageNo,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   @Sort @RequestParam(defaultValue = "create_time") String order,
                                   @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity=PageEntity.page(pageNo,pageSize);
        pageEntity.setOrder(order);
        pageEntity.setDesc(desc);
        PageInfo<GrouponListItemVO> pageInfo = grouponService.listGrouponList(grouponRuleId, pageEntity);
        return RestResponse.ok(pageInfo);
    }

    @Operation(summary = "团购规则查询")
    @GetMapping
    public RestResponse<PageInfo<GrouponRules>> list(Long goodsId,
                       @RequestParam(defaultValue = "1") Integer pageNo,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @Sort @RequestParam(defaultValue = "create_time") String order,
                       @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity=PageEntity.page(pageNo,pageSize);
        pageEntity.setOrder(order);
        pageEntity.setDesc(desc);
        PageInfo<GrouponRules> pageInfo = rulesService.listGrouponRules(goodsId, pageEntity);
        return RestResponse.ok(pageInfo);
    }



    @Operation(summary = "编辑")
    @PutMapping
    public RestResponse update(@RequestBody GrouponRules grouponRules) {
        rulesService.update(grouponRules);
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "添加")
    @PostMapping
    public RestResponse create(@RequestBody GrouponRules grouponRules) {
        rulesService.createRules(grouponRules);
        return RestResponse.ok(grouponRules);
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public RestResponse delete(@PathVariable Long id) {
        rulesService.delete(id);
        return RestResponse.success("操作成功");
    }
}
