package pub.avalon.sqlhelper.readme.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class UserRoleModel implements Model<UserRoleModel, UserRoleModel.Column, UserRoleModel.On, UserRoleModel.Where, UserRoleModel.Sort, UserRoleModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "user_role";
    /**
     * 表别名
     */
    public static final String tableAlias = "UserRole";

    /**
     * 主键名
     */
    public static final String primaryKeyName = "id";
    /**
     * 主键别名
     */
    public static final String primaryKeyAlias = "id";


    /**
     *
     */
    public static final String id = "id";
    /**
     *
     */
    public static final String id_alias = "id";
    /**
     *
     */
    public static final String userId = "user_id";
    /**
     *
     */
    public static final String userId_alias = "userId";
    /**
     *
     */
    public static final String roleId = "role_id";
    /**
     *
     */
    public static final String roleId_alias = "roleId";
    /**
     *
     */
    public static final String roleName = "role_name";
    /**
     *
     */
    public static final String roleName_alias = "roleName";
    /**
     *
     */
    public static final String sortIndex = "sort_index";
    /**
     *
     */
    public static final String sortIndex_alias = "sortIndex";

    /**
     * 字段-别名 集合
     */
    public static final Map<String, String> COLUMN_ALIAS_MAP = new LinkedHashMap<>();
    /**
     * 别名-字段 集合
     */
    public static final Map<String, String> ALIAS_COLUMN_MAP = new LinkedHashMap<>();

    static {
        COLUMN_ALIAS_MAP.put(id, id_alias);
        ALIAS_COLUMN_MAP.put(id_alias, id);
        COLUMN_ALIAS_MAP.put(userId, userId_alias);
        ALIAS_COLUMN_MAP.put(userId_alias, userId);
        COLUMN_ALIAS_MAP.put(roleId, roleId_alias);
        ALIAS_COLUMN_MAP.put(roleId_alias, roleId);
        COLUMN_ALIAS_MAP.put(roleName, roleName_alias);
        ALIAS_COLUMN_MAP.put(roleName_alias, roleName);
        COLUMN_ALIAS_MAP.put(sortIndex, sortIndex_alias);
        ALIAS_COLUMN_MAP.put(sortIndex_alias, sortIndex);
    }

    /**
     * 表名
     */
    @Override
    public String getTableName() {
        return tableName;
    }

    /**
     * 表别名
     */
    @Override
    public String getTableAlias() {
        return tableAlias;
    }

    /**
     * 主键名
     */
    @Override
    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    /**
     * 主键别名
     */
    @Override
    public String getPrimaryKeyAlias() {
        return primaryKeyAlias;
    }

    /**
     * 列名-别名 集合
     */
    @Override
    public Map<String, String> getColumnAliasMap() {
        return COLUMN_ALIAS_MAP;
    }

    /**
     * 别名-列名 集合
     */
    @Override
    public Map<String, String> getAliasColumnMap() {
        return ALIAS_COLUMN_MAP;
    }

    @Override
    public Column getColumnModel() {
        return new Column();
    }

    public static final class Column extends ColumnModel<UserRoleModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.modelDataBuilder.apply(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.primaryKeyName, UserRoleModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         *
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.modelDataBuilder.apply(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.primaryKeyName, alias);
            return this;
        }

        /**
         *
         */
        public Column id() {
            this.modelDataBuilder.apply(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.id, UserRoleModel.id_alias);
            return this;
        }

        /**
         * @param alias 别名
         */
        public Column id(String alias) {
            this.modelDataBuilder.apply(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.id, alias);
            return this;
        }

        /**
         *
         */
        public Column userId() {
            this.modelDataBuilder.apply(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.userId, UserRoleModel.userId_alias);
            return this;
        }

        /**
         * @param alias 别名
         */
        public Column userId(String alias) {
            this.modelDataBuilder.apply(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.userId, alias);
            return this;
        }

        /**
         *
         */
        public Column roleId() {
            this.modelDataBuilder.apply(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.roleId, UserRoleModel.roleId_alias);
            return this;
        }

        /**
         * @param alias 别名
         */
        public Column roleId(String alias) {
            this.modelDataBuilder.apply(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.roleId, alias);
            return this;
        }

        /**
         *
         */
        public Column roleName() {
            this.modelDataBuilder.apply(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.roleName, UserRoleModel.roleName_alias);
            return this;
        }

        /**
         * @param alias 别名
         */
        public Column roleName(String alias) {
            this.modelDataBuilder.apply(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.roleName, alias);
            return this;
        }

        /**
         *
         */
        public Column sortIndex() {
            this.modelDataBuilder.apply(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.sortIndex, UserRoleModel.sortIndex_alias);
            return this;
        }

        /**
         * @param alias 别名
         */
        public Column sortIndex(String alias) {
            this.modelDataBuilder.apply(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.sortIndex, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<UserRoleModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<UserRoleModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.primaryKeyName);
        }

        /**
         *
         */
        public OnBuilder<UserRoleModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.id);
        }

        /**
         *
         */
        public OnBuilder<UserRoleModel, Column, On, Where, Sort, Group> userId() {
            return this.onBuilder.handler(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.userId);
        }

        /**
         *
         */
        public OnBuilder<UserRoleModel, Column, On, Where, Sort, Group> roleId() {
            return this.onBuilder.handler(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.roleId);
        }

        /**
         *
         */
        public OnBuilder<UserRoleModel, Column, On, Where, Sort, Group> roleName() {
            return this.onBuilder.handler(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.roleName);
        }

        /**
         *
         */
        public OnBuilder<UserRoleModel, Column, On, Where, Sort, Group> sortIndex() {
            return this.onBuilder.handler(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.sortIndex);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<UserRoleModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<UserRoleModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.primaryKeyName);
        }

        /**
         *
         */
        public WhereBuilder<UserRoleModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.id);
        }

        /**
         *
         */
        public WhereBuilder<UserRoleModel, Column, On, Where, Sort, Group> userId() {
            return this.whereBuilder.handler(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.userId);
        }

        /**
         *
         */
        public WhereBuilder<UserRoleModel, Column, On, Where, Sort, Group> roleId() {
            return this.whereBuilder.handler(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.roleId);
        }

        /**
         *
         */
        public WhereBuilder<UserRoleModel, Column, On, Where, Sort, Group> roleName() {
            return this.whereBuilder.handler(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.roleName);
        }

        /**
         *
         */
        public WhereBuilder<UserRoleModel, Column, On, Where, Sort, Group> sortIndex() {
            return this.whereBuilder.handler(UserRoleModel.tableName, UserRoleModel.tableAlias, UserRoleModel.sortIndex);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<UserRoleModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(UserRoleModel.primaryKeyName);
            return this;
        }

        /**
         *
         */
        public Group id() {
            this.addColumn(UserRoleModel.id);
            return this;
        }

        /**
         *
         */
        public Group userId() {
            this.addColumn(UserRoleModel.userId);
            return this;
        }

        /**
         *
         */
        public Group roleId() {
            this.addColumn(UserRoleModel.roleId);
            return this;
        }

        /**
         *
         */
        public Group roleName() {
            this.addColumn(UserRoleModel.roleName);
            return this;
        }

        /**
         *
         */
        public Group sortIndex() {
            this.addColumn(UserRoleModel.sortIndex);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<UserRoleModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<UserRoleModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(UserRoleModel.primaryKeyName);
        }

        /**
         *
         */
        public SortBuilder<UserRoleModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(UserRoleModel.id);
        }

        /**
         *
         */
        public SortBuilder<UserRoleModel, Column, On, Where, Sort, Group> userId() {
            return this.sortBuilder.handler(UserRoleModel.userId);
        }

        /**
         *
         */
        public SortBuilder<UserRoleModel, Column, On, Where, Sort, Group> roleId() {
            return this.sortBuilder.handler(UserRoleModel.roleId);
        }

        /**
         *
         */
        public SortBuilder<UserRoleModel, Column, On, Where, Sort, Group> roleName() {
            return this.sortBuilder.handler(UserRoleModel.roleName);
        }

        /**
         *
         */
        public SortBuilder<UserRoleModel, Column, On, Where, Sort, Group> sortIndex() {
            return this.sortBuilder.handler(UserRoleModel.sortIndex);
        }

    }

}