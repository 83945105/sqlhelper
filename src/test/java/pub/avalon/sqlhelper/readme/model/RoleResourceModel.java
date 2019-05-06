package pub.avalon.sqlhelper.readme.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.builder.SortDataBuilder;
import pub.avalon.sqlhelper.core.builder.WhereDataBuilder;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class RoleResourceModel implements Model<RoleResourceModel, RoleResourceModel.Column, RoleResourceModel.On, RoleResourceModel.Where, RoleResourceModel.Sort, RoleResourceModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "role_resource";
    /**
     * 表别名
     */
    public static final String tableAlias = "RoleResource";

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
    public static final String resourceId = "resource_id";
    /**
     *
     */
    public static final String resourceId_alias = "resourceId";
    /**
     *
     */
    public static final String resourceName = "resource_name";
    /**
     *
     */
    public static final String resourceName_alias = "resourceName";
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
        COLUMN_ALIAS_MAP.put(roleId, roleId_alias);
        ALIAS_COLUMN_MAP.put(roleId_alias, roleId);
        COLUMN_ALIAS_MAP.put(roleName, roleName_alias);
        ALIAS_COLUMN_MAP.put(roleName_alias, roleName);
        COLUMN_ALIAS_MAP.put(resourceId, resourceId_alias);
        ALIAS_COLUMN_MAP.put(resourceId_alias, resourceId);
        COLUMN_ALIAS_MAP.put(resourceName, resourceName_alias);
        ALIAS_COLUMN_MAP.put(resourceName_alias, resourceName);
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

    public static final class Column extends ColumnModel<RoleResourceModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.modelDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.primaryKeyName, RoleResourceModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         *
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.modelDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.primaryKeyName, alias);
            return this;
        }

        /**
         *
         */
        public Column id() {
            this.modelDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.id, RoleResourceModel.id_alias);
            return this;
        }

        /**
         * @param alias 别名
         */
        public Column id(String alias) {
            this.modelDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.id, alias);
            return this;
        }

        /**
         *
         */
        public Column roleId() {
            this.modelDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.roleId, RoleResourceModel.roleId_alias);
            return this;
        }

        /**
         * @param alias 别名
         */
        public Column roleId(String alias) {
            this.modelDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.roleId, alias);
            return this;
        }

        /**
         *
         */
        public Column roleName() {
            this.modelDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.roleName, RoleResourceModel.roleName_alias);
            return this;
        }

        /**
         * @param alias 别名
         */
        public Column roleName(String alias) {
            this.modelDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.roleName, alias);
            return this;
        }

        /**
         *
         */
        public Column resourceId() {
            this.modelDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.resourceId, RoleResourceModel.resourceId_alias);
            return this;
        }

        /**
         * @param alias 别名
         */
        public Column resourceId(String alias) {
            this.modelDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.resourceId, alias);
            return this;
        }

        /**
         *
         */
        public Column resourceName() {
            this.modelDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.resourceName, RoleResourceModel.resourceName_alias);
            return this;
        }

        /**
         * @param alias 别名
         */
        public Column resourceName(String alias) {
            this.modelDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.resourceName, alias);
            return this;
        }

        /**
         *
         */
        public Column sortIndex() {
            this.modelDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.sortIndex, RoleResourceModel.sortIndex_alias);
            return this;
        }

        /**
         * @param alias 别名
         */
        public Column sortIndex(String alias) {
            this.modelDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.sortIndex, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<RoleResourceModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<RoleResourceModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.primaryKeyName);
        }

        /**
         *
         */
        public OnBuilder<RoleResourceModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.id);
        }

        /**
         *
         */
        public OnBuilder<RoleResourceModel, Column, On, Where, Sort, Group> roleId() {
            return this.onBuilder.handler(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.roleId);
        }

        /**
         *
         */
        public OnBuilder<RoleResourceModel, Column, On, Where, Sort, Group> roleName() {
            return this.onBuilder.handler(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.roleName);
        }

        /**
         *
         */
        public OnBuilder<RoleResourceModel, Column, On, Where, Sort, Group> resourceId() {
            return this.onBuilder.handler(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.resourceId);
        }

        /**
         *
         */
        public OnBuilder<RoleResourceModel, Column, On, Where, Sort, Group> resourceName() {
            return this.onBuilder.handler(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.resourceName);
        }

        /**
         *
         */
        public OnBuilder<RoleResourceModel, Column, On, Where, Sort, Group> sortIndex() {
            return this.onBuilder.handler(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.sortIndex);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<RoleResourceModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereDataBuilder<RoleResourceModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.primaryKeyName, RoleResourceModel.primaryKeyAlias);
        }

        /**
         *
         */
        public WhereDataBuilder<RoleResourceModel, Column, On, Where, Sort, Group> id() {
            return this.whereDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.id, RoleResourceModel.id_alias);
        }

        /**
         *
         */
        public WhereDataBuilder<RoleResourceModel, Column, On, Where, Sort, Group> roleId() {
            return this.whereDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.roleId, RoleResourceModel.roleId_alias);
        }

        /**
         *
         */
        public WhereDataBuilder<RoleResourceModel, Column, On, Where, Sort, Group> roleName() {
            return this.whereDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.roleName, RoleResourceModel.roleName_alias);
        }

        /**
         *
         */
        public WhereDataBuilder<RoleResourceModel, Column, On, Where, Sort, Group> resourceId() {
            return this.whereDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.resourceId, RoleResourceModel.resourceId_alias);
        }

        /**
         *
         */
        public WhereDataBuilder<RoleResourceModel, Column, On, Where, Sort, Group> resourceName() {
            return this.whereDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.resourceName, RoleResourceModel.resourceName_alias);
        }

        /**
         *
         */
        public WhereDataBuilder<RoleResourceModel, Column, On, Where, Sort, Group> sortIndex() {
            return this.whereDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.sortIndex, RoleResourceModel.sortIndex_alias);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<RoleResourceModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.groupDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.primaryKeyName, RoleResourceModel.primaryKeyAlias);
            return this;
        }

        /**
         *
         */
        public Group id() {
            this.groupDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.id, RoleResourceModel.id_alias);
            return this;
        }

        /**
         *
         */
        public Group roleId() {
            this.groupDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.roleId, RoleResourceModel.roleId_alias);
            return this;
        }

        /**
         *
         */
        public Group roleName() {
            this.groupDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.roleName, RoleResourceModel.roleName_alias);
            return this;
        }

        /**
         *
         */
        public Group resourceId() {
            this.groupDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.resourceId, RoleResourceModel.resourceId_alias);
            return this;
        }

        /**
         *
         */
        public Group resourceName() {
            this.groupDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.resourceName, RoleResourceModel.resourceName_alias);
            return this;
        }

        /**
         *
         */
        public Group sortIndex() {
            this.groupDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.sortIndex, RoleResourceModel.sortIndex_alias);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<RoleResourceModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortDataBuilder<RoleResourceModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.primaryKeyName, RoleResourceModel.primaryKeyAlias);
        }

        /**
         *
         */
        public SortDataBuilder<RoleResourceModel, Column, On, Where, Sort, Group> id() {
            return this.sortDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.id, RoleResourceModel.id_alias);
        }

        /**
         *
         */
        public SortDataBuilder<RoleResourceModel, Column, On, Where, Sort, Group> roleId() {
            return this.sortDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.roleId, RoleResourceModel.roleId_alias);
        }

        /**
         *
         */
        public SortDataBuilder<RoleResourceModel, Column, On, Where, Sort, Group> roleName() {
            return this.sortDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.roleName, RoleResourceModel.roleName_alias);
        }

        /**
         *
         */
        public SortDataBuilder<RoleResourceModel, Column, On, Where, Sort, Group> resourceId() {
            return this.sortDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.resourceId, RoleResourceModel.resourceId_alias);
        }

        /**
         *
         */
        public SortDataBuilder<RoleResourceModel, Column, On, Where, Sort, Group> resourceName() {
            return this.sortDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.resourceName, RoleResourceModel.resourceName_alias);
        }

        /**
         *
         */
        public SortDataBuilder<RoleResourceModel, Column, On, Where, Sort, Group> sortIndex() {
            return this.sortDataBuilder.apply(RoleResourceModel.tableName, RoleResourceModel.tableAlias, RoleResourceModel.sortIndex, RoleResourceModel.sortIndex_alias);
        }

    }

}