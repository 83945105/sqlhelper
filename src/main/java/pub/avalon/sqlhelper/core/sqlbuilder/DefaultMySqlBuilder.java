package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.holygrail.utils.ClassUtil;
import pub.avalon.sqlhelper.core.beans.SqlBuilderResult;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.exception.SqlException;

import java.util.*;

/**
 * @author 白超
 * @date 2019/5/22
 */
public class DefaultMySqlBuilder extends AbstractMySqlBuilder {

    @Override
    public SqlBuilderResult copyTable(String targetTableName, boolean copyData) {
        StringBuilder preparedStatementSql = new StringBuilder(128);
        preparedStatementSql.append("create table `")
                .append(targetTableName)
                .append("` like `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("`");
        if (copyData) {
            preparedStatementSql.append("; insert into `")
                    .append(targetTableName)
                    .append("` select * from `")
                    .append(this.sqlData.getMainTableData().getTableName())
                    .append("`");
            return new SqlBuilderResult(preparedStatementSql.toString(), new ArrayList<>(0));
        }
        return new SqlBuilderResult(preparedStatementSql.toString(), new ArrayList<>(0));
    }

    @Override
    public SqlBuilderResult deleteTable() {
        return new SqlBuilderResult(new StringBuilder(32)
                .append("drop table `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("`").toString(), new ArrayList<>(0));
    }

    @Override
    public SqlBuilderResult renameTable(String newTableName) {
        return new SqlBuilderResult(new StringBuilder(32)
                .append("rename table `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` to `")
                .append(newTableName)
                .append("`").toString(), new ArrayList<>(0));
    }

    @Override
    public SqlBuilderResult isTableExist() {
        return new SqlBuilderResult(new StringBuilder(128)
                .append("select table_name from information_schema.TABLES where table_name = '")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("' and table_schema = (select database())").toString(), new ArrayList<>(0));
    }

    @Override
    public SqlBuilderResult insertArgs(Object... args) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        preparedStatementSql.append("insert into `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` (");
        int i = 0;
        Set<ColumnDatum> columnData = this.getMainTableColumnData();
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append("`").append(columnDatum.getOwnerColumnName()).append("`");
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
        return new SqlBuilderResult(preparedStatementSql.toString(), Arrays.asList(args));
    }

