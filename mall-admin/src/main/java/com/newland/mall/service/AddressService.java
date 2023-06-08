package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Address;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 收货地址表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface AddressService extends IService<Address> {
    /**
     * 获取分页地址
     * @param userId
     * @param name
     * @param pageEntity
     * @return
     */
    PageInfo<Address> getAddressPage(Long userId, String name, PageEntity pageEntity);

    /**
     * 用户地址列表
     * @param userId
     * @return
     */
    List<Address> listAddress(Long userId);

    /**
     * 地址
     * @param userId
     * @param id
     * @return
     */
    Address getAddress(Long userId, Long id);

    /**
     * 保存地址
     * @param userId
     * @param address
     */
    void saveAddress(Long userId, Address address);

    /**
     * 删除地址
     * @param userId
     * @param id
     */
    void removeAddress(Long userId, Long id);

    /**
     * 获取默认地址
     * @param userId
     * @return
     */
    Address getDefaultAddress(Long userId);
}