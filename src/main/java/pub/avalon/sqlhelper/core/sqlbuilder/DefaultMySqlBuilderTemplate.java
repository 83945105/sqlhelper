package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.holygrail.utils.ClassUtil;
import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.SqlBuilderResult;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.SqlDataConsumer;
import pub.avalon.sqlhelper.core.data.TableColumnDatum;
import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.helper.TableHelper;

import java.util.*;

/**
 * @author 白超
 * @date 2019/5/22
 */
public final class DefaultMySqlBuilderTemplate extends AbstractMySqlBuilderTemplate {

    public static final DefaultMySqlBuilderTemplate DEFAULT_DEFAULT_MY_SQL_BUILDER_TEMPLATE = new DefaultMySqlBuilderTemplate();

    @Override
    public SqlBuilderResult buildCopyTable(SqlDataConsumer sqlDataConsumer, String targetTableName, boolean copyData) {
        String tableName = sqlDataConsumer.getMainTableDatum().getTableName();
        StringBuilder preparedStatementSql = new StringBuilder(128);
        preparedStatementSql.append("create table `")
                .append(targetTableName)
                .append("` like `")
                .append(tableName)
                .append("`");
        if (copyData) {
            preparedStatementSql.append("; insert into `")
                    .append(targetTableName)
                    .append("` select * from `")
                    .append(tableName)
                    .append("`");
            return SqlBuilderResult.newInstance(preparedStatementSql.toString());
        }
        return SqlBuilderResult.newInstance(preparedStatementSql.toString());
    }

    @Override
    public SqlBuilderResult buildDeleteTable(SqlDataConsumer sqlDataConsumer) {
        return SqlBuilderResult.newInstance("drop table `" +
                sqlDataConsumer.getMainTableDatum().getTableName() +
                "`");
    }

    @Override
    public SqlBuilderResult buildRenameTable(SqlDataConsumer sqlDataConsumer, String newTableName) {
        return SqlBuilderResult.newInstance("rename table `" +
                sqlDataConsumer.getMainTableDatum().getTableName() +
                "` to `" +
                newTableName +
                "`");
    }

    @Override
    public SqlBuilderResult buildIsTableExist(SqlDataConsumer sqlDataConsumer) {
        return SqlBuilderResult.newInstance("select table_name from information_schema.TABLES where table_name = '" +
                sqlDataConsumer.getMainTableDatum().getTableName() +
                "' and table_schema = (select database())");
    }

    private Set<ColumnDatum> getOnlyColumnData(SqlDataConsumer sqlDataConsumer) {
        Set<ColumnDatum> columnData;
        List<TableColumnDatum> tableColumnData = sqlDataConsumer.getTableColumnData();
        if (tableColumnData == null || tableColumnData.size() == 0) {
            return BeanUtils.getColumnData(sqlDataConsumer.getMainTableDatum());
        }
        if (tableColumnData.size() > 1) {
            throw new RuntimeException("TableColumnData must only one.");
        }
        columnData = tableColumnData.iterator().next().getColumnData();
        if (columnData == null || columnData.size() == 0) {
            return BeanUtils.getColumnData(sqlDataConsumer.getMainTableDatum());
        }
        return columnData;
    }

