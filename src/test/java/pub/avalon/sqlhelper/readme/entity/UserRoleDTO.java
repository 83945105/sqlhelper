package pub.avalon.sqlhelper.readme.entity;

import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.beans.TableColumn;
import pub.avalon.sqlhelper.core.builder.HavingSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.JoinSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.SortSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.WhereSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.LinkedHashSet;
import java.util.Set;


@SuppressWarnings("all")
public class UserRoleDTO {

    
        
            /**
             * 
             */
         private String id;
        
            /**
             * 
             */
         private String userId;
        
            /**
             * 
             */
         private String roleId;
        
            /**
             * 
             */
         private String roleName;
        
            /**
             * 
             */
         private Long sortIndex;
    


    
        public String getId() {
            return this.id;
        }
        
            
                public UserRoleDTO setId(String id) {
                    this.id = id;
                    return this;
                }
            
            
        
        public String getUserId() {
            return this.userId;
        }
        
            
                public UserRoleDTO setUserId(String userId) {
                    this.userId = userId;
                    return this;
                }
            
            
        
        public String getRoleId() {
            return this.roleId;
        }
        
            
                public UserRoleDTO setRoleId(String roleId) {
                    this.roleId = roleId;
                    return this;
                }
            
            
        
        public String getRoleName() {
            return this.roleName;
        }
        
            
                public UserRoleDTO setRoleName(String roleName) {
                    this.roleName = roleName;
                    return this;
                }
            
            
        
        public Long getSortIndex() {
            return this.sortIndex;
        }
        
            
                public UserRoleDTO setSortIndex(Long sortIndex) {
                    this.sortIndex = sortIndex;
                    return this;
                }
            
            
        
    

    public final static class Helper implements TableHelper<Helper, Helper.Join, Helper.Column, Helper.Where, Helper.Group, Helper.Having, Helper.Sort> {

        public final static Helper HELPER = new Helper();

        
            /**
             * 表名
             */
         public final static String TABLE_NAME = "user_role";
        
            /**
             * 表 - 别名
             */
         public final static String TABLE_ALIAS = "UserRole";
        
            /**
             * 主键名
             */
         public final static String PRIMARY_KEY_NAME = "id";
        
            /**
             * 主键 - 别名
             */
         public final static String PRIMARY_KEY_ALIAS = "id";
        
            
                /**
                 * 
                 */
             public final static String ID = "id";
            
                /**
                 *  - 别名
                 */
             public final static String ID_ALIAS = "id";
            
                /**
                 * 
                 */
             public final static String USER_ID = "user_id";
            
                /**
                 *  - 别名
                 */
             public final static String USER_ID_ALIAS = "userId";
            
                /**
                 * 
                 */
             public final static String ROLE_ID = "role_id";
            
                /**
                 *  - 别名
                 */
             public final static String ROLE_ID_ALIAS = "roleId";
            
                /**
                 * 
                 */
             public final static String ROLE_NAME = "role_name";
            
                /**
                 *  - 别名
                 */
             public final static String ROLE_NAME_ALIAS = "roleName";
            
                /**
                 * 
                 */
             public final static String SORT_INDEX = "sort_index";
            
                /**
                 *  - 别名
                 */
             public final static String SORT_INDEX_ALIAS = "sortIndex";
        
        
            /**
             * 表列数据
             */
         public final static Set<TableColumn> TABLE_COLUMNS;

