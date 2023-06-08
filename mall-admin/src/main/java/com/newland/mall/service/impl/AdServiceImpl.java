package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Ad;
import com.newland.mall.mapper.AdMapper;
import com.newland.mall.model.dto.AdDTO;
import com.newland.mall.service.AdService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements AdService {
    @Override
    public PageInfo<Ad> getPageAd(String name, String content, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listPageAd(name, content));
    }

    @Override
    public Ad getAd(Long id) {
        Ad dbAd = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(dbAd, "广告不存在");
        return dbAd;
    }

    @Override
    public void add(AdDTO adDTO) {
        Ad ad = new Ad();
        BeanUtils.copyProperties(adDTO, ad);
        baseMapper.insertSelective(ad);
    }

    @Override
    public void updateAd(AdDTO adDTO) {
        Ad ad = new Ad();
        BeanUtils.copyProperties(adDTO, ad);
        Ad dbAd = baseMapper.selectByPrimaryKey(ad.getId());
        AssertUtil.notNull(dbAd, "广告不存在");
        baseMapper.updateByPrimaryKeySelective(ad);
    }

    @Override
    public void delete(Long id) {
        Ad dbAd = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(dbAd, "广告不存在");
        baseMapper.deleteLogicalByPrimaryKey(id);
    }

    @Override
    public List<Ad> queryIndex() {
        return baseMapper.listIndex();
    }
}