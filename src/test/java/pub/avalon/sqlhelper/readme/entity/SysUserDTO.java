package pub.avalon.sqlhelper.readme.entity;

import pub.avalon.sqlhelper.core.builder.*;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.LinkedHashMap;
import java.util.Map;


@SuppressWarnings("all")
public final class SysUserDTO {

    
        
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
            
            
        
    

    public static class Helper implements TableHelper<Helper, Helper.On, Helper.Column, Helper.Where, Helper.Group, Helper.Sort> {
        
            /**
             * 表名
             */
         public final static String tableName = "sys_user";
        
            /**
             * 表 - 别名
             */
         public final static String tableAlias = "SysUser";
        
            /**
             * 主键名
             */
         public final static String primaryKeyName = "id";
        
            /**
             * 主键 - 别名
             */
         public final static String primaryKeyAlias = "id";
        
            
                /**
                 * 主键ID
                 */
             public final static String id = "id";
            
                /**
                 * 主键ID - 别名
                 */
             public final static String id_alias = "id";
            
                /**
                 * 用户名
                 */
             public final static String user_name = "user_name";
            
                /**
                 * 用户名 - 别名
                 */
             public final static String user_name_alias = "userName";
            
                /**
                 * 登录名
                 */
             public final static String login_name = "login_name";
            
                /**
                 * 登录名 - 别名
                 */
             public final static String login_name_alias = "loginName";
        
        
            /**
             *  列名 - 别名 键值对集合
             */
         public final static Map<String, String> columnAliasMap;

        static {
            columnAliasMap = new LinkedHashMap<>(3);
        }

        public static On on() {
            return new On();
        }

        public static Column column() {
            return new Column();
        }

        public static Where where() {
            return new Where();
        }

        public static Group group() {
            return new Group();
        }

        public static Sort sort() {
            return new Sort();
        }

        @Override
        public Map<String, String> getColumnAliasMap() {
            return columnAliasMap;
        }

        @Override
        public String getTableName() {
            return tableName;
        }

        @Override
        public String getTableAlias() {
            return tableAlias;
        }

        @Override
        public String getPrimaryKeyName() {
            return primaryKeyName;
        }

        @Override
        public String getPrimaryKeyAlias() {
            return primaryKeyAlias;
        }

        @Override
        public On newOnHelper() {
            return on();
        }

        @Override
        public Column newColumnHelper() {
            return column();
        }

        @Override
        public Where newWhereHelper() {
            return where();
        }

        @Override
        public Group newGroupHelper() {
            return group();
        }

        @Override
        public Sort newSortHelper() {
            return sort();
        }

        public final static class On extends OnHelper<On> {

            private On() {
                super(new OnSqlDataBuilder<>());
            }

            public OnSqlDataBuilder<On> primaryKey() {
                return this.apply(tableName, tableAlias, primaryKeyName, primaryKeyAlias);
            }

            
                public OnSqlDataBuilder<On> id() {
                    return this.apply(tableName, tableAlias, id, id_alias);
                }
                public OnSqlDataBuilder<On> userName() {
                    return this.apply(tableName, tableAlias, user_name, user_name_alias);
                }
                public OnSqlDataBuilder<On> loginName() {
                    return this.apply(tableName, tableAlias, login_name, login_name_alias);
                }
            

        }

        public final static class Column extends ColumnHelper<Column> {

            private Column() {
                super(new ColumnSqlDataBuilder<>());
            }

            public Column primaryKey() {
                return this.apply(tableName, tableAlias, primaryKeyName, primaryKeyAlias).getSqlModel();
            }

            public Column primaryKey(String alias) {
                return this.apply(tableName, tableAlias, primaryKeyName, alias).getSqlModel();
            }

            
                public Column id() {
                    return this.apply(tableName, tableAlias, id, id_alias).getSqlModel();
                }

                public Column id(String alias) {
                    return this.apply(tableName, tableAlias, id, alias).getSqlModel();
                }
                public Column userName() {
                    return this.apply(tableName, tableAlias, user_name, user_name_alias).getSqlModel();
                }

                public Column userName(String alias) {
                    return this.apply(tableName, tableAlias, user_name, alias).getSqlModel();
                }
                public Column loginName() {
                    return this.apply(tableName, tableAlias, login_name, login_name_alias).getSqlModel();
                }

                public Column loginName(String alias) {
                    return this.apply(tableName, tableAlias, login_name, alias).getSqlModel();
                }
            

        }

        public final static class Where extends WhereHelper<Where> {

            private Where() {
                super(new WhereSqlDataBuilder<>());
            }

            public WhereSqlDataBuilder<Where> primaryKey() {
                return this.apply(tableName, tableAlias, primaryKeyName, primaryKeyAlias);
            }

            
                public WhereSqlDataBuilder<Where> id() {
                    return this.apply(tableName, tableAlias, id, id_alias);
                }
                public WhereSqlDataBuilder<Where> userName() {
                    return this.apply(tableName, tableAlias, user_name, user_name_alias);
                }
                public WhereSqlDataBuilder<Where> loginName() {
                    return this.apply(tableName, tableAlias, login_name, login_name_alias);
                }
            

        }

        public final static class Group extends GroupHelper<Group> {

            private Group() {
                super(new GroupSqlDataBuilder<>());
            }

            public Group primaryKey() {
                return this.apply(tableName, tableAlias, primaryKeyName, primaryKeyAlias).getSqlModel();
            }

            
                public Group id() {
                    return this.apply(tableName, tableAlias, id, id_alias).getSqlModel();
                }
                public Group userName() {
                    return this.apply(tableName, tableAlias, user_name, user_name_alias).getSqlModel();
                }
                public Group loginName() {
                    return this.apply(tableName, tableAlias, login_name, login_name_alias).getSqlModel();
                }
            

        }

        public final static class Sort extends SortHelper<Sort> {

            private Sort() {
                super(new SortSqlDataBuilder<>());
            }

            public SortSqlDataBuilder<Sort> primaryKey() {
                return this.apply(tableName, tableAlias, primaryKeyName, primaryKeyAlias);
            }

            
                public SortSqlDataBuilder<Sort> id() {
                    return this.apply(tableName, tableAlias, id, id_alias);
                }
                public SortSqlDataBuilder<Sort> userName() {
                    return this.apply(tableName, tableAlias, user_name, user_name_alias);
                }
                public SortSqlDataBuilder<Sort> loginName() {
                    return this.apply(tableName, tableAlias, login_name, login_name_alias);
                }
            

        }

    }

}