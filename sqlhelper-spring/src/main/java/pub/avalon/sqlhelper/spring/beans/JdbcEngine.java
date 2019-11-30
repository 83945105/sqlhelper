package pub.avalon.sqlhelper.spring.beans;

import pub.avalon.beans.Pagination;
import pub.avalonframework.sqlhelper.core.data.LimitDatum;
import pub.avalonframework.sqlhelper.core.engine.AbstractEngine;
import pub.avalonframework.sqlhelper.core.sqlbuilder.*;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * jdbc 增删改查接口
 * <p>注意,凡是涉及列名请使用驼峰命名法转义后的属性名,生成的模板类有对应的常量供使用
 *
 * @author 白超
 * @version 1.0
 * @see SpringJdbcEngine
 * @since 2018/7/10
 */
@SuppressWarnings("unused")
public interface JdbcEngine {

    /**
     * 新增数据
     *
     * @param sql sql语句
     * @return
     */
    int insert(String sql);

    /**
     * 新增数据
     *
     * @param sql  sql语句
     * @param args 参数
     * @return
     */
    int insert(String sql, Object... args);

    /**
     * 更新数据
     *
     * @param sql sql语句
     * @return
     */
    int update(String sql);

    /**
     * 更新数据
     *
     * @param sql  sql语句
     * @param args 参数
     * @return
     */
    int update(String sql, Object... args);

    /**
     * 删除数据
     *
     * @param sql sql语句
     * @return
     */
    int delete(String sql);

    /**
     * 删除数据
     *
     * @param sql  sql语句
     * @param args 参数
     * @return
     */
    int delete(String sql, Object... args);

    /**
     * 复制一张表
     * <p>不会复制表的数据
     *
     * @param targetTableName 目标表名
     * @param copyData        是否复制数据
     * @param tableSqlBuilder 表引擎
     * @return 不反回任何值, 这里返回int为占位用
     */
    int copyTable(String targetTableName, boolean copyData, TableSqlBuilder tableSqlBuilder);

    /**
     * 删除表
     *
     * @param tableSqlBuilder 表引擎
     * @return 不反回任何值, 这里返回int为占位用
     */
    int deleteTable(TableSqlBuilder tableSqlBuilder);

    /**
     * 重命名表
     *
     * @param targetTableName 目标表名
     * @param tableSqlBuilder 表引擎
     * @return 不反回任何值, 这里返回 {@code int} 为占位用
     */
    int renameTable(String targetTableName, TableSqlBuilder tableSqlBuilder);

    /**
     * 查询表是否存在
     *
     * @param tableSqlBuilder 表引擎
     * @return 存在返回 {@code true}, 不存在返回 {@link false}
     */
    boolean isTableExist(TableSqlBuilder tableSqlBuilder);

    /**
     * 根据主键查询
     * <p>若查询不到对应数据,返回 {@code null}
     *
     * @param keyValue         主键值
     * @param selectSqlBuilder 用于构建SQL的字段引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 查询结果注入Map返回, key-属性名(驼峰命名法) value-属性值
     */
    Map<String, Object> queryByPrimaryKey(Object keyValue, SelectSqlBuilder selectSqlBuilder);

