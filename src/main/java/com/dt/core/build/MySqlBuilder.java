package com.dt.core.build;

import com.avalon.holygrail.utils.ClassUtil;
import com.dt.core.data.SqlData;
import com.dt.core.norm.Model;
import com.dt.core.sql.SqlSplicer;

import java.util.*;

/**
 * MySql Sql 缓存构建器
 *
 * @author 白超
 * @date 2018/8/20
 */
public class MySqlBuilder<M extends Model> extends AbstractMySqlBuilder<M> {

    public MySqlBuilder(SqlData<M> sqlData) {
        super(sqlData);
    }

    @Override
    public SqlBuilder copyTable(String targetTableName) {
        this.sqlSplicer.clear()
                .append("create table ")
                .append(targetTableName)
                .append(" like ")
                .append(this.sqlData.getMainTableData().getTableName());
        this.sqlArgs = new ArrayList<>(0);
        return this;
    }

    @Override
    public SqlBuilder deleteTable() {
        this.sqlSplicer.clear().append("drop table ").append(this.sqlData.getMainTableData().getTableName());
        this.sqlArgs = new ArrayList<>(0);
        return this;
    }

    @Override
    public SqlBuilder renameTable(String newTableName) {
        this.sqlSplicer.clear()
                .append("rename table ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" to ")
                .append(newTableName);
        this.sqlArgs = new ArrayList<>(0);
        return this;
    }

    @Override
    public SqlBuilder isTableExist() {
        this.sqlSplicer.clear()
                .append("select count(*) from information_schema.TABLES where table_name = '")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("'");
        this.sqlArgs = new ArrayList<>(0);
        return this;
    }

    @Override
    public SqlBuilder queryByPrimaryKey(Object keyValue) {
        this.sqlSplicer.clear().append("select");
        this.appendColumnSql(this.sqlSplicer);
        this.sqlSplicer.append(" from ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" ")
                .append(this.sqlData.getMainTableData().getTableAlias())
                .append(" where ")
                .append(this.sqlData.getMainTableData().getTableAlias())
                .append(".")
                .append(this.sqlData.getMainTableData().getPrimaryKeyName())
                .append(" = ?");
        this.sqlArgs = new ArrayList<>(1);
        this.sqlArgs.add(keyValue);
        return this;
    }

    @Override
    public SqlBuilder query() {
        this.sqlArgs = new ArrayList<>(16);
        this.sqlSplicer.clear().append("select");
        this.appendColumnSql(this.sqlSplicer);
        this.sqlSplicer.append(" from ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" ")
                .append(this.sqlData.getMainTableData().getTableAlias());
        this.appendJoinSql(this.sqlSplicer);
        this.appendWhereSql(this.sqlSplicer);
        this.appendGroupSql(this.sqlSplicer);
        this.appendSortSql(this.sqlSplicer);
        this.appendLimitSql(this.sqlSplicer);
        return this;
    }

    @Override
    public SqlBuilder queryCount() {
        this.sqlArgs = new ArrayList<>(16);
        String groupSql = this.appendGroupSql(new SqlSplicer(32)).getSql();
        boolean hasGroup = groupSql.length() > 0;
        this.sqlSplicer.clear();
        if (hasGroup) {
            this.sqlSplicer.append("select count(1) from (select ")
                    .append(this.sqlData.getMainTableData().getTableAlias())
                    .append(".* from ");
        } else {
            this.sqlSplicer.append("select count(1) from ");
        }
        this.sqlSplicer.append(this.sqlData.getMainTableData().getTableName())
                .append(" ")
                .append(this.sqlData.getMainTableData().getTableAlias());
        this.appendJoinSql(this.sqlSplicer);
        this.appendWhereSql(this.sqlSplicer);
        if (hasGroup) {
            this.sqlSplicer.append(groupSql).append(") C");
        }
        return this;
    }

    @Override
    public SqlBuilder insertArgs(Object[] args) {
        this.sqlArgs = new ArrayList<>(32);
        this.sqlSplicer.clear()
                .append("insert into ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" (");
        int i = 0;
        for (Map.Entry<String, String> entry : this.getColumnAliasMap().entrySet()) {
            if (i++ > 0) {
                this.sqlSplicer.append(",");
            }
            this.sqlSplicer.append("`").append(entry.getKey()).append("`");
        }
        this.sqlSplicer.append(") values (");
        for (; i > 0; i--) {
            if (i == 1) {
                this.sqlSplicer.append("?");
            } else {
                this.sqlSplicer.append("?,");
            }
        }
        this.sqlSplicer.append(")");
        this.sqlArgs.addAll(Arrays.asList(args));
        return this;
    }

    @Override
    public SqlBuilder insertArgs(Collection<?> args) {
        this.sqlArgs = new ArrayList<>(32);
        this.sqlSplicer.clear()
                .append("insert into ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" (");
        int i = 0;
        for (Map.Entry<String, String> entry : this.getColumnAliasMap().entrySet()) {
            if (i++ > 0) {
                this.sqlSplicer.append(",");
            }
            this.sqlSplicer.append("`").append(entry.getKey()).append("`");
        }
        this.sqlSplicer.append(") values (");
        for (; i > 0; i--) {
            if (i == 1) {
                this.sqlSplicer.append("?");
            } else {
                this.sqlSplicer.append("?,");
            }
        }
        this.sqlSplicer.append(")");
        this.sqlArgs.addAll(args);
        return this;
    }

    @Override
    public SqlBuilder insertJavaBean(Object record) {
        this.sqlArgs = new ArrayList<>(32);
        this.sqlSplicer.clear()
                .append("insert into ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" (");
        int i = 0;
        for (Map.Entry<String, String> entry : this.getColumnAliasMap().entrySet()) {
            if (i++ > 0) {
                this.sqlSplicer.append(",");
            }
            this.sqlSplicer.append("`").append(entry.getKey()).append("`");
            this.sqlArgs.add(ClassUtil.getProperty(record, entry.getValue()));
        }
        this.sqlSplicer.append(") values (");
        for (; i > 0; i--) {
            if (i == 1) {
                this.sqlSplicer.append("?");
            } else {
                this.sqlSplicer.append("?,");
            }
        }
        this.sqlSplicer.append(")");
        return this;
    }

    @Override
    public SqlBuilder insertJavaBeanSelective(Object record) {
        this.sqlArgs = new ArrayList<>(32);
        this.sqlSplicer.clear()
                .append("insert into ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" (");
        int i = 0;
        Object value;
        for (Map.Entry<String, String> entry : this.getColumnAliasMap().entrySet()) {
            value = ClassUtil.getProperty(record, entry.getValue());
            if (value == null) {
                continue;
            }
            if (i++ != 0) {
                this.sqlSplicer.append(",");
            }
            this.sqlSplicer.append("`").append(entry.getKey()).append("`");
            this.sqlArgs.add(value);
        }
        this.sqlSplicer.append(") values (");
        for (; i > 0; i--) {
            if (i == 1) {
                this.sqlSplicer.append("?");
            } else {
                this.sqlSplicer.append("?,");
            }
        }
        this.sqlSplicer.append(")");
        return this;
    }

    @Override
    public SqlBuilder batchInsertJavaBeans(Object[] records) {
        this.sqlArgs = new ArrayList<>(32 * records.length);
        this.sqlSplicer.clear()
                .append("insert into ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" (");
        Set<Map.Entry<String, String>> entrySet = this.getColumnAliasMap().entrySet();
        int i = 0;
        for (Map.Entry<String, String> entry : entrySet) {
            if (i++ > 0) {
                this.sqlSplicer.append(",");
            }
            this.sqlSplicer.append("`").append(entry.getKey()).append("`");
        }
        this.sqlSplicer.append(") values ");
        SqlSplicer valPart = new SqlSplicer(34).append("(");
        for (; i > 0; i--) {
            if (i == 1) {
                valPart.append("?)");
            } else {
                valPart.append("?,");
            }
        }
        for (Object record : records) {
            if (i++ > 0) {
                this.sqlSplicer.append(",");
            }
            this.sqlSplicer.append(valPart.getSql());
            for (Map.Entry<String, String> entry : entrySet) {
                this.sqlArgs.add(ClassUtil.getProperty(record, entry.getKey()));
            }
        }
        return this;
    }

    @Override
    public SqlBuilder batchInsertJavaBeans(Collection<?> records) {
        this.sqlArgs = new ArrayList<>(32 * records.size());
        this.sqlSplicer.clear()
                .append("insert into ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" (");
        Set<Map.Entry<String, String>> entrySet = this.getColumnAliasMap().entrySet();
        int i = 0;
        for (Map.Entry<String, String> entry : entrySet) {
            if (i++ > 0) {
                this.sqlSplicer.append(",");
            }
            this.sqlSplicer.append("`").append(entry.getKey()).append("`");
        }
        this.sqlSplicer.append(") values ");
        SqlSplicer valPart = new SqlSplicer(34).append("(");
        for (; i > 0; i--) {
            if (i == 1) {
                valPart.append("?)");
            } else {
                valPart.append("?,");
            }
        }
        for (Object record : records) {
            if (i++ > 0) {
                this.sqlSplicer.append(",");
            }
            this.sqlSplicer.append(valPart.getSql());
            for (Map.Entry<String, String> entry : entrySet) {
                this.sqlArgs.add(ClassUtil.getProperty(record, entry.getKey()));
            }
        }
        return this;
    }

    @Override
    public SqlBuilder updateArgsByPrimaryKey(Object keyValue, Object[] args) {

        return null;
    }

    @Override
    public SqlBuilder updateArgsByPrimaryKey(Object keyValue, Collection<?> args) {
        return null;
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKey(Object keyValue, Object record) {
        return null;
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKeySelective(Object keyValue, Object record) {
        return null;
    }

    @Override
    public SqlBuilder updateJavaBean(Object record) {
        return null;
    }

    @Override
    public SqlBuilder updateJavaBeanSelective(Object record) {
        return null;
    }

    @Override
    public SqlBuilder batchUpdateJavaBeansByPrimaryKeys(Object[] records) {
        return null;
    }

    @Override
    public SqlBuilder batchUpdateJavaBeansByPrimaryKeys(Collection<?> records) {
        return null;
    }

    @Override
    public SqlBuilder updateOrInsertArgs(Object[] batchArgs) {
        return null;
    }

    @Override
    public SqlBuilder updateOrInsertArgs(Collection<?> batchArgs) {
        return null;
    }

    @Override
    public SqlBuilder updateOrInsertJavaBeans(Object[] records) {
        return null;
    }

    @Override
    public SqlBuilder updateOrInsertJavaBeans(Collection<?> records) {
        return null;
    }

    @Override
    public SqlBuilder deleteByPrimaryKey(Object keyValue) {
        return null;
    }

    @Override
    public SqlBuilder batchDeleteByPrimaryKeys(Object[] keyValues) {
        return null;
    }

    @Override
    public SqlBuilder batchDeleteByPrimaryKeys(Collection<?> keyValues) {
        return null;
    }

    @Override
    public SqlBuilder delete() {
        return null;
    }
}
