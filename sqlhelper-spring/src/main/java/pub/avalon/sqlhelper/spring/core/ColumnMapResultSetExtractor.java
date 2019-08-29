package pub.avalon.sqlhelper.spring.core;

import pub.avalon.sqlhelper.spring.utils.JdbcTools;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 白超
 * @date 2018/7/3
 */
public final class ColumnMapResultSetExtractor<K> implements ResultSetExtractor<Map<K, Map<String, Object>>> {

    /**
     * index mode 1 => name mode
     */
    private int mode = 0;

    private int keyIndex = 1;

    private String keyColumnName;

    public ColumnMapResultSetExtractor(int keyIndex) {
        this.keyIndex = keyIndex;
    }

    public ColumnMapResultSetExtractor(String keyColumnName) {
        this.keyColumnName = keyColumnName;
        this.mode = 1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<K, Map<String, Object>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<K, Map<String, Object>> result = new LinkedHashMap<>();
        Object key;
        Map<String, Object> value;
        if (mode == 0) {
            String name;
            while (rs.next()) {
                key = null;
                value = new HashMap<>(16);
                ResultSetMetaData rsd = rs.getMetaData();
                int columnCount = rsd.getColumnCount();
                if (this.keyIndex <= columnCount) {
                    key = JdbcTools.getColumnValue(rs, this.keyIndex);
                }
                for (int i = 1; i <= columnCount; i++) {
                    name = JdbcTools.getColumnKey(JdbcUtils.lookupColumnName(rsd, i));
                    value.put(name, JdbcTools.getColumnValue(rs, i));
                }
                result.put((K) key, value);
            }
        } else if (mode == 1) {
            String name;
            while (rs.next()) {
                key = null;
                value = new HashMap<>(16);
                ResultSetMetaData rsd = rs.getMetaData();
                int columnCount = rsd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    name = JdbcTools.getColumnKey(JdbcUtils.lookupColumnName(rsd, i));
                    if (name.equals(keyColumnName)) {
                        key = JdbcTools.getColumnValue(rs, i);
                    }
                    value.put(name, JdbcTools.getColumnValue(rs, i));
                }
                result.put((K) key, value);
            }
        }
        return result;
    }

}
