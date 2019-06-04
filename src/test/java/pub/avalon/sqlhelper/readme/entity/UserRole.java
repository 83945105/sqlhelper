package pub.avalon.sqlhelper.readme.entity;

import pub.avalon.sqlhelper.core.modelbuilder.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserRole {

    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public UserRole setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
        return this;
    }

    public static class Helper implements TableModel<UserRole.Helper, UserRole.Helper.On, UserRole.Helper.Column, UserRole.Helper.Where, UserRole.Helper.Group, UserRole.Helper.Sort> {

        public final static String tableName = "role_resource";
        public final static String tableAlias = "UserRole";
        public final static String primaryKeyName = "roleId";
        public final static String primaryKeyAlias = "roleId";
        public final static String roleId = "roleId";
        public final static String roleId_alias = "roleId";

        public final static Map<String, String> columnAliasMap;

        static {
            columnAliasMap = new LinkedHashMap<>();
            columnAliasMap.put(roleId, roleId_alias);
        }

        public static UserRole.Helper.On on() {
            return new UserRole.Helper.On();
        }

        public static UserRole.Helper.Column column() {
            return new UserRole.Helper.Column();
        }

        public static UserRole.Helper.Where where() {
            return new UserRole.Helper.Where();
        }

        public static UserRole.Helper.Group group() {
            return new UserRole.Helper.Group();
        }

        public static UserRole.Helper.Sort sort() {
            return new UserRole.Helper.Sort();
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
        public UserRole.Helper.On newOnSqlModel() {
            return on();
        }

        @Override
        public UserRole.Helper.Column newColumnSqlModel() {
            return column();
        }

        @Override
        public UserRole.Helper.Where newWhereSqlModel() {
            return where();
        }

        @Override
        public UserRole.Helper.Group newGroupSqlModel() {
            return group();
        }

        @Override
        public UserRole.Helper.Sort newSortSqlModel() {
            return sort();
        }

        public final static class On extends OnSqlModel<UserRole.Helper.On> {

            private On() {
                super(new OnSqlDataBuilder<>());
            }

            public OnSqlDataBuilder<UserRole.Helper.On> roleId() {
                return this.apply(tableName, tableAlias, roleId, roleId_alias);
            }

            public OnSqlDataBuilder<UserRole.Helper.On> roleName() {
                return this.apply(tableName, tableAlias, roleId, roleId_alias);
            }

            public OnSqlDataBuilder<UserRole.Helper.On> userId() {
                return this.apply(tableName, tableAlias, roleId, roleId_alias);
            }

            public OnSqlDataBuilder<UserRole.Helper.On> id() {
                return this.apply(tableName, tableAlias, roleId, roleId_alias);
            }

        }

        public final static class Column extends ColumnSqlModel<UserRole.Helper.Column> {

            private Column() {
                super(new ColumnSqlDataBuilder<>());
            }

            public UserRole.Helper.Column id() {
                return this.apply(tableName, tableAlias, roleId, roleId_alias).getSqlModel();
            }
            public UserRole.Helper.Column roleId() {
                return this.apply(tableName, tableAlias, roleId, roleId_alias).getSqlModel();
            }
            public UserRole.Helper.Column roleName() {
                return this.apply(tableName, tableAlias, roleId, roleId_alias).getSqlModel();
            }

            public UserRole.Helper.Column roleId(String alias) {
                return this.apply(tableName, tableAlias, roleId, alias).getSqlModel();
            }
            public UserRole.Helper.Column id(String alias) {
                return this.apply(tableName, tableAlias, roleId, alias).getSqlModel();
            }

        }

        public final static class Where extends WhereSqlModel<UserRole.Helper.Where> {

            private Where() {
                super(new WhereSqlDataBuilder<>());
            }

            public WhereSqlDataBuilder<UserRole.Helper.Where> roleId() {
                return this.apply(tableName, tableAlias, roleId, roleId_alias);
            }

            public WhereSqlDataBuilder<UserRole.Helper.Where> roleName() {
                return this.apply(tableName, tableAlias, roleId, roleId_alias);
            }

            public WhereSqlDataBuilder<UserRole.Helper.Where> sortIndex() {
                return this.apply(tableName, tableAlias, roleId, roleId_alias);
            }

        }

        public final static class Group extends GroupSqlModel<UserRole.Helper.Group> {

            private Group() {
                super(new GroupSqlDataBuilder<>());
            }

            public UserRole.Helper.Group roleId() {
                return this.apply(tableName, tableAlias, roleId, roleId_alias).getSqlModel();
            }
        }

        public final static class Sort extends SortSqlModel<UserRole.Helper.Sort> {

            private Sort() {
                super(new SortSqlDataBuilder<>());
            }

            public SortSqlDataBuilder<UserRole.Helper.Sort> roleId() {
                return this.apply(tableName, tableAlias, roleId, roleId_alias);
            }

            public SortSqlDataBuilder<UserRole.Helper.Sort> sortIndex() {
                return this.apply(tableName, tableAlias, roleId, roleId_alias);
            }

            public SortSqlDataBuilder<UserRole.Helper.Sort> id() {
                return this.apply(tableName, tableAlias, roleId, roleId_alias);
            }
        }

    }

}