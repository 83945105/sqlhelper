package pub.avalon.sqlhelper.spring.core;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import pub.avalon.sqlhelper.spring.utils.JdbcTools;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 白超
 * @version 1.0
 * @see
 * @since 2018/7/18
 */
public final class ListColumnResultSetExtractor<T> implements ResultSetExtractor<List<T>> {

    /**
     * index mode 1 => name mode
     */
    private int mode = 0;

    private int columnIndex = 1;

    private String columnName;

    private Class<T> valueType;

    private final int rowsExpected;

    public ListColumnResultSetExtractor(int columnIndex, Class<T> valueType) {
        this.columnIndex = columnIndex;
        this.valueType = valueType;
        this.rowsExpected = 0;
    }

    public ListColumnResultSetExtractor(int columnIndex, Class<T> valueType, int rowsExpected) {
        this.columnIndex = columnIndex;
        this.valueType = valueType;
        this.rowsExpected = rowsExpected;
    }

    public ListColumnResultSetExtractor(String columnName, Class<T> valueType) {
        this.columnName = columnName;
        this.mode = 1;
        this.valueType = valueType;
        this.rowsExpected = 0;
    }

    public ListColumnResultSetExtractor(String columnName, Class<T> valueType, int rowsExpected) {
        this.columnName = columnName;
        this.mode = 1;
        this.valueType = valueType;
        this.rowsExpected = rowsExpected;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<T> results = (this.rowsExpected > 0 ? new ArrayList<>(this.rowsExpected) : new ArrayList<>());
        Object value;
        if (mode == 0) {
            while (rs.next()) {
                value = null;
                ResultSetMetaData rsd = rs.getMetaData();
                int columnCount = rsd.getColumnCount();
                if (this.columnIndex <= columnCount) {
                    value = JdbcTools.getColumnValue(rs, this.columnIndex);
                }
                results.add(TypeUtils.cast(value, this.valueType, ParserConfig.getGlobalInstance()));
            }
        } else if (mode == 1) {
            String name;
            while (rs.next()) {
                value = null;
                ResultSetMetaData rsd = rs.getMetaData();
                int columnCount = rsd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    name = JdbcTools.getColumnKey(JdbcUtils.lookupColumnName(rsd, i));
                    if (name.equals(columnName)) {
                        value = JdbcTools.getColumnValue(rs, i);
                        break;
                    }
                }
                results.add(TypeUtils.cast(value, this.valueType, ParserConfig.getGlobalInstance()));
            }
        }
        return results;
    }

}
