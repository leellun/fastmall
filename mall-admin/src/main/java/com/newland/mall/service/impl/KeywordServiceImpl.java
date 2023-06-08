package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Issue;
import com.newland.mall.entity.Keyword;
import com.newland.mall.mapper.KeywordMapper;
import com.newland.mall.service.KeywordService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.List;

/**
 * 关键字表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class KeywordServiceImpl extends ServiceImpl<KeywordMapper, Keyword> implements KeywordService {
    @Override
    public PageInfo<Keyword> listKeywordPage(String keyword, String url, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity,()->baseMapper.listByKeywordAndUrl(keyword,url));
    }

    @Override
    public Keyword get(Long id) {
        Keyword keyword =baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(keyword,"数据不存在");
        return keyword;
    }

    @Override
    public void add(Keyword keyword) {
        validate(keyword);
        baseMapper.insert(keyword);
    }

    @Override
    public void update(Keyword keyword) {
        validate(keyword);
        Keyword dbKeyword =baseMapper.selectByPrimaryKey(keyword.getId());
        AssertUtil.notNull(dbKeyword,"数据不存在");
        baseMapper.updateByPrimaryKeySelective(keyword);
    }

    @Override
    public void delete(Long id) {
        Keyword t = new Keyword();
        t.setId(id);
        t.setDeleted(1);
        AssertUtil.isTrue(baseMapper.updateByPrimaryKeySelective(t) > 0, "删除失败");
    }

    @Override
    public Keyword getDefault() {
        return baseMapper.getDefault();
    }

    @Override
    public List<Keyword> listHots() {
        return baseMapper.listHots();
    }

    private void validate(Keyword keywords) {
        AssertUtil.isNotTrue(StringUtils.isEmpty(keywords.getKeyword()), "关键词不能为空");
    }
}