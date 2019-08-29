package pub.avalon.sqlhelper.spring.core;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.engine.AbstractEngine;
import pub.avalon.sqlhelper.core.sqlbuilder.*;
import pub.avalon.sqlhelper.core.sqlbuilder.beans.*;
import pub.avalon.sqlhelper.spring.beans.JdbcEngine;
import pub.avalon.sqlhelper.spring.utils.JdbcTools;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * SpringJdbc引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class SpringJdbcEngine implements JdbcEngine {

    private String name;

    private JdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    private DataBaseType dataBaseType;

    @Override
    public int insert(String sql) {
        return this.jdbcTemplate.update(sql);
    }

    @Override
    public int insert(String sql, Object... args) {
        return this.jdbcTemplate.update(sql, args);
    }

    @Override
    public int update(String sql) {
        return this.jdbcTemplate.update(sql);
    }

    @Override
    public int update(String sql, Object... args) {
        return this.jdbcTemplate.update(sql, args);
    }

    @Override
    public int delete(String sql) {
        return this.jdbcTemplate.update(sql);
    }

    @Override
    public int delete(String sql, Object... args) {
        return this.jdbcTemplate.update(sql, args);
    }

    @Override
    public int copyTable(String targetTableName, boolean copyData, TableSqlBuilder tableSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (tableSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) tableSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        TableSqlBuilderResult tableSqlBuilderResult = tableSqlBuilder.copyTable(targetTableName, copyData);
        return this.jdbcTemplate.update(tableSqlBuilderResult.getPreparedStatementSql());
    }

    @Override
    public int deleteTable(TableSqlBuilder tableSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (tableSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) tableSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        TableSqlBuilderResult tableSqlBuilderResult = tableSqlBuilder.deleteTable();
        return this.jdbcTemplate.update(tableSqlBuilderResult.getPreparedStatementSql());
    }

    @Override
    public int renameTable(String newTableName, TableSqlBuilder tableSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (tableSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) tableSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        TableSqlBuilderResult tableSqlBuilderResult = tableSqlBuilder.renameTable(newTableName);
        return this.jdbcTemplate.update(tableSqlBuilderResult.getPreparedStatementSql());
    }

    @Override
    public boolean isTableExist(TableSqlBuilder tableSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (tableSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) tableSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        TableSqlBuilderResult tableSqlBuilderResult = tableSqlBuilder.isTableExist();
        List<Map<String, Object>> results = this.jdbcTemplate.query(tableSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(tableSqlBuilderResult.getPreparedStatementArgs()), new ListMapResultSetExtractor(1));
        return results != null && results.size() == 1 && results.get(0).size() == 1;
    }

    @Override
    public Map<String, Object> queryByPrimaryKey(Object keyValue, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.queryByPrimaryKey(keyValue);
        List<Map<String, Object>> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new ListMapResultSetExtractor(1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public <T> T queryByPrimaryKey(Object keyValue, Class<T> returnType, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.queryByPrimaryKey(keyValue);
        List<T> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new ListJavaBeanResultSetExtractor<>(returnType, 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public Map<String, Object> queryOne(String sql, Object... args) {
        List<Map<String, Object>> results = this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)), new ListMapResultSetExtractor(1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public Map<String, Object> queryOne(SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        List<Map<String, Object>> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new ListMapResultSetExtractor(1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public <T> T queryOne(Class<T> returnType, String sql, Object... args) {
        List<T> results = this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)), new ListJavaBeanResultSetExtractor<>(returnType, 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public <T> T queryOne(Class<T> returnType, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        List<T> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new ListJavaBeanResultSetExtractor<>(returnType, 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public List<Map<String, Object>> queryList(String sql, Object... args) {
        return this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)), new ListMapResultSetExtractor());
    }

    @Override
    public List<Map<String, Object>> queryList(SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new ListMapResultSetExtractor());
    }

    @Override
    public <T> List<T> queryList(Class<T> returnType, String sql, Object... args) {
        return this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)), new ListJavaBeanResultSetExtractor<>(returnType));
    }

    @Override
    public <T> List<T> queryList(Class<T> returnType, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new ListJavaBeanResultSetExtractor<>(returnType));
    }

    @Override
    public long queryCount(String sql, Object... args) {
        List<Integer> results = this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new RowMapperResultSetExtractor<>(new SingleColumnRowMapper<>(Integer.class), 1));
        return JdbcTools.countSingleResult(results);
    }

    @Override
    public long queryCount(SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.queryCount();
        List<Integer> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new RowMapperResultSetExtractor<>(new SingleColumnRowMapper<>(Integer.class), 1));
        return JdbcTools.countSingleResult(results);
    }

    @Override
    public <K, V> Map<K, V> queryPairColumnInMap(int keyIndex, int valueIndex, String sql, Object... args) {
        return this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new PairColumnResultSetExtractor<>(keyIndex, valueIndex));
    }

    @Override
    public <K, V> Map<K, V> queryPairColumnInMap(int keyIndex, int valueIndex, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new PairColumnResultSetExtractor<>(keyIndex, valueIndex));
    }

    @Override
    public <K, V> Map<K, V> queryPairColumnInMap(String keyColumnName, String valueColumnName, String sql, Object... args) {
        return this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new PairColumnResultSetExtractor<>(keyColumnName, valueColumnName));
    }

    @Override
    public <K, V> Map<K, V> queryPairColumnInMap(String keyColumnName, String valueColumnName, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new PairColumnResultSetExtractor<>(keyColumnName, valueColumnName));
    }

    @Override
    public <K> Map<K, Map<String, Object>> queryInMap(int keyIndex, String sql, Object... args) {
        return this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new ColumnMapResultSetExtractor<>(keyIndex));
    }

    @Override
    public <K> Map<K, Map<String, Object>> queryInMap(int keyIndex, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ColumnMapResultSetExtractor<>(keyIndex));
    }

    @Override
    public <K> Map<K, Map<String, Object>> queryInMap(String keyColumnName, String sql, Object... args) {
        return this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new ColumnMapResultSetExtractor<>(keyColumnName));
    }

    @Override
    public <K> Map<K, Map<String, Object>> queryInMap(String keyColumnName, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ColumnMapResultSetExtractor<>(keyColumnName));
    }

    @Override
    public <K, T> Map<K, T> queryInMap(int keyIndex, Class<T> returnType, String sql, Object... args) {
        return this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new ColumnObjectResultSetExtractor<>(keyIndex, returnType));
    }

    @Override
    public <K, T> Map<K, T> queryInMap(int keyIndex, Class<T> returnType, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ColumnObjectResultSetExtractor<>(keyIndex, returnType));
    }

    @Override
    public <K, T> Map<K, T> queryInMap(String keyColumnName, Class<T> returnType, String sql, Object... args) {
        return this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new ColumnObjectResultSetExtractor<>(keyColumnName, returnType));
    }

    @Override
    public <K, T> Map<K, T> queryInMap(String keyColumnName, Class<T> returnType, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ColumnObjectResultSetExtractor<>(keyColumnName, returnType));
    }

    @Override
    public <K> Map<K, List<Map<String, Object>>> queryListInMap(int keyIndex, String sql, Object... args) {
        return this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new ColumnMapListResultSetExtractor<>(keyIndex));
    }

    @Override
    public <K> Map<K, List<Map<String, Object>>> queryListInMap(int keyIndex, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ColumnMapListResultSetExtractor<>(keyIndex));
    }

    @Override
    public <K> Map<K, List<Map<String, Object>>> queryListInMap(String keyColumnName, String sql, Object... args) {
        return this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new ColumnMapListResultSetExtractor<>(keyColumnName));
    }

    @Override
    public <K> Map<K, List<Map<String, Object>>> queryListInMap(String keyColumnName, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ColumnMapListResultSetExtractor<>(keyColumnName));
    }

    @Override
    public <K, T> Map<K, List<T>> queryListInMap(int keyIndex, Class<T> returnType, String sql, Object... args) {
        return this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new ColumnObjectListResultSetExtractor<>(keyIndex, returnType));
    }

    @Override
    public <K, T> Map<K, List<T>> queryListInMap(int keyIndex, Class<T> returnType, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ColumnObjectListResultSetExtractor<>(keyIndex, returnType));
    }

    @Override
    public <K, T> Map<K, List<T>> queryListInMap(String keyColumnName, Class<T> returnType, String sql, Object... args) {
        return this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new ColumnObjectListResultSetExtractor<>(keyColumnName, returnType));
    }

    @Override
    public <K, T> Map<K, List<T>> queryListInMap(String keyColumnName, Class<T> returnType, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ColumnObjectListResultSetExtractor<>(keyColumnName, returnType));
    }

    @Override
    public Object queryColumnOne(int columnIndex, String sql, Object... args) {
        List<Object> results = this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new ListColumnResultSetExtractor<>(columnIndex, Object.class, 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public Object queryColumnOne(int columnIndex, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        List<Object> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ListColumnResultSetExtractor<>(columnIndex, Object.class, 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public Object queryColumnOne(String columnName, String sql, Object... args) {
        List<Object> results = this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new ListColumnResultSetExtractor<>(columnName, Object.class, 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public Object queryColumnOne(String columnName, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        List<Object> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ListColumnResultSetExtractor<>(columnName, Object.class, 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public <T> T queryColumnOne(int columnIndex, Class<T> columnType, String sql, Object... args) {
        List<T> results = this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new ListColumnResultSetExtractor<>(columnIndex, columnType, 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public <T> T queryColumnOne(int columnIndex, Class<T> columnType, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        List<T> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ListColumnResultSetExtractor<>(columnIndex, columnType, 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public <T> T queryColumnOne(String columnName, Class<T> columnType, String sql, Object... args) {
        List<T> results = this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new ListColumnResultSetExtractor<>(columnName, columnType, 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public <T> T queryColumnOne(String columnName, Class<T> columnType, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        List<T> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ListColumnResultSetExtractor<>(columnName, columnType, 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public List<Object> queryColumnList(int columnIndex, String sql, Object... args) {
        return this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new ListColumnResultSetExtractor<>(columnIndex, Object.class));
    }

    @Override
    public List<Object> queryColumnList(int columnIndex, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ListColumnResultSetExtractor<>(columnIndex, Object.class));
    }

    @Override
    public List<Object> queryColumnList(String columnName, String sql, Object... args) {
        return this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new ListColumnResultSetExtractor<>(columnName, Object.class));
    }

    @Override
    public List<Object> queryColumnList(String columnName, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ListColumnResultSetExtractor<>(columnName, Object.class));
    }

    @Override
    public <T> List<T> queryColumnList(int columnIndex, Class<T> columnType, String sql, Object... args) {
        return this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new ListColumnResultSetExtractor<>(columnIndex, columnType));
    }

    @Override
    public <T> List<T> queryColumnList(int columnIndex, Class<T> columnType, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ListColumnResultSetExtractor<>(columnIndex, columnType));
    }

    @Override
    public <T> List<T> queryColumnList(String columnName, Class<T> columnType, String sql, Object... args) {
        return this.jdbcTemplate.query(sql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(args)),
                new ListColumnResultSetExtractor<>(columnName, columnType));
    }

    @Override
    public <T> List<T> queryColumnList(String columnName, Class<T> columnType, SelectSqlBuilder selectSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (selectSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) selectSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ListColumnResultSetExtractor<>(columnName, columnType));
    }

    @Override
    public int insertArgs(Collection<?> args, InsertSqlBuilder insertSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (insertSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) insertSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        InsertSqlBuilderResult insertSqlBuilderResult = insertSqlBuilder.insertArgs(args);
        return this.jdbcTemplate.update(insertSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(insertSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int insertJavaBean(Object javaBean, InsertSqlBuilder insertSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (insertSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) insertSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        InsertSqlBuilderResult insertSqlBuilderResult = insertSqlBuilder.insertJavaBean(javaBean);
        return this.jdbcTemplate.update(insertSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(insertSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int insertJavaBeanSelective(Object javaBean, InsertSqlBuilder insertSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (insertSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) insertSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        InsertSqlBuilderResult insertSqlBuilderResult = insertSqlBuilder.insertJavaBeanSelective(javaBean);
        return this.jdbcTemplate.update(insertSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(insertSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int batchInsertJavaBeans(Collection<?> javaBeans, InsertSqlBuilder insertSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (insertSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) insertSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        InsertSqlBuilderResult insertSqlBuilderResult = insertSqlBuilder.batchInsertJavaBeans(javaBeans);
        return this.jdbcTemplate.update(insertSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(insertSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int updateArgsByPrimaryKey(Object keyValue, Collection<?> args, UpdateSqlBuilder updateSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (updateSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) updateSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        UpdateSqlBuilderResult updateSqlBuilderResult = updateSqlBuilder.updateArgsByPrimaryKey(keyValue, args);
        return this.jdbcTemplate.update(updateSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(updateSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int updateJavaBeanByPrimaryKey(Object keyValue, Object javaBean, UpdateSqlBuilder updateSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (updateSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) updateSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        UpdateSqlBuilderResult updateSqlBuilderResult = updateSqlBuilder.updateJavaBeanByPrimaryKey(keyValue, javaBean);
        return this.jdbcTemplate.update(updateSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(updateSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int updateJavaBeanByPrimaryKeySelective(Object keyValue, Object javaBean, UpdateSqlBuilder updateSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (updateSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) updateSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        UpdateSqlBuilderResult updateSqlBuilderResult = updateSqlBuilder.updateJavaBeanByPrimaryKeySelective(keyValue, javaBean);
        return this.jdbcTemplate.update(updateSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(updateSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int updateJavaBean(Object javaBean, UpdateSqlBuilder updateSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (updateSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) updateSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        UpdateSqlBuilderResult updateSqlBuilderResult = updateSqlBuilder.updateJavaBean(javaBean);
        return this.jdbcTemplate.update(updateSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(updateSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int updateJavaBeanSelective(Object javaBean, UpdateSqlBuilder updateSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (updateSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) updateSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        UpdateSqlBuilderResult updateSqlBuilderResult = updateSqlBuilder.updateJavaBeanSelective(javaBean);
        return this.jdbcTemplate.update(updateSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(updateSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans, UpdateSqlBuilder updateSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (updateSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) updateSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        UpdateSqlBuilderResult updateSqlBuilderResult = updateSqlBuilder.batchUpdateJavaBeansByPrimaryKeys(javaBeans);
        return this.jdbcTemplate.update(updateSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(updateSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int updateOrInsertJavaBeans(Collection<?> javaBeans, UpdateSqlBuilder updateSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (updateSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) updateSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        UpdateSqlBuilderResult updateSqlBuilderResult = updateSqlBuilder.updateOrInsertJavaBeans(javaBeans);
        return this.jdbcTemplate.update(updateSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(updateSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int deleteByPrimaryKey(Object keyValue, DeleteSqlBuilder deleteSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (deleteSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) deleteSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        DeleteSqlBuilderResult deleteSqlBuilderResult = deleteSqlBuilder.deleteByPrimaryKey(keyValue);
        return this.jdbcTemplate.update(deleteSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(deleteSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int batchDeleteByPrimaryKeys(Collection<?> keyValues, DeleteSqlBuilder deleteSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (deleteSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) deleteSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        DeleteSqlBuilderResult deleteSqlBuilderResult = deleteSqlBuilder.batchDeleteByPrimaryKeys(keyValues);
        return this.jdbcTemplate.update(deleteSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(deleteSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int delete(DeleteSqlBuilder deleteSqlBuilder) {
        //TODO 使用代理模式优化掉该代码
        if (deleteSqlBuilder instanceof AbstractEngine) {
            ((AbstractEngine) deleteSqlBuilder).setDataBaseType(this.dataBaseType);
        }
        DeleteSqlBuilderResult deleteSqlBuilderResult = deleteSqlBuilder.delete();
        return this.jdbcTemplate.update(deleteSqlBuilderResult.getPreparedStatementSql(),
                new CollectionArgumentPreparedStatementSetter(deleteSqlBuilderResult.getPreparedStatementArgs()));
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = jdbcTemplate.getDataSource();
        if (this.dataSource != null) {
            try {
                String driverName = dataSource.getConnection().getMetaData().getDatabaseProductName().toUpperCase();
                this.dataBaseType = DataBaseType.valueOf(driverName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public DataBaseType getDataBaseType() {
        return dataBaseType;
    }
}
