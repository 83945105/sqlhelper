package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.beans.TableColumn;

import java.util.Set;

/**
 * @author baichao
 */
public interface TableHelper<T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
        TJ extends JoinHelper<TJ>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> {

    /**
     * get table name
     *
     * @return table name
     */
    String getTableName();

    /**
     * get table alias
     *
     * @return table alias
     */
    String getTableAlias();

    /**
     * get primary key name
     *
     * @return primary key name
     */
    String getPrimaryKeyName();

    /**
     * get primary key alias
     *
     * @return primary key alias
     */
    String getPrimaryKeyAlias();

    /**
     * get table columns
     *
     * @return set {@link TableColumn}
     */
    Set<TableColumn> getTableColumns();

    /**
     * get extends {@link Helper} single object
     *
     * @return extends {@link Helper} single object
     */
    T getSingleHelper();

    /**
     * create new extends {@link JoinHelper} class object
     *
     * @param tableAlias table alias
     * @return extends {@link JoinHelper} class object
     */
    TJ newJoinHelper(String tableAlias);

    /**
     * create new extends {@link ColumnHelper} class object
     *
     * @param tableAlias table alias
     * @return extends {@link ColumnHelper} class object
     */
    TC newColumnHelper(String tableAlias);

    /**
     * create new extends {@link WhereHelper} class object
     *
     * @param tableAlias table alias
     * @return extends {@link WhereHelper} class object
     */
    TW newWhereHelper(String tableAlias);

    /**
     * create new extends {@link GroupHelper} class object
     *
     * @param tableAlias table alias
     * @return extends {@link GroupHelper} class object
     */
    TG newGroupHelper(String tableAlias);

    /**
     * create new extends {@link HavingHelper} class object
     *
     * @param tableAlias table alias
     * @return extends {@link HavingHelper} class object
     */
    TH newHavingHelper(String tableAlias);

    /**
     * create new extends {@link SortHelper} class object
     *
     * @param tableAlias table alias
     * @return extends {@link SortHelper} class object
     */
    TS newSortHelper(String tableAlias);
}