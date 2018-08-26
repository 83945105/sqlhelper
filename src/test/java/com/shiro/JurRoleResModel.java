package com.shiro;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

public final class JurRoleResModel implements Model<JurRoleResModel, JurRoleResModel.Column, JurRoleResModel.On, JurRoleResModel.Where, JurRoleResModel.Sort, JurRoleResModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "jur_role_res";
    /**
     * 表别名
     */
    public static final String tableAlias = "JurRoleRes";

    /**
     * 主键名
     */
    public static final String primaryKeyName = "id";
    /**
     * 主键别名
     */
    public static final String primaryKeyAlias = "id";



    /**
     * ID-c:hidden
     */
    public static final String id = "id";
    /**
     * ID-c:hidden
     */
    public static final String id_alias = "id";
    /**
     * 角色id
     */
    public static final String roleId = "role_id";
    /**
     * 角色id
     */
    public static final String roleId_alias = "roleId";
    /**
     * 角色
     */
    public static final String role = "role";
    /**
     * 角色
     */
    public static final String role_alias = "role";
    /**
     * 角色名称
     */
    public static final String roleName = "role_name";
    /**
     * 角色名称
     */
    public static final String roleName_alias = "roleName";
    /**
     * 角色类型
     */
    public static final String roleType = "role_type";
    /**
     * 角色类型
     */
    public static final String roleType_alias = "roleType";
    /**
     * 资源id
     */
    public static final String resId = "res_id";
    /**
     * 资源id
     */
    public static final String resId_alias = "resId";
    /**
     * 资源名称
     */
    public static final String resName = "res_name";
    /**
     * 资源名称
     */
    public static final String resName_alias = "resName";
    /**
     * 资源地址
     */
    public static final String resUrl = "res_url";
    /**
     * 资源地址
     */
    public static final String resUrl_alias = "resUrl";
    /**
     * 资源类型
     */
    public static final String resType = "res_type";
    /**
     * 资源类型
     */
    public static final String resType_alias = "resType";
    /**
     * 
     */
    public static final String index = "index";
    /**
     * 
     */
    public static final String index_alias = "index";
    /**
     * 状态(00:正常,01:删除)-d:0
     */
    public static final String status = "status";
    /**
     * 状态(00:正常,01:删除)-d:0
     */
    public static final String status_alias = "status";
    /**
     * 创建时间
     */
    public static final String createTime = "create_time";
    /**
     * 创建时间
     */
    public static final String createTime_alias = "createTime";
    /**
     * 修改时间
     */
    public static final String updateTime = "update_time";
    /**
     * 修改时间
     */
    public static final String updateTime_alias = "updateTime";
    /**
     * 删除时间
     */
    public static final String deleteTime = "delete_time";
    /**
     * 删除时间
     */
    public static final String deleteTime_alias = "deleteTime";
    /**
     * 创建时间
     */
    public static final String createTimeStamp = "create_time_stamp";
    /**
     * 创建时间
     */
    public static final String createTimeStamp_alias = "createTimeStamp";
    /**
     * 修改时间
     */
    public static final String updateTimeStamp = "update_time_stamp";
    /**
     * 修改时间
     */
    public static final String updateTimeStamp_alias = "updateTimeStamp";
    /**
     * 删除时间
     */
    public static final String deleteTimeStamp = "delete_time_stamp";
    /**
     * 删除时间
     */
    public static final String deleteTimeStamp_alias = "deleteTimeStamp";

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
        COLUMN_ALIAS_MAP.put(role, role_alias);
        ALIAS_COLUMN_MAP.put(role_alias, role);
        COLUMN_ALIAS_MAP.put(roleName, roleName_alias);
        ALIAS_COLUMN_MAP.put(roleName_alias, roleName);
        COLUMN_ALIAS_MAP.put(roleType, roleType_alias);
        ALIAS_COLUMN_MAP.put(roleType_alias, roleType);
        COLUMN_ALIAS_MAP.put(resId, resId_alias);
        ALIAS_COLUMN_MAP.put(resId_alias, resId);
        COLUMN_ALIAS_MAP.put(resName, resName_alias);
        ALIAS_COLUMN_MAP.put(resName_alias, resName);
        COLUMN_ALIAS_MAP.put(resUrl, resUrl_alias);
        ALIAS_COLUMN_MAP.put(resUrl_alias, resUrl);
        COLUMN_ALIAS_MAP.put(resType, resType_alias);
        ALIAS_COLUMN_MAP.put(resType_alias, resType);
        COLUMN_ALIAS_MAP.put(index, index_alias);
        ALIAS_COLUMN_MAP.put(index_alias, index);
        COLUMN_ALIAS_MAP.put(status, status_alias);
        ALIAS_COLUMN_MAP.put(status_alias, status);
        COLUMN_ALIAS_MAP.put(createTime, createTime_alias);
        ALIAS_COLUMN_MAP.put(createTime_alias, createTime);
        COLUMN_ALIAS_MAP.put(updateTime, updateTime_alias);
        ALIAS_COLUMN_MAP.put(updateTime_alias, updateTime);
        COLUMN_ALIAS_MAP.put(deleteTime, deleteTime_alias);
        ALIAS_COLUMN_MAP.put(deleteTime_alias, deleteTime);
        COLUMN_ALIAS_MAP.put(createTimeStamp, createTimeStamp_alias);
        ALIAS_COLUMN_MAP.put(createTimeStamp_alias, createTimeStamp);
        COLUMN_ALIAS_MAP.put(updateTimeStamp, updateTimeStamp_alias);
        ALIAS_COLUMN_MAP.put(updateTimeStamp_alias, updateTimeStamp);
        COLUMN_ALIAS_MAP.put(deleteTimeStamp, deleteTimeStamp_alias);
        ALIAS_COLUMN_MAP.put(deleteTimeStamp_alias, deleteTimeStamp);
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
    public ColumnModel<JurRoleResModel, Column, On, Where, Sort, Group> getColumnModel() {
        return new Column();
    }

    public static final class Column extends ColumnModel<JurRoleResModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(JurRoleResModel.primaryKeyName, JurRoleResModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(JurRoleResModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * ID-c:hidden
         */
        public Column id() {
            this.addColumnAlias(JurRoleResModel.id, JurRoleResModel.id_alias);
            return this;
        }

        /**
         * ID-c:hidden
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(JurRoleResModel.id, alias);
            return this;
        }

        /**
         * 角色id
         */
        public Column roleId() {
            this.addColumnAlias(JurRoleResModel.roleId, JurRoleResModel.roleId_alias);
            return this;
        }

        /**
         * 角色id
         * @param alias 别名
         */
        public Column roleId(String alias) {
            this.addColumnAlias(JurRoleResModel.roleId, alias);
            return this;
        }

        /**
         * 角色
         */
        public Column role() {
            this.addColumnAlias(JurRoleResModel.role, JurRoleResModel.role_alias);
            return this;
        }

        /**
         * 角色
         * @param alias 别名
         */
        public Column role(String alias) {
            this.addColumnAlias(JurRoleResModel.role, alias);
            return this;
        }

        /**
         * 角色名称
         */
        public Column roleName() {
            this.addColumnAlias(JurRoleResModel.roleName, JurRoleResModel.roleName_alias);
            return this;
        }

        /**
         * 角色名称
         * @param alias 别名
         */
        public Column roleName(String alias) {
            this.addColumnAlias(JurRoleResModel.roleName, alias);
            return this;
        }

        /**
         * 角色类型
         */
        public Column roleType() {
            this.addColumnAlias(JurRoleResModel.roleType, JurRoleResModel.roleType_alias);
            return this;
        }

        /**
         * 角色类型
         * @param alias 别名
         */
        public Column roleType(String alias) {
            this.addColumnAlias(JurRoleResModel.roleType, alias);
            return this;
        }

        /**
         * 资源id
         */
        public Column resId() {
            this.addColumnAlias(JurRoleResModel.resId, JurRoleResModel.resId_alias);
            return this;
        }

        /**
         * 资源id
         * @param alias 别名
         */
        public Column resId(String alias) {
            this.addColumnAlias(JurRoleResModel.resId, alias);
            return this;
        }

        /**
         * 资源名称
         */
        public Column resName() {
            this.addColumnAlias(JurRoleResModel.resName, JurRoleResModel.resName_alias);
            return this;
        }

        /**
         * 资源名称
         * @param alias 别名
         */
        public Column resName(String alias) {
            this.addColumnAlias(JurRoleResModel.resName, alias);
            return this;
        }

        /**
         * 资源地址
         */
        public Column resUrl() {
            this.addColumnAlias(JurRoleResModel.resUrl, JurRoleResModel.resUrl_alias);
            return this;
        }

        /**
         * 资源地址
         * @param alias 别名
         */
        public Column resUrl(String alias) {
            this.addColumnAlias(JurRoleResModel.resUrl, alias);
            return this;
        }

        /**
         * 资源类型
         */
        public Column resType() {
            this.addColumnAlias(JurRoleResModel.resType, JurRoleResModel.resType_alias);
            return this;
        }

        /**
         * 资源类型
         * @param alias 别名
         */
        public Column resType(String alias) {
            this.addColumnAlias(JurRoleResModel.resType, alias);
            return this;
        }

        /**
         * 
         */
        public Column index() {
            this.addColumnAlias(JurRoleResModel.index, JurRoleResModel.index_alias);
            return this;
        }

        /**
         * 
         * @param alias 别名
         */
        public Column index(String alias) {
            this.addColumnAlias(JurRoleResModel.index, alias);
            return this;
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public Column status() {
            this.addColumnAlias(JurRoleResModel.status, JurRoleResModel.status_alias);
            return this;
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(JurRoleResModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(JurRoleResModel.createTime, JurRoleResModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(JurRoleResModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(JurRoleResModel.updateTime, JurRoleResModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(JurRoleResModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(JurRoleResModel.deleteTime, JurRoleResModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(JurRoleResModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTimeStamp() {
            this.addColumnAlias(JurRoleResModel.createTimeStamp, JurRoleResModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(JurRoleResModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(JurRoleResModel.updateTimeStamp, JurRoleResModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(JurRoleResModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(JurRoleResModel.deleteTimeStamp, JurRoleResModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(JurRoleResModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public OnModel<JurRoleResModel, Column, On, Where, Sort, Group> getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<JurRoleResModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<JurRoleResModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.primaryKeyName);
        }
    
        /**
         * ID-c:hidden
         */
        public OnBuilder<JurRoleResModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.id);
        }

        /**
         * 角色id
         */
        public OnBuilder<JurRoleResModel, Column, On, Where, Sort, Group> roleId() {
            return this.onBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.roleId);
        }

        /**
         * 角色
         */
        public OnBuilder<JurRoleResModel, Column, On, Where, Sort, Group> role() {
            return this.onBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.role);
        }

        /**
         * 角色名称
         */
        public OnBuilder<JurRoleResModel, Column, On, Where, Sort, Group> roleName() {
            return this.onBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.roleName);
        }

        /**
         * 角色类型
         */
        public OnBuilder<JurRoleResModel, Column, On, Where, Sort, Group> roleType() {
            return this.onBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.roleType);
        }

        /**
         * 资源id
         */
        public OnBuilder<JurRoleResModel, Column, On, Where, Sort, Group> resId() {
            return this.onBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.resId);
        }

        /**
         * 资源名称
         */
        public OnBuilder<JurRoleResModel, Column, On, Where, Sort, Group> resName() {
            return this.onBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.resName);
        }

        /**
         * 资源地址
         */
        public OnBuilder<JurRoleResModel, Column, On, Where, Sort, Group> resUrl() {
            return this.onBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.resUrl);
        }

        /**
         * 资源类型
         */
        public OnBuilder<JurRoleResModel, Column, On, Where, Sort, Group> resType() {
            return this.onBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.resType);
        }

        /**
         * 
         */
        public OnBuilder<JurRoleResModel, Column, On, Where, Sort, Group> index() {
            return this.onBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.index);
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public OnBuilder<JurRoleResModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<JurRoleResModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<JurRoleResModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<JurRoleResModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public OnBuilder<JurRoleResModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.createTimeStamp);
        }

        /**
         * 修改时间
         */
        public OnBuilder<JurRoleResModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.updateTimeStamp);
        }

        /**
         * 删除时间
         */
        public OnBuilder<JurRoleResModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<JurRoleResModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<JurRoleResModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.primaryKeyName);
        }
    
        /**
         * ID-c:hidden
         */
        public WhereBuilder<JurRoleResModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.id);
        }

        /**
         * 角色id
         */
        public WhereBuilder<JurRoleResModel, Column, On, Where, Sort, Group> roleId() {
            return this.whereBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.roleId);
        }

        /**
         * 角色
         */
        public WhereBuilder<JurRoleResModel, Column, On, Where, Sort, Group> role() {
            return this.whereBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.role);
        }

        /**
         * 角色名称
         */
        public WhereBuilder<JurRoleResModel, Column, On, Where, Sort, Group> roleName() {
            return this.whereBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.roleName);
        }

        /**
         * 角色类型
         */
        public WhereBuilder<JurRoleResModel, Column, On, Where, Sort, Group> roleType() {
            return this.whereBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.roleType);
        }

        /**
         * 资源id
         */
        public WhereBuilder<JurRoleResModel, Column, On, Where, Sort, Group> resId() {
            return this.whereBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.resId);
        }

        /**
         * 资源名称
         */
        public WhereBuilder<JurRoleResModel, Column, On, Where, Sort, Group> resName() {
            return this.whereBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.resName);
        }

        /**
         * 资源地址
         */
        public WhereBuilder<JurRoleResModel, Column, On, Where, Sort, Group> resUrl() {
            return this.whereBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.resUrl);
        }

        /**
         * 资源类型
         */
        public WhereBuilder<JurRoleResModel, Column, On, Where, Sort, Group> resType() {
            return this.whereBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.resType);
        }

        /**
         * 
         */
        public WhereBuilder<JurRoleResModel, Column, On, Where, Sort, Group> index() {
            return this.whereBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.index);
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public WhereBuilder<JurRoleResModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<JurRoleResModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<JurRoleResModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<JurRoleResModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<JurRoleResModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.createTimeStamp);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<JurRoleResModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.updateTimeStamp);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<JurRoleResModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(JurRoleResModel.tableName, JurRoleResModel.tableAlias, JurRoleResModel.deleteTimeStamp);
        }

    }

    @Override
    public GroupModel<JurRoleResModel, Column, On, Where, Sort, Group> getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<JurRoleResModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(JurRoleResModel.primaryKeyName);
            return this;
        }
    
        /**
         * ID-c:hidden
         */
        public Group id() {
            this.addColumn(JurRoleResModel.id);
            return this;
        }

        /**
         * 角色id
         */
        public Group roleId() {
            this.addColumn(JurRoleResModel.roleId);
            return this;
        }

        /**
         * 角色
         */
        public Group role() {
            this.addColumn(JurRoleResModel.role);
            return this;
        }

        /**
         * 角色名称
         */
        public Group roleName() {
            this.addColumn(JurRoleResModel.roleName);
            return this;
        }

        /**
         * 角色类型
         */
        public Group roleType() {
            this.addColumn(JurRoleResModel.roleType);
            return this;
        }

        /**
         * 资源id
         */
        public Group resId() {
            this.addColumn(JurRoleResModel.resId);
            return this;
        }

        /**
         * 资源名称
         */
        public Group resName() {
            this.addColumn(JurRoleResModel.resName);
            return this;
        }

        /**
         * 资源地址
         */
        public Group resUrl() {
            this.addColumn(JurRoleResModel.resUrl);
            return this;
        }

        /**
         * 资源类型
         */
        public Group resType() {
            this.addColumn(JurRoleResModel.resType);
            return this;
        }

        /**
         * 
         */
        public Group index() {
            this.addColumn(JurRoleResModel.index);
            return this;
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public Group status() {
            this.addColumn(JurRoleResModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(JurRoleResModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(JurRoleResModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(JurRoleResModel.deleteTime);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTimeStamp() {
            this.addColumn(JurRoleResModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTimeStamp() {
            this.addColumn(JurRoleResModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTimeStamp() {
            this.addColumn(JurRoleResModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public SortModel<JurRoleResModel, Column, On, Where, Sort, Group> getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<JurRoleResModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<JurRoleResModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(JurRoleResModel.primaryKeyName);
        }
    
        /**
         * ID-c:hidden
         */
        public SortBuilder<JurRoleResModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(JurRoleResModel.id);
        }

        /**
         * 角色id
         */
        public SortBuilder<JurRoleResModel, Column, On, Where, Sort, Group> roleId() {
            return this.sortBuilder.handler(JurRoleResModel.roleId);
        }

        /**
         * 角色
         */
        public SortBuilder<JurRoleResModel, Column, On, Where, Sort, Group> role() {
            return this.sortBuilder.handler(JurRoleResModel.role);
        }

        /**
         * 角色名称
         */
        public SortBuilder<JurRoleResModel, Column, On, Where, Sort, Group> roleName() {
            return this.sortBuilder.handler(JurRoleResModel.roleName);
        }

        /**
         * 角色类型
         */
        public SortBuilder<JurRoleResModel, Column, On, Where, Sort, Group> roleType() {
            return this.sortBuilder.handler(JurRoleResModel.roleType);
        }

        /**
         * 资源id
         */
        public SortBuilder<JurRoleResModel, Column, On, Where, Sort, Group> resId() {
            return this.sortBuilder.handler(JurRoleResModel.resId);
        }

        /**
         * 资源名称
         */
        public SortBuilder<JurRoleResModel, Column, On, Where, Sort, Group> resName() {
            return this.sortBuilder.handler(JurRoleResModel.resName);
        }

        /**
         * 资源地址
         */
        public SortBuilder<JurRoleResModel, Column, On, Where, Sort, Group> resUrl() {
            return this.sortBuilder.handler(JurRoleResModel.resUrl);
        }

        /**
         * 资源类型
         */
        public SortBuilder<JurRoleResModel, Column, On, Where, Sort, Group> resType() {
            return this.sortBuilder.handler(JurRoleResModel.resType);
        }

        /**
         * 
         */
        public SortBuilder<JurRoleResModel, Column, On, Where, Sort, Group> index() {
            return this.sortBuilder.handler(JurRoleResModel.index);
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public SortBuilder<JurRoleResModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(JurRoleResModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<JurRoleResModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(JurRoleResModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<JurRoleResModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(JurRoleResModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<JurRoleResModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(JurRoleResModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public SortBuilder<JurRoleResModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(JurRoleResModel.createTimeStamp);
        }

        /**
         * 修改时间
         */
        public SortBuilder<JurRoleResModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(JurRoleResModel.updateTimeStamp);
        }

        /**
         * 删除时间
         */
        public SortBuilder<JurRoleResModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(JurRoleResModel.deleteTimeStamp);
        }

    }

}