    @Override
    public SqlBuilderResult buildInsertArgs(SqlDataConsumer sqlDataConsumer, Object... args) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = new ArrayList<>(args.length);
        preparedStatementSql.append("insert into `")
                .append(sqlDataConsumer.getMainTableDatum().getTableName())
                .append("` (");
        int i = 0;
        Set<ColumnDatum> columnData = getOnlyColumnData(sqlDataConsumer);
        for (ColumnDatum columnDatum : columnData) {
            preparedStatementArgs.add(args[i]);
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append("`").append(columnDatum.getColumnName()).append("`");
        }
        preparedStatementSql.append(") values (");
        for (; i > 0; i--) {
            if (i == 1) {
                preparedStatementSql.append("?");
            } else {
                preparedStatementSql.append("?,");
            }
        }
        preparedStatementSql.append(")");
        return SqlBuilderResult.newInstance(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult buildInsertJavaBean(SqlDataConsumer sqlDataConsumer, Object javaBean) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = new ArrayList<>(64);
        preparedStatementSql.append("insert into `")
                .append(sqlDataConsumer.getMainTableDatum().getTableName())
                .append("` (");
        int i = 0;
        Set<ColumnDatum> columnData = getOnlyColumnData(sqlDataConsumer);
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append("`").append(columnDatum.getColumnName()).append("`");
            preparedStatementArgs.add(ClassUtil.getProperty(javaBean, columnDatum.getColumnAlias()));
        }
        preparedStatementSql.append(") values (");
        for (; i > 0; i--) {
            if (i == 1) {
                preparedStatementSql.append("?");
            } else {
                preparedStatementSql.append("?,");
            }
        }
        preparedStatementSql.append(")");
        return SqlBuilderResult.newInstance(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult buildInsertJavaBeanSelective(SqlDataConsumer sqlDataConsumer, Object javaBean) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = new ArrayList<>(64);
        preparedStatementSql.append("insert into `")
                .append(sqlDataConsumer.getMainTableDatum().getTableName())
                .append("` (");
        int i = 0;
        Object value;
        Set<ColumnDatum> columnData = getOnlyColumnData(sqlDataConsumer);
        for (ColumnDatum columnDatum : columnData) {
            value = ClassUtil.getProperty(javaBean, columnDatum.getColumnAlias());
            if (value == null) {
                continue;
            }
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append("`").append(columnDatum.getColumnName()).append("`");
            preparedStatementArgs.add(value);
        }
        preparedStatementSql.append(") values (");
        for (; i > 0; i--) {
            if (i == 1) {
                preparedStatementSql.append("?");
            } else {
                preparedStatementSql.append("?,");
            }
        }
        preparedStatementSql.append(")");
        return SqlBuilderResult.newInstance(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult buildBatchInsertJavaBeans(SqlDataConsumer sqlDataConsumer, Collection<?> javaBeans) {
        StringBuilder preparedStatementSql = new StringBuilder(2048);
        List<Object> preparedStatementArgs = new ArrayList<>(64 * javaBeans.size());
        preparedStatementSql.append("insert into `")
                .append(sqlDataConsumer.getMainTableDatum().getTableName())
                .append("` (");
        int i = 0;
        Set<ColumnDatum> columnData = getOnlyColumnData(sqlDataConsumer);
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append("`").append(columnDatum.getColumnName()).append("`");
        }
        preparedStatementSql.append(") values ");
        StringBuilder valPart = new StringBuilder(34).append("(");
        for (; i > 0; i--) {
            if (i == 1) {
                valPart.append("?)");
            } else {
                valPart.append("?,");
            }
        }
        for (Object javaBean : javaBeans) {
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append(valPart.toString());
            for (ColumnDatum columnDatum : columnData) {
                preparedStatementArgs.add(ClassUtil.getProperty(javaBean, columnDatum.getColumnAlias()));
            }
        }
        return SqlBuilderResult.newInstance(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult buildDelete(SqlDataConsumer sqlDataConsumer) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = new ArrayList<>(64);
        String tableAlias = sqlDataConsumer.getMainTableDatum().getTableAlias();
        preparedStatementSql.append("delete ")
                .append(tableAlias)
                .append(" from `")
                .append(sqlDataConsumer.getMainTableDatum().getTableName())
                .append("` ")
                .append(tableAlias);
        SqlBuilderResult sqlBuilderResult = SqlBuilderResult.newInstance(preparedStatementSql.toString(), preparedStatementArgs);
        sqlBuilderResult.append(this.sqlPartBuilderTemplate.buildJoin(sqlDataConsumer));
        sqlBuilderResult.append(this.sqlPartBuilderTemplate.buildWhere(sqlDataConsumer));
        return sqlBuilderResult;
    }

    @Override
    public SqlBuilderResult buildDeleteByPrimaryKey(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue) {
        StringBuilder preparedStatementSql = new StringBuilder(128);
        List<Object> preparedStatementArgs = Collections.singletonList(primaryKeyValue);
        preparedStatementSql.append("delete from `")
                .append(sqlDataConsumer.getMainTableDatum().getTableName())
                .append("` where `")
                .append(BeanUtils.tableHelper(sqlDataConsumer.getMainTableDatum()).getPrimaryKeyName())
                .append("` = ?");
        return SqlBuilderResult.newInstance(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult buildBatchDeleteByPrimaryKeys(SqlDataConsumer sqlDataConsumer, Object... primaryKeyValues) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = Arrays.asList(primaryKeyValues);
        preparedStatementSql.append("delete from `")
                .append(sqlDataConsumer.getMainTableDatum().getTableName())
                .append("` where `")
                .append(BeanUtils.tableHelper(sqlDataConsumer.getMainTableDatum()).getPrimaryKeyName())
                .append("` in (");
        int size = primaryKeyValues.length;
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append("?");
        }
        preparedStatementSql.append(")");
        return SqlBuilderResult.newInstance(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult buildUpdateJavaBean(SqlDataConsumer sqlDataConsumer, Object javaBean) {
        SqlBuilderResult sqlBuilderResult = SqlBuilderResult.init(new StringBuilder(512), new ArrayList<>(64));
        String tableAlias = sqlDataConsumer.getMainTableDatum().getTableAlias();
        sqlBuilderResult.appendSqlPart("update `")
                .appendSqlPart(sqlDataConsumer.getMainTableDatum().getTableName())
                .appendSqlPart("` ")
                .appendSqlPart(tableAlias);
        sqlBuilderResult.append(this.sqlPartBuilderTemplate.buildJoin(sqlDataConsumer));
        sqlBuilderResult.appendSqlPart(" set ");
        int i = 0;
        Set<ColumnDatum> columnData = getOnlyColumnData(sqlDataConsumer);
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                sqlBuilderResult.appendSqlPart(",");
            }
            sqlBuilderResult.appendSqlPart(tableAlias).appendSqlPart(".`").appendSqlPart(columnDatum.getColumnName()).appendSqlPart("`").appendSqlPart(" = ?");
            sqlBuilderResult.appendArg(ClassUtil.getProperty(javaBean, columnDatum.getColumnAlias()));
        }
        sqlBuilderResult.append(this.sqlPartBuilderTemplate.buildWhere(sqlDataConsumer));
        return sqlBuilderResult;
    }

    @Override
    public SqlBuilderResult buildUpdateJavaBeanSelective(SqlDataConsumer sqlDataConsumer, Object javaBean) {
        SqlBuilderResult sqlBuilderResult = SqlBuilderResult.init(new StringBuilder(512), new ArrayList<>(64));
        String tableAlias = sqlDataConsumer.getMainTableDatum().getTableAlias();
        sqlBuilderResult.appendSqlPart("update `")
                .appendSqlPart(sqlDataConsumer.getMainTableDatum().getTableName())
                .appendSqlPart("` ")
                .appendSqlPart(tableAlias);
        sqlBuilderResult.append(this.sqlPartBuilderTemplate.buildJoin(sqlDataConsumer));
        sqlBuilderResult.appendSqlPart(" set ");
        int i = 0;
        Object value;
        Set<ColumnDatum> columnData = getOnlyColumnData(sqlDataConsumer);
        for (ColumnDatum columnDatum : columnData) {
            value = ClassUtil.getProperty(javaBean, columnDatum.getColumnAlias());
            if (value == null) {
                continue;
            }
            if (i++ > 0) {
                sqlBuilderResult.appendSqlPart(",");
            }
            sqlBuilderResult.appendSqlPart(tableAlias).appendSqlPart(".`").appendSqlPart(columnDatum.getColumnName()).appendSqlPart("`").appendSqlPart(" = ?");
            sqlBuilderResult.appendArg(value);
        }
        sqlBuilderResult.append(this.sqlPartBuilderTemplate.buildWhere(sqlDataConsumer));
        return sqlBuilderResult;
    }

    @Override
    public SqlBuilderResult buildUpdateArgsByPrimaryKey(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue, Object... args) {
        SqlBuilderResult sqlBuilderResult = SqlBuilderResult.init(new StringBuilder(512), new ArrayList<>(args.length + 1));
        sqlBuilderResult.appendSqlPart("update `")
                .appendSqlPart(sqlDataConsumer.getMainTableDatum().getTableName())
                .appendSqlPart("` set ");
        TableHelper tableHelper = BeanUtils.tableHelper(sqlDataConsumer.getMainTableDatum());
        String primaryKeyName = tableHelper.getPrimaryKeyName();
        int i = 0;
        Set<ColumnDatum> columnData = getOnlyColumnData(sqlDataConsumer);
        for (ColumnDatum columnDatum : columnData) {
            if (columnDatum.getColumnName().equals(primaryKeyName)) {
                continue;
            }
            if (i++ > 0) {
                sqlBuilderResult.appendSqlPart(",");
            }
            sqlBuilderResult.appendSqlPart("`").appendSqlPart(columnDatum.getColumnName()).appendSqlPart("`").appendSqlPart(" = ?");
        }
        sqlBuilderResult.appendSqlPart(" where `")
                .appendSqlPart(primaryKeyName)
                .appendSqlPart("` = ?");
        sqlBuilderResult.appendArgs(Arrays.asList(args));
        sqlBuilderResult.appendArg(primaryKeyValue);
        return sqlBuilderResult;
    }

    @Override
    public SqlBuilderResult buildUpdateJavaBeanByPrimaryKey(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue, Object javaBean) {
        SqlBuilderResult sqlBuilderResult = SqlBuilderResult.init(new StringBuilder(512), new ArrayList<>(65));
        sqlBuilderResult.appendSqlPart("update `")
                .appendSqlPart(sqlDataConsumer.getMainTableDatum().getTableName())
                .appendSqlPart("` set ");
        TableHelper tableHelper = BeanUtils.tableHelper(sqlDataConsumer.getMainTableDatum());
        String primaryKeyName = tableHelper.getPrimaryKeyName();
        int i = 0;
        Set<ColumnDatum> columnData = getOnlyColumnData(sqlDataConsumer);
        for (ColumnDatum columnDatum : columnData) {
            if (columnDatum.getColumnName().equals(primaryKeyName)) {
                continue;
            }
            if (i++ > 0) {
                sqlBuilderResult.appendSqlPart(",");
            }
            sqlBuilderResult.appendSqlPart("`").appendSqlPart(columnDatum.getColumnName()).appendSqlPart("`").appendSqlPart(" = ?");
            sqlBuilderResult.appendArg(ClassUtil.getProperty(javaBean, columnDatum.getColumnAlias()));
        }
        sqlBuilderResult.appendSqlPart(" where `")
                .appendSqlPart(primaryKeyName)
                .appendSqlPart("` = ?");
        sqlBuilderResult.appendArg(primaryKeyValue);
        return sqlBuilderResult;
    }

    @Override
    public SqlBuilderResult buildUpdateJavaBeanByPrimaryKeySelective(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue, Object javaBean) {
        SqlBuilderResult sqlBuilderResult = SqlBuilderResult.init(new StringBuilder(512), new ArrayList<>(65));
        sqlBuilderResult.appendSqlPart("update `")
                .appendSqlPart(sqlDataConsumer.getMainTableDatum().getTableName())
                .appendSqlPart("` set ");
        TableHelper tableHelper = BeanUtils.tableHelper(sqlDataConsumer.getMainTableDatum());
        String primaryKeyName = tableHelper.getPrimaryKeyName();
        int i = 0;
        Object value;
        Set<ColumnDatum> columnData = getOnlyColumnData(sqlDataConsumer);
        for (ColumnDatum columnDatum : columnData) {
            if (columnDatum.getColumnName().equals(primaryKeyName)) {
                continue;
            }
            value = ClassUtil.getProperty(javaBean, columnDatum.getColumnAlias());
            if (value == null) {
                continue;
            }
            if (i++ > 0) {
                sqlBuilderResult.appendSqlPart(",");
            }
            sqlBuilderResult.appendSqlPart("`").appendSqlPart(columnDatum.getColumnName()).appendSqlPart("`").appendSqlPart(" = ?");
            sqlBuilderResult.appendArg(value);
        }
        sqlBuilderResult.appendSqlPart(" where `")
                .appendSqlPart(primaryKeyName)
                .appendSqlPart("` = ?");
        sqlBuilderResult.appendArg(primaryKeyValue);
        return sqlBuilderResult;
    }

    @Override
    public SqlBuilderResult buildBatchUpdateJavaBeansByPrimaryKeys(SqlDataConsumer sqlDataConsumer, Collection<?> javaBeans) {
        SqlBuilderResult sqlBuilderResult = SqlBuilderResult.init(new StringBuilder(2048), new ArrayList<>(128));
        String tableAlias = sqlDataConsumer.getMainTableDatum().getTableAlias();
        sqlBuilderResult.appendSqlPart("update `")
                .appendSqlPart(sqlDataConsumer.getMainTableDatum().getTableName())
                .appendSqlPart("` ")
                .appendSqlPart(tableAlias);
        sqlBuilderResult.append(this.sqlPartBuilderTemplate.buildJoin(sqlDataConsumer));
        sqlBuilderResult.appendSqlPart(" set ");
        int i = 0;
        TableHelper tableHelper = BeanUtils.tableHelper(sqlDataConsumer.getMainTableDatum());
        String primaryKeyName = tableHelper.getPrimaryKeyName();
        String primaryKeyAlias = tableHelper.getPrimaryKeyAlias();
        Object keyValue;
        StringBuilder whenSql = new StringBuilder(128);
        StringBuilder inSql = new StringBuilder(32);
        List<Object> inArgs = new ArrayList<>(64);
        Set<ColumnDatum> columnData = getOnlyColumnData(sqlDataConsumer);
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
        for (ColumnDatum columnDatum : columnData) {
            // 主键略过
            if (columnDatum.getColumnAlias().equals(primaryKeyAlias)) {
                continue;
            }
            // 非主键计算sql
            if (i++ > 0) {
                sqlBuilderResult.appendSqlPart(",");
            }
            sqlBuilderResult.appendSqlPart(tableAlias)
                    .appendSqlPart(".`")
                    .appendSqlPart(columnDatum.getColumnName())
                    .appendSqlPart("`=case ")
                    .appendSqlPart(tableAlias)
                    .appendSqlPart(".`")
                    .appendSqlPart(primaryKeyName)
                    .appendSqlPart("` ")
                    .appendSqlPart(whenSql.toString())
                    .appendSqlPart(" end");
            // 非主键计算参数
            for (Object javaBean : javaBeans) {
                if (javaBean instanceof Map) {
                    sqlBuilderResult.appendArg(((Map) javaBean).get(columnDatum.getColumnAlias()));
                } else {
                    sqlBuilderResult.appendArg(ClassUtil.getProperty(javaBean, columnDatum.getColumnAlias()));
                }
            }
        }
        //拼接上最后的where条件
        sqlBuilderResult.appendSqlPart(" where ")
                .appendSqlPart(tableAlias)
                .appendSqlPart(".`")
                .appendSqlPart(primaryKeyName)
                .appendSqlPart("` in (")
                .appendSqlPart(inSql.toString())
                .appendSqlPart(")");
        sqlBuilderResult.appendArgs(inArgs);
        return sqlBuilderResult;
    }

    @Override
    public SqlBuilderResult buildUpdateOrInsertJavaBeans(SqlDataConsumer sqlDataConsumer, Collection<?> javaBeans) {
        SqlBuilderResult sqlBuilderResult = SqlBuilderResult.init(new StringBuilder(2048), new ArrayList<>(128));
        sqlBuilderResult.appendSqlPart("insert into ")
                .appendSqlPart(sqlDataConsumer.getMainTableDatum().getTableName())
                .appendSqlPart(" (");
        int i = 0;
        StringBuilder onSql = new StringBuilder(64);
        Set<ColumnDatum> columnData = getOnlyColumnData(sqlDataConsumer);
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                sqlBuilderResult.appendSqlPart(",");
                onSql.append(",");
            }
            sqlBuilderResult.appendSqlPart("`").appendSqlPart(columnDatum.getColumnName()).appendSqlPart("`");
            onSql.append("`").append(columnDatum.getColumnName()).append("` = values(`").append(columnDatum.getColumnName()).append("`)");
        }
        sqlBuilderResult.appendSqlPart(") values ");
        StringBuilder valueSql = new StringBuilder(32).append("(");
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
                sqlBuilderResult.appendSqlPart(",");
            }
            sqlBuilderResult.appendSqlPart(valueSql.toString());
            for (ColumnDatum columnDatum : columnData) {
                sqlBuilderResult.appendArg(ClassUtil.getProperty(javaBean, columnDatum.getColumnAlias()));
            }
        }
        sqlBuilderResult.appendSqlPart(" on duplicate key update ").appendSqlPart(onSql.toString());
        return sqlBuilderResult;
    }

    @Override
    public SqlBuilderResult buildQuery(SqlDataConsumer sqlDataConsumer) {
        SqlBuilderResult sqlBuilderResult = SqlBuilderResult.init(new StringBuilder(1024), new ArrayList<>(32));
        sqlBuilderResult.appendSqlPart("select");
        sqlBuilderResult.append(this.sqlPartBuilderTemplate.buildColumn(sqlDataConsumer));
        sqlBuilderResult.appendSqlPart(" from `")
                .appendSqlPart(sqlDataConsumer.getMainTableDatum().getTableName())
                .appendSqlPart("` ")
                .appendSqlPart(sqlDataConsumer.getMainTableDatum().getTableAlias());
        sqlBuilderResult.append(this.sqlPartBuilderTemplate.buildJoin(sqlDataConsumer));
        sqlBuilderResult.append(this.sqlPartBuilderTemplate.buildWhere(sqlDataConsumer));
        sqlBuilderResult.append(this.sqlPartBuilderTemplate.buildGroup(sqlDataConsumer));
        sqlBuilderResult.append(this.sqlPartBuilderTemplate.buildSort(sqlDataConsumer));
        sqlBuilderResult.append(this.sqlPartBuilderTemplate.buildLimit(sqlDataConsumer));
        return sqlBuilderResult;
    }

    @Override
    public SqlBuilderResult buildQueryCount(SqlDataConsumer sqlDataConsumer) {
        SqlBuilderResult sqlBuilderResult = SqlBuilderResult.init(new StringBuilder(1024), new ArrayList<>(32));
        SqlBuilderResult group = this.sqlPartBuilderTemplate.buildGroup(sqlDataConsumer);
        SqlBuilderResult limit = this.sqlPartBuilderTemplate.buildLimit(sqlDataConsumer);
        boolean hasGroup = group.hasPreparedStatementSql();
        boolean hasLimit = limit.hasPreparedStatementSql();
        if (hasGroup || hasLimit) {
            sqlBuilderResult.appendSqlPart("select count(1) from (select ")
                    .appendSqlPart(sqlDataConsumer.getMainTableDatum().getTableAlias())
                    .appendSqlPart(".`")
                    .appendSqlPart(BeanUtils.tableHelper(sqlDataConsumer.getMainTableDatum()).getPrimaryKeyName())
                    .appendSqlPart("` from `");
        } else {
            sqlBuilderResult.appendSqlPart("select count(1) from `");
        }
        sqlBuilderResult.appendSqlPart(sqlDataConsumer.getMainTableDatum().getTableName())
                .appendSqlPart("` ")
                .appendSqlPart(sqlDataConsumer.getMainTableDatum().getTableAlias());
        sqlBuilderResult.append(this.sqlPartBuilderTemplate.buildJoin(sqlDataConsumer));
        sqlBuilderResult.append(this.sqlPartBuilderTemplate.buildWhere(sqlDataConsumer));
        if (hasGroup || hasLimit) {
            if (hasGroup) {
                sqlBuilderResult.append(group);
            }
            if (hasLimit) {
                sqlBuilderResult.append(limit);
            }
            sqlBuilderResult.appendSqlPart(") C");
        }
        return sqlBuilderResult;
    }

    @Override
    public SqlBuilderResult buildQueryByPrimaryKey(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue) {
        SqlBuilderResult sqlBuilderResult = SqlBuilderResult.init(new StringBuilder(128), Collections.singletonList(primaryKeyValue));
        sqlBuilderResult.appendSqlPart("select");
        sqlBuilderResult.append(this.sqlPartBuilderTemplate.buildColumn(sqlDataConsumer));
        sqlBuilderResult.appendSqlPart(" from `")
                .appendSqlPart(sqlDataConsumer.getMainTableDatum().getTableName())
                .appendSqlPart("` ")
                .appendSqlPart(sqlDataConsumer.getMainTableDatum().getTableAlias())
                .appendSqlPart(" where ")
                .appendSqlPart(sqlDataConsumer.getMainTableDatum().getTableAlias())
                .appendSqlPart(".`")
                .appendSqlPart(BeanUtils.tableHelper(sqlDataConsumer.getMainTableDatum()).getPrimaryKeyName())
                .appendSqlPart("` = ?");
        return sqlBuilderResult;
    }

}
