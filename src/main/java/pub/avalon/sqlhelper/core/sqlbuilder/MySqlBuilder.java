package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.holygrail.utils.ClassUtil;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.*;

/**
 * @author 白超
 * @date 2019/5/22
 */
public class MySqlBuilder extends AbstractMySqlBuilder {

    public MySqlBuilder(SqlBuilderOptions sqlBuilderOptions) {
        super(sqlBuilderOptions);
    }

    @Override
    public SqlBuilder copyTable(String targetTableName, boolean copyData) {
        this.preparedStatementSql = new StringBuilder(128);
        this.preparedStatementArgs = new ArrayList<>(0);
        this.preparedStatementSql.append("create table `")
                .append(targetTableName)
                .append("` like `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("`");
        if (copyData) {
            this.preparedStatementSql.append("; insert into `")
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
        this.preparedStatementSql = new StringBuilder(32);
        this.preparedStatementArgs = new ArrayList<>(0);
        this.preparedStatementSql.append("drop table `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("`");
        return this;
    }

    @Override
    public SqlBuilder renameTable(String newTableName) {
        this.preparedStatementSql = new StringBuilder(32);
        this.preparedStatementArgs = new ArrayList<>(0);
        this.preparedStatementSql.append("rename table `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` to `")
                .append(newTableName)
                .append("`");
        return this;
    }

    @Override
    public SqlBuilder isTableExist() {
        this.preparedStatementSql = new StringBuilder(128);
        this.preparedStatementArgs = new ArrayList<>(0);
        this.preparedStatementSql.append("select table_name from information_schema.TABLES where table_name = '")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("' and table_schema = (select database())");
        return this;
    }

    @Override
    public SqlBuilder insertArgs(Object... args) {
        this.preparedStatementSql = new StringBuilder(512);
        this.preparedStatementArgs = Arrays.asList(args);
        this.preparedStatementSql.append("insert into `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` (");
        int i = 0;
        Set<ColumnDatum> columnData = this.getMainTableColumnData();
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                this.preparedStatementSql.append(",");
            }
            this.preparedStatementSql.append("`").append(columnDatum.getOwnerColumnName()).append("`");
        }
        this.preparedStatementSql.append(") values (");
        for (; i > 0; i--) {
            if (i == 1) {
                this.preparedStatementSql.append("?");
            } else {
                this.preparedStatementSql.append("?,");
            }
        }
        this.preparedStatementSql.append(")");
        return this;
    }

