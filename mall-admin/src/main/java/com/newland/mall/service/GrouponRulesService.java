package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.GrouponRules;
import com.newland.mall.model.vo.wx.GrouponRuleVO;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 团购规则表 服务类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface GrouponRulesService extends IService<GrouponRules> {
    /**
     * 团购规则列表
     *
     * @param goodsId    商品id
     * @param pageEntity 分页参数
     * @return
     */
    PageInfo<GrouponRules> listGrouponRules(Long goodsId, PageEntity pageEntity);

    /**
     * 添加规则
     *
     * @param grouponRules 规则列表
     */
    void createRules(GrouponRules grouponRules);

    /**
     * 更新规则
     *
     * @param grouponRules
     */
    void update(GrouponRules grouponRules);

    /**
     * 删除规则
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 获取指定状态的团购规则
     *
     * @param status
     * @return
     */
    List<GrouponRules> listByStatus(Integer status);

    /**
     * 退购规则列表
     * @param pageEntity
     * @return
     */
    PageInfo<GrouponRuleVO> listGrouponRuleVO(PageEntity pageEntity);
}