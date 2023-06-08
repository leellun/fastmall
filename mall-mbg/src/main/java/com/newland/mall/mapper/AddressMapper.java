package com.newland.mall.mapper;

import com.newland.mall.entity.Ad;
import com.newland.mall.entity.Address;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 收货地址表 Mapper 接口
 *
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface AddressMapper extends BaseMapper<Address> {
    /**
     * 查询地址
     *
     * @param userId 用户id
     * @param name   地址
     * @return
     */
    List<Address> listByUserIdAndName(@Param("userId") Long userId, @Param("name") String name);

    /**
     * 重置默认地址
     *
     * @param userId
     * @return
     */
    Integer resetDefault(@Param("userId") Long userId);

    /**
     * 获取默认地址
     *
     * @param userId
     * @return
     */
    Address getDefautlByUserId(@Param("userId") Long userId);

    /**
     * 通过地址id和用户id获取地址
     * @param addressId 地址id
     * @param userId 用户id
     * @return
     */

    Address getByIdAndUserId(@Param("addressId") Long addressId, @Param("userId") Long userId);
}