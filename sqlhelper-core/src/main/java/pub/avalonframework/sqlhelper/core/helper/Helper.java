package pub.avalonframework.sqlhelper.core.helper;

/**
 * @author baichao
 */
public abstract class Helper {

    protected String tableAlias;

    public Helper(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    abstract public TableHelper getDefaultTableHelper();

    public String getTableAlias() {
        return tableAlias;
    }
}