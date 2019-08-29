package pub.avalon.sqlhelper.spring.beans;

import pub.avalon.beans.LimitSql;

/**
 * @author 白超
 * @version 1.0
 * @since 2018/7/11
 */
public abstract class AbstractPageResult {

    protected LimitSql limit;

    public LimitSql getLimit() {
        return limit;
    }

    public void setLimit(LimitSql limit) {
        this.limit = limit;
    }
}
