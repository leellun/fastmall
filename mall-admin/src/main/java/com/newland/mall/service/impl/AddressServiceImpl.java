package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Address;
import com.newland.mall.mapper.AddressMapper;
import com.newland.mall.service.AddressService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
    @Override
    public PageInfo<Address> getAddressPage(Long userId, String name, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listByUserIdAndName(userId, name));
    }

    @Override
    public List<Address> listAddress(Long userId) {
        return baseMapper.listByUserIdAndName(userId, null);
    }

    @Override
    public Address getAddress(Long userId, Long id) {
        Address address = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(address, "地址不存在");
        AssertUtil.isTrue(address.getUserId().equals(userId), "你无权限查看");
        return address;
    }

    @Override
    public void saveAddress(Long userId, Address address) {
        if (address.getId() == null || address.getId().equals(0)) {
            if (address.getIsDefault() == 1) {
                // 重置其他收货地址的默认选项
                baseMapper.resetDefault(userId);
            }

            address.setId(null);
            address.setUserId(userId);
            baseMapper.insertSelective(address);
        } else {
            Address dbAddress = baseMapper.selectByPrimaryKey(address.getId());
            if (address.getIsDefault() == 1) {
                // 重置其他收货地址的默认选项
                baseMapper.resetDefault(userId);
            }
            address.setUserId(userId);
            if (dbAddress == null) {
                address.setId(null);
                baseMapper.insertSelective(address);
            } else {
                baseMapper.updateByPrimaryKeySelective(address);
            }

        }
    }

    @Override
    public void removeAddress(Long userId, Long id) {
        Address address = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(address, "地址不存在");
        AssertUtil.isTrue(address.getUserId().equals(userId), "你无权限查看");
        baseMapper.deleteLogicalByPrimaryKey(id);
    }

    @Override
    public Address getDefaultAddress(Long userId) {
        return baseMapper.getDefautlByUserId(userId);
    }
}