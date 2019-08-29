package pub.avalon.sqlhelper.core.data;

/**
 * @author baichao
 */
public final class LimitDatum {

    private Long total;

    private Long currentPage;

    private Long pageSize;

    public LimitDatum() {
    }

    public LimitDatum(Long currentPage, Long pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public LimitDatum(Long total, Long currentPage, Long pageSize) {
        this.total = total;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public LimitDatum setTotal(Long total) {
        this.total = total;
        return this;
    }

    public LimitDatum setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public LimitDatum setPageSize(Long pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}