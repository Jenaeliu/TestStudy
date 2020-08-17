package com.example.entity;

import java.util.List;

public class page<T> {
    private int pageSize;
    private int total;
    private int pageIndex;
    private int subPage;

   // private List<T> rows;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getSubPage() {
        return subPage;
    }

    public void setSubPage(int subPage) {
        this.subPage = subPage;
    }
}
