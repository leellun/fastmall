package com.newland.mall.mapper;

import com.newland.mall.entity.Groupon;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 团购活动表 Mapper 接口
 *
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface GrouponMapper extends BaseMapper<Groupon> {
    /**
     * 获取团购活动
     *
     * @param grouponId 如果是开团用户，则groupon_id是0；如果是参团用户，则groupon_id是团购活动ID
     * @param status
     * @return
     */
    List<Groupon> listSubGroupon(@Param("grouponId") Long grouponId, @Param("status") Integer status);

    /**
     * 团购活动列表
     *
     * @param rulesId
     * @return
     */

    List<Groupon> listByRulesId(@Param("rulesId") Long rulesId);

    /**
     * 获取相关用户
     *
     * @param userId   用户id
     * @param showType 0：开团 非0：参团
     * @param status   状态
     * @return
     */
    List<Groupon> listRelated(@Param("userId") Long userId, @Param("showType") Integer showType, @Param("status") Integer status);

    /**
     * 获取指定参团和状态下的数量
     *
     * @param grouponId
     * @param status
     * @return
     */
    Long countByGroupIdAndStatus(@Param("grouponId") Long grouponId, @Param("status") Integer status);

    /**
     * 获取指定参团和状态下的数量
     *
     * @param grouponId 如果是开团用户，则groupon_id是0；如果是参团用户，则groupon_id是团购活动ID
     * @param status
     * @return
     */
    Long countByUserIdAndGroupIdAndStatus(@Param("userId") Long userId, @Param("grouponId") Long grouponId, @Param("status") Integer status);

    /**
     * 根据订单id获取团购西悉尼
     *
     * @param orderId 订单id
     * @return
     */
    Groupon getByOrderId(@Param("orderId") Long orderId);

    /**
     * 获取某个团购活动参与的记录
     *
     * @param grouponId
     * @return
     */
    List<Groupon> listJoinRecord(@Param("grouponId") Long grouponId);
}