    /**
     * 根据主键查询
     * <p>若查询不到对应数据,返回 {@code null}
     * <p>注意,用于接收数据的容器必须具备对应查询字段(驼峰命名法)的setter方法
     *
     * @param keyValue         主键值
     * @param returnType       返回容器类型,用于接收查询结果
     * @param selectSqlBuilder 用于构建SQL的字段引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @param <T>              与returnType指定数据类型一致
     * @return 查询结果注入指定的returnType对象
     */
    <T> T queryByPrimaryKey(Object keyValue, Class<T> returnType, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询唯一一条数据
     *
     * @param sql  sql语句
     * @param args 参数
     * @return 查询结果注入Map返回, key-属性名(驼峰命名法) value-属性值
     */
    Map<String, Object> queryOne(String sql, Object... args);

    /**
     * 查询唯一一条数据
     * <p>若查询不到对应数据,返回 {@code null}
     * <p>若查询到多条数据,抛异常 {@link org.springframework.dao.IncorrectResultSizeDataAccessException}
     *
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 查询结果注入Map返回, key-属性名(驼峰命名法) value-属性值
     */
    Map<String, Object> queryOne(SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询唯一一条数据
     *
     * @param returnType 返回容器类型,用于接收查询结果
     * @param sql        sql语句
     * @param args       参数
     * @param <T>        returnType指定数据类型一致
     * @return 查询结果注入指定的returnType对象
     */
    <T> T queryOne(Class<T> returnType, String sql, Object... args);

    /**
     * 查询唯一一条数据
     * <p>若查询不到对应数据,返回 {@code null}
     * <p>若查询到多条数据,抛异常 {@link org.springframework.dao.IncorrectResultSizeDataAccessException}
     * <p>注意,用于接收数据的容器必须具备对应查询字段(驼峰命名法)的setter方法
     *
     * @param returnType       返回容器类型,用于接收查询结果
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @param <T>              与returnType指定数据类型一致
     * @return 查询结果注入指定的returnType对象
     */
    <T> T queryOne(Class<T> returnType, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询多条数据
     *
     * @param sql  sql语句
     * @param args 参数
     * @return 查询结果注入Map装入ArrayList返回, key-属性名(驼峰命名法) value-属性值
     */
    List<Map<String, Object>> queryList(String sql, Object... args);

    /**
     * 查询多条数据
     * <p>若查询不到对应数据,返回长度为0的空集合
     *
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 查询结果注入Map装入ArrayList返回, key-属性名(驼峰命名法) value-属性值
     */
    List<Map<String, Object>> queryList(SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询多条数据
     *
     * @param returnType 返回容器类型,用于接收查询结果
     * @param sql        sql语句
     * @param args       参数
     * @param <T>        与returnType指定数据类型一致
     * @return 查询结果注入指定的returnType对象装入ArrayList返回
     */
    <T> List<T> queryList(Class<T> returnType, String sql, Object... args);

    /**
     * 查询多条数据
     * <p>若查询不到对应数据,返回长度为0的空集合
     * <p>注意,用于接收数据的容器必须具备对应查询字段(驼峰命名法)的setter方法
     *
     * @param returnType       返回容器类型,用于接收查询结果
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @param <T>              与returnType指定数据类型一致
     * @return 查询结果注入指定的returnType对象装入ArrayList返回
     */
    <T> List<T> queryList(Class<T> returnType, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询总数
     *
     * @param sql  sql语句
     * @param args 参数
     * @return 总数
     */
    long queryCount(String sql, Object... args);

    /**
     * 查询总数
     * <p>若查询不到对应数据,抛异常 {@link org.springframework.dao.EmptyResultDataAccessException}
     * <p>若查询到多条数据,抛异常 {@link org.springframework.dao.IncorrectResultSizeDataAccessException}
     *
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 总数
     */
    long queryCount(SelectSqlBuilder selectSqlBuilder);

    /**
     * 分页查询
     * <p>默认方法,内部先调用 {@link #queryCount(SelectSqlBuilder)} 查询总数
     * <p>若总数为0,则直接返回结果
     * <p>若总数不为0,则根据参数构建分页对象 {@link pub.avalonframework.beans.LimitSql} 并获取分页起始号
     * <p>最后调用 {@link #queryList(SelectSqlBuilder)} 查询数据
     *
     * @param currentPage    当前页号
     * @param pageSize       每页显示条数
     * @param abstractEngine 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 分页结果 {@link PageResultForMap}
     */
    default PageResultForMap pageQueryList(long currentPage, long pageSize, AbstractEngine abstractEngine) {
        long count = this.queryCount(abstractEngine);
        Pagination pagination = new Pagination(abstractEngine.getDataBaseType(), count, currentPage, pageSize);
        PageResultForMap pageResult = new PageResultForMap();
        pageResult.setLimit(pagination);
        if (count == 0) {
            pageResult.setResult(new ArrayList<>());
            return pageResult;
        }
        abstractEngine.setLimitDatum(new LimitDatum(count, currentPage, pageSize));
        pageResult.setResult(this.queryList(abstractEngine));
        return pageResult;
    }

    /**
     * 分页查询
     * <p>默认方法,内部先调用 {@link #queryCount(SelectSqlBuilder)} 查询总数
     * <p>若总数为0,则直接返回结果
     * <p>若总数不为0,则根据参数构建分页对象 {@link Pagination} 并获取分页起始号
     * <p>最后调用 {@link #queryList(SelectSqlBuilder)} 查询数据
     *
     * @param returnType     返回容器类型,用于接收查询结果
     * @param currentPage    当前页号
     * @param pageSize       每页显示条数
     * @param abstractEngine 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @param <T>            与returnType指定数据类型一致
     * @return 分页结果 {@link PageResultForBean}
     */
    default <T> PageResultForBean<T> pageQueryList(Class<T> returnType, long currentPage, long pageSize, AbstractEngine abstractEngine) {
        long count = this.queryCount(abstractEngine);
        Pagination pagination = new Pagination(abstractEngine.getDataBaseType(), count, currentPage, pageSize);
        PageResultForBean<T> pageResult = new PageResultForBean<>();
        pageResult.setLimit(pagination);
        if (count == 0) {
            pageResult.setResult(new ArrayList<>());
            return pageResult;
        }
        abstractEngine.setLimitDatum(new LimitDatum(count, currentPage, pageSize));
        pageResult.setResult(this.queryList(returnType, abstractEngine));
        return pageResult;
    }

    /**
     * 查询一对列值存入Map
     *
     * @param keyIndex   作为key的列下标(从1开始)
     * @param valueIndex 作为value的列下标(从1开始)
     * @param sql        sql语句
     * @param args       参数
     * @param <K>        作为key的列值类型
     * @param <V>        作为value的列值类型
     * @return 查询结果注入Map返回
     */
    <K, V> Map<K, V> queryPairColumnInMap(int keyIndex, int valueIndex, String sql, Object... args);

    /**
     * 查询一对列值存入Map
     * <p>你可以使用该方法将某列值指定为key,另一列列值为value,结果集注入Map中
     * <p>注意,由于Map集合特性,作为key的列值,若重复出现,则会覆盖前者数据
     *
     * @param keyIndex         作为key的列下标(从1开始)
     * @param valueIndex       作为value的列下标(从1开始)
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @param <K>              作为key的列值类型
     * @param <V>              作为value的列值类型
     * @return 查询结果注入Map返回
     */
    <K, V> Map<K, V> queryPairColumnInMap(int keyIndex, int valueIndex, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询一对列值存入Map
     *
     * @param keyColumnName   作为key的列字段名(驼峰命名法)
     * @param valueColumnName 作为value的列字段名(驼峰命名法)
     * @param sql             sql语句
     * @param args            参数
     * @param <K>             作为key的列值类型
     * @param <V>             作为value的列值类型
     * @return 查询结果注入Map返回
     */
    <K, V> Map<K, V> queryPairColumnInMap(String keyColumnName, String valueColumnName, String sql, Object... args);

    /**
     * 查询一对列值存入Map
     * <p>你可以使用该方法将某列值指定为key,另一列列值为value,结果集注入Map中
     * <p>注意,由于Map集合特性,作为key的列值,若重复出现,则会覆盖前者数据
     *
     * @param keyColumnName    作为key的列字段名(驼峰命名法)
     * @param valueColumnName  作为value的列字段名(驼峰命名法)
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @param <K>              作为key的列值类型
     * @param <V>              作为value的列值类型
     * @return 查询结果注入Map返回
     */
    <K, V> Map<K, V> queryPairColumnInMap(String keyColumnName, String valueColumnName, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询结果存入Map
     *
     * @param keyIndex 作为key的列下标(从1开始)
     * @param sql      sql语句
     * @param args     参数
     * @param <K>      作为key的列值类型
     * @return 查询结果注入Map返回
     */
    <K> Map<K, Map<String, Object>> queryInMap(int keyIndex, String sql, Object... args);

    /**
     * 查询结果存入Map
     * <p>该方法类似于 {@link #queryPairColumnInMap(int, int, SelectSqlBuilder)}
     * <p>你可以使用该方法将某列值指定为key,然后每一行的结果数据作为value,结果集注入Map中
     * <p>注意,由于Map集合特性,作为key的列值,若重复出现,则会覆盖前者数据
     *
     * @param keyIndex         作为key的列下标(从1开始)
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @param <K>              作为key的列值类型
     * @return 查询结果注入Map返回
     */
    <K> Map<K, Map<String, Object>> queryInMap(int keyIndex, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询结果存入Map
     *
     * @param keyColumnName 作为key的列字段名(驼峰命名法)
     * @param sql           sql语句
     * @param args          参数
     * @param <K>           作为key的列值类型
     * @return 查询结果注入Map返回
     */
    <K> Map<K, Map<String, Object>> queryInMap(String keyColumnName, String sql, Object... args);

    /**
     * 查询结果存入Map
     * <p>该方法类似于 {@link #queryPairColumnInMap(int, int, SelectSqlBuilder)}
     * <p>你可以使用该方法将某列值指定为key,然后每一行的结果数据作为value,结果集注入Map中
     * <p>注意,由于Map集合特性,作为key的列值,若重复出现,则会覆盖前者数据
     *
     * @param keyColumnName    作为key的列字段名(驼峰命名法)
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @param <K>              作为key的列值类型
     * @return 查询结果注入Map返回
     */
    <K> Map<K, Map<String, Object>> queryInMap(String keyColumnName, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询结果存入Map
     *
     * @param keyIndex   作为key的列下标(从1开始)
     * @param returnType 返回容器类型,用于接收查询结果
     * @param sql        sql语句
     * @param args       参数
     * @param <K>        作为key的列值类型
     * @param <T>        与returnType指定数据类型一致
     * @return 查询结果注入Map返回
     */
    <K, T> Map<K, T> queryInMap(int keyIndex, Class<T> returnType, String sql, Object... args);

    /**
     * 查询结果存入Map
     * <p>该方法类似于 {@link #queryPairColumnInMap(int, int, SelectSqlBuilder)}
     * <p>你可以使用该方法将某列值指定为key,然后每一行的结果数据作为value,结果集注入Map中
     * <p>注意,由于Map集合特性,作为key的列值,若重复出现,则会覆盖前者数据
     *
     * @param keyIndex         作为key的列下标(从1开始)
     * @param returnType       返回容器类型,用于接收查询结果
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @param <K>              作为key的列值类型
     * @param <T>              与returnType指定数据类型一致
     * @return 查询结果注入Map返回
     */
    <K, T> Map<K, T> queryInMap(int keyIndex, Class<T> returnType, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询结果存入Map
     *
     * @param keyColumnName 作为key的列字段名(驼峰命名法)
     * @param returnType    返回容器类型,用于接收查询结果
     * @param sql           sql语句
     * @param args          参数
     * @param <K>           作为key的列值类型
     * @param <T>           与returnType指定数据类型一致
     * @return 查询结果注入Map返回
     */
    <K, T> Map<K, T> queryInMap(String keyColumnName, Class<T> returnType, String sql, Object... args);

    /**
     * 查询结果存入Map
     * <p>该方法类似于 {@link #queryPairColumnInMap(int, int, SelectSqlBuilder)}
     * <p>你可以使用该方法将某列值指定为key,然后每一行的结果数据作为value,结果集注入Map中
     * <p>注意,由于Map集合特性,作为key的列值,若重复出现,则会覆盖前者数据
     *
     * @param keyColumnName    作为key的列字段名(驼峰命名法)
     * @param returnType       返回容器类型,用于接收查询结果
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @param <K>              作为key的列值类型
     * @param <T>              与returnType指定数据类型一致
     * @return 查询结果注入Map返回
     */
    <K, T> Map<K, T> queryInMap(String keyColumnName, Class<T> returnType, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询结果根据指定列值分组存入Map
     *
     * @param keyIndex 作为key的列下标(从1开始)
     * @param sql      sql语句
     * @param args     参数
     * @param <K>      作为key的列值类型
     * @return 查询结果进入Key值分组注入Map返回
     */
    <K> Map<K, List<Map<String, Object>>> queryListInMap(int keyIndex, String sql, Object... args);

    /**
     * 查询结果根据指定列值分组存入Map
     * <p>该方法类似于 {@link #queryPairColumnInMap(int, int, SelectSqlBuilder)}
     * <p>你可以使用该方法将某列值指定为key,然后所有拥有改列值的结果数据作为value,结果集注入Map中
     *
     * @param keyIndex         作为key的列下标(从1开始)
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @param <K>              作为key的列值类型
     * @return 查询结果进入Key值分组注入Map返回
     */
    <K> Map<K, List<Map<String, Object>>> queryListInMap(int keyIndex, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询结果根据指定列值分组存入Map
     *
     * @param keyColumnName 作为key的列字段名(驼峰命名法)
     * @param sql           sql语句
     * @param args          参数
     * @param <K>           作为key的列值类型
     * @return 查询结果进入Key值分组注入Map返回
     */
    <K> Map<K, List<Map<String, Object>>> queryListInMap(String keyColumnName, String sql, Object... args);

    /**
     * 查询结果根据指定列值分组存入Map
     * <p>该方法类似于 {@link #queryPairColumnInMap(int, int, SelectSqlBuilder)}
     * <p>你可以使用该方法将某列值指定为key,然后所有拥有改列值的结果数据作为value,结果集注入Map中
     *
     * @param keyColumnName    作为key的列字段名(驼峰命名法)
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @param <K>              作为key的列值类型
     * @return 查询结果进入Key值分组注入Map返回
     */
    <K> Map<K, List<Map<String, Object>>> queryListInMap(String keyColumnName, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询结果根据指定列值分组存入Map
     *
     * @param keyIndex   作为key的列下标(从1开始)
     * @param returnType 返回容器类型,用于接收查询结果
     * @param sql        sql语句
     * @param args       参数
     * @param <K>        作为key的列值类型
     * @param <T>        与returnType指定数据类型一致
     * @return 查询结果进入Key值分组注入Map返回
     */
    <K, T> Map<K, List<T>> queryListInMap(int keyIndex, Class<T> returnType, String sql, Object... args);

    /**
     * 查询结果根据指定列值分组存入Map
     * <p>该方法类似于 {@link #queryPairColumnInMap(int, int, SelectSqlBuilder)}
     * <p>你可以使用该方法将某列值指定为key,然后所有拥有改列值的结果数据作为value,结果集注入Map中
     *
     * @param keyIndex         作为key的列下标(从1开始)
     * @param returnType       返回容器类型,用于接收查询结果
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @param <K>              作为key的列值类型
     * @param <T>              与returnType指定数据类型一致
     * @return 查询结果进入Key值分组注入Map返回
     */
    <K, T> Map<K, List<T>> queryListInMap(int keyIndex, Class<T> returnType, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询结果根据指定列值分组存入Map
     *
     * @param keyColumnName 作为key的列字段名(驼峰命名法)
     * @param returnType    返回容器类型,用于接收查询结果
     * @param sql           sql语句
     * @param args          参数
     * @param <K>           作为key的列值类型
     * @param <T>           与returnType指定数据类型一致
     * @return 查询结果进入Key值分组注入Map返回
     */
    <K, T> Map<K, List<T>> queryListInMap(String keyColumnName, Class<T> returnType, String sql, Object... args);

    /**
     * 查询结果根据指定列值分组存入Map
     * <p>该方法类似于 {@link #queryPairColumnInMap(int, int, SelectSqlBuilder)}
     * <p>你可以使用该方法将某列值指定为key,然后所有拥有改列值的结果数据作为value,结果集注入Map中
     *
     * @param keyColumnName    作为key的列字段名(驼峰命名法)
     * @param returnType       返回容器类型,用于接收查询结果
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @param <K>              作为key的列值类型
     * @param <T>              与returnType指定数据类型一致
     * @return 查询结果进入Key值分组注入Map返回
     */
    <K, T> Map<K, List<T>> queryListInMap(String keyColumnName, Class<T> returnType, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询指定列唯一一条数据
     *
     * @param columnIndex 列下标(从1开始)
     * @param sql         sql语句
     * @param args        参数
     * @return 查询结果
     */
    Object queryColumnOne(int columnIndex, String sql, Object... args);

    /**
     * 查询指定列唯一一条数据
     * <p>若查询不到对应数据,返回 {@code null}
     * <p>若查询到多条数据,抛异常 {@link org.springframework.dao.IncorrectResultSizeDataAccessException}
     *
     * @param columnIndex      列下标(从1开始)
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 查询结果
     */
    Object queryColumnOne(int columnIndex, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询指定列唯一一条数据
     *
     * @param columnName 列字段名(驼峰命名法)
     * @param sql        sql语句
     * @param args       参数
     * @return 查询结果
     */
    Object queryColumnOne(String columnName, String sql, Object... args);

    /**
     * 查询指定列唯一一条数据
     * <p>若查询不到对应数据,返回 {@code null}
     * <p>若查询到多条数据,抛异常 {@link org.springframework.dao.IncorrectResultSizeDataAccessException}
     *
     * @param columnName       列字段名(驼峰命名法)
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 查询结果
     */
    Object queryColumnOne(String columnName, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询指定列唯一一条数据
     *
     * @param columnIndex 列下标(从1开始)
     * @param columnType  列类型
     * @param sql         sql语句
     * @param args        参数
     * @param <T>         与returnType指定数据类型一致
     * @return 查询结果
     */
    <T> T queryColumnOne(int columnIndex, Class<T> columnType, String sql, Object... args);

    /**
     * 查询指定列唯一一条数据
     * <p>若查询不到对应数据,返回 {@code null}
     * <p>若查询到多条数据,抛异常 {@link org.springframework.dao.IncorrectResultSizeDataAccessException}
     *
     * @param columnIndex      列下标(从1开始)
     * @param columnType       列类型
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @param <T>              与returnType指定数据类型一致
     * @return 查询结果
     */
    <T> T queryColumnOne(int columnIndex, Class<T> columnType, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询指定列唯一一条数据
     *
     * @param columnName 列字段名(驼峰命名法)
     * @param columnType 列类型
     * @param sql        sql语句
     * @param args       参数
     * @param <T>        与returnType指定数据类型一致
     * @return 查询结果
     */
    <T> T queryColumnOne(String columnName, Class<T> columnType, String sql, Object... args);

    /**
     * 查询指定列唯一一条数据
     * <p>若查询不到对应数据,返回 {@code null}
     * <p>若查询到多条数据,抛异常 {@link org.springframework.dao.IncorrectResultSizeDataAccessException}
     *
     * @param columnName       列字段名(驼峰命名法)
     * @param columnType       列类型
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @param <T>              与returnType指定数据类型一致
     * @return 查询结果
     */
    <T> T queryColumnOne(String columnName, Class<T> columnType, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询指定列数据
     *
     * @param columnIndex 列下标(从1开始)
     * @param sql         sql语句
     * @param args        参数
     * @return 查询结果装入ArrayList
     */
    List<Object> queryColumnList(int columnIndex, String sql, Object... args);

    /**
     * 查询指定列数据
     * <p>若查询不到对应数据,返回长度为0的空集合
     *
     * @param columnIndex      列下标(从1开始)
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 查询结果装入ArrayList
     */
    List<Object> queryColumnList(int columnIndex, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询指定列数据
     *
     * @param columnName 列字段名(驼峰命名法)
     * @param sql        sql语句
     * @param args       参数
     * @return 查询结果装入ArrayList
     */
    List<Object> queryColumnList(String columnName, String sql, Object... args);

    /**
     * 查询指定列数据
     * <p>若查询不到对应数据,返回长度为0的空集合
     *
     * @param columnName       列字段名(驼峰命名法)
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 查询结果装入ArrayList
     */
    List<Object> queryColumnList(String columnName, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询指定列数据
     *
     * @param columnIndex 列下标(从1开始)
     * @param columnType  列类型
     * @param sql         sql语句
     * @param args        参数
     * @param <T>         与returnType指定数据类型一致
     * @return 查询结果装入ArrayList
     */
    <T> List<T> queryColumnList(int columnIndex, Class<T> columnType, String sql, Object... args);

    /**
     * 查询指定列数据
     * <p>若查询不到对应数据,返回长度为0的空集合
     *
     * @param columnIndex      列下标(从1开始)
     * @param columnType       列类型
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @param <T>              与returnType指定数据类型一致
     * @return 查询结果装入ArrayList
     */
    <T> List<T> queryColumnList(int columnIndex, Class<T> columnType, SelectSqlBuilder selectSqlBuilder);

    /**
     * 查询指定列数据
     *
     * @param columnName 列字段名(驼峰命名法)
     * @param columnType 列类型
     * @param sql        sql语句
     * @param args       参数
     * @param <T>        与returnType指定数据类型一致
     * @return 查询结果装入ArrayList
     */
    <T> List<T> queryColumnList(String columnName, Class<T> columnType, String sql, Object... args);

    /**
     * 查询指定列数据
     * <p>若查询不到对应数据,返回长度为0的空集合
     *
     * @param columnName       列字段名(驼峰命名法)
     * @param columnType       列类型
     * @param selectSqlBuilder 用于构建SQL的引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @param <T>              与returnType指定数据类型一致
     * @return 查询结果装入ArrayList
     */
    <T> List<T> queryColumnList(String columnName, Class<T> columnType, SelectSqlBuilder selectSqlBuilder);

    /**
     * 指定列及参数插入一条数据
     * <p>列顺序必须和参数顺序一致
     *
     * @param args             参数
     * @param insertSqlBuilder 用于构建SQL的字段引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 影响的行数
     */
    int insertArgs(Collection<?> args, InsertSqlBuilder insertSqlBuilder);

    /**
     * 使用数据容器指定列名插入一条数据
     * <p>数据容器属性如果为 {@code null},则属性对应列值也为 {@code null}
     * <p>数据容器与列名使用驼峰命名法进行映射
     *
     * @param javaBean         数据容器
     * @param insertSqlBuilder 用于构建SQL的字段引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 影响的行数
     */
    int insertJavaBean(Object javaBean, InsertSqlBuilder insertSqlBuilder);

    /**
     * 使用数据容器指定列名插入一条数据
     * <p>数据容器属性如果为 {@code null},则不插入该属性对应列
     * <p>数据容器与列名使用驼峰命名法进行映射
     *
     * @param javaBean         数据容器
     * @param insertSqlBuilder 用于构建SQL的字段引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 影响的行数
     */
    int insertJavaBeanSelective(Object javaBean, InsertSqlBuilder insertSqlBuilder);

    /**
     * 使用数据容器指定列名批量插入数据
     * <p>数据容器属性如果为 {@code null},则不插入该属性对应列
     * <p>数据容器与列名使用驼峰命名法进行映射
     *
     * @param javaBeans        数据容器
     * @param insertSqlBuilder 用于构建SQL的字段引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 影响的行数
     */
    int batchInsertJavaBeans(Collection<?> javaBeans, InsertSqlBuilder insertSqlBuilder);

    /**
     * 根据主键更新指定字段
     * <p>列顺序必须和参数顺序一致
     *
     * @param keyValue         主键值
     * @param args             参数
     * @param updateSqlBuilder 用于构建SQL的字段引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 影响的行数
     */
    int updateArgsByPrimaryKey(Object keyValue, Collection<?> args, UpdateSqlBuilder updateSqlBuilder);

    /**
     * 根据主键使用数据容器对指定字段进行更新
     * <p>数据容器属性如果为 {@code null},则属性对应列值也为 {@code null}
     * <p>数据容器与列名使用驼峰命名法进行映射
     *
     * @param keyValue         主键值
     * @param javaBean         数据容器
     * @param updateSqlBuilder 用于构建SQL的字段引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 影响的行数
     */
    int updateJavaBeanByPrimaryKey(Object keyValue, Object javaBean, UpdateSqlBuilder updateSqlBuilder);

    /**
     * 根据主键使用数据容器对指定字段进行更新
     * <p>数据容器属性如果为 {@code null},则不插入该属性对应列
     * <p>数据容器与列名使用驼峰命名法进行映射
     *
     * @param keyValue         主键值
     * @param javaBean         数据容器
     * @param updateSqlBuilder 用于构建SQL的字段引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 影响的行数
     */
    int updateJavaBeanByPrimaryKeySelective(Object keyValue, Object javaBean, UpdateSqlBuilder updateSqlBuilder);

    /**
     * 更新数据
     * <p>数据容器属性如果为 {@code null},则属性对应列值也为 {@code null}
     * <p>数据容器与列名使用驼峰命名法进行映射
     *
     * @param javaBean         数据容器
     * @param updateSqlBuilder 用于构建SQL的条件引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 影响的行数
     */
    int updateJavaBean(Object javaBean, UpdateSqlBuilder updateSqlBuilder);

    /**
     * 更新数据
     * <p>数据容器属性如果为 {@code null},则不插入该属性对应列
     * <p>数据容器与列名使用驼峰命名法进行映射
     *
     * @param javaBean         数据容器
     * @param updateSqlBuilder 用于构建SQL的条件引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 影响的行数
     */
    int updateJavaBeanSelective(Object javaBean, UpdateSqlBuilder updateSqlBuilder);

    /**
     * 根据主键批量更新数据
     * <p>数据容器对应的主键字段值不能为空
     *
     * @param javaBeans        数据容器
     * @param updateSqlBuilder 用于构建SQL的条件引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 影响的行数
     */
    int batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans, UpdateSqlBuilder updateSqlBuilder);

    /**
     * 根据数据容器更新或插入指定列数据
     * <p>数据存在执行更新,反之执行插入
     * <p>每个参数为数据容器数组或数据容器集合
     *
     * @param javaBeans        数据容器
     * @param updateSqlBuilder 用于构建SQL的字段引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 影响的行数
     */
    int updateOrInsertJavaBeans(Collection<?> javaBeans, UpdateSqlBuilder updateSqlBuilder);

    /**
     * 根据主键删除数据
     *
     * @param keyValue         主键值
     * @param deleteSqlBuilder 用于构建SQL的条件引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 影响的行数
     */
    int deleteByPrimaryKey(Object keyValue, DeleteSqlBuilder deleteSqlBuilder);

    /**
     * 根据多个主键批量删除数据
     *
     * @param keyValues        主键值
     * @param deleteSqlBuilder 用于构建SQL的条件引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 影响的行数
     */
    int batchDeleteByPrimaryKeys(Collection<?> keyValues, DeleteSqlBuilder deleteSqlBuilder);

    /**
     * 删除数据
     *
     * @param deleteSqlBuilder 用于构建SQL的条件引擎 {@link pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine}
     * @return 影响的行数
     */
    int delete(DeleteSqlBuilder deleteSqlBuilder);

}
