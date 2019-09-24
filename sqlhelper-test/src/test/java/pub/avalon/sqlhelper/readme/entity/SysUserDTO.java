package pub.avalon.sqlhelper.readme.entity;

import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.beans.TableColumn;
import pub.avalon.sqlhelper.core.builder.HavingSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.OnSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.SortSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.WhereSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.LinkedHashSet;
import java.util.Set;

@SuppressWarnings("all")
public class SysUserDTO {

            /**
     * 
     */
        private String id;
            /**
     * 
     */
        private String userName;
            /**
     * 
     */
        private String loginName;
    
        public String getId() {
        return this.id;
    }
        public SysUserDTO setId(String id) {
        this.id = id;
        return this;
    }
            public String getUserName() {
        return this.userName;
    }
        public SysUserDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }
            public String getLoginName() {
        return this.loginName;
    }
        public SysUserDTO setLoginName(String loginName) {
        this.loginName = loginName;
        return this;
    }
        
    public final static class Helper implements TableHelper<Helper, Helper.Join, Helper.Column, Helper.Where, Helper.Group, Helper.Having, Helper.Sort> {

        public final static Helper DEFAULT_INSTANCE = new Helper();

                /**
         * table name
         */
                public final static String TABLE_NAME = "sys_user";
                /**
         * table alias
         */
        public final static String TABLE_ALIAS = "SysUser";
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
                public final static String USER_NAME = "user_name";
                /**
         *  alias
         */
                public final static String USER_NAME_ALIAS = "userName";
                        /**
         * 
         */
                public final static String LOGIN_NAME = "login_name";
                /**
         *  alias
         */
                public final static String LOGIN_NAME_ALIAS = "loginName";
        
                /**
         * table columns
         */
                public final static Set<TableColumn> TABLE_COLUMNS;

        static {
            TABLE_COLUMNS = new LinkedHashSet<>(3);
            TableColumn primaryKeyTableColumn = new TableColumn(PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, TABLE_NAME, TABLE_ALIAS, null, TABLE_COLUMNS);
            primaryKeyTableColumn.setPrimaryKeyColumnInfo(primaryKeyTableColumn);
                        TABLE_COLUMNS.add(new TableColumn(ID, ID_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
                        TABLE_COLUMNS.add(new TableColumn(USER_NAME, USER_NAME_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
                        TABLE_COLUMNS.add(new TableColumn(LOGIN_NAME, LOGIN_NAME_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
                    }

        public static Helper newInstance() {
            return new Helper();
        }

        public static Join join() {
            return new Join(TABLE_ALIAS);
        }

        public static Join join(String tableAlias) {
            return new Join(tableAlias);
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
        public Helper getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public Join newJoinHelper(String tableAlias) {
            return join(tableAlias);
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

        public final static class Join extends OnHelper<Join> {

            public Join() {
                super(TABLE_ALIAS);
            }

            public Join(String tableAlias) {
                super(tableAlias);
            }

            @Override
            public Helper getDefaultTableHelper() {
                return DEFAULT_INSTANCE.getDefaultInstance();
            }

            public OnSqlPartDatumBuilder<Join> sqlPart(String sqlPart) {
                return this.apply(TABLE_NAME, TABLE_ALIAS, sqlPart);
            }

            public OnSqlPartDatumBuilder<Join> primaryKey() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, PRIMARY_KEY_ALIAS);
            }

                        public OnSqlPartDatumBuilder<Join> id() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, ID_ALIAS);
            }
                        public OnSqlPartDatumBuilder<Join> userName() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, USER_NAME_ALIAS);
            }
                        public OnSqlPartDatumBuilder<Join> loginName() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, LOGIN_NAME_ALIAS);
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
            public Helper getDefaultTableHelper() {
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
                        public Column userName() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, USER_NAME_ALIAS).getHelper();
            }

            public Column userName(ColumnHandler... columnHandlers) {
                return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, USER_NAME_ALIAS, columnHandlers).getHelper();
            }

            public Column userName(String alias) {
                return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, alias).getHelper();
            }

            public Column userName(String alias, ColumnHandler... columnHandlers) {
                return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, alias, columnHandlers).getHelper();
            }
                        public Column loginName() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, LOGIN_NAME_ALIAS).getHelper();
            }

            public Column loginName(ColumnHandler... columnHandlers) {
                return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, LOGIN_NAME_ALIAS, columnHandlers).getHelper();
            }

            public Column loginName(String alias) {
                return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, alias).getHelper();
            }

            public Column loginName(String alias, ColumnHandler... columnHandlers) {
                return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, alias, columnHandlers).getHelper();
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
            public Helper getDefaultTableHelper() {
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
                        public WhereSqlPartDatumBuilder<Where> userName() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, USER_NAME_ALIAS);
            }
                        public WhereSqlPartDatumBuilder<Where> loginName() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, LOGIN_NAME_ALIAS);
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
            public Helper getDefaultTableHelper() {
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
                        public Group userName() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, USER_NAME_ALIAS).getHelper();
            }
                        public Group loginName() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, LOGIN_NAME_ALIAS).getHelper();
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
            public Helper getDefaultTableHelper() {
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
                        public HavingSqlPartDatumBuilder<Having> userName() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, USER_NAME_ALIAS);
            }

            public HavingSqlPartDatumBuilder<Having> userName(ColumnHandler... columnHandlers) {
                return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, USER_NAME_ALIAS, columnHandlers);
            }
                        public HavingSqlPartDatumBuilder<Having> loginName() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, LOGIN_NAME_ALIAS);
            }

            public HavingSqlPartDatumBuilder<Having> loginName(ColumnHandler... columnHandlers) {
                return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, LOGIN_NAME_ALIAS, columnHandlers);
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
            public Helper getDefaultTableHelper() {
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
                        public SortSqlPartDatumBuilder<Sort> userName() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, USER_NAME_ALIAS);
            }
                        public SortSqlPartDatumBuilder<Sort> loginName() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, LOGIN_NAME_ALIAS);
            }
                    }

        public static class Sql extends pub.avalon.sqlhelper.core.engine.builder.Sql<Helper, Join, Column, Where, Group, Having, Sort> {
            public Sql() {
                super(TABLE_ALIAS);
            }
            public Sql(String tableAlias) {
                super(tableAlias);
            }
        }

        public static class SqlJoin extends pub.avalon.sqlhelper.core.engine.builder.SqlJoin<Join> {
            public SqlJoin() {
                super(TABLE_ALIAS);
            }
            public SqlJoin(String tableAlias) {
                super(tableAlias);
            }
        }

        public static class SqlColumn extends pub.avalon.sqlhelper.core.engine.builder.SqlColumn<Column> {
            public SqlColumn() {
                super(TABLE_ALIAS);
            }
            public SqlColumn(String tableAlias) {
                super(tableAlias);
            }
        }

        public static class SqlWhere extends pub.avalon.sqlhelper.core.engine.builder.SqlWhere<Where> {
            public SqlWhere() {
                super(TABLE_ALIAS);
            }
            public SqlWhere(String tableAlias) {
                super(tableAlias);
            }
        }

        public static class SqlGroup extends pub.avalon.sqlhelper.core.engine.builder.SqlGroup<Group> {
            public SqlGroup() {
                super(TABLE_ALIAS);
            }
            public SqlGroup(String tableAlias) {
                super(tableAlias);
            }
        }

        public static class SqlHaving extends pub.avalon.sqlhelper.core.engine.builder.SqlHaving<Having> {
            public SqlHaving() {
                super(TABLE_ALIAS);
            }
            public SqlHaving(String tableAlias) {
                super(tableAlias);
            }
        }

        public static class SqlSort extends pub.avalon.sqlhelper.core.engine.builder.SqlSort<Sort> {
            public SqlSort() {
                super(TABLE_ALIAS);
            }
            public SqlSort(String tableAlias) {
                super(tableAlias);
            }
        }
    }
}