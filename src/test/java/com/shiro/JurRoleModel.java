package com.shiro;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

public final class JurRoleModel implements Model<JurRoleModel, JurRoleModel.Column, JurRoleModel.On, JurRoleModel.Where, JurRoleModel.Sort, JurRoleModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "jur_role";
    /**
     * 表别名
     */
    public static final String tableAlias = "JurRole";

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
     * 名称
     */
    public static final String name = "name";
    /**
     * 名称
     */
    public static final String name_alias = "name";
    /**
     * 角色名(后台用)
     */
    public static final String role = "role";
    /**
     * 角色名(后台用)
     */
    public static final String role_alias = "role";
    /**
     * 角色名(前台显示用)
     */
    public static final String description = "description";
    /**
     * 角色名(前台显示用)
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
     * 角色类型
     */
    public static final String type = "type";
    /**
     * 角色类型
     */
    public static final String type_alias = "type";
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
        COLUMN_ALIAS_MAP.put(role, role_alias);
        ALIAS_COLUMN_MAP.put(role_alias, role);
        COLUMN_ALIAS_MAP.put(description, description_alias);
        ALIAS_COLUMN_MAP.put(description_alias, description);
        COLUMN_ALIAS_MAP.put(parentId, parentId_alias);
        ALIAS_COLUMN_MAP.put(parentId_alias, parentId);
        COLUMN_ALIAS_MAP.put(parentIds, parentIds_alias);
        ALIAS_COLUMN_MAP.put(parentIds_alias, parentIds);
        COLUMN_ALIAS_MAP.put(type, type_alias);
        ALIAS_COLUMN_MAP.put(type_alias, type);
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
    public ColumnModel<JurRoleModel, Column, On, Where, Sort, Group> getColumnModel() {
        return new Column();
    }

    public static final class Column extends ColumnModel<JurRoleModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(JurRoleModel.primaryKeyName, JurRoleModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(JurRoleModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * ID-c:hidden
         */
        public Column id() {
            this.addColumnAlias(JurRoleModel.id, JurRoleModel.id_alias);
            return this;
        }

        /**
         * ID-c:hidden
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(JurRoleModel.id, alias);
            return this;
        }

        /**
         * 名称
         */
        public Column name() {
            this.addColumnAlias(JurRoleModel.name, JurRoleModel.name_alias);
            return this;
        }

        /**
         * 名称
         * @param alias 别名
         */
        public Column name(String alias) {
            this.addColumnAlias(JurRoleModel.name, alias);
            return this;
        }

        /**
         * 角色名(后台用)
         */
        public Column role() {
            this.addColumnAlias(JurRoleModel.role, JurRoleModel.role_alias);
            return this;
        }

        /**
         * 角色名(后台用)
         * @param alias 别名
         */
        public Column role(String alias) {
            this.addColumnAlias(JurRoleModel.role, alias);
            return this;
        }

        /**
         * 角色名(前台显示用)
         */
        public Column description() {
            this.addColumnAlias(JurRoleModel.description, JurRoleModel.description_alias);
            return this;
        }

        /**
         * 角色名(前台显示用)
         * @param alias 别名
         */
        public Column description(String alias) {
            this.addColumnAlias(JurRoleModel.description, alias);
            return this;
        }

        /**
         * 父id
         */
        public Column parentId() {
            this.addColumnAlias(JurRoleModel.parentId, JurRoleModel.parentId_alias);
            return this;
        }

        /**
         * 父id
         * @param alias 别名
         */
        public Column parentId(String alias) {
            this.addColumnAlias(JurRoleModel.parentId, alias);
            return this;
        }

        /**
         * 祖先id
         */
        public Column parentIds() {
            this.addColumnAlias(JurRoleModel.parentIds, JurRoleModel.parentIds_alias);
            return this;
        }

        /**
         * 祖先id
         * @param alias 别名
         */
        public Column parentIds(String alias) {
            this.addColumnAlias(JurRoleModel.parentIds, alias);
            return this;
        }

        /**
         * 角色类型
         */
        public Column type() {
            this.addColumnAlias(JurRoleModel.type, JurRoleModel.type_alias);
            return this;
        }

        /**
         * 角色类型
         * @param alias 别名
         */
        public Column type(String alias) {
            this.addColumnAlias(JurRoleModel.type, alias);
            return this;
        }

        /**
         * 
         */
        public Column index() {
            this.addColumnAlias(JurRoleModel.index, JurRoleModel.index_alias);
            return this;
        }

        /**
         * 
         * @param alias 别名
         */
        public Column index(String alias) {
            this.addColumnAlias(JurRoleModel.index, alias);
            return this;
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public Column status() {
            this.addColumnAlias(JurRoleModel.status, JurRoleModel.status_alias);
            return this;
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(JurRoleModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(JurRoleModel.createTime, JurRoleModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(JurRoleModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(JurRoleModel.updateTime, JurRoleModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(JurRoleModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(JurRoleModel.deleteTime, JurRoleModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(JurRoleModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTimeStamp() {
            this.addColumnAlias(JurRoleModel.createTimeStamp, JurRoleModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(JurRoleModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(JurRoleModel.updateTimeStamp, JurRoleModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(JurRoleModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(JurRoleModel.deleteTimeStamp, JurRoleModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(JurRoleModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public OnModel<JurRoleModel, Column, On, Where, Sort, Group> getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<JurRoleModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<JurRoleModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.primaryKeyName);
        }
    
        /**
         * ID-c:hidden
         */
        public OnBuilder<JurRoleModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.id);
        }

        /**
         * 名称
         */
        public OnBuilder<JurRoleModel, Column, On, Where, Sort, Group> name() {
            return this.onBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.name);
        }

        /**
         * 角色名(后台用)
         */
        public OnBuilder<JurRoleModel, Column, On, Where, Sort, Group> role() {
            return this.onBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.role);
        }

        /**
         * 角色名(前台显示用)
         */
        public OnBuilder<JurRoleModel, Column, On, Where, Sort, Group> description() {
            return this.onBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.description);
        }

        /**
         * 父id
         */
        public OnBuilder<JurRoleModel, Column, On, Where, Sort, Group> parentId() {
            return this.onBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.parentId);
        }

        /**
         * 祖先id
         */
        public OnBuilder<JurRoleModel, Column, On, Where, Sort, Group> parentIds() {
            return this.onBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.parentIds);
        }

        /**
         * 角色类型
         */
        public OnBuilder<JurRoleModel, Column, On, Where, Sort, Group> type() {
            return this.onBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.type);
        }

        /**
         * 
         */
        public OnBuilder<JurRoleModel, Column, On, Where, Sort, Group> index() {
            return this.onBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.index);
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public OnBuilder<JurRoleModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<JurRoleModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<JurRoleModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<JurRoleModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public OnBuilder<JurRoleModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.createTimeStamp);
        }

        /**
         * 修改时间
         */
        public OnBuilder<JurRoleModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.updateTimeStamp);
        }

        /**
         * 删除时间
         */
        public OnBuilder<JurRoleModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<JurRoleModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<JurRoleModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.primaryKeyName);
        }
    
        /**
         * ID-c:hidden
         */
        public WhereBuilder<JurRoleModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.id);
        }

        /**
         * 名称
         */
        public WhereBuilder<JurRoleModel, Column, On, Where, Sort, Group> name() {
            return this.whereBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.name);
        }

        /**
         * 角色名(后台用)
         */
        public WhereBuilder<JurRoleModel, Column, On, Where, Sort, Group> role() {
            return this.whereBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.role);
        }

        /**
         * 角色名(前台显示用)
         */
        public WhereBuilder<JurRoleModel, Column, On, Where, Sort, Group> description() {
            return this.whereBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.description);
        }

        /**
         * 父id
         */
        public WhereBuilder<JurRoleModel, Column, On, Where, Sort, Group> parentId() {
            return this.whereBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.parentId);
        }

        /**
         * 祖先id
         */
        public WhereBuilder<JurRoleModel, Column, On, Where, Sort, Group> parentIds() {
            return this.whereBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.parentIds);
        }

        /**
         * 角色类型
         */
        public WhereBuilder<JurRoleModel, Column, On, Where, Sort, Group> type() {
            return this.whereBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.type);
        }

        /**
         * 
         */
        public WhereBuilder<JurRoleModel, Column, On, Where, Sort, Group> index() {
            return this.whereBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.index);
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public WhereBuilder<JurRoleModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<JurRoleModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<JurRoleModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<JurRoleModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<JurRoleModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.createTimeStamp);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<JurRoleModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.updateTimeStamp);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<JurRoleModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(JurRoleModel.tableName, JurRoleModel.tableAlias, JurRoleModel.deleteTimeStamp);
        }

    }

    @Override
    public GroupModel<JurRoleModel, Column, On, Where, Sort, Group> getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<JurRoleModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(JurRoleModel.primaryKeyName);
            return this;
        }
    
        /**
         * ID-c:hidden
         */
        public Group id() {
            this.addColumn(JurRoleModel.id);
            return this;
        }

        /**
         * 名称
         */
        public Group name() {
            this.addColumn(JurRoleModel.name);
            return this;
        }

        /**
         * 角色名(后台用)
         */
        public Group role() {
            this.addColumn(JurRoleModel.role);
            return this;
        }

        /**
         * 角色名(前台显示用)
         */
        public Group description() {
            this.addColumn(JurRoleModel.description);
            return this;
        }

        /**
         * 父id
         */
        public Group parentId() {
            this.addColumn(JurRoleModel.parentId);
            return this;
        }

        /**
         * 祖先id
         */
        public Group parentIds() {
            this.addColumn(JurRoleModel.parentIds);
            return this;
        }

        /**
         * 角色类型
         */
        public Group type() {
            this.addColumn(JurRoleModel.type);
            return this;
        }

        /**
         * 
         */
        public Group index() {
            this.addColumn(JurRoleModel.index);
            return this;
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public Group status() {
            this.addColumn(JurRoleModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(JurRoleModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(JurRoleModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(JurRoleModel.deleteTime);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTimeStamp() {
            this.addColumn(JurRoleModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTimeStamp() {
            this.addColumn(JurRoleModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTimeStamp() {
            this.addColumn(JurRoleModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public SortModel<JurRoleModel, Column, On, Where, Sort, Group> getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<JurRoleModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<JurRoleModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(JurRoleModel.primaryKeyName);
        }
    
        /**
         * ID-c:hidden
         */
        public SortBuilder<JurRoleModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(JurRoleModel.id);
        }

        /**
         * 名称
         */
        public SortBuilder<JurRoleModel, Column, On, Where, Sort, Group> name() {
            return this.sortBuilder.handler(JurRoleModel.name);
        }

        /**
         * 角色名(后台用)
         */
        public SortBuilder<JurRoleModel, Column, On, Where, Sort, Group> role() {
            return this.sortBuilder.handler(JurRoleModel.role);
        }

        /**
         * 角色名(前台显示用)
         */
        public SortBuilder<JurRoleModel, Column, On, Where, Sort, Group> description() {
            return this.sortBuilder.handler(JurRoleModel.description);
        }

        /**
         * 父id
         */
        public SortBuilder<JurRoleModel, Column, On, Where, Sort, Group> parentId() {
            return this.sortBuilder.handler(JurRoleModel.parentId);
        }

        /**
         * 祖先id
         */
        public SortBuilder<JurRoleModel, Column, On, Where, Sort, Group> parentIds() {
            return this.sortBuilder.handler(JurRoleModel.parentIds);
        }

        /**
         * 角色类型
         */
        public SortBuilder<JurRoleModel, Column, On, Where, Sort, Group> type() {
            return this.sortBuilder.handler(JurRoleModel.type);
        }

        /**
         * 
         */
        public SortBuilder<JurRoleModel, Column, On, Where, Sort, Group> index() {
            return this.sortBuilder.handler(JurRoleModel.index);
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public SortBuilder<JurRoleModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(JurRoleModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<JurRoleModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(JurRoleModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<JurRoleModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(JurRoleModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<JurRoleModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(JurRoleModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public SortBuilder<JurRoleModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(JurRoleModel.createTimeStamp);
        }

        /**
         * 修改时间
         */
        public SortBuilder<JurRoleModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(JurRoleModel.updateTimeStamp);
        }

        /**
         * 删除时间
         */
        public SortBuilder<JurRoleModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(JurRoleModel.deleteTimeStamp);
        }

    }

}