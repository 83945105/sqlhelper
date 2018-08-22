package com.shiro.model;

import com.dt.core.bean.*;
import com.dt.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

public final class JurResModel implements Model<JurResModel, JurResModel.Column, JurResModel.On, JurResModel.Where, JurResModel.Sort, JurResModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "jur_res";
    /**
     * 表别名
     */
    public static final String tableAlias = "JurRes";

    /**
     * 主键名
     */
    public static final String primaryKeyName = "ID";
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
     * 名称
     */
    public static final String name = "name";
    /**
     * 名称
     */
    public static final String name_alias = "name";
    /**
     * 类型
     */
    public static final String type = "type";
    /**
     * 类型
     */
    public static final String type_alias = "type";
    /**
     * url
     */
    public static final String url = "url";
    /**
     * url
     */
    public static final String url_alias = "url";
    /**
     * 页面路径
     */
    public static final String path = "path";
    /**
     * 页面路径
     */
    public static final String path_alias = "path";
    /**
     * 资源描述
     */
    public static final String description = "description";
    /**
     * 资源描述
     */
    public static final String description_alias = "description";
    /**
     * 父id
     */
    public static final String parentId = "parent_id";
    /**
     * 父id
     */
    public static final String parentId_alias = "parentId";
    /**
     * 祖先id
     */
    public static final String parentIds = "parent_ids";
    /**
     * 祖先id
     */
    public static final String parentIds_alias = "parentIds";
    /**
     * 权限
     */
    public static final String permission = "permission";
    /**
     * 权限
     */
    public static final String permission_alias = "permission";
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
        COLUMN_ALIAS_MAP.put(name, name_alias);
        ALIAS_COLUMN_MAP.put(name_alias, name);
        COLUMN_ALIAS_MAP.put(type, type_alias);
        ALIAS_COLUMN_MAP.put(type_alias, type);
        COLUMN_ALIAS_MAP.put(url, url_alias);
        ALIAS_COLUMN_MAP.put(url_alias, url);
        COLUMN_ALIAS_MAP.put(path, path_alias);
        ALIAS_COLUMN_MAP.put(path_alias, path);
        COLUMN_ALIAS_MAP.put(description, description_alias);
        ALIAS_COLUMN_MAP.put(description_alias, description);
        COLUMN_ALIAS_MAP.put(parentId, parentId_alias);
        ALIAS_COLUMN_MAP.put(parentId_alias, parentId);
        COLUMN_ALIAS_MAP.put(parentIds, parentIds_alias);
        ALIAS_COLUMN_MAP.put(parentIds_alias, parentIds);
        COLUMN_ALIAS_MAP.put(permission, permission_alias);
        ALIAS_COLUMN_MAP.put(permission_alias, permission);
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
    public ColumnModel<JurResModel, Column, On, Where, Sort, Group> getColumnModel() {
        return new Column();
    }

    public static final class Column extends ColumnModel<JurResModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(JurResModel.primaryKeyName, JurResModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(JurResModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * ID-c:hidden
         */
        public Column id() {
            this.addColumnAlias(JurResModel.id, JurResModel.id_alias);
            return this;
        }

        /**
         * ID-c:hidden
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(JurResModel.id, alias);
            return this;
        }

        /**
         * 名称
         */
        public Column name() {
            this.addColumnAlias(JurResModel.name, JurResModel.name_alias);
            return this;
        }

        /**
         * 名称
         * @param alias 别名
         */
        public Column name(String alias) {
            this.addColumnAlias(JurResModel.name, alias);
            return this;
        }

        /**
         * 类型
         */
        public Column type() {
            this.addColumnAlias(JurResModel.type, JurResModel.type_alias);
            return this;
        }

        /**
         * 类型
         * @param alias 别名
         */
        public Column type(String alias) {
            this.addColumnAlias(JurResModel.type, alias);
            return this;
        }

        /**
         * url
         */
        public Column url() {
            this.addColumnAlias(JurResModel.url, JurResModel.url_alias);
            return this;
        }

        /**
         * url
         * @param alias 别名
         */
        public Column url(String alias) {
            this.addColumnAlias(JurResModel.url, alias);
            return this;
        }

        /**
         * 页面路径
         */
        public Column path() {
            this.addColumnAlias(JurResModel.path, JurResModel.path_alias);
            return this;
        }

        /**
         * 页面路径
         * @param alias 别名
         */
        public Column path(String alias) {
            this.addColumnAlias(JurResModel.path, alias);
            return this;
        }

        /**
         * 资源描述
         */
        public Column description() {
            this.addColumnAlias(JurResModel.description, JurResModel.description_alias);
            return this;
        }

        /**
         * 资源描述
         * @param alias 别名
         */
        public Column description(String alias) {
            this.addColumnAlias(JurResModel.description, alias);
            return this;
        }

        /**
         * 父id
         */
        public Column parentId() {
            this.addColumnAlias(JurResModel.parentId, JurResModel.parentId_alias);
            return this;
        }

        /**
         * 父id
         * @param alias 别名
         */
        public Column parentId(String alias) {
            this.addColumnAlias(JurResModel.parentId, alias);
            return this;
        }

        /**
         * 祖先id
         */
        public Column parentIds() {
            this.addColumnAlias(JurResModel.parentIds, JurResModel.parentIds_alias);
            return this;
        }

        /**
         * 祖先id
         * @param alias 别名
         */
        public Column parentIds(String alias) {
            this.addColumnAlias(JurResModel.parentIds, alias);
            return this;
        }

        /**
         * 权限
         */
        public Column permission() {
            this.addColumnAlias(JurResModel.permission, JurResModel.permission_alias);
            return this;
        }

        /**
         * 权限
         * @param alias 别名
         */
        public Column permission(String alias) {
            this.addColumnAlias(JurResModel.permission, alias);
            return this;
        }

        /**
         * 
         */
        public Column index() {
            this.addColumnAlias(JurResModel.index, JurResModel.index_alias);
            return this;
        }

        /**
         * 
         * @param alias 别名
         */
        public Column index(String alias) {
            this.addColumnAlias(JurResModel.index, alias);
            return this;
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public Column status() {
            this.addColumnAlias(JurResModel.status, JurResModel.status_alias);
            return this;
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(JurResModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(JurResModel.createTime, JurResModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(JurResModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(JurResModel.updateTime, JurResModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(JurResModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(JurResModel.deleteTime, JurResModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(JurResModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTimeStamp() {
            this.addColumnAlias(JurResModel.createTimeStamp, JurResModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(JurResModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(JurResModel.updateTimeStamp, JurResModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(JurResModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(JurResModel.deleteTimeStamp, JurResModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(JurResModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public OnModel<JurResModel, Column, On, Where, Sort, Group> getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<JurResModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<JurResModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(JurResModel.tableName, JurRoleModel.tableAlias, JurResModel.primaryKeyName);
        }
    
        /**
         * ID-c:hidden
         */
        public OnBuilder<JurResModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.id);
        }

        /**
         * 名称
         */
        public OnBuilder<JurResModel, Column, On, Where, Sort, Group> name() {
            return this.onBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.name);
        }

        /**
         * 类型
         */
        public OnBuilder<JurResModel, Column, On, Where, Sort, Group> type() {
            return this.onBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.type);
        }

        /**
         * url
         */
        public OnBuilder<JurResModel, Column, On, Where, Sort, Group> url() {
            return this.onBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.url);
        }

        /**
         * 页面路径
         */
        public OnBuilder<JurResModel, Column, On, Where, Sort, Group> path() {
            return this.onBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.path);
        }

        /**
         * 资源描述
         */
        public OnBuilder<JurResModel, Column, On, Where, Sort, Group> description() {
            return this.onBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.description);
        }

        /**
         * 父id
         */
        public OnBuilder<JurResModel, Column, On, Where, Sort, Group> parentId() {
            return this.onBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.parentId);
        }

        /**
         * 祖先id
         */
        public OnBuilder<JurResModel, Column, On, Where, Sort, Group> parentIds() {
            return this.onBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.parentIds);
        }

        /**
         * 权限
         */
        public OnBuilder<JurResModel, Column, On, Where, Sort, Group> permission() {
            return this.onBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.permission);
        }

        /**
         * 
         */
        public OnBuilder<JurResModel, Column, On, Where, Sort, Group> index() {
            return this.onBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.index);
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public OnBuilder<JurResModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<JurResModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<JurResModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<JurResModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public OnBuilder<JurResModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.createTimeStamp);
        }

        /**
         * 修改时间
         */
        public OnBuilder<JurResModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.updateTimeStamp);
        }

        /**
         * 删除时间
         */
        public OnBuilder<JurResModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<JurResModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<JurResModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.primaryKeyName);
        }
    
        /**
         * ID-c:hidden
         */
        public WhereBuilder<JurResModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.id);
        }

        /**
         * 名称
         */
        public WhereBuilder<JurResModel, Column, On, Where, Sort, Group> name() {
            return this.whereBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.name);
        }

        /**
         * 类型
         */
        public WhereBuilder<JurResModel, Column, On, Where, Sort, Group> type() {
            return this.whereBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.type);
        }

        /**
         * url
         */
        public WhereBuilder<JurResModel, Column, On, Where, Sort, Group> url() {
            return this.whereBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.url);
        }

        /**
         * 页面路径
         */
        public WhereBuilder<JurResModel, Column, On, Where, Sort, Group> path() {
            return this.whereBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.path);
        }

        /**
         * 资源描述
         */
        public WhereBuilder<JurResModel, Column, On, Where, Sort, Group> description() {
            return this.whereBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.description);
        }

        /**
         * 父id
         */
        public WhereBuilder<JurResModel, Column, On, Where, Sort, Group> parentId() {
            return this.whereBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.parentId);
        }

        /**
         * 祖先id
         */
        public WhereBuilder<JurResModel, Column, On, Where, Sort, Group> parentIds() {
            return this.whereBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.parentIds);
        }

        /**
         * 权限
         */
        public WhereBuilder<JurResModel, Column, On, Where, Sort, Group> permission() {
            return this.whereBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.permission);
        }

        /**
         * 
         */
        public WhereBuilder<JurResModel, Column, On, Where, Sort, Group> index() {
            return this.whereBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.index);
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public WhereBuilder<JurResModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<JurResModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<JurResModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<JurResModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<JurResModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.createTimeStamp);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<JurResModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.updateTimeStamp);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<JurResModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(JurResModel.tableName, JurResModel.tableAlias, JurResModel.deleteTimeStamp);
        }

    }

    @Override
    public GroupModel getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<JurResModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(JurResModel.primaryKeyName);
            return this;
        }
    
        /**
         * ID-c:hidden
         */
        public Group id() {
            this.addColumn(JurResModel.id);
            return this;
        }

        /**
         * 名称
         */
        public Group name() {
            this.addColumn(JurResModel.name);
            return this;
        }

        /**
         * 类型
         */
        public Group type() {
            this.addColumn(JurResModel.type);
            return this;
        }

        /**
         * url
         */
        public Group url() {
            this.addColumn(JurResModel.url);
            return this;
        }

        /**
         * 页面路径
         */
        public Group path() {
            this.addColumn(JurResModel.path);
            return this;
        }

        /**
         * 资源描述
         */
        public Group description() {
            this.addColumn(JurResModel.description);
            return this;
        }

        /**
         * 父id
         */
        public Group parentId() {
            this.addColumn(JurResModel.parentId);
            return this;
        }

        /**
         * 祖先id
         */
        public Group parentIds() {
            this.addColumn(JurResModel.parentIds);
            return this;
        }

        /**
         * 权限
         */
        public Group permission() {
            this.addColumn(JurResModel.permission);
            return this;
        }

        /**
         * 
         */
        public Group index() {
            this.addColumn(JurResModel.index);
            return this;
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public Group status() {
            this.addColumn(JurResModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(JurResModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(JurResModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(JurResModel.deleteTime);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTimeStamp() {
            this.addColumn(JurResModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTimeStamp() {
            this.addColumn(JurResModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTimeStamp() {
            this.addColumn(JurResModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public SortModel getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<JurResModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<JurResModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(JurResModel.primaryKeyName);
        }
    
        /**
         * ID-c:hidden
         */
        public SortBuilder<JurResModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(JurResModel.id);
        }

        /**
         * 名称
         */
        public SortBuilder<JurResModel, Column, On, Where, Sort, Group> name() {
            return this.sortBuilder.handler(JurResModel.name);
        }

        /**
         * 类型
         */
        public SortBuilder<JurResModel, Column, On, Where, Sort, Group> type() {
            return this.sortBuilder.handler(JurResModel.type);
        }

        /**
         * url
         */
        public SortBuilder<JurResModel, Column, On, Where, Sort, Group> url() {
            return this.sortBuilder.handler(JurResModel.url);
        }

        /**
         * 页面路径
         */
        public SortBuilder<JurResModel, Column, On, Where, Sort, Group> path() {
            return this.sortBuilder.handler(JurResModel.path);
        }

        /**
         * 资源描述
         */
        public SortBuilder<JurResModel, Column, On, Where, Sort, Group> description() {
            return this.sortBuilder.handler(JurResModel.description);
        }

        /**
         * 父id
         */
        public SortBuilder<JurResModel, Column, On, Where, Sort, Group> parentId() {
            return this.sortBuilder.handler(JurResModel.parentId);
        }

        /**
         * 祖先id
         */
        public SortBuilder<JurResModel, Column, On, Where, Sort, Group> parentIds() {
            return this.sortBuilder.handler(JurResModel.parentIds);
        }

        /**
         * 权限
         */
        public SortBuilder<JurResModel, Column, On, Where, Sort, Group> permission() {
            return this.sortBuilder.handler(JurResModel.permission);
        }

        /**
         * 
         */
        public SortBuilder<JurResModel, Column, On, Where, Sort, Group> index() {
            return this.sortBuilder.handler(JurResModel.index);
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public SortBuilder<JurResModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(JurResModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<JurResModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(JurResModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<JurResModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(JurResModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<JurResModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(JurResModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public SortBuilder<JurResModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(JurResModel.createTimeStamp);
        }

        /**
         * 修改时间
         */
        public SortBuilder<JurResModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(JurResModel.updateTimeStamp);
        }

        /**
         * 删除时间
         */
        public SortBuilder<JurResModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(JurResModel.deleteTimeStamp);
        }

    }

}