package com.shiro;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

public final class JurRoleUserModel implements Model<JurRoleUserModel, JurRoleUserModel.Column, JurRoleUserModel.On, JurRoleUserModel.Where, JurRoleUserModel.Sort, JurRoleUserModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "jur_role_user";
    /**
     * 表别名
     */
    public static final String tableAlias = "JurRoleUser";

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
     * 用户id
     */
    public static final String userId = "user_id";
    /**
     * 用户id
     */
    public static final String userId_alias = "userId";
    /**
     * 用户名
     */
    public static final String userName = "user_name";
    /**
     * 用户名
     */
    public static final String userName_alias = "userName";
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
        COLUMN_ALIAS_MAP.put(userId, userId_alias);
        ALIAS_COLUMN_MAP.put(userId_alias, userId);
        COLUMN_ALIAS_MAP.put(userName, userName_alias);
        ALIAS_COLUMN_MAP.put(userName_alias, userName);
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
    public ColumnModel<JurRoleUserModel, Column, On, Where, Sort, Group> getColumnModel() {
        return new Column();
    }

    public static final class Column extends ColumnModel<JurRoleUserModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(JurRoleUserModel.primaryKeyName, JurRoleUserModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(JurRoleUserModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * ID-c:hidden
         */
        public Column id() {
            this.addColumnAlias(JurRoleUserModel.id, JurRoleUserModel.id_alias);
            return this;
        }

        /**
         * ID-c:hidden
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(JurRoleUserModel.id, alias);
            return this;
        }

        /**
         * 角色id
         */
        public Column roleId() {
            this.addColumnAlias(JurRoleUserModel.roleId, JurRoleUserModel.roleId_alias);
            return this;
        }

        /**
         * 角色id
         * @param alias 别名
         */
        public Column roleId(String alias) {
            this.addColumnAlias(JurRoleUserModel.roleId, alias);
            return this;
        }

        /**
         * 角色
         */
        public Column role() {
            this.addColumnAlias(JurRoleUserModel.role, JurRoleUserModel.role_alias);
            return this;
        }

        /**
         * 角色
         * @param alias 别名
         */
        public Column role(String alias) {
            this.addColumnAlias(JurRoleUserModel.role, alias);
            return this;
        }

        /**
         * 角色名称
         */
        public Column roleName() {
            this.addColumnAlias(JurRoleUserModel.roleName, JurRoleUserModel.roleName_alias);
            return this;
        }

        /**
         * 角色名称
         * @param alias 别名
         */
        public Column roleName(String alias) {
            this.addColumnAlias(JurRoleUserModel.roleName, alias);
            return this;
        }

        /**
         * 角色类型
         */
        public Column roleType() {
            this.addColumnAlias(JurRoleUserModel.roleType, JurRoleUserModel.roleType_alias);
            return this;
        }

        /**
         * 角色类型
         * @param alias 别名
         */
        public Column roleType(String alias) {
            this.addColumnAlias(JurRoleUserModel.roleType, alias);
            return this;
        }

        /**
         * 用户id
         */
        public Column userId() {
            this.addColumnAlias(JurRoleUserModel.userId, JurRoleUserModel.userId_alias);
            return this;
        }

        /**
         * 用户id
         * @param alias 别名
         */
        public Column userId(String alias) {
            this.addColumnAlias(JurRoleUserModel.userId, alias);
            return this;
        }

        /**
         * 用户名
         */
        public Column userName() {
            this.addColumnAlias(JurRoleUserModel.userName, JurRoleUserModel.userName_alias);
            return this;
        }

        /**
         * 用户名
         * @param alias 别名
         */
        public Column userName(String alias) {
            this.addColumnAlias(JurRoleUserModel.userName, alias);
            return this;
        }

        /**
         * 
         */
        public Column index() {
            this.addColumnAlias(JurRoleUserModel.index, JurRoleUserModel.index_alias);
            return this;
        }

        /**
         * 
         * @param alias 别名
         */
        public Column index(String alias) {
            this.addColumnAlias(JurRoleUserModel.index, alias);
            return this;
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public Column status() {
            this.addColumnAlias(JurRoleUserModel.status, JurRoleUserModel.status_alias);
            return this;
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(JurRoleUserModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(JurRoleUserModel.createTime, JurRoleUserModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(JurRoleUserModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(JurRoleUserModel.updateTime, JurRoleUserModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(JurRoleUserModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(JurRoleUserModel.deleteTime, JurRoleUserModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(JurRoleUserModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTimeStamp() {
            this.addColumnAlias(JurRoleUserModel.createTimeStamp, JurRoleUserModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(JurRoleUserModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(JurRoleUserModel.updateTimeStamp, JurRoleUserModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(JurRoleUserModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(JurRoleUserModel.deleteTimeStamp, JurRoleUserModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(JurRoleUserModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public OnModel<JurRoleUserModel, Column, On, Where, Sort, Group> getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<JurRoleUserModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.primaryKeyName);
        }
    
        /**
         * ID-c:hidden
         */
        public OnBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.id);
        }

        /**
         * 角色id
         */
        public OnBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> roleId() {
            return this.onBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.roleId);
        }

        /**
         * 角色
         */
        public OnBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> role() {
            return this.onBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.role);
        }

        /**
         * 角色名称
         */
        public OnBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> roleName() {
            return this.onBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.roleName);
        }

        /**
         * 角色类型
         */
        public OnBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> roleType() {
            return this.onBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.roleType);
        }

        /**
         * 用户id
         */
        public OnBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> userId() {
            return this.onBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.userId);
        }

        /**
         * 用户名
         */
        public OnBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> userName() {
            return this.onBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.userName);
        }

        /**
         * 
         */
        public OnBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> index() {
            return this.onBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.index);
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public OnBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public OnBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.createTimeStamp);
        }

        /**
         * 修改时间
         */
        public OnBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.updateTimeStamp);
        }

        /**
         * 删除时间
         */
        public OnBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<JurRoleUserModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.primaryKeyName);
        }
    
        /**
         * ID-c:hidden
         */
        public WhereBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.id);
        }

        /**
         * 角色id
         */
        public WhereBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> roleId() {
            return this.whereBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.roleId);
        }

        /**
         * 角色
         */
        public WhereBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> role() {
            return this.whereBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.role);
        }

        /**
         * 角色名称
         */
        public WhereBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> roleName() {
            return this.whereBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.roleName);
        }

        /**
         * 角色类型
         */
        public WhereBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> roleType() {
            return this.whereBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.roleType);
        }

        /**
         * 用户id
         */
        public WhereBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> userId() {
            return this.whereBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.userId);
        }

        /**
         * 用户名
         */
        public WhereBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> userName() {
            return this.whereBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.userName);
        }

        /**
         * 
         */
        public WhereBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> index() {
            return this.whereBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.index);
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public WhereBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.createTimeStamp);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.updateTimeStamp);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(JurRoleUserModel.tableName, JurRoleUserModel.tableAlias, JurRoleUserModel.deleteTimeStamp);
        }

    }

    @Override
    public GroupModel<JurRoleUserModel, Column, On, Where, Sort, Group> getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<JurRoleUserModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(JurRoleUserModel.primaryKeyName);
            return this;
        }
    
        /**
         * ID-c:hidden
         */
        public Group id() {
            this.addColumn(JurRoleUserModel.id);
            return this;
        }

        /**
         * 角色id
         */
        public Group roleId() {
            this.addColumn(JurRoleUserModel.roleId);
            return this;
        }

        /**
         * 角色
         */
        public Group role() {
            this.addColumn(JurRoleUserModel.role);
            return this;
        }

        /**
         * 角色名称
         */
        public Group roleName() {
            this.addColumn(JurRoleUserModel.roleName);
            return this;
        }

        /**
         * 角色类型
         */
        public Group roleType() {
            this.addColumn(JurRoleUserModel.roleType);
            return this;
        }

        /**
         * 用户id
         */
        public Group userId() {
            this.addColumn(JurRoleUserModel.userId);
            return this;
        }

        /**
         * 用户名
         */
        public Group userName() {
            this.addColumn(JurRoleUserModel.userName);
            return this;
        }

        /**
         * 
         */
        public Group index() {
            this.addColumn(JurRoleUserModel.index);
            return this;
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public Group status() {
            this.addColumn(JurRoleUserModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(JurRoleUserModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(JurRoleUserModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(JurRoleUserModel.deleteTime);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTimeStamp() {
            this.addColumn(JurRoleUserModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTimeStamp() {
            this.addColumn(JurRoleUserModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTimeStamp() {
            this.addColumn(JurRoleUserModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public SortModel<JurRoleUserModel, Column, On, Where, Sort, Group> getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<JurRoleUserModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(JurRoleUserModel.primaryKeyName);
        }
    
        /**
         * ID-c:hidden
         */
        public SortBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(JurRoleUserModel.id);
        }

        /**
         * 角色id
         */
        public SortBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> roleId() {
            return this.sortBuilder.handler(JurRoleUserModel.roleId);
        }

        /**
         * 角色
         */
        public SortBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> role() {
            return this.sortBuilder.handler(JurRoleUserModel.role);
        }

        /**
         * 角色名称
         */
        public SortBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> roleName() {
            return this.sortBuilder.handler(JurRoleUserModel.roleName);
        }

        /**
         * 角色类型
         */
        public SortBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> roleType() {
            return this.sortBuilder.handler(JurRoleUserModel.roleType);
        }

        /**
         * 用户id
         */
        public SortBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> userId() {
            return this.sortBuilder.handler(JurRoleUserModel.userId);
        }

        /**
         * 用户名
         */
        public SortBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> userName() {
            return this.sortBuilder.handler(JurRoleUserModel.userName);
        }

        /**
         * 
         */
        public SortBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> index() {
            return this.sortBuilder.handler(JurRoleUserModel.index);
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public SortBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(JurRoleUserModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(JurRoleUserModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(JurRoleUserModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(JurRoleUserModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public SortBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(JurRoleUserModel.createTimeStamp);
        }

        /**
         * 修改时间
         */
        public SortBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(JurRoleUserModel.updateTimeStamp);
        }

        /**
         * 删除时间
         */
        public SortBuilder<JurRoleUserModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(JurRoleUserModel.deleteTimeStamp);
        }

    }

}