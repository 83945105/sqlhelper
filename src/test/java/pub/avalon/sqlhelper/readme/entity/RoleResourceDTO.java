package pub.avalon.sqlhelper.readme.entity;

import pub.avalon.sqlhelper.core.builder.OnSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.SortSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.WhereSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.LinkedHashMap;
import java.util.Map;


@SuppressWarnings("all")
public class RoleResourceDTO {

    
        
            /**
             * 
             */
         private String id;
        
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
         private String resourceId;
        
            /**
             * 
             */
         private String resourceName;
        
            /**
             * 
             */
         private Long sortIndex;
    


    
        public String getId() {
            return this.id;
        }
        
            
                public RoleResourceDTO setId(String id) {
                    this.id = id;
                    return this;
                }
            
            
        
        public String getRoleId() {
            return this.roleId;
        }
        
            
                public RoleResourceDTO setRoleId(String roleId) {
                    this.roleId = roleId;
                    return this;
                }
            
            
        
        public String getRoleName() {
            return this.roleName;
        }
        
            
                public RoleResourceDTO setRoleName(String roleName) {
                    this.roleName = roleName;
                    return this;
                }
            
            
        
        public String getResourceId() {
            return this.resourceId;
        }
        
            
                public RoleResourceDTO setResourceId(String resourceId) {
                    this.resourceId = resourceId;
                    return this;
                }
            
            
        
        public String getResourceName() {
            return this.resourceName;
        }
        
            
                public RoleResourceDTO setResourceName(String resourceName) {
                    this.resourceName = resourceName;
                    return this;
                }
            
            
        
        public Long getSortIndex() {
            return this.sortIndex;
        }
        
            
                public RoleResourceDTO setSortIndex(Long sortIndex) {
                    this.sortIndex = sortIndex;
                    return this;
                }
            
            
        
    

    public final static class Helper implements TableHelper<Helper, Helper.On, Helper.Column, Helper.Where, Helper.Group, Helper.Sort> {
        
            /**
             * 表名
             */
         public final static String tableName = "role_resource";
        
            /**
             * 表 - 别名
             */
         public final static String tableAlias = "RoleResource";
        
            /**
             * 主键名
             */
         public final static String primaryKeyName = "id";
        
            /**
             * 主键 - 别名
             */
         public final static String primaryKeyAlias = "id";
        
            
                /**
                 * 
                 */
             public final static String id = "id";
            
                /**
                 *  - 别名
                 */
             public final static String id_alias = "id";
            
                /**
                 * 
                 */
             public final static String role_id = "role_id";
            
                /**
                 *  - 别名
                 */
             public final static String role_id_alias = "roleId";
            
                /**
                 * 
                 */
             public final static String role_name = "role_name";
            
                /**
                 *  - 别名
                 */
             public final static String role_name_alias = "roleName";
            
                /**
                 * 
                 */
             public final static String resource_id = "resource_id";
            
                /**
                 *  - 别名
                 */
             public final static String resource_id_alias = "resourceId";
            
                /**
                 * 
                 */
             public final static String resource_name = "resource_name";
            
                /**
                 *  - 别名
                 */
             public final static String resource_name_alias = "resourceName";
            
                /**
                 * 
                 */
             public final static String sort_index = "sort_index";
            
                /**
                 *  - 别名
                 */
             public final static String sort_index_alias = "sortIndex";
        
        
            /**
             *  列名 - 别名 键值对集合
             */
         public final static Map<String, String> columnNameAliasMap;

        static {
            columnNameAliasMap = new LinkedHashMap<>(6);
            
                columnNameAliasMap.put(id, id_alias);
                columnNameAliasMap.put(role_id, role_id_alias);
                columnNameAliasMap.put(role_name, role_name_alias);
                columnNameAliasMap.put(resource_id, resource_id_alias);
                columnNameAliasMap.put(resource_name, resource_name_alias);
                columnNameAliasMap.put(sort_index, sort_index_alias);
            
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
        public Map<String, String> getColumnNameAliasMap() {
            return columnNameAliasMap;
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
                super();
            }

            public OnSqlPartDatumBuilder<On> primaryKey() {
                return this.apply(tableName, tableAlias, primaryKeyName, primaryKeyAlias);
            }

            
                public OnSqlPartDatumBuilder<On> id() {
                    return this.apply(tableName, tableAlias, id, id_alias);
                }
                public OnSqlPartDatumBuilder<On> roleId() {
                    return this.apply(tableName, tableAlias, role_id, role_id_alias);
                }
                public OnSqlPartDatumBuilder<On> roleName() {
                    return this.apply(tableName, tableAlias, role_name, role_name_alias);
                }
                public OnSqlPartDatumBuilder<On> resourceId() {
                    return this.apply(tableName, tableAlias, resource_id, resource_id_alias);
                }
                public OnSqlPartDatumBuilder<On> resourceName() {
                    return this.apply(tableName, tableAlias, resource_name, resource_name_alias);
                }
                public OnSqlPartDatumBuilder<On> sortIndex() {
                    return this.apply(tableName, tableAlias, sort_index, sort_index_alias);
                }
            

        }

        public final static class Column extends ColumnHelper<Column> {

