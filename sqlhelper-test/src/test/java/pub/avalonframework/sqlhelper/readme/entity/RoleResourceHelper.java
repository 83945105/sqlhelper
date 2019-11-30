package pub.avalonframework.sqlhelper.readme.entity;

import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.beans.TableColumn;
import pub.avalonframework.sqlhelper.core.builder.HavingSqlPartDatumBuilder;
import pub.avalonframework.sqlhelper.core.builder.OnSqlPartDatumBuilder;
import pub.avalonframework.sqlhelper.core.builder.SortSqlPartDatumBuilder;
import pub.avalonframework.sqlhelper.core.builder.WhereSqlPartDatumBuilder;
import pub.avalonframework.sqlhelper.core.helper.*;

import java.util.LinkedHashSet;
import java.util.Set;

@SuppressWarnings("all")
public class RoleResourceHelper implements TableHelper<RoleResourceHelper, RoleResourceHelper.On, RoleResourceHelper.Column, RoleResourceHelper.Where, RoleResourceHelper.Group, RoleResourceHelper.Having, RoleResourceHelper.Sort> {

    public final static RoleResourceHelper DEFAULT_INSTANCE = new RoleResourceHelper();

        /**
     * table name
     */
        public final static String TABLE_NAME = "role_resource";
        /**
     * table alias
     */
    public final static String TABLE_ALIAS = "RoleResource";
                /**
     * primary key name
     */
        public final static String PRIMARY_KEY_NAME = "id";
        /**
     * primary key alias
     */
        public final static String PRIMARY_KEY_ALIAS = "id";
    
            /**
     * 
     */
        public final static String ID = "id";
        /**
     *  alias
     */
        public final static String ID_ALIAS = "id";
            /**
     * 
     */
        public final static String ROLE_ID = "role_id";
        /**
     *  alias
     */
        public final static String ROLE_ID_ALIAS = "roleId";
            /**
     * 
     */
        public final static String ROLE_NAME = "role_name";
        /**
     *  alias
     */
        public final static String ROLE_NAME_ALIAS = "roleName";
            /**
     * 
     */
        public final static String RESOURCE_ID = "resource_id";
        /**
     *  alias
     */
        public final static String RESOURCE_ID_ALIAS = "resourceId";
            /**
     * 
     */
        public final static String RESOURCE_NAME = "resource_name";
        /**
     *  alias
     */
        public final static String RESOURCE_NAME_ALIAS = "resourceName";
            /**
     * 
     */
        public final static String SORT_INDEX = "sort_index";
        /**
     *  alias
     */
        public final static String SORT_INDEX_ALIAS = "sortIndex";
    
        /**
     * table columns
     */
        public final static Set<TableColumn> TABLE_COLUMNS;

