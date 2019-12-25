package pub.avalon.sqlhelper.spring.beans;

import pub.avalonframework.core.beans.Limit;

/**
 * @author 白超
 * @version 1.0
 * @since 2018/7/11
 */
public abstract class AbstractPageResult {

    protected Limit limit;

    public Limit getLimit() {
        return limit;
    }

    public void setLimit(Limit limit) {
        this.limit = limit;
    }
}