            private Column() {
                super();
            }

            public Column primaryKey() {
                return this.apply(tableName, tableAlias, primaryKeyName, primaryKeyAlias).getHelper();
            }

            public Column primaryKey(String alias) {
                return this.apply(tableName, tableAlias, primaryKeyName, alias).getHelper();
            }

            
                public Column id() {
                    return this.apply(tableName, tableAlias, id, id_alias).getHelper();
                }

                public Column id(String alias) {
                    return this.apply(tableName, tableAlias, id, alias).getHelper();
                }
                public Column roleId() {
                    return this.apply(tableName, tableAlias, role_id, role_id_alias).getHelper();
                }

                public Column roleId(String alias) {
                    return this.apply(tableName, tableAlias, role_id, alias).getHelper();
                }
                public Column roleName() {
                    return this.apply(tableName, tableAlias, role_name, role_name_alias).getHelper();
                }

                public Column roleName(String alias) {
                    return this.apply(tableName, tableAlias, role_name, alias).getHelper();
                }
                public Column resourceId() {
                    return this.apply(tableName, tableAlias, resource_id, resource_id_alias).getHelper();
                }

                public Column resourceId(String alias) {
                    return this.apply(tableName, tableAlias, resource_id, alias).getHelper();
                }
                public Column resourceName() {
                    return this.apply(tableName, tableAlias, resource_name, resource_name_alias).getHelper();
                }

                public Column resourceName(String alias) {
                    return this.apply(tableName, tableAlias, resource_name, alias).getHelper();
                }
                public Column sortIndex() {
                    return this.apply(tableName, tableAlias, sort_index, sort_index_alias).getHelper();
                }

                public Column sortIndex(String alias) {
                    return this.apply(tableName, tableAlias, sort_index, alias).getHelper();
                }
            

        }

        public final static class Where extends WhereHelper<Where> {

            private Where() {
                super();
            }

            public WhereSqlPartDatumBuilder<Where> primaryKey() {
                return this.apply(tableName, tableAlias, primaryKeyName, primaryKeyAlias);
            }

            
                public WhereSqlPartDatumBuilder<Where> id() {
                    return this.apply(tableName, tableAlias, id, id_alias);
                }
                public WhereSqlPartDatumBuilder<Where> roleId() {
                    return this.apply(tableName, tableAlias, role_id, role_id_alias);
                }
                public WhereSqlPartDatumBuilder<Where> roleName() {
                    return this.apply(tableName, tableAlias, role_name, role_name_alias);
                }
                public WhereSqlPartDatumBuilder<Where> resourceId() {
                    return this.apply(tableName, tableAlias, resource_id, resource_id_alias);
                }
                public WhereSqlPartDatumBuilder<Where> resourceName() {
                    return this.apply(tableName, tableAlias, resource_name, resource_name_alias);
                }
                public WhereSqlPartDatumBuilder<Where> sortIndex() {
                    return this.apply(tableName, tableAlias, sort_index, sort_index_alias);
                }
            

        }

        public final static class Group extends GroupHelper<Group> {

            private Group() {
                super();
            }

            public Group primaryKey() {
                return this.apply(tableName, tableAlias, primaryKeyName, primaryKeyAlias).getHelper();
            }

            
                public Group id() {
                    return this.apply(tableName, tableAlias, id, id_alias).getHelper();
                }
                public Group roleId() {
                    return this.apply(tableName, tableAlias, role_id, role_id_alias).getHelper();
                }
                public Group roleName() {
                    return this.apply(tableName, tableAlias, role_name, role_name_alias).getHelper();
                }
                public Group resourceId() {
                    return this.apply(tableName, tableAlias, resource_id, resource_id_alias).getHelper();
                }
                public Group resourceName() {
                    return this.apply(tableName, tableAlias, resource_name, resource_name_alias).getHelper();
                }
                public Group sortIndex() {
                    return this.apply(tableName, tableAlias, sort_index, sort_index_alias).getHelper();
                }
            

        }

        public final static class Sort extends SortHelper<Sort> {

            private Sort() {
                super();
            }

            public SortSqlPartDatumBuilder<Sort> primaryKey() {
                return this.apply(tableName, tableAlias, primaryKeyName, primaryKeyAlias);
            }

            
                public SortSqlPartDatumBuilder<Sort> id() {
                    return this.apply(tableName, tableAlias, id, id_alias);
                }
                public SortSqlPartDatumBuilder<Sort> roleId() {
                    return this.apply(tableName, tableAlias, role_id, role_id_alias);
                }
                public SortSqlPartDatumBuilder<Sort> roleName() {
                    return this.apply(tableName, tableAlias, role_name, role_name_alias);
                }
                public SortSqlPartDatumBuilder<Sort> resourceId() {
                    return this.apply(tableName, tableAlias, resource_id, resource_id_alias);
                }
                public SortSqlPartDatumBuilder<Sort> resourceName() {
                    return this.apply(tableName, tableAlias, resource_name, resource_name_alias);
                }
                public SortSqlPartDatumBuilder<Sort> sortIndex() {
                    return this.apply(tableName, tableAlias, sort_index, sort_index_alias);
                }
            

        }

    }

}