        static {
            TABLE_COLUMNS = new LinkedHashSet<>(5);
            TableColumn primaryKeyTableColumn = new TableColumn(PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, TABLE_NAME, TABLE_ALIAS, null, TABLE_COLUMNS);
            primaryKeyTableColumn.setPrimaryKeyColumnInfo(primaryKeyTableColumn);
            
                TABLE_COLUMNS.add(new TableColumn(ID, ID_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
                TABLE_COLUMNS.add(new TableColumn(USER_ID, USER_ID_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
                TABLE_COLUMNS.add(new TableColumn(ROLE_ID, ROLE_ID_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
                TABLE_COLUMNS.add(new TableColumn(ROLE_NAME, ROLE_NAME_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
                TABLE_COLUMNS.add(new TableColumn(SORT_INDEX, SORT_INDEX_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
            
        }

        public static Helper helper() {
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

        public static Group group() {
            return new Group(TABLE_ALIAS);
        }

        public static Group group(String tableAlias) {
            return new Group(tableAlias);
        }

        public static Having having() {
            return new Having(TABLE_ALIAS);
        }

        public static Having having(String tableAlias) {
            return new Having(tableAlias);
        }

        public static Sort sort() {
            return new Sort(TABLE_ALIAS);
        }

        public static Sort sort(String tableAlias) {
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
        public Helper getSingleHelper() {
            return HELPER;
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
            return group(tableAlias);
        }

        @Override
        public Having newHavingHelper(String tableAlias) {
            return having(tableAlias);
        }

        @Override
        public Sort newSortHelper(String tableAlias) {
            return sort(tableAlias);
        }

        public final static class Join extends JoinHelper<Join> {

            public Join() {
                super(TABLE_ALIAS);
            }

            public Join(String tableAlias) {
                super(tableAlias);
            }

            public JoinSqlPartDatumBuilder<Join> primaryKey() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS);
            }

            
                public JoinSqlPartDatumBuilder<Join> id() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS);
                }
                public JoinSqlPartDatumBuilder<Join> userId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, USER_ID_ALIAS);
                }
                public JoinSqlPartDatumBuilder<Join> roleId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS);
                }
                public JoinSqlPartDatumBuilder<Join> roleName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS);
                }
                public JoinSqlPartDatumBuilder<Join> sortIndex() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS);
                }
            

        }

        public final static class Column extends ColumnHelper<Column> {

            public Column() {
                super(TABLE_ALIAS);
            }

            public Column(String tableAlias) {
                super(tableAlias);
            }

            public Column primaryKey() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS).getHelper();
            }

