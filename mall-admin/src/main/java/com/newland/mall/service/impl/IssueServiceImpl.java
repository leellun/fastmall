package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.GrouponRules;
import com.newland.mall.entity.Issue;
import com.newland.mall.mapper.IssueMapper;
import com.newland.mall.service.IssueService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 常见问题表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class IssueServiceImpl extends ServiceImpl<IssueMapper, Issue> implements IssueService {
    @Override
    public PageInfo<Issue> listIssuePage(String question, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listByQuestion(question));
    }

    @Override
    public void delete(Long id) {
        Issue t = new Issue();
        t.setId(id);
        t.setDeleted(1);
        AssertUtil.isTrue(baseMapper.updateByPrimaryKeySelective(t) > 0, "删除失败");
    }

    @Override
    public Issue get(Long id) {
        Issue dbIssue = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(dbIssue, "数据不存在");
        return dbIssue;
    }

    @Override
    public void add(Issue issue) {
        validate(issue);
        baseMapper.insert(issue);
    }

    @Override
    public void update(Issue issue) {
        validate(issue);
        Issue dbIssue = baseMapper.selectByPrimaryKey(issue.getId());
        AssertUtil.notNull(dbIssue, "数据不存在");
        baseMapper.updateByPrimaryKeySelective(issue);
    }

    /**
     * 校验
     *
     * @param issue
     */
    private void validate(Issue issue) {
        AssertUtil.isNotTrue(StringUtils.isEmpty(issue.getQuestion()), "问题不能为空");
        AssertUtil.isNotTrue(StringUtils.isEmpty(issue.getAnswer()), "答案不能为空");
    }
}