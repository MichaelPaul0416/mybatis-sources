package com.wq.mybatis.source.demo.plugins;

/**
 * @Author: wangqiang20995
 * @Date:2018/6/27
 * @Description:
 * @Resource:
 */
public class DefaultPager {
    private int pageSize;
    private int currentPage;
    private int totalRecords;

    @Override
    public String toString() {
        return "DefaultPager{" +
                "pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", totalRecords=" + totalRecords +
                '}';
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }
}