            public Column primaryKey(ColumnHandler... columnHandlers) {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, columnHandlers).getHelper();
            }

            public Column primaryKey(String alias) {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, alias).getHelper();
            }

            public Column primaryKey(String alias, ColumnHandler... columnHandlers) {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, alias, columnHandlers).getHelper();
            }

            
                public Column id() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS).getHelper();
                }

                public Column id(ColumnHandler... columnHandlers) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, columnHandlers).getHelper();
                }

                public Column id(String alias) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, alias).getHelper();
                }

                public Column id(String alias, ColumnHandler... columnHandlers) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, alias, columnHandlers).getHelper();
                }
                public Column userId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, USER_ID_ALIAS).getHelper();
                }

                public Column userId(ColumnHandler... columnHandlers) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, USER_ID_ALIAS, columnHandlers).getHelper();
                }

                public Column userId(String alias) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, alias).getHelper();
                }

                public Column userId(String alias, ColumnHandler... columnHandlers) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, alias, columnHandlers).getHelper();
                }
                public Column roleId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS).getHelper();
                }

                public Column roleId(ColumnHandler... columnHandlers) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, columnHandlers).getHelper();
                }

                public Column roleId(String alias) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, alias).getHelper();
                }

                public Column roleId(String alias, ColumnHandler... columnHandlers) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, alias, columnHandlers).getHelper();
                }
                public Column roleName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS).getHelper();
                }

                public Column roleName(ColumnHandler... columnHandlers) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, columnHandlers).getHelper();
                }

                public Column roleName(String alias) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, alias).getHelper();
                }

                public Column roleName(String alias, ColumnHandler... columnHandlers) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, alias, columnHandlers).getHelper();
                }
                public Column sortIndex() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS).getHelper();
                }

                public Column sortIndex(ColumnHandler... columnHandlers) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, columnHandlers).getHelper();
                }

                public Column sortIndex(String alias) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, alias).getHelper();
                }

                public Column sortIndex(String alias, ColumnHandler... columnHandlers) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, alias, columnHandlers).getHelper();
                }
            

        }

        public final static class Where extends WhereHelper<Where> {

            public Where() {
                super(TABLE_ALIAS);
            }

            public Where(String tableAlias) {
                super(tableAlias);
            }

            public WhereSqlPartDatumBuilder<Where> primaryKey() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS);
            }

            
                public WhereSqlPartDatumBuilder<Where> id() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS);
                }
                public WhereSqlPartDatumBuilder<Where> userId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, USER_ID_ALIAS);
                }
                public WhereSqlPartDatumBuilder<Where> roleId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS);
                }
                public WhereSqlPartDatumBuilder<Where> roleName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS);
                }
                public WhereSqlPartDatumBuilder<Where> sortIndex() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS);
                }
            

        }

        public final static class Group extends GroupHelper<Group> {

            public Group() {
                super(TABLE_ALIAS);
            }

            public Group(String tableAlias) {
                super(tableAlias);
            }

            public Group primaryKey() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS).getHelper();
            }

            
                public Group id() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS).getHelper();
                }
                public Group userId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, USER_ID_ALIAS).getHelper();
                }
                public Group roleId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS).getHelper();
                }
                public Group roleName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS).getHelper();
                }
                public Group sortIndex() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS).getHelper();
                }
            

        }

        public final static class Having extends HavingHelper<Having> {

            public Having() {
                super(TABLE_ALIAS);
            }

            public Having(String tableAlias) {
                super(tableAlias);
            }

            public HavingSqlPartDatumBuilder<Having> primaryKey() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS);
            }

            public HavingSqlPartDatumBuilder<Having> primaryKey(ColumnHandler... columnHandlers) {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, columnHandlers);
            }

            
                public HavingSqlPartDatumBuilder<Having> id() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS);
                }

                public HavingSqlPartDatumBuilder<Having> id(ColumnHandler... columnHandlers) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, columnHandlers);
                }
                public HavingSqlPartDatumBuilder<Having> userId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, USER_ID_ALIAS);
                }

                public HavingSqlPartDatumBuilder<Having> userId(ColumnHandler... columnHandlers) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, USER_ID_ALIAS, columnHandlers);
                }
                public HavingSqlPartDatumBuilder<Having> roleId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS);
                }

                public HavingSqlPartDatumBuilder<Having> roleId(ColumnHandler... columnHandlers) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, columnHandlers);
                }
                public HavingSqlPartDatumBuilder<Having> roleName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS);
                }

                public HavingSqlPartDatumBuilder<Having> roleName(ColumnHandler... columnHandlers) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, columnHandlers);
                }
                public HavingSqlPartDatumBuilder<Having> sortIndex() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS);
                }

                public HavingSqlPartDatumBuilder<Having> sortIndex(ColumnHandler... columnHandlers) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, columnHandlers);
                }
            

        }

        public final static class Sort extends SortHelper<Sort> {

            public Sort() {
                super(TABLE_ALIAS);
            }

            public Sort(String tableAlias) {
                super(tableAlias);
            }

            public SortSqlPartDatumBuilder<Sort> primaryKey() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS);
            }

            
                public SortSqlPartDatumBuilder<Sort> id() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS);
                }
                public SortSqlPartDatumBuilder<Sort> userId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, USER_ID_ALIAS);
                }
                public SortSqlPartDatumBuilder<Sort> roleId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS);
                }
                public SortSqlPartDatumBuilder<Sort> roleName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS);
                }
                public SortSqlPartDatumBuilder<Sort> sortIndex() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS);
                }
            

        }

        public static class Sql extends pub.avalon.sqlhelper.core.engine.Sql<Helper, Join, Column, Where, Group, Having, Sort> {
            public Sql() {
                super(TABLE_ALIAS);
            }
            public Sql(String tableAlias) {
                super(tableAlias);
            }
        }

        public static class SqlJoin extends pub.avalon.sqlhelper.core.engine.SqlJoin<Join> {
            public SqlJoin() {
                super(TABLE_ALIAS);
            }
            public SqlJoin(String tableAlias) {
                super(tableAlias);
            }
        }

        public static class SqlColumn extends pub.avalon.sqlhelper.core.engine.SqlColumn<Column> {
            public SqlColumn() {
                super(TABLE_ALIAS);
            }
            public SqlColumn(String tableAlias) {
                super(tableAlias);
            }
        }

        public static class SqlWhere extends pub.avalon.sqlhelper.core.engine.SqlWhere<Where> {
            public SqlWhere() {
                super(TABLE_ALIAS);
            }
            public SqlWhere(String tableAlias) {
                super(tableAlias);
            }
        }

        public static class SqlGroup extends pub.avalon.sqlhelper.core.engine.SqlGroup<Group> {
            public SqlGroup() {
                super(TABLE_ALIAS);
            }
            public SqlGroup(String tableAlias) {
                super(tableAlias);
            }
        }

        public static class SqlHaving extends pub.avalon.sqlhelper.core.engine.SqlHaving<Having> {
            public SqlHaving() {
                super(TABLE_ALIAS);
            }
            public SqlHaving(String tableAlias) {
                super(tableAlias);
            }
        }

        public static class SqlSort extends pub.avalon.sqlhelper.core.engine.SqlSort<Sort> {
            public SqlSort() {
                super(TABLE_ALIAS);
            }
            public SqlSort(String tableAlias) {
                super(tableAlias);
            }
        }

    }

}