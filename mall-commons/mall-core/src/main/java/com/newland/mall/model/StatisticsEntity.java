package com.newland.mall.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Data
public class StatisticsEntity<T> implements Serializable {

    private List<T> list = new ArrayList<T>();
    private long total;

    public StatisticsEntity() {
    }

    public StatisticsEntity(List<T> list, long total) {
        this.total = total;
        this.list=list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotal() {
        return total;
    }
}