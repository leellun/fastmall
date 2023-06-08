package com.newland.mall.mapper;

import com.newland.mall.entity.Collect;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 收藏表 Mapper 接口
 *
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface CollectMapper extends BaseMapper<Collect> {
    /**
     * 获取收藏列表
     *
     * @param userId  用户
     * @param valueId 值
     * @return
     */
    List<Collect> listByUserIdAndValueId(@Param("userId") Long userId, @Param("valueId") Long valueId);

    /**
     * 通过类型和valueid查询当前用户收藏
     *
     * @param userId
     * @param type
     * @param valueId
     * @return
     */
    Collect getByTypeAndValue(@Param("userId") Long userId, @Param("type") Integer type, @Param("valueId") Long valueId);

    /**
     * 统计用户收藏
     * @param userId 用户id
     * @param type 收藏类型
     * @param valueId 对应id
     * @return
     */
    Long countByUserIdAndTypeAndValueId(@Param("userId") Long userId, @Param("type") Integer type, @Param("valueId") Long valueId);
}