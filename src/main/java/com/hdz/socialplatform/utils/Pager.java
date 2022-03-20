package com.hdz.socialplatform.utils;

import java.util.List;

/**
 * @author hdz
 * @description 分页工具
 * @create 2022年03月19日 19:04
 */
public class Pager<T> {

    private int page;//分页起始页

    private int size;//每页记录数

    private List<T> list;//返回的记录集合

    private long total;//总记录条数

    public Pager() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
