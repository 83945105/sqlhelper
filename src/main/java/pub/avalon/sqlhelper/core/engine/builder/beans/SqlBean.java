package pub.avalon.sqlhelper.core.engine.builder.beans;

/**
 * @author 白超
 * @date 2019/7/17
 */
public abstract class SqlBean {

    protected String tableAlias;

    public SqlBean(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }
}