    @Override
    public SqlBuilder insertJavaBean(Object javaBean) {
        this.preparedStatementSql = new StringBuilder(512);
        this.preparedStatementArgs = new ArrayList<>(64);
        this.preparedStatementSql.append("insert into `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` (");
        int i = 0;
        Set<ColumnDatum> columnData = this.getMainTableColumnData();
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                this.preparedStatementSql.append(",");
            }
            this.preparedStatementSql.append("`").append(columnDatum.getOwnerColumnName()).append("`");
            this.preparedStatementArgs.add(ClassUtil.getProperty(javaBean, columnDatum.getOwnerColumnAlias()));
        }
        this.preparedStatementSql.append(") values (");
        for (; i > 0; i--) {
            if (i == 1) {
                this.preparedStatementSql.append("?");
            } else {
                this.preparedStatementSql.append("?,");
            }
        }
        this.preparedStatementSql.append(")");
        return this;
    }

    @Override
    public SqlBuilder insertJavaBeanSelective(Object javaBean) {
        this.preparedStatementSql = new StringBuilder(512);
        this.preparedStatementArgs = new ArrayList<>(64);
        this.preparedStatementSql.append("insert into `")
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
                this.preparedStatementSql.append(",");
            }
            this.preparedStatementSql.append("`").append(columnDatum.getOwnerColumnName()).append("`");
            this.preparedStatementArgs.add(value);
        }
        this.preparedStatementSql.append(") values (");
        for (; i > 0; i--) {
            if (i == 1) {
                this.preparedStatementSql.append("?");
            } else {
                this.preparedStatementSql.append("?,");
            }
        }
        this.preparedStatementSql.append(")");
        return this;
    }

    @Override
    public SqlBuilder batchInsertJavaBeans(Collection<?> javaBeans) {
        this.preparedStatementSql = new StringBuilder(2048);
        this.preparedStatementArgs = new ArrayList<>(64 * javaBeans.size());
        this.preparedStatementSql.append("insert into `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` (");
        Set<ColumnDatum> columnData = this.getMainTableColumnData();
        int i = 0;
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                this.preparedStatementSql.append(",");
            }
            this.preparedStatementSql.append("`").append(columnDatum.getOwnerColumnName()).append("`");
        }
        this.preparedStatementSql.append(") values ");
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
                this.preparedStatementSql.append(",");
            }
            this.preparedStatementSql.append(valPart.toString());
            for (ColumnDatum columnDatum : columnData) {
                this.preparedStatementArgs.add(ClassUtil.getProperty(javaBean, columnDatum.getOwnerColumnAlias()));
            }
        }
        return this;
    }

    @Override
    public SqlBuilder delete() {
        this.preparedStatementSql = new StringBuilder(512);
        this.preparedStatementArgs = new ArrayList<>(64);
        String tableAlias = this.sqlData.getMainTableData().getTableAlias();
        this.preparedStatementSql.append("delete ")
                .append(tableAlias)
                .append(" from `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` ")
                .append(tableAlias);
        this.preparedStatementSql = this.appendJoinSql(this.preparedStatementSql);
        this.preparedStatementSql = this.appendWhereSql(this.preparedStatementSql);
        return this;
    }

    @Override
    public SqlBuilder deleteByPrimaryKey(Object primaryKeyValue) {
        this.preparedStatementSql = new StringBuilder(128);
        this.preparedStatementArgs = Collections.singletonList(primaryKeyValue);
        this.preparedStatementSql.append("delete from `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` where `")
                .append(this.sqlData.getMainTableData().getTableModel().getPrimaryKeyName())
                .append("` = ?");
        return this;
    }

    @Override
    public SqlBuilder batchDeleteByPrimaryKeys(Object... primaryKeyValues) {
        this.preparedStatementSql = new StringBuilder(512);
        this.preparedStatementArgs = Arrays.asList(primaryKeyValues);
        this.preparedStatementSql.append("delete from `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` where `")
                .append(this.sqlData.getMainTableData().getTableModel().getPrimaryKeyName())
                .append("` in (");
        int size = primaryKeyValues.length;
        for (int i = 0; i < size; i++) {
            if (i++ > 0) {
                this.preparedStatementSql.append(",");
            }
            this.preparedStatementSql.append("?");
        }
        this.preparedStatementSql.append(")");
        return this;
    }

    @Override
    public SqlBuilder updateJavaBean(Object javaBean) {
        this.preparedStatementSql = new StringBuilder(512);
        this.preparedStatementArgs = new ArrayList<>(64);
        String tableAlias = this.sqlData.getMainTableData().getTableAlias();
        this.preparedStatementSql.append("update `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` ")
                .append(tableAlias);
        this.preparedStatementSql = this.appendJoinSql(this.preparedStatementSql);
        this.preparedStatementSql.append(" set ");
        int i = 0;
        Set<ColumnDatum> columnData = this.getMainTableColumnData();
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                this.preparedStatementSql.append(",");
            }
            this.preparedStatementSql.append(tableAlias).append(".`").append(columnDatum.getOwnerColumnName()).append("`").append(" = ?");
            this.preparedStatementArgs.add(ClassUtil.getProperty(javaBean, columnDatum.getOwnerColumnAlias()));
        }
        this.preparedStatementSql = this.appendWhereSql(this.preparedStatementSql);
        return this;
    }

    @Override
    public SqlBuilder updateJavaBeanSelective(Object javaBean) {
        this.preparedStatementSql = new StringBuilder(512);
        this.preparedStatementArgs = new ArrayList<>(64);
        String tableAlias = this.sqlData.getMainTableData().getTableAlias();
        this.preparedStatementSql.append("update `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` ")
                .append(tableAlias);
        this.preparedStatementSql = this.appendJoinSql(this.preparedStatementSql);
        this.preparedStatementSql.append(" set ");
        int i = 0;
        Object value;
        Set<ColumnDatum> columnData = this.getMainTableColumnData();
        for (ColumnDatum columnDatum : columnData) {
            value = ClassUtil.getProperty(javaBean, columnDatum.getOwnerColumnAlias());
            if (value == null) {
                continue;
            }
            if (i++ > 0) {
                this.preparedStatementSql.append(",");
            }
            this.preparedStatementSql.append(tableAlias).append(".`").append(columnDatum.getOwnerColumnName()).append("`").append(" = ?");
            this.preparedStatementArgs.add(value);
        }
        this.preparedStatementSql = this.appendWhereSql(this.preparedStatementSql);
        return this;
    }

    @Override
    public SqlBuilder updateArgsByPrimaryKey(Object primaryKeyValue, Object... args) {
        this.preparedStatementSql = new StringBuilder(512);
        this.preparedStatementArgs = new ArrayList<>(args.length + 1);
        this.preparedStatementSql.append("update `")
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
                this.preparedStatementSql.append(",");
            }
            this.preparedStatementSql.append("`").append(columnDatum.getOwnerColumnName()).append("`").append(" = ?");
        }
        this.preparedStatementSql.append(" where `")
                .append(primaryKeyName)
                .append("` = ?");
        this.preparedStatementArgs.addAll(Arrays.asList(args));
        this.preparedStatementArgs.add(primaryKeyValue);
        return this;
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean) {
        this.preparedStatementSql = new StringBuilder(512);
        this.preparedStatementArgs = new ArrayList<>(65);
        this.preparedStatementSql.append("update `")
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
                this.preparedStatementSql.append(",");
            }
            this.preparedStatementSql.append("`").append(columnDatum.getOwnerColumnName()).append("`").append(" = ?");
            this.preparedStatementArgs.add(ClassUtil.getProperty(javaBean, columnDatum.getOwnerColumnAlias()));
        }
        this.preparedStatementSql.append(" where `")
                .append(primaryKeyName)
                .append("` = ?");
        this.preparedStatementArgs.add(primaryKeyValue);
        return this;
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean) {
        this.preparedStatementSql = new StringBuilder(512);
        this.preparedStatementArgs = new ArrayList<>(65);
        this.preparedStatementSql.append("update `")
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
                this.preparedStatementSql.append(",");
            }
            this.preparedStatementSql.append("`").append(columnDatum.getOwnerColumnName()).append("`").append(" = ?");
            this.preparedStatementArgs.add(value);
        }
        this.preparedStatementSql.append(" where `")
                .append(primaryKeyName)
                .append("` = ?");
        this.preparedStatementArgs.add(primaryKeyValue);
        return this;
    }

    @Override
    public SqlBuilder batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        this.preparedStatementSql = new StringBuilder(2048);
        this.preparedStatementArgs = new ArrayList<>(128);
        String tableAlias = this.sqlData.getMainTableData().getTableAlias();
        this.preparedStatementSql.append("update `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` ")
                .append(tableAlias);
        this.preparedStatementSql = this.appendJoinSql(this.preparedStatementSql);
        this.preparedStatementSql.append(" set ");
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
                this.preparedStatementSql.append(",");
            }
            this.preparedStatementSql.append(tableAlias)
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
                    this.preparedStatementArgs.add(((Map) javaBean).get(columnDatum.getOwnerColumnAlias()));
                } else {
                    this.preparedStatementArgs.add(ClassUtil.getProperty(javaBean, columnDatum.getOwnerColumnAlias()));
                }
            }
        }
        //拼接上最后的where条件
        this.preparedStatementSql.append(" where ")
                .append(tableAlias)
                .append(".`")
                .append(primaryKeyName)
                .append("` in (")
                .append(inSql.toString())
                .append(")");
        this.preparedStatementArgs.addAll(inArgs);
        return this;
    }

    @Override
    public SqlBuilder updateOrInsertJavaBeans(Collection<?> javaBeans) {
        this.preparedStatementSql = new StringBuilder(2048);
        this.preparedStatementArgs = new ArrayList<>(128);
        this.preparedStatementSql.append("insert into ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" (");
        int i = 0;
        StringBuilder onSql = new StringBuilder(64);
        Set<ColumnDatum> columnData = this.getMainTableColumnData();
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                this.preparedStatementSql.append(",");
                onSql.append(",");
            }
            this.preparedStatementSql.append("`").append(columnDatum.getOwnerColumnName()).append("`");
            onSql.append("`").append(columnDatum.getOwnerColumnName()).append("` = values(`").append(columnDatum.getOwnerColumnName()).append("`)");
        }
        this.preparedStatementSql.append(") values ");
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
                this.preparedStatementSql.append(",");
            }
            this.preparedStatementSql.append(valueSql.toString());
            for (ColumnDatum columnDatum : columnData) {
                this.preparedStatementArgs.add(ClassUtil.getProperty(javaBean, columnDatum.getOwnerColumnAlias()));
            }
        }
        this.preparedStatementSql.append(" on duplicate key update ").append(onSql.toString());
        return this;
    }

    @Override
    public SqlBuilder query() {
        this.preparedStatementSql = new StringBuilder(1024);
        this.preparedStatementArgs = new ArrayList<>(32);
        this.preparedStatementSql.append("select");
        this.preparedStatementSql = this.appendColumnSql(this.preparedStatementSql);
        this.preparedStatementSql.append(" from `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` ")
                .append(this.sqlData.getMainTableData().getTableAlias());
        this.preparedStatementSql = this.appendJoinSql(this.preparedStatementSql);
        this.preparedStatementSql = this.appendWhereSql(this.preparedStatementSql);
        this.preparedStatementSql = this.appendGroupSql(this.preparedStatementSql);
        this.preparedStatementSql = this.appendSortSql(this.preparedStatementSql);
        this.preparedStatementSql = this.appendLimitSql(this.preparedStatementSql);
        return this;
    }

    @Override
    public SqlBuilder queryCount() {
        this.preparedStatementSql = new StringBuilder(1024);
        this.preparedStatementArgs = new ArrayList<>(32);
        String groupSql = this.appendGroupSql(new StringBuilder(32)).toString();
        String limitSql = this.appendLimitSql(new StringBuilder(16)).toString();
        boolean hasGroup = groupSql.length() > 0;
        boolean hasLimit = limitSql.length() > 0;
        if (hasGroup || hasLimit) {
            this.preparedStatementSql.append("select count(1) from (select ")
                    .append(this.sqlData.getMainTableData().getTableAlias())
                    .append(".`")
                    .append(this.sqlData.getMainTableData().getTableModel().getPrimaryKeyName())
                    .append("` from `");
        } else {
            this.preparedStatementSql.append("select count(1) from `");
        }
        this.preparedStatementSql.append(this.sqlData.getMainTableData().getTableName())
                .append("` ")
                .append(this.sqlData.getMainTableData().getTableAlias());
        this.preparedStatementSql = this.appendJoinSql(this.preparedStatementSql);
        this.preparedStatementSql = this.appendWhereSql(this.preparedStatementSql);
        if (hasGroup || hasLimit) {
            if (hasGroup) {
                this.preparedStatementSql.append(groupSql);
            }
            if (hasLimit) {
                this.preparedStatementSql.append(limitSql);
            }
            this.preparedStatementSql.append(") C");
        }
        return this;
    }

    @Override
    public SqlBuilder queryByPrimaryKey(Object primaryKeyValue) {
        this.preparedStatementSql = new StringBuilder(128);
        this.preparedStatementArgs = Collections.singletonList(primaryKeyValue);
        this.preparedStatementSql.append("select");
        this.preparedStatementSql = this.appendColumnSql(this.preparedStatementSql);
        this.preparedStatementSql.append(" from `")
                .append(this.sqlData.getMainTableData().getTableName())
                .append("` ")
                .append(this.sqlData.getMainTableData().getTableAlias())
                .append(" where ")
                .append(this.sqlData.getMainTableData().getTableAlias())
                .append(".`")
                .append(this.sqlData.getMainTableData().getTableModel().getPrimaryKeyName())
                .append("` = ?");
        return this;
    }
}
