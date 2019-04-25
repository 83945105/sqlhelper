package pub.avalon.sqlhelper.readme.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class SysUserModel implements Model<SysUserModel, SysUserModel.Column, SysUserModel.On, SysUserModel.Where, SysUserModel.Sort, SysUserModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "sys_user";
    /**
     * 表别名
     */
    public static final String tableAlias = "SysUser";

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
    public static final String userName = "user_name";
    /**
     * 
     */
    public static final String userName_alias = "userName";
    /**
     * 
     */
    public static final String loginName = "login_name";
    /**
     * 
     */
    public static final String loginName_alias = "loginName";

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
        COLUMN_ALIAS_MAP.put(userName, userName_alias);
        ALIAS_COLUMN_MAP.put(userName_alias, userName);
        COLUMN_ALIAS_MAP.put(loginName, loginName_alias);
        ALIAS_COLUMN_MAP.put(loginName_alias, loginName);
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

    public static final class Column extends ColumnModel<SysUserModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(SysUserModel.primaryKeyName, SysUserModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(SysUserModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 
         */
        public Column id() {
            this.addColumnAlias(SysUserModel.id, SysUserModel.id_alias);
            return this;
        }

        /**
         * 
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(SysUserModel.id, alias);
            return this;
        }

        /**
         * 
         */
        public Column userName() {
            this.addColumnAlias(SysUserModel.userName, SysUserModel.userName_alias);
            return this;
        }

        /**
         * 
         * @param alias 别名
         */
        public Column userName(String alias) {
            this.addColumnAlias(SysUserModel.userName, alias);
            return this;
        }

        /**
         * 
         */
        public Column loginName() {
            this.addColumnAlias(SysUserModel.loginName, SysUserModel.loginName_alias);
            return this;
        }

        /**
         * 
         * @param alias 别名
         */
        public Column loginName(String alias) {
            this.addColumnAlias(SysUserModel.loginName, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<SysUserModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<SysUserModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(SysUserModel.tableName, SysUserModel.tableAlias, SysUserModel.primaryKeyName);
        }
    
        /**
         * 
         */
        public OnBuilder<SysUserModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(SysUserModel.tableName, SysUserModel.tableAlias, SysUserModel.id);
        }

        /**
         * 
         */
        public OnBuilder<SysUserModel, Column, On, Where, Sort, Group> userName() {
            return this.onBuilder.handler(SysUserModel.tableName, SysUserModel.tableAlias, SysUserModel.userName);
        }

        /**
         * 
         */
        public OnBuilder<SysUserModel, Column, On, Where, Sort, Group> loginName() {
            return this.onBuilder.handler(SysUserModel.tableName, SysUserModel.tableAlias, SysUserModel.loginName);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<SysUserModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<SysUserModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(SysUserModel.tableName, SysUserModel.tableAlias, SysUserModel.primaryKeyName);
        }
    
        /**
         * 
         */
        public WhereBuilder<SysUserModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(SysUserModel.tableName, SysUserModel.tableAlias, SysUserModel.id);
        }

        /**
         * 
         */
        public WhereBuilder<SysUserModel, Column, On, Where, Sort, Group> userName() {
            return this.whereBuilder.handler(SysUserModel.tableName, SysUserModel.tableAlias, SysUserModel.userName);
        }

        /**
         * 
         */
        public WhereBuilder<SysUserModel, Column, On, Where, Sort, Group> loginName() {
            return this.whereBuilder.handler(SysUserModel.tableName, SysUserModel.tableAlias, SysUserModel.loginName);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<SysUserModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(SysUserModel.primaryKeyName);
            return this;
        }
    
        /**
         * 
         */
        public Group id() {
            this.addColumn(SysUserModel.id);
            return this;
        }

        /**
         * 
         */
        public Group userName() {
            this.addColumn(SysUserModel.userName);
            return this;
        }

        /**
         * 
         */
        public Group loginName() {
            this.addColumn(SysUserModel.loginName);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<SysUserModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<SysUserModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(SysUserModel.primaryKeyName);
        }
    
        /**
         * 
         */
        public SortBuilder<SysUserModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(SysUserModel.id);
        }

        /**
         * 
         */
        public SortBuilder<SysUserModel, Column, On, Where, Sort, Group> userName() {
            return this.sortBuilder.handler(SysUserModel.userName);
        }

        /**
         * 
         */
        public SortBuilder<SysUserModel, Column, On, Where, Sort, Group> loginName() {
            return this.sortBuilder.handler(SysUserModel.loginName);
        }

    }

}