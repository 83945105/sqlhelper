package pub.avalon.sqlhelper.readme.entity;

import pub.avalon.sqlhelper.core.beans.TableColumn;
import pub.avalon.sqlhelper.core.builder.OnSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.SortSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.WhereSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.LinkedHashSet;
import java.util.Set;


@SuppressWarnings("all")
public class SysUserDTO {

    
        
            /**
             * 主键ID
             */
         private String id;
        
            /**
             * 用户名
             */
         private String userName;
        
            /**
             * 登录名
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
            
            
        
    

    public final static class Helper implements TableHelper<Helper, Helper.On, Helper.Column, Helper.Where, Helper.Group, Helper.Sort> {
        
            /**
             * 表名
             */
         public final static String TABLE_NAME = "sys_user";
        
            /**
             * 表 - 别名
             */
         public final static String TABLE_ALIAS = "SysUser";
        
            /**
             * 主键名
             */
         public final static String PRIMARY_KEY_NAME = "id";
        
            /**
             * 主键 - 别名
             */
         public final static String PRIMARY_KEY_ALIAS = "id";
        
            
                /**
                 * 主键ID
                 */
             public final static String ID = "id";
            
                /**
                 * 主键ID - 别名
                 */
             public final static String ID_ALIAS = "id";
            
                /**
                 * 用户名
                 */
             public final static String USER_NAME = "user_name";
            
                /**
                 * 用户名 - 别名
                 */
             public final static String USER_NAME_ALIAS = "userName";
            
                /**
                 * 登录名
                 */
             public final static String LOGIN_NAME = "login_name";
            
                /**
                 * 登录名 - 别名
                 */
             public final static String LOGIN_NAME_ALIAS = "loginName";
        
        
            /**
             * 表列数据
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

        public static Helper helper() {
            return new Helper();
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

        public static Group group() {
            return new Group(TABLE_ALIAS);
        }

        public static Group group(String tableAlias) {
            return new Group(tableAlias);
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
        public Helper newHelper() {
            return helper();
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
            return group(tableAlias);
        }

        @Override
        public Sort newSortHelper(String tableAlias) {
            return sort(tableAlias);
        }

        public final static class On extends OnHelper<On> {

            private On(String tableAlias) {
                super(tableAlias);
            }

            public OnSqlPartDatumBuilder<On> primaryKey() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS);
            }

            
                public OnSqlPartDatumBuilder<On> id() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS);
                }
                public OnSqlPartDatumBuilder<On> userName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS);
                }
                public OnSqlPartDatumBuilder<On> loginName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS);
                }
            

        }

        public final static class Column extends ColumnHelper<Column> {

            private Column(String tableAlias) {
                super(tableAlias);
            }

            public Column primaryKey() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS).getHelper();
            }

            public Column primaryKey(String alias) {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, alias).getHelper();
            }

            
                public Column id() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS).getHelper();
                }

                public Column id(String alias) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, alias).getHelper();
                }
                public Column userName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS).getHelper();
                }

                public Column userName(String alias) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, alias).getHelper();
                }
                public Column loginName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS).getHelper();
                }

                public Column loginName(String alias) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, alias).getHelper();
                }
            

        }

        public final static class Where extends WhereHelper<Where> {

            private Where(String tableAlias) {
                super(tableAlias);
            }

            public WhereSqlPartDatumBuilder<Where> primaryKey() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS);
            }

            
                public WhereSqlPartDatumBuilder<Where> id() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS);
                }
                public WhereSqlPartDatumBuilder<Where> userName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS);
                }
                public WhereSqlPartDatumBuilder<Where> loginName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS);
                }
            

        }

        public final static class Group extends GroupHelper<Group> {

            private Group(String tableAlias) {
                super(tableAlias);
            }

            public Group primaryKey() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS).getHelper();
            }

            
                public Group id() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS).getHelper();
                }
                public Group userName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS).getHelper();
                }
                public Group loginName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS).getHelper();
                }
            

        }

        public final static class Sort extends SortHelper<Sort> {

            private Sort(String tableAlias) {
                super(tableAlias);
            }

            public SortSqlPartDatumBuilder<Sort> primaryKey() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS);
            }

            
                public SortSqlPartDatumBuilder<Sort> id() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS);
                }
                public SortSqlPartDatumBuilder<Sort> userName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS);
                }
                public SortSqlPartDatumBuilder<Sort> loginName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS);
                }
            

        }

    }

}