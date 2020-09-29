package Ricard.code.domain;


import java.util.List;

public class PageBean<T> {
    private int totalCount; // 总的记录数
    private int totalPage;  // 总的页数
    private int currentPage;    // 当前页码
    private int pageCount;  // 每一页的记录数
    private List<T> stus; // 每页的记录信息

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getStus() {
        return stus;
    }

    public void setStus(List<T> stus) {
        this.stus = stus;
    }
}