    @Override
    public SqlBuilderResult insertJavaBean(Object javaBean) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = new ArrayList<>(64);
        preparedStatementSql.append("insert into `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` (");
        int i = 0;
        Set<ColumnDatum> columnData = this.getMainTableColumnData();
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append("`").append(columnDatum.getOwnerColumnName()).append("`");
            preparedStatementArgs.add(ClassUtil.getProperty(javaBean, columnDatum.getOwnerColumnAlias()));
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
        return new SqlBuilderResult(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult insertJavaBeanSelective(Object javaBean) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = new ArrayList<>(64);
        preparedStatementSql.append("insert into `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` (");
        int i = 0;
        Object value;
        Set<ColumnDatum> columnData = this.getMainTableColumnData();
        for (ColumnDatum columnDatum : columnData) {
            value = ClassUtil.getProperty(javaBean, columnDatum.getOwnerColumnAlias());
            if (value == null) {
                continue;
            }
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append("`").append(columnDatum.getOwnerColumnName()).append("`");
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
        return new SqlBuilderResult(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult batchInsertJavaBeans(Collection<?> javaBeans) {
        StringBuilder preparedStatementSql = new StringBuilder(2048);
        List<Object> preparedStatementArgs = new ArrayList<>(64 * javaBeans.size());
        preparedStatementSql.append("insert into `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` (");
        Set<ColumnDatum> columnData = this.getMainTableColumnData();
        int i = 0;
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append("`").append(columnDatum.getOwnerColumnName()).append("`");
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
                preparedStatementArgs.add(ClassUtil.getProperty(javaBean, columnDatum.getOwnerColumnAlias()));
            }
        }
        return new SqlBuilderResult(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult delete() {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = new ArrayList<>(64);
        String tableAlias = this.sqlData.getMainTableData().getTableAlias();
        preparedStatementSql.append("delete ")
                .append(tableAlias)
                .append(" from `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` ")
                .append(tableAlias);
        this.appendJoinSqlArgs(preparedStatementSql, preparedStatementArgs);
        this.appendWhereSqlArgs(preparedStatementSql, preparedStatementArgs);
        return new SqlBuilderResult(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult deleteByPrimaryKey(Object primaryKeyValue) {
        StringBuilder preparedStatementSql = new StringBuilder(128);
        List<Object> preparedStatementArgs = Collections.singletonList(primaryKeyValue);
        preparedStatementSql.append("delete from `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` where `")
                .append(this.sqlData.getMainTableData().getTableModel().getPrimaryKeyName())
                .append("` = ?");
        return new SqlBuilderResult(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult batchDeleteByPrimaryKeys(Object... primaryKeyValues) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = Arrays.asList(primaryKeyValues);
        preparedStatementSql.append("delete from `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` where `")
                .append(this.sqlData.getMainTableData().getTableModel().getPrimaryKeyName())
                .append("` in (");
        int size = primaryKeyValues.length;
        for (int i = 0; i < size; i++) {
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append("?");
        }
        preparedStatementSql.append(")");
        return new SqlBuilderResult(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult updateJavaBean(Object javaBean) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = new ArrayList<>(64);
        String tableAlias = this.sqlData.getMainTableData().getTableAlias();
        preparedStatementSql.append("update `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` ")
                .append(tableAlias);
        this.appendJoinSqlArgs(preparedStatementSql, preparedStatementArgs);
        preparedStatementSql.append(" set ");
        int i = 0;
        Set<ColumnDatum> columnData = this.getMainTableColumnData();
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append(tableAlias).append(".`").append(columnDatum.getOwnerColumnName()).append("`").append(" = ?");
            preparedStatementArgs.add(ClassUtil.getProperty(javaBean, columnDatum.getOwnerColumnAlias()));
        }
        this.appendWhereSqlArgs(preparedStatementSql, preparedStatementArgs);
        return new SqlBuilderResult(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult updateJavaBeanSelective(Object javaBean) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = new ArrayList<>(64);
        String tableAlias = this.sqlData.getMainTableData().getTableAlias();
        preparedStatementSql.append("update `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` ")
                .append(tableAlias);
        this.appendJoinSqlArgs(preparedStatementSql, preparedStatementArgs);
        preparedStatementSql.append(" set ");
        int i = 0;
        Object value;
        Set<ColumnDatum> columnData = this.getMainTableColumnData();
        for (ColumnDatum columnDatum : columnData) {
            value = ClassUtil.getProperty(javaBean, columnDatum.getOwnerColumnAlias());
            if (value == null) {
                continue;
            }
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append(tableAlias).append(".`").append(columnDatum.getOwnerColumnName()).append("`").append(" = ?");
            preparedStatementArgs.add(value);
        }
        this.appendWhereSqlArgs(preparedStatementSql, preparedStatementArgs);
        return new SqlBuilderResult(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult updateArgsByPrimaryKey(Object primaryKeyValue, Object... args) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = new ArrayList<>(args.length + 1);
        preparedStatementSql.append("update `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` set ");
        String primaryKeyName = this.sqlData.getMainTableData().getTableModel().getPrimaryKeyName();
        int i = 0;
        Set<ColumnDatum> columnData = this.getMainTableColumnData();
        for (ColumnDatum columnDatum : columnData) {
            if (columnDatum.getOwnerColumnName().equals(primaryKeyName)) {
                continue;
            }
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append("`").append(columnDatum.getOwnerColumnName()).append("`").append(" = ?");
        }
        preparedStatementSql.append(" where `")
                .append(primaryKeyName)
                .append("` = ?");
        preparedStatementArgs.addAll(Arrays.asList(args));
        preparedStatementArgs.add(primaryKeyValue);
        return new SqlBuilderResult(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = new ArrayList<>(65);
        preparedStatementSql.append("update `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` set ");
        String primaryKeyName = this.sqlData.getMainTableData().getTableModel().getPrimaryKeyName();
        int i = 0;
        Set<ColumnDatum> columnData = this.getMainTableColumnData();
        for (ColumnDatum columnDatum : columnData) {
            if (columnDatum.getOwnerColumnName().equals(primaryKeyName)) {
                continue;
            }
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append("`").append(columnDatum.getOwnerColumnName()).append("`").append(" = ?");
            preparedStatementArgs.add(ClassUtil.getProperty(javaBean, columnDatum.getOwnerColumnAlias()));
        }
        preparedStatementSql.append(" where `")
                .append(primaryKeyName)
                .append("` = ?");
        preparedStatementArgs.add(primaryKeyValue);
        return new SqlBuilderResult(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = new ArrayList<>(65);
        preparedStatementSql.append("update `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` set ");
        String primaryKeyName = this.sqlData.getMainTableData().getTableModel().getPrimaryKeyName();
        int i = 0;
        Object value;
        Set<ColumnDatum> columnData = this.getMainTableColumnData();
        for (ColumnDatum columnDatum : columnData) {
            if (columnDatum.getOwnerColumnName().equals(primaryKeyName)) {
                continue;
            }
            value = ClassUtil.getProperty(javaBean, columnDatum.getOwnerColumnAlias());
            if (value == null) {
                continue;
            }
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append("`").append(columnDatum.getOwnerColumnName()).append("`").append(" = ?");
            preparedStatementArgs.add(value);
        }
        preparedStatementSql.append(" where `")
                .append(primaryKeyName)
                .append("` = ?");
        preparedStatementArgs.add(primaryKeyValue);
        return new SqlBuilderResult(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        StringBuilder preparedStatementSql = new StringBuilder(2048);
        List<Object> preparedStatementArgs = new ArrayList<>(128);
        String tableAlias = this.sqlData.getMainTableData().getTableAlias();
        preparedStatementSql.append("update `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` ")
                .append(tableAlias);
        this.appendJoinSqlArgs(preparedStatementSql, preparedStatementArgs);
        preparedStatementSql.append(" set ");
        int i = 0;
        String primaryKeyName = this.sqlData.getMainTableData().getTableModel().getPrimaryKeyName();
        String primaryKeyAlias = this.sqlData.getMainTableData().getTableModel().getPrimaryKeyAlias();
        Object keyValue;
        StringBuilder whenSql = new StringBuilder(128);
        StringBuilder inSql = new StringBuilder(32);
        List<Object> inArgs = new ArrayList<>(64);
        Set<ColumnDatum> columnData = this.getMainTableColumnData();
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
            if (columnDatum.getOwnerColumnAlias().equals(primaryKeyAlias)) {
                continue;
            }
            // 非主键计算sql
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append(tableAlias)
                    .append(".`")
                    .append(columnDatum.getOwnerColumnName())
                    .append("`=case ")
                    .append(tableAlias)
                    .append(".`")
                    .append(primaryKeyName)
                    .append("` ")
                    .append(whenSql.toString())
                    .append(" end");
            // 非主键计算参数
            for (Object javaBean : javaBeans) {
                if (javaBean instanceof Map) {
                    preparedStatementArgs.add(((Map) javaBean).get(columnDatum.getOwnerColumnAlias()));
                } else {
                    preparedStatementArgs.add(ClassUtil.getProperty(javaBean, columnDatum.getOwnerColumnAlias()));
                }
            }
        }
        //拼接上最后的where条件
        preparedStatementSql.append(" where ")
                .append(tableAlias)
                .append(".`")
                .append(primaryKeyName)
                .append("` in (")
                .append(inSql.toString())
                .append(")");
        preparedStatementArgs.addAll(inArgs);
        return new SqlBuilderResult(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult updateOrInsertJavaBeans(Collection<?> javaBeans) {
        StringBuilder preparedStatementSql = new StringBuilder(2048);
        List<Object> preparedStatementArgs = new ArrayList<>(128);
        preparedStatementSql.append("insert into ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" (");
        int i = 0;
        StringBuilder onSql = new StringBuilder(64);
        Set<ColumnDatum> columnData = this.getMainTableColumnData();
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                preparedStatementSql.append(",");
                onSql.append(",");
            }
            preparedStatementSql.append("`").append(columnDatum.getOwnerColumnName()).append("`");
            onSql.append("`").append(columnDatum.getOwnerColumnName()).append("` = values(`").append(columnDatum.getOwnerColumnName()).append("`)");
        }
        preparedStatementSql.append(") values ");
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
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append(valueSql.toString());
            for (ColumnDatum columnDatum : columnData) {
                preparedStatementArgs.add(ClassUtil.getProperty(javaBean, columnDatum.getOwnerColumnAlias()));
            }
        }
        preparedStatementSql.append(" on duplicate key update ").append(onSql.toString());
        return new SqlBuilderResult(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult query() {
        StringBuilder preparedStatementSql = new StringBuilder(1024);
        List<Object> preparedStatementArgs = new ArrayList<>(32);
        preparedStatementSql.append("select");
        this.appendColumnSqlArgs(preparedStatementSql, preparedStatementArgs);
        preparedStatementSql.append(" from `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` ")
                .append(this.sqlData.getMainTableData().getTableAlias());
        this.appendJoinSqlArgs(preparedStatementSql, preparedStatementArgs);
        this.appendWhereSqlArgs(preparedStatementSql, preparedStatementArgs);
        this.appendGroupSqlArgs(preparedStatementSql, preparedStatementArgs);
        this.appendSortSqlArgs(preparedStatementSql, preparedStatementArgs);
        this.appendLimitSqlArgs(preparedStatementSql, preparedStatementArgs);
        return new SqlBuilderResult(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult queryCount() {
        StringBuilder preparedStatementSql = new StringBuilder(1024);
        List<Object> preparedStatementArgs = new ArrayList<>(32);
        StringBuilder groupSqlBuilder = new StringBuilder(32);
        this.appendGroupSqlArgs(groupSqlBuilder, null);
        String groupSql = groupSqlBuilder.toString();
        StringBuilder limitSqlBuilder = new StringBuilder(16);
        List<Object> limitArgs = new ArrayList<>(2);
        this.appendLimitSqlArgs(limitSqlBuilder, limitArgs);
        String limitSql = limitSqlBuilder.toString();
        boolean hasGroup = groupSql.length() > 0;
        boolean hasLimit = limitSql.length() > 0;
        if (hasGroup || hasLimit) {
            preparedStatementSql.append("select count(1) from (select ")
                    .append(this.sqlData.getMainTableData().getTableAlias())
                    .append(".`")
                    .append(this.sqlData.getMainTableData().getTableModel().getPrimaryKeyName())
                    .append("` from `");
        } else {
            preparedStatementSql.append("select count(1) from `");
        }
        preparedStatementSql.append(this.sqlData.getMainTableData().getTableName())
                .append("` ")
                .append(this.sqlData.getMainTableData().getTableAlias());
        this.appendJoinSqlArgs(preparedStatementSql, preparedStatementArgs);
        this.appendWhereSqlArgs(preparedStatementSql, preparedStatementArgs);
        if (hasGroup || hasLimit) {
            if (hasGroup) {
                preparedStatementSql.append(groupSql);
            }
            if (hasLimit) {
                preparedStatementSql.append(limitSql);
            }
            preparedStatementSql.append(") C");
        }
        return new SqlBuilderResult(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public SqlBuilderResult queryByPrimaryKey(Object primaryKeyValue) {
        StringBuilder preparedStatementSql = new StringBuilder(128);
        List<Object> preparedStatementArgs = Collections.singletonList(primaryKeyValue);
        preparedStatementSql.append("select");
        this.appendColumnSqlArgs(preparedStatementSql, preparedStatementArgs);
        preparedStatementSql.append(" from `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` ")
                .append(this.sqlData.getMainTableData().getTableAlias())
                .append(" where ")
                .append(this.sqlData.getMainTableData().getTableAlias())
                .append(".`")
                .append(this.sqlData.getMainTableData().getTableModel().getPrimaryKeyName())
                .append("` = ?");
        return new SqlBuilderResult(preparedStatementSql.toString(), preparedStatementArgs);
    }

}
