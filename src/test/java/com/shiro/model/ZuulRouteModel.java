package com.shiro.model;

import com.dt.core.bean.*;
import com.dt.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ZuulRouteModel implements Model<ZuulRouteModel, ZuulRouteModel.Column, ZuulRouteModel.On, ZuulRouteModel.Where, ZuulRouteModel.Sort, ZuulRouteModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "zuul_route";
    /**
     * 表别名
     */
    public static final String tableAlias = "ZuulRoute";

    /**
     * 主键名
     */
    public static final String primaryKeyName = "id";
    /**
     * 主键别名
     */
    public static final String primaryKeyAlias = "id";



    /**
     * 主键
     */
    public static final String id = "id";
    /**
     * 主键
     */
    public static final String id_alias = "id";
    /**
     * 
     */
    public static final String path = "path";
    /**
     * 
     */
    public static final String path_alias = "path";
    /**
     * 
     */
    public static final String serviceId = "service_id";
    /**
     * 
     */
    public static final String serviceId_alias = "serviceId";
    /**
     * 
     */
    public static final String serviceName = "service_name";
    /**
     * 
     */
    public static final String serviceName_alias = "serviceName";
    /**
     * 
     */
    public static final String url = "url";
    /**
     * 
     */
    public static final String url_alias = "url";
    /**
     * 登录地址
     */
    public static final String loginUrl = "login_url";
    /**
     * 登录地址
     */
    public static final String loginUrl_alias = "loginUrl";
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
        COLUMN_ALIAS_MAP.put(path, path_alias);
        ALIAS_COLUMN_MAP.put(path_alias, path);
        COLUMN_ALIAS_MAP.put(serviceId, serviceId_alias);
        ALIAS_COLUMN_MAP.put(serviceId_alias, serviceId);
        COLUMN_ALIAS_MAP.put(serviceName, serviceName_alias);
        ALIAS_COLUMN_MAP.put(serviceName_alias, serviceName);
        COLUMN_ALIAS_MAP.put(url, url_alias);
        ALIAS_COLUMN_MAP.put(url_alias, url);
        COLUMN_ALIAS_MAP.put(loginUrl, loginUrl_alias);
        ALIAS_COLUMN_MAP.put(loginUrl_alias, loginUrl);
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
    public ColumnModel<ZuulRouteModel, Column, On, Where, Sort, Group> getColumnModel() {
        return new Column();
    }

    public static final class Column extends ColumnModel<ZuulRouteModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(ZuulRouteModel.primaryKeyName, ZuulRouteModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(ZuulRouteModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 主键
         */
        public Column id() {
            this.addColumnAlias(ZuulRouteModel.id, ZuulRouteModel.id_alias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(ZuulRouteModel.id, alias);
            return this;
        }

        /**
         * 
         */
        public Column path() {
            this.addColumnAlias(ZuulRouteModel.path, ZuulRouteModel.path_alias);
            return this;
        }

        /**
         * 
         * @param alias 别名
         */
        public Column path(String alias) {
            this.addColumnAlias(ZuulRouteModel.path, alias);
            return this;
        }

        /**
         * 
         */
        public Column serviceId() {
            this.addColumnAlias(ZuulRouteModel.serviceId, ZuulRouteModel.serviceId_alias);
            return this;
        }

        /**
         * 
         * @param alias 别名
         */
        public Column serviceId(String alias) {
            this.addColumnAlias(ZuulRouteModel.serviceId, alias);
            return this;
        }

        /**
         * 
         */
        public Column serviceName() {
            this.addColumnAlias(ZuulRouteModel.serviceName, ZuulRouteModel.serviceName_alias);
            return this;
        }

        /**
         * 
         * @param alias 别名
         */
        public Column serviceName(String alias) {
            this.addColumnAlias(ZuulRouteModel.serviceName, alias);
            return this;
        }

        /**
         * 
         */
        public Column url() {
            this.addColumnAlias(ZuulRouteModel.url, ZuulRouteModel.url_alias);
            return this;
        }

        /**
         * 
         * @param alias 别名
         */
        public Column url(String alias) {
            this.addColumnAlias(ZuulRouteModel.url, alias);
            return this;
        }

        /**
         * 登录地址
         */
        public Column loginUrl() {
            this.addColumnAlias(ZuulRouteModel.loginUrl, ZuulRouteModel.loginUrl_alias);
            return this;
        }

        /**
         * 登录地址
         * @param alias 别名
         */
        public Column loginUrl(String alias) {
            this.addColumnAlias(ZuulRouteModel.loginUrl, alias);
            return this;
        }

        /**
         * 
         */
        public Column index() {
            this.addColumnAlias(ZuulRouteModel.index, ZuulRouteModel.index_alias);
            return this;
        }

        /**
         * 
         * @param alias 别名
         */
        public Column index(String alias) {
            this.addColumnAlias(ZuulRouteModel.index, alias);
            return this;
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public Column status() {
            this.addColumnAlias(ZuulRouteModel.status, ZuulRouteModel.status_alias);
            return this;
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(ZuulRouteModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(ZuulRouteModel.createTime, ZuulRouteModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(ZuulRouteModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(ZuulRouteModel.updateTime, ZuulRouteModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(ZuulRouteModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(ZuulRouteModel.deleteTime, ZuulRouteModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(ZuulRouteModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTimeStamp() {
            this.addColumnAlias(ZuulRouteModel.createTimeStamp, ZuulRouteModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(ZuulRouteModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(ZuulRouteModel.updateTimeStamp, ZuulRouteModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(ZuulRouteModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(ZuulRouteModel.deleteTimeStamp, ZuulRouteModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(ZuulRouteModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public OnModel<ZuulRouteModel, Column, On, Where, Sort, Group> getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<ZuulRouteModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(ZuulRouteModel.tableName, JurRoleModel.tableAlias, ZuulRouteModel.primaryKeyName);
        }
    
        /**
         * 主键
         */
        public OnBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.id);
        }

        /**
         * 
         */
        public OnBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> path() {
            return this.onBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.path);
        }

        /**
         * 
         */
        public OnBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> serviceId() {
            return this.onBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.serviceId);
        }

        /**
         * 
         */
        public OnBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> serviceName() {
            return this.onBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.serviceName);
        }

        /**
         * 
         */
        public OnBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> url() {
            return this.onBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.url);
        }

        /**
         * 登录地址
         */
        public OnBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> loginUrl() {
            return this.onBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.loginUrl);
        }

        /**
         * 
         */
        public OnBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> index() {
            return this.onBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.index);
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public OnBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public OnBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.createTimeStamp);
        }

        /**
         * 修改时间
         */
        public OnBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.updateTimeStamp);
        }

        /**
         * 删除时间
         */
        public OnBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<ZuulRouteModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.primaryKeyName);
        }
    
        /**
         * 主键
         */
        public WhereBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.id);
        }

        /**
         * 
         */
        public WhereBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> path() {
            return this.whereBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.path);
        }

        /**
         * 
         */
        public WhereBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> serviceId() {
            return this.whereBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.serviceId);
        }

        /**
         * 
         */
        public WhereBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> serviceName() {
            return this.whereBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.serviceName);
        }

        /**
         * 
         */
        public WhereBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> url() {
            return this.whereBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.url);
        }

        /**
         * 登录地址
         */
        public WhereBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> loginUrl() {
            return this.whereBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.loginUrl);
        }

        /**
         * 
         */
        public WhereBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> index() {
            return this.whereBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.index);
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public WhereBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.createTimeStamp);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.updateTimeStamp);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(ZuulRouteModel.tableName, ZuulRouteModel.tableAlias, ZuulRouteModel.deleteTimeStamp);
        }

    }

    @Override
    public GroupModel getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<ZuulRouteModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(ZuulRouteModel.primaryKeyName);
            return this;
        }
    
        /**
         * 主键
         */
        public Group id() {
            this.addColumn(ZuulRouteModel.id);
            return this;
        }

        /**
         * 
         */
        public Group path() {
            this.addColumn(ZuulRouteModel.path);
            return this;
        }

        /**
         * 
         */
        public Group serviceId() {
            this.addColumn(ZuulRouteModel.serviceId);
            return this;
        }

        /**
         * 
         */
        public Group serviceName() {
            this.addColumn(ZuulRouteModel.serviceName);
            return this;
        }

        /**
         * 
         */
        public Group url() {
            this.addColumn(ZuulRouteModel.url);
            return this;
        }

        /**
         * 登录地址
         */
        public Group loginUrl() {
            this.addColumn(ZuulRouteModel.loginUrl);
            return this;
        }

        /**
         * 
         */
        public Group index() {
            this.addColumn(ZuulRouteModel.index);
            return this;
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public Group status() {
            this.addColumn(ZuulRouteModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(ZuulRouteModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(ZuulRouteModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(ZuulRouteModel.deleteTime);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTimeStamp() {
            this.addColumn(ZuulRouteModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTimeStamp() {
            this.addColumn(ZuulRouteModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTimeStamp() {
            this.addColumn(ZuulRouteModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public SortModel getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<ZuulRouteModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(ZuulRouteModel.primaryKeyName);
        }
    
        /**
         * 主键
         */
        public SortBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(ZuulRouteModel.id);
        }

        /**
         * 
         */
        public SortBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> path() {
            return this.sortBuilder.handler(ZuulRouteModel.path);
        }

        /**
         * 
         */
        public SortBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> serviceId() {
            return this.sortBuilder.handler(ZuulRouteModel.serviceId);
        }

        /**
         * 
         */
        public SortBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> serviceName() {
            return this.sortBuilder.handler(ZuulRouteModel.serviceName);
        }

        /**
         * 
         */
        public SortBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> url() {
            return this.sortBuilder.handler(ZuulRouteModel.url);
        }

        /**
         * 登录地址
         */
        public SortBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> loginUrl() {
            return this.sortBuilder.handler(ZuulRouteModel.loginUrl);
        }

        /**
         * 
         */
        public SortBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> index() {
            return this.sortBuilder.handler(ZuulRouteModel.index);
        }

        /**
         * 状态(00:正常,01:删除)-d:0
         */
        public SortBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(ZuulRouteModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(ZuulRouteModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(ZuulRouteModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(ZuulRouteModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public SortBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(ZuulRouteModel.createTimeStamp);
        }

        /**
         * 修改时间
         */
        public SortBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(ZuulRouteModel.updateTimeStamp);
        }

        /**
         * 删除时间
         */
        public SortBuilder<ZuulRouteModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(ZuulRouteModel.deleteTimeStamp);
        }

    }

}