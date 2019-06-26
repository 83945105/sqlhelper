package pub.avalon.sqlhelper.readme.entity;

import pub.avalon.sqlhelper.core.beans.TableColumn;
import pub.avalon.sqlhelper.core.builder.OnSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.SortSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.WhereSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.LinkedHashSet;
import java.util.Set;


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
         public final static String TABLE_NAME = "role_resource";
        
            /**
             * 表 - 别名
             */
         public final static String TABLE_ALIAS = "RoleResource";
        
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
             public final static String RESOURCE_ID = "resource_id";
            
                /**
                 *  - 别名
                 */
             public final static String RESOURCE_ID_ALIAS = "resourceId";
            
                /**
                 * 
                 */
             public final static String RESOURCE_NAME = "resource_name";
            
                /**
                 *  - 别名
                 */
             public final static String RESOURCE_NAME_ALIAS = "resourceName";
            
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

        public static Helper helper() {
            return new Helper();
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
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS);
            }

            
                public OnSqlPartDatumBuilder<On> id() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS);
                }
                public OnSqlPartDatumBuilder<On> roleId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS);
                }
                public OnSqlPartDatumBuilder<On> roleName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS);
                }
                public OnSqlPartDatumBuilder<On> resourceId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_ID, RESOURCE_ID_ALIAS);
                }
                public OnSqlPartDatumBuilder<On> resourceName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_NAME, RESOURCE_NAME_ALIAS);
                }
                public OnSqlPartDatumBuilder<On> sortIndex() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS);
                }
            

        }

        public final static class Column extends ColumnHelper<Column> {

            private Column() {
                super();
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
                public Column roleId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS).getHelper();
                }

                public Column roleId(String alias) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, alias).getHelper();
                }
                public Column roleName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS).getHelper();
                }

                public Column roleName(String alias) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, alias).getHelper();
                }
                public Column resourceId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_ID, RESOURCE_ID_ALIAS).getHelper();
                }

                public Column resourceId(String alias) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_ID, alias).getHelper();
                }
                public Column resourceName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_NAME, RESOURCE_NAME_ALIAS).getHelper();
                }

                public Column resourceName(String alias) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_NAME, alias).getHelper();
                }
                public Column sortIndex() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS).getHelper();
                }

                public Column sortIndex(String alias) {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, alias).getHelper();
                }
            

        }

        public final static class Where extends WhereHelper<Where> {

            private Where() {
                super();
            }

            public WhereSqlPartDatumBuilder<Where> primaryKey() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS);
            }

            
                public WhereSqlPartDatumBuilder<Where> id() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS);
                }
                public WhereSqlPartDatumBuilder<Where> roleId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS);
                }
                public WhereSqlPartDatumBuilder<Where> roleName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS);
                }
                public WhereSqlPartDatumBuilder<Where> resourceId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_ID, RESOURCE_ID_ALIAS);
                }
                public WhereSqlPartDatumBuilder<Where> resourceName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_NAME, RESOURCE_NAME_ALIAS);
                }
                public WhereSqlPartDatumBuilder<Where> sortIndex() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS);
                }
            

        }

        public final static class Group extends GroupHelper<Group> {

            private Group() {
                super();
            }

            public Group primaryKey() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS).getHelper();
            }

            
                public Group id() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS).getHelper();
                }
                public Group roleId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS).getHelper();
                }
                public Group roleName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS).getHelper();
                }
                public Group resourceId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_ID, RESOURCE_ID_ALIAS).getHelper();
                }
                public Group resourceName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_NAME, RESOURCE_NAME_ALIAS).getHelper();
                }
                public Group sortIndex() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS).getHelper();
                }
            

        }

        public final static class Sort extends SortHelper<Sort> {

            private Sort() {
                super();
            }

            public SortSqlPartDatumBuilder<Sort> primaryKey() {
                return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS);
            }

            
                public SortSqlPartDatumBuilder<Sort> id() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS);
                }
                public SortSqlPartDatumBuilder<Sort> roleId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS);
                }
                public SortSqlPartDatumBuilder<Sort> roleName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS);
                }
                public SortSqlPartDatumBuilder<Sort> resourceId() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_ID, RESOURCE_ID_ALIAS);
                }
                public SortSqlPartDatumBuilder<Sort> resourceName() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, RESOURCE_NAME, RESOURCE_NAME_ALIAS);
                }
                public SortSqlPartDatumBuilder<Sort> sortIndex() {
                    return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS);
                }
            

        }

    }

}