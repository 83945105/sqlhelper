package pub.avalon.sqlhelper.readme.entity;

import pub.avalon.sqlhelper.core.modelbuilder.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class SysUser {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public SysUser setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String loginName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public static class Helper implements TableModel<SysUser.Helper, SysUser.Helper.On, SysUser.Helper.Column, SysUser.Helper.Where, SysUser.Helper.Group, SysUser.Helper.Sort> {

        public final static String tableName = "role_resource";
        public final static String tableAlias = "SysUser";
        public final static String primaryKeyName = "userName";
        public final static String primaryKeyAlias = "userName";
        public final static String userName = "userName";
        public final static String id_alias = "userName";

        public final static Map<String, String> columnAliasMap;

        static {
            columnAliasMap = new LinkedHashMap<>();
            columnAliasMap.put(userName, id_alias);
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
        public SysUser.Helper.On newOnSqlModel() {
            return on();
        }

        @Override
        public SysUser.Helper.Column newColumnSqlModel() {
            return column();
        }

        @Override
        public SysUser.Helper.Where newWhereSqlModel() {
            return where();
        }

        @Override
        public SysUser.Helper.Group newGroupSqlModel() {
            return group();
        }

        @Override
        public SysUser.Helper.Sort newSortSqlModel() {
            return sort();
        }

        public final static class On extends OnSqlModel<SysUser.Helper.On> {

            private On() {
                super(new OnSqlDataBuilder<>());
            }

            public OnSqlDataBuilder<SysUser.Helper.On> userName() {
                return this.apply(tableName, tableAlias, userName, id_alias);
            }

            public OnSqlDataBuilder<SysUser.Helper.On> id() {
                return this.apply(tableName, tableAlias, userName, id_alias);
            }

            public OnSqlDataBuilder<SysUser.Helper.On> primaryKey() {
                return this.apply(tableName, tableAlias, userName, id_alias);
            }

            public OnSqlDataBuilder<SysUser.Helper.On> loginName() {
                return this.apply(tableName, tableAlias, userName, id_alias);
            }

        }

        public final static class Column extends ColumnSqlModel<SysUser.Helper.Column> {

            private Column() {
                super(new ColumnSqlDataBuilder<>());
            }

            public SysUser.Helper.Column id() {
                return this.apply(tableName, tableAlias, userName, id_alias).getSqlModel();
            }

            public SysUser.Helper.Column userName() {
                return this.apply(tableName, tableAlias, userName, id_alias).getSqlModel();
            }

            public SysUser.Helper.Column userName(String alias) {
                return this.apply(tableName, tableAlias, userName, alias).getSqlModel();
            }
            public SysUser.Helper.Column id(String alias) {
                return this.apply(tableName, tableAlias, userName, alias).getSqlModel();
            }

            public SysUser.Helper.Column loginName() {
                return this.apply(tableName, tableAlias, userName, id_alias).getSqlModel();
            }

        }

        public final static class Where extends WhereSqlModel<SysUser.Helper.Where> {

            private Where() {
                super(new WhereSqlDataBuilder<>());
            }

            public WhereSqlDataBuilder<SysUser.Helper.Where> userName() {
                return this.apply(tableName, tableAlias, userName, id_alias);
            }

            public WhereSqlDataBuilder<SysUser.Helper.Where> loginName() {
                return this.apply(tableName, tableAlias, userName, id_alias);
            }

            public WhereSqlDataBuilder<SysUser.Helper.Where> id() {
                return this.apply(tableName, tableAlias, userName, id_alias);
            }

        }

        public final static class Group extends GroupSqlModel<SysUser.Helper.Group> {

            private Group() {
                super(new GroupSqlDataBuilder<>());
            }

            public SysUser.Helper.Group userName() {
                return this.apply(tableName, tableAlias, userName, id_alias).getSqlModel();
            }

            public SysUser.Helper.Group id() {
                return this.apply(tableName, tableAlias, userName, id_alias).getSqlModel();
            }

            public SysUser.Helper.Group loginName() {
                return this.apply(tableName, tableAlias, userName, id_alias).getSqlModel();
            }
        }

        public final static class Sort extends SortSqlModel<SysUser.Helper.Sort> {

            private Sort() {
                super(new SortSqlDataBuilder<>());
            }

            public SortSqlDataBuilder<SysUser.Helper.Sort> userName() {
                return this.apply(tableName, tableAlias, userName, id_alias);
            }

            public SortSqlDataBuilder<SysUser.Helper.Sort> id() {
                return this.apply(tableName, tableAlias, userName, id_alias);
            }
        }

    }

}