package com.newland.mall.mapper;

import com.newland.mall.entity.Keyword;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 关键字表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface KeywordMapper extends BaseMapper<Keyword> {
    /**
     * 通过关键词和url获取关键
     * @param keyword
     * @param url
     * @return
     */
    List<Keyword> listByKeywordAndUrl(@Param("keyword") String keyword,@Param("url") String url);

    /**
     * 获取默认关键词
     * @return
     */
    Keyword getDefault();

    /**
     * 火热关键词
     * @return
     */
    List<Keyword> listHots();
}