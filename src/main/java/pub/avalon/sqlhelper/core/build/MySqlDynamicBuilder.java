package pub.avalon.sqlhelper.core.build;

import pub.avalon.holygrail.utils.ClassUtil;
import pub.avalon.sqlhelper.core.data.SqlData;
import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.norm.Model;
import pub.avalon.sqlhelper.core.sql.SqlSplicer;

import java.util.*;

/**
 * MySql Sql 构建器
 *
 * @author 白超
 * @date 2018/8/20
 */
public class MySqlDynamicBuilder<M extends Model> extends AbstractMySqlBuilder<M> {

    public MySqlDynamicBuilder(SqlData<M> sqlData) {
        super(sqlData);
    }

    @Override
    public SqlBuilder copyTable(String targetTableName, boolean copyData) {
        this.sqlSplicer.clear()
                .append("create table `")
                .append(targetTableName)
                .append("` like `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("`");
        this.sqlArgs = new ArrayList<>(0);
        if (copyData) {
            this.sqlSplicer.append("; insert into `")
                    .append(targetTableName)
                    .append("` select * from `")
                    .append(this.sqlData.getMainTableData().getTableName())
                    .append("`");
            return this;
        }
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
                .append("select table_name from information_schema.TABLES where table_name = '")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("' and table_schema = (select database())");
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
                .append(".`")
                .append(this.sqlData.getMainTableData().getPrimaryKeyName())
                .append("` = ?");
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
    public SqlBuilder insertJavaBean(Object javaBean) {
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
            this.sqlArgs.add(ClassUtil.getProperty(javaBean, entry.getValue()));
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
    public SqlBuilder insertJavaBeanSelective(Object javaBean) {
        this.sqlArgs = new ArrayList<>(32);
        this.sqlSplicer.clear()
                .append("insert into ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" (");
        int i = 0;
        Object value;
        for (Map.Entry<String, String> entry : this.getColumnAliasMap().entrySet()) {
            value = ClassUtil.getProperty(javaBean, entry.getValue());
            if (value == null) {
                continue;
            }
            if (i++ > 0) {
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
    public SqlBuilder batchInsertJavaBeans(Collection<?> javaBeans) {
        this.sqlArgs = new ArrayList<>(32 * javaBeans.size());
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
        for (Object javaBean : javaBeans) {
            if (i++ > 0) {
                this.sqlSplicer.append(",");
            }
            this.sqlSplicer.append(valPart.getSql());
            for (Map.Entry<String, String> entry : entrySet) {
                this.sqlArgs.add(ClassUtil.getProperty(javaBean, entry.getValue()));
            }
        }
        return this;
    }

    @Override
    public SqlBuilder updateArgsByPrimaryKey(Object keyValue, Collection<?> args) {
        this.sqlSplicer.clear()
                .append("update ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" set ");
        String primaryKeyName = this.sqlData.getMainTableData().getPrimaryKeyName();
        int i = 0;
        for (Map.Entry<String, String> entry : this.getColumnAliasMap().entrySet()) {
            if (entry.getKey().equals(primaryKeyName)) {
                continue;
            }
            if (i++ > 0) {
                this.sqlSplicer.append(",");
            }
            this.sqlSplicer.append("`").append(entry.getKey()).append("`").append(" = ?");
        }
        this.sqlSplicer.append(" where `")
                .append(primaryKeyName)
                .append("` = ?");
        this.sqlArgs = new ArrayList<>(args.size() + 1);
        this.sqlArgs.addAll(args);
        this.sqlArgs.add(keyValue);
        return this;
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKey(Object keyValue, Object javaBean) {
        this.sqlArgs = new ArrayList<>(37);
        this.sqlSplicer.clear()
                .append("update ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" set ");
        String primaryKeyName = this.sqlData.getMainTableData().getPrimaryKeyName();
        int i = 0;
        for (Map.Entry<String, String> entry : this.getColumnAliasMap().entrySet()) {
            if (entry.getKey().equals(primaryKeyName)) {
                continue;
            }
            if (i++ > 0) {
                this.sqlSplicer.append(",");
            }
            this.sqlSplicer.append("`").append(entry.getKey()).append("`").append(" = ?");
            this.sqlArgs.add(ClassUtil.getProperty(javaBean, entry.getValue()));
        }
        this.sqlSplicer.append(" where `")
                .append(primaryKeyName)
                .append("` = ?");
        this.sqlArgs.add(keyValue);
        return this;
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKeySelective(Object keyValue, Object javaBean) {
        this.sqlArgs = new ArrayList<>(37);
        this.sqlSplicer.clear()
                .append("update ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" set ");
        String primaryKeyName = this.sqlData.getMainTableData().getPrimaryKeyName();
        int i = 0;
        Object value;
        for (Map.Entry<String, String> entry : this.getColumnAliasMap().entrySet()) {
            if (entry.getKey().equals(primaryKeyName)) {
                continue;
            }
            value = ClassUtil.getProperty(javaBean, entry.getValue());
            if (value == null) {
                continue;
            }
            if (i++ > 0) {
                this.sqlSplicer.append(",");
            }
            this.sqlSplicer.append("`").append(entry.getKey()).append("`").append(" = ?");
            this.sqlArgs.add(value);
        }
        this.sqlSplicer.append(" where `")
                .append(primaryKeyName)
                .append("` = ?");
        this.sqlArgs.add(keyValue);
        return this;
    }

    @Override
    public SqlBuilder updateJavaBean(Object javaBean) {
        this.sqlArgs = new ArrayList<>(36);
        String tableAlias = this.sqlData.getMainTableData().getTableAlias();
        this.sqlSplicer.clear()
                .append("update ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" ")
                .append(tableAlias);
        this.appendJoinSql(this.sqlSplicer);
        this.sqlSplicer.append(" set ");
        int i = 0;
        for (Map.Entry<String, String> entry : this.getColumnAliasMap().entrySet()) {
            if (i++ > 0) {
                this.sqlSplicer.append(",");
            }
            this.sqlSplicer.append(tableAlias).append(".`").append(entry.getKey()).append("`").append(" = ?");
            this.sqlArgs.add(ClassUtil.getProperty(javaBean, entry.getValue()));
        }
        this.appendWhereSql(this.sqlSplicer);
        return this;
    }

    @Override
    public SqlBuilder updateJavaBeanSelective(Object javaBean) {
        this.sqlArgs = new ArrayList<>(36);
        String tableAlias = this.sqlData.getMainTableData().getTableAlias();
        this.sqlSplicer.clear()
                .append("update ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" ")
                .append(tableAlias);
        this.appendJoinSql(this.sqlSplicer);
        this.sqlSplicer.append(" set ");
        int i = 0;
        Object value;
        for (Map.Entry<String, String> entry : this.getColumnAliasMap().entrySet()) {
            value = ClassUtil.getProperty(javaBean, entry.getValue());
            if (value == null) {
                continue;
            }
            if (i++ > 0) {
                this.sqlSplicer.append(",");
            }
            this.sqlSplicer.append(tableAlias).append(".`").append(entry.getKey()).append("`").append(" = ?");
            this.sqlArgs.add(value);
        }
        this.appendWhereSql(this.sqlSplicer);
        return this;
    }

    @Override
    public SqlBuilder batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        this.sqlArgs = new ArrayList<>(128);
        String tableAlias = this.sqlData.getMainTableData().getTableAlias();
        this.sqlSplicer.clear()
                .append("update ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" ")
                .append(tableAlias);
        this.appendJoinSql(this.sqlSplicer);
        this.sqlSplicer.append(" set ");
        int i = 0;
        String primaryKeyName = this.sqlData.getMainTableData().getPrimaryKeyName();
        String primaryKeyAlias = this.sqlData.getMainTableData().getPrimaryKeyAlias();
        Object keyValue;
        SqlSplicer whenSql = new SqlSplicer(128);
        SqlSplicer inSql = new SqlSplicer(32);
        List<Object> inArgs = new ArrayList<>(64);
        Set<Map.Entry<String, String>> entrySet = this.getColumnAliasMap().entrySet();
        // 遍历所有bean,计算出where条件的sql和参数
        // 计算出when条件sql
        for (Object javaBean : javaBeans) {
            if (javaBean instanceof Map) {
                keyValue = ((Map) javaBean).get(primaryKeyAlias);
                if (i++ > 0) {
                    inSql.append(",");
                }
                inSql.append("?");
                inArgs.add(keyValue);
            } else {
                keyValue = ClassUtil.getProperty(javaBean, primaryKeyAlias);
                if (i++ > 0) {
                    inSql.append(",");
                }
                inSql.append("?");
                inArgs.add(keyValue);
            }
            if (keyValue == null) {
                throw new SqlException("the primaryKey value can not be null.");
            }
            whenSql.append("when '").append(keyValue.toString()).append("' then ? ");
        }
        i = 0;
        //遍历所有属性
        for (Map.Entry<String, String> entry : entrySet) {
            // 主键略过
            if (entry.getValue().equals(primaryKeyAlias)) {
                continue;
            }
            // 非主键计算sql
            if (i++ > 0) {
                this.sqlSplicer.append(",");
            }
            this.sqlSplicer.append(tableAlias)
                    .append(".`")
                    .append(entry.getKey())
                    .append("`=case ")
                    .append(tableAlias)
                    .append(".`")
                    .append(primaryKeyName)
                    .append("` ")
                    .append(whenSql.getSql())
                    .append(" end");
            // 非主键计算参数
            for (Object javaBean : javaBeans) {
                if (javaBean instanceof Map) {
                    this.sqlArgs.add(((Map) javaBean).get(entry.getValue()));
                } else {
                    this.sqlArgs.add(ClassUtil.getProperty(javaBean, entry.getValue()));
                }
            }
        }
        //拼接上最后的where条件
        this.sqlSplicer.append(" where ")
                .append(tableAlias)
                .append(".`")
                .append(primaryKeyName)
                .append("` in (")
                .append(inSql.getSql())
                .append(")");
        this.sqlArgs.addAll(inArgs);
        return this;
    }

    @Override
    public SqlBuilder updateOrInsertJavaBeans(Collection<?> javaBeans) {
        this.sqlArgs = new ArrayList<>(128);
        this.sqlSplicer.clear()
                .append("insert into ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" (");
        int i = 0;
        SqlSplicer onSql = new SqlSplicer(64);
        Set<Map.Entry<String, String>> entrySet = this.getColumnAliasMap().entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            if (i++ > 0) {
                this.sqlSplicer.append(",");
                onSql.append(",");
            }
            this.sqlSplicer.append("`").append(entry.getKey()).append("`");
            onSql.append("`").append(entry.getKey()).append("` = values(`").append(entry.getKey()).append("`)");
        }
        this.sqlSplicer.append(") values ");
        SqlSplicer valueSql = new SqlSplicer(32).append("(");
        for (; i > 0; i--) {
            if (i == 1) {
                valueSql.append("?)");
            } else {
                valueSql.append("?,");
            }
        }
        i = 0;
        for (Object javaBean : javaBeans) {
            if (i++ > 0) {
                this.sqlSplicer.append(",");
            }
            this.sqlSplicer.append(valueSql.getSql());
            for (Map.Entry<String, String> entry : entrySet) {
                this.sqlArgs.add(ClassUtil.getProperty(javaBean, entry.getValue()));
            }
        }
        this.sqlSplicer.append(" on duplicate key update ").append(onSql.getSql());
        return this;
    }

    @Override
    public SqlBuilder deleteByPrimaryKey(Object keyValue) {
        this.sqlSplicer.clear()
                .append("delete from ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" where ")
                .append(this.sqlData.getMainTableData().getPrimaryKeyName())
                .append(" = ?");
        this.sqlArgs = new ArrayList<>(1);
        this.sqlArgs.add(keyValue);
        return this;
    }

    @Override
    public SqlBuilder batchDeleteByPrimaryKeys(Collection<?> keyValues) {
        this.sqlArgs = new ArrayList<>(keyValues.size());
        this.sqlSplicer.clear()
                .append("delete from ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" where ")
                .append(this.sqlData.getMainTableData().getPrimaryKeyName())
                .append(" in (");
        int i = 0;
        for (Object keyValue : keyValues) {
            if (i++ > 0) {
                this.sqlSplicer.append(",");
            }
            this.sqlSplicer.append("?");
            this.sqlArgs.add(keyValue);
        }
        this.sqlSplicer.append(")");
        return this;
    }

    @Override
    public SqlBuilder delete() {
        this.sqlArgs = new ArrayList<>(32);
        String tableAlias = this.sqlData.getMainTableData().getTableAlias();
        this.sqlSplicer.clear()
                .append("delete ")
                .append(tableAlias)
                .append(" from ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" ")
                .append(tableAlias);
        this.appendJoinSql(this.sqlSplicer);
        this.appendWhereSql(this.sqlSplicer);
        return this;
    }
}
