package pub.avalon.sqlhelper.readme.entity;

import pub.avalon.sqlhelper.core.modelbuilder.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class RoleResource {

    private String id;

    public String getId() {
        return id;
    }

    public RoleResource setId(String id) {
        this.id = id == null ? null : id.trim();
        return this;
    }

    public static class Helper implements TableModel<Helper, Helper.On, Helper.Column, Helper.Where, Helper.Group, Helper.Sort> {

        public final static String tableName = "role_resource";
        public final static String tableAlias = "RoleResource";
        public final static String primaryKeyName = "id";
        public final static String primaryKeyAlias = "id";
        public final static String id = "id";
        public final static String id_alias = "id";

        public final static Map<String, String> columnAliasMap;

        static {
            columnAliasMap = new LinkedHashMap<>();
            columnAliasMap.put(id, id_alias);
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
        public On newOnSqlModel() {
            return on();
        }

        @Override
        public Column newColumnSqlModel() {
            return column();
        }

        @Override
        public Where newWhereSqlModel() {
            return where();
        }

        @Override
        public Group newGroupSqlModel() {
            return group();
        }

        @Override
        public Sort newSortSqlModel() {
            return sort();
        }

        public final static class On extends OnSqlModel<On> {

            private On() {
                super(new OnSqlDataBuilder<>());
            }

            public OnSqlDataBuilder<On> id() {
                return this.apply(tableName, tableAlias, id, id_alias);
            }

        }

        public final static class Column extends ColumnSqlModel<Column> {

            private Column() {
                super(new ColumnSqlDataBuilder<>());
            }

            public Column id() {
                return this.apply(tableName, tableAlias, id, id_alias).getSqlModel();
            }

            public Column id(String alias) {
                return this.apply(tableName, tableAlias, id, alias).getSqlModel();
            }

        }

        public final static class Where extends WhereSqlModel<Where> {

            private Where() {
                super(new WhereSqlDataBuilder<>());
            }

            public WhereSqlDataBuilder<Where> id() {
                return this.apply(tableName, tableAlias, id, id_alias);
            }

        }

        public final static class Group extends GroupSqlModel<Group> {

            private Group() {
                super(new GroupSqlDataBuilder<>());
            }

            public Group id() {
                return this.apply(tableName, tableAlias, id, id_alias).getSqlModel();
            }
        }

        public final static class Sort extends SortSqlModel<Sort> {

            private Sort() {
                super(new SortSqlDataBuilder<>());
            }

            public SortSqlDataBuilder<Sort> id() {
                return this.apply(tableName, tableAlias, id, id_alias);
            }
        }

    }

}