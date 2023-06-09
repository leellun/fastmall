package com.newland.mybatis.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Author: leell
 * Date: 2023/1/10 21:57:16
 */
public class PageWrapper {
    /**
     * 页码
     */
    private int pageNo;
    /**
     * 页大小
     */
    private int pageSize;
    /**
     * 排序
     */
    private String orderBy;

    public static <T> PageInfo<T> page(PageEntity pageEntity, PageSelectCallback<T> callback) {
        setPageHelper(pageEntity);
        PageInfo<T> pageInfo = new PageInfo<>(callback.get());
        PageHelper.clearPage();
        return pageInfo;
    }

    public static <T> PageInfo<T> page(int pageNo, int pageSize, PageSelectCallback<T> callback) {
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<T> pageInfo = new PageInfo<>(callback.get());
        PageHelper.clearPage();
        return pageInfo;
    }

    public static <T> List<T> list(PageEntity pageEntity, PageSelectCallback<T> callback) {
        setPageHelper(pageEntity);
        List<T> list = callback.get();
        PageHelper.clearPage();
        return list;
    }

    private static void setPageHelper(PageEntity pageEntity) {
        PageHelper.startPage(pageEntity.getPageNo(), pageEntity.getPageSize());
        if (StringUtils.hasText(pageEntity.getOrder())) {
            if (pageEntity.getDesc() != null && pageEntity.getDesc()) {
                PageHelper.orderBy(pageEntity.getOrder() + " desc ");
            } else {
                PageHelper.orderBy(pageEntity.getOrder() + " asc ");
            }
        }
    }

    public static <T> List<T> list(int pageNo, int pageSize, PageSelectCallback<T> callback) {
        PageHelper.startPage(pageNo, pageSize);
        List<T> list = callback.get();
        PageHelper.clearPage();
        return list;
    }

    /**
     * 排序查找
     *
     * @param order
     * @param desc
     * @param callback
     * @param <T>
     * @return
     */
    public static <T> List<T> order(String order, Boolean desc, PageSelectCallback<T> callback) {
        if (desc) {
            PageHelper.orderBy(order + " desc ");
        } else {
            PageHelper.orderBy(order + " asc ");
        }
        PageInfo<T> pageInfo = new PageInfo<>(callback.get());
        PageHelper.clearPage();
        return pageInfo.getList();
    }

    public static <T> PageInfo<T> newPageInfo(PageInfo<?> pageInfo, List<T> list) {
        PageInfo<T> result = new PageInfo<>();
        result.setPageNum(pageInfo.getPageNum());
        result.setPages(pageInfo.getPages());
        result.setEndRow(pageInfo.getEndRow());
        result.setPageSize(pageInfo.getPageSize());
        result.setSize(pageInfo.getSize());
        result.setList(list);
        return result;
    }

    public PageWrapper page(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        return this;
    }

    public PageWrapper order(String order) {
        this.orderBy = order;
        return this;
    }

    public <T> PageInfo<T> list(PageSelectCallback<T> callback) {
        PageHelper.startPage(pageNo, pageSize);
        if (StringUtils.hasText(orderBy)) {
            PageHelper.orderBy(orderBy);
        }
        PageInfo<T> pageInfo = new PageInfo<>(callback.get());
        PageHelper.clearPage();
        return pageInfo;
    }
}