    static {
        TABLE_COLUMNS = new LinkedHashSet<>(6);
        TableColumn primaryKeyTableColumn = new TableColumn(PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, TABLE_NAME, TABLE_ALIAS, null, TABLE_COLUMNS);
        primaryKeyTableColumn.setPrimaryKeyColumnInfo(primaryKeyTableColumn);
                TABLE_COLUMNS.add(new TableColumn(ID, ID_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
                TABLE_COLUMNS.add(new TableColumn(ROLE_ID, ROLE_ID_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
                TABLE_COLUMNS.add(new TableColumn(ROLE_NAME, ROLE_NAME_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
                TABLE_COLUMNS.add(new TableColumn(RESOURCE_ID, RESOURCE_ID_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
                TABLE_COLUMNS.add(new TableColumn(RESOURCE_NAME, RESOURCE_NAME_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
                TABLE_COLUMNS.add(new TableColumn(SORT_INDEX, SORT_INDEX_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
            }

    public static RoleResourceHelper newInstance() {
        return new RoleResourceHelper();
    }

    public static On on() {
        return new On(TABLE_ALIAS);
    }

    public static On on(String tableAlias) {
        return new On(tableAlias);
    }

    public static Column column() {
        return new Column(TABLE_ALIAS);
    }

    public static Column column(String tableAlias) {
        return new Column(tableAlias);
    }

    public static Where where() {
        return new Where(TABLE_ALIAS);
    }

    public static Where where(String tableAlias) {
        return new Where(tableAlias);
    }

    public static Group groupBy() {
        return new Group(TABLE_ALIAS);
    }

    public static Group groupBy(String tableAlias) {
        return new Group(tableAlias);
    }

    public static Having having() {
        return new Having(TABLE_ALIAS);
    }

    public static Having having(String tableAlias) {
        return new Having(tableAlias);
    }

    public static Sort orderBy() {
        return new Sort(TABLE_ALIAS);
    }

    public static Sort orderBy(String tableAlias) {
        return new Sort(tableAlias);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getTableAlias() {
        return TABLE_ALIAS;
    }

    @Override
    public String getPrimaryKeyName() {
        return PRIMARY_KEY_NAME;
    }

    @Override
    public String getPrimaryKeyAlias() {
        return PRIMARY_KEY_ALIAS;
    }

    @Override
    public Set<TableColumn> getTableColumns() {
        return TABLE_COLUMNS;
    }

    @Override
    public RoleResourceHelper getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    @Override
    public On newOnHelper(String tableAlias) {
        return on(tableAlias);
    }

    @Override
    public Column newColumnHelper(String tableAlias) {
        return column(tableAlias);
    }

    @Override
    public Where newWhereHelper(String tableAlias) {
        return where(tableAlias);
    }

    @Override
    public Group newGroupHelper(String tableAlias) {
        return groupBy(tableAlias);
    }

    @Override
    public Having newHavingHelper(String tableAlias) {
        return having(tableAlias);
    }

    @Override
    public Sort newSortHelper(String tableAlias) {
        return orderBy(tableAlias);
    }

    public final static class On extends OnHelper<On> {

        public On() {
            super(TABLE_ALIAS);
        }

        public On(String tableAlias) {
            super(tableAlias);
        }

        @Override
        public RoleResourceHelper getDefaultTableHelper() {
            return DEFAULT_INSTANCE.getDefaultInstance();
        }

        public OnSqlPartDatumBuilder<On> sqlPart(String sqlPart) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, sqlPart);
        }

        public OnSqlPartDatumBuilder<On> primaryKey() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, PRIMARY_KEY_ALIAS);
        }

                public OnSqlPartDatumBuilder<On> id() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, ID_ALIAS);
        }
                public OnSqlPartDatumBuilder<On> roleId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, ROLE_ID_ALIAS);
        }
                public OnSqlPartDatumBuilder<On> roleName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, ROLE_NAME_ALIAS);
        }
                public OnSqlPartDatumBuilder<On> resourceId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_ID, RESOURCE_ID_ALIAS, RESOURCE_ID_ALIAS);
        }
                public OnSqlPartDatumBuilder<On> resourceName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_NAME, RESOURCE_NAME_ALIAS, RESOURCE_NAME_ALIAS);
        }
                public OnSqlPartDatumBuilder<On> sortIndex() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, SORT_INDEX_ALIAS);
        }
            }

    public final static class Column extends ColumnHelper<Column> {

        public Column() {
            super(TABLE_ALIAS);
        }

        public Column(String tableAlias) {
            super(tableAlias);
        }

        @Override
        public RoleResourceHelper getDefaultTableHelper() {
            return DEFAULT_INSTANCE.getDefaultInstance();
        }

        @Override
        public Set<TableColumn> getTableDefaultColumns() {
            return DEFAULT_INSTANCE.getTableColumns();
        }

        public Column sqlPart(String sqlPart) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, sqlPart).getHelper();
        }

        public Column primaryKey() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, PRIMARY_KEY_ALIAS).getHelper();
        }

        public Column primaryKey(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, PRIMARY_KEY_ALIAS, columnHandlers).getHelper();
        }

        public Column primaryKey(String alias) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, alias).getHelper();
        }

        public Column primaryKey(String alias, ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, alias, columnHandlers).getHelper();
        }

                public Column id() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, ID_ALIAS).getHelper();
        }

        public Column id(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, ID_ALIAS, columnHandlers).getHelper();
        }

        public Column id(String alias) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, alias).getHelper();
        }

        public Column id(String alias, ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, alias, columnHandlers).getHelper();
        }
                public Column roleId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, ROLE_ID_ALIAS).getHelper();
        }

        public Column roleId(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, ROLE_ID_ALIAS, columnHandlers).getHelper();
        }

        public Column roleId(String alias) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, alias).getHelper();
        }

        public Column roleId(String alias, ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, alias, columnHandlers).getHelper();
        }
                public Column roleName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, ROLE_NAME_ALIAS).getHelper();
        }

        public Column roleName(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, ROLE_NAME_ALIAS, columnHandlers).getHelper();
        }

        public Column roleName(String alias) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, alias).getHelper();
        }

        public Column roleName(String alias, ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, alias, columnHandlers).getHelper();
        }
                public Column resourceId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_ID, RESOURCE_ID_ALIAS, RESOURCE_ID_ALIAS).getHelper();
        }

        public Column resourceId(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_ID, RESOURCE_ID_ALIAS, RESOURCE_ID_ALIAS, columnHandlers).getHelper();
        }

        public Column resourceId(String alias) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_ID, RESOURCE_ID_ALIAS, alias).getHelper();
        }

        public Column resourceId(String alias, ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_ID, RESOURCE_ID_ALIAS, alias, columnHandlers).getHelper();
        }
                public Column resourceName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_NAME, RESOURCE_NAME_ALIAS, RESOURCE_NAME_ALIAS).getHelper();
        }

        public Column resourceName(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_NAME, RESOURCE_NAME_ALIAS, RESOURCE_NAME_ALIAS, columnHandlers).getHelper();
        }

        public Column resourceName(String alias) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_NAME, RESOURCE_NAME_ALIAS, alias).getHelper();
        }

        public Column resourceName(String alias, ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_NAME, RESOURCE_NAME_ALIAS, alias, columnHandlers).getHelper();
        }
                public Column sortIndex() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, SORT_INDEX_ALIAS).getHelper();
        }

        public Column sortIndex(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, SORT_INDEX_ALIAS, columnHandlers).getHelper();
        }

        public Column sortIndex(String alias) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, alias).getHelper();
        }

        public Column sortIndex(String alias, ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, alias, columnHandlers).getHelper();
        }
            }

    public final static class Where extends WhereHelper<Where> {

        public Where() {
            super(TABLE_ALIAS);
        }

        public Where(String tableAlias) {
            super(tableAlias);
        }

        @Override
        public RoleResourceHelper getDefaultTableHelper() {
            return DEFAULT_INSTANCE.getDefaultInstance();
        }

        public WhereSqlPartDatumBuilder<Where> sqlPart(String sqlPart) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, sqlPart);
        }

        public WhereSqlPartDatumBuilder<Where> primaryKey() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, PRIMARY_KEY_ALIAS);
        }

                public WhereSqlPartDatumBuilder<Where> id() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, ID_ALIAS);
        }
                public WhereSqlPartDatumBuilder<Where> roleId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, ROLE_ID_ALIAS);
        }
                public WhereSqlPartDatumBuilder<Where> roleName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, ROLE_NAME_ALIAS);
        }
                public WhereSqlPartDatumBuilder<Where> resourceId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_ID, RESOURCE_ID_ALIAS, RESOURCE_ID_ALIAS);
        }
                public WhereSqlPartDatumBuilder<Where> resourceName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_NAME, RESOURCE_NAME_ALIAS, RESOURCE_NAME_ALIAS);
        }
                public WhereSqlPartDatumBuilder<Where> sortIndex() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, SORT_INDEX_ALIAS);
        }
            }

    public final static class Group extends GroupHelper<Group> {

        public Group() {
            super(TABLE_ALIAS);
        }

        public Group(String tableAlias) {
            super(tableAlias);
        }

        @Override
        public RoleResourceHelper getDefaultTableHelper() {
            return DEFAULT_INSTANCE.getDefaultInstance();
        }

        public Group sqlPart(String sqlPart) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, sqlPart).getHelper();
        }

        public Group primaryKey() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, PRIMARY_KEY_ALIAS).getHelper();
        }

                public Group id() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, ID_ALIAS).getHelper();
        }
                public Group roleId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, ROLE_ID_ALIAS).getHelper();
        }
                public Group roleName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, ROLE_NAME_ALIAS).getHelper();
        }
                public Group resourceId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_ID, RESOURCE_ID_ALIAS, RESOURCE_ID_ALIAS).getHelper();
        }
                public Group resourceName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_NAME, RESOURCE_NAME_ALIAS, RESOURCE_NAME_ALIAS).getHelper();
        }
                public Group sortIndex() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, SORT_INDEX_ALIAS).getHelper();
        }
            }

    public final static class Having extends HavingHelper<Having> {

        public Having() {
            super(TABLE_ALIAS);
        }

        public Having(String tableAlias) {
            super(tableAlias);
        }

        @Override
        public RoleResourceHelper getDefaultTableHelper() {
            return DEFAULT_INSTANCE.getDefaultInstance();
        }

        public HavingSqlPartDatumBuilder<Having> sqlPart(String sqlPart) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, sqlPart);
        }

        public HavingSqlPartDatumBuilder<Having> primaryKey() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, PRIMARY_KEY_ALIAS);
        }

        public HavingSqlPartDatumBuilder<Having> primaryKey(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, PRIMARY_KEY_ALIAS, columnHandlers);
        }

                public HavingSqlPartDatumBuilder<Having> id() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, ID_ALIAS);
        }

        public HavingSqlPartDatumBuilder<Having> id(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, ID_ALIAS, columnHandlers);
        }
                public HavingSqlPartDatumBuilder<Having> roleId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, ROLE_ID_ALIAS);
        }

        public HavingSqlPartDatumBuilder<Having> roleId(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, ROLE_ID_ALIAS, columnHandlers);
        }
                public HavingSqlPartDatumBuilder<Having> roleName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, ROLE_NAME_ALIAS);
        }

        public HavingSqlPartDatumBuilder<Having> roleName(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, ROLE_NAME_ALIAS, columnHandlers);
        }
                public HavingSqlPartDatumBuilder<Having> resourceId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_ID, RESOURCE_ID_ALIAS, RESOURCE_ID_ALIAS);
        }

        public HavingSqlPartDatumBuilder<Having> resourceId(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_ID, RESOURCE_ID_ALIAS, RESOURCE_ID_ALIAS, columnHandlers);
        }
                public HavingSqlPartDatumBuilder<Having> resourceName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_NAME, RESOURCE_NAME_ALIAS, RESOURCE_NAME_ALIAS);
        }

        public HavingSqlPartDatumBuilder<Having> resourceName(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_NAME, RESOURCE_NAME_ALIAS, RESOURCE_NAME_ALIAS, columnHandlers);
        }
                public HavingSqlPartDatumBuilder<Having> sortIndex() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, SORT_INDEX_ALIAS);
        }

        public HavingSqlPartDatumBuilder<Having> sortIndex(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, SORT_INDEX_ALIAS, columnHandlers);
        }
            }

    public final static class Sort extends SortHelper<Sort> {

        public Sort() {
            super(TABLE_ALIAS);
        }

        public Sort(String tableAlias) {
            super(tableAlias);
        }

        @Override
        public RoleResourceHelper getDefaultTableHelper() {
            return DEFAULT_INSTANCE.getDefaultInstance();
        }

        public SortSqlPartDatumBuilder<Sort> sqlPart(String sqlPart) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, sqlPart);
        }

        public SortSqlPartDatumBuilder<Sort> primaryKey() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, PRIMARY_KEY_ALIAS);
        }

                public SortSqlPartDatumBuilder<Sort> id() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, ID_ALIAS);
        }
                public SortSqlPartDatumBuilder<Sort> roleId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, ROLE_ID_ALIAS);
        }
                public SortSqlPartDatumBuilder<Sort> roleName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, ROLE_NAME_ALIAS);
        }
                public SortSqlPartDatumBuilder<Sort> resourceId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_ID, RESOURCE_ID_ALIAS, RESOURCE_ID_ALIAS);
        }
                public SortSqlPartDatumBuilder<Sort> resourceName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_NAME, RESOURCE_NAME_ALIAS, RESOURCE_NAME_ALIAS);
        }
                public SortSqlPartDatumBuilder<Sort> sortIndex() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, SORT_INDEX_ALIAS);
        }
            }

    public static class Sql extends pub.avalonframework.sqlhelper.core.engine.builder.Sql<RoleResourceHelper, On, Column, Where, Group, Having, Sort> {
        public Sql() {
            super(TABLE_ALIAS);
        }
        public Sql(String tableAlias) {
            super(tableAlias);
        }
    }

    public static class SqlJoin extends pub.avalonframework.sqlhelper.core.engine.builder.SqlJoin<On> {
        public SqlJoin() {
            super(TABLE_ALIAS);
        }
        public SqlJoin(String tableAlias) {
            super(tableAlias);
        }
    }

    public static class SqlColumn extends pub.avalonframework.sqlhelper.core.engine.builder.SqlColumn<Column> {
        public SqlColumn() {
            super(TABLE_ALIAS);
        }
        public SqlColumn(String tableAlias) {
            super(tableAlias);
        }
    }

    public static class SqlWhere extends pub.avalonframework.sqlhelper.core.engine.builder.SqlWhere<Where> {
        public SqlWhere() {
            super(TABLE_ALIAS);
        }
        public SqlWhere(String tableAlias) {
            super(tableAlias);
        }
    }

    public static class SqlGroup extends pub.avalonframework.sqlhelper.core.engine.builder.SqlGroup<Group> {
        public SqlGroup() {
            super(TABLE_ALIAS);
        }
        public SqlGroup(String tableAlias) {
            super(tableAlias);
        }
    }

    public static class SqlHaving extends pub.avalonframework.sqlhelper.core.engine.builder.SqlHaving<Having> {
        public SqlHaving() {
            super(TABLE_ALIAS);
        }
        public SqlHaving(String tableAlias) {
            super(tableAlias);
        }
    }

    public static class SqlSort extends pub.avalonframework.sqlhelper.core.engine.builder.SqlSort<Sort> {
        public SqlSort() {
            super(TABLE_ALIAS);
        }
        public SqlSort(String tableAlias) {
            super(tableAlias);
        }
    }
}