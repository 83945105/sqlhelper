package pub.avalon.sqlhelper.spring.core;

import pub.avalon.sqlhelper.spring.utils.JdbcTools;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 白超
 * @date 2018/7/3
 */
public final class PairColumnResultSetExtractor<K, V> implements ResultSetExtractor<Map<K, V>> {

    /**
     * index mode 1 => name mode
     */
    private int mode = 0;

    private int keyIndex = 1;

    private int valueIndex = 2;

    private String keyColumnName;

    private String valueColumnName;

    public PairColumnResultSetExtractor() {
    }

    public PairColumnResultSetExtractor(int keyIndex, int valueIndex) {
        this.keyIndex = keyIndex;
        this.valueIndex = valueIndex;
    }

    public PairColumnResultSetExtractor(String keyColumnName, String valueColumnName) {
        this.keyColumnName = keyColumnName;
        this.valueColumnName = valueColumnName;
        this.mode = 1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<K, V> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<K, V> result = new LinkedHashMap<>();
        Object key;
        Object value;
        if (mode == 0) {
            while (rs.next()) {
                key = null;
                value = null;
                ResultSetMetaData rsd = rs.getMetaData();
                int columnCount = rsd.getColumnCount();
                if (this.keyIndex <= columnCount) {
                    key = JdbcTools.getColumnValue(rs, this.keyIndex);
                }
                if (this.valueIndex <= columnCount) {
                    value = JdbcTools.getColumnValue(rs, this.valueIndex);
                }
                result.put((K) key, (V) value);
            }
        } else if (mode == 1) {
            String name;
            while (rs.next()) {
                key = null;
                value = null;
                ResultSetMetaData rsd = rs.getMetaData();
                int columnCount = rsd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    name = JdbcTools.getColumnKey(JdbcUtils.lookupColumnName(rsd, i));
                    if (name.equals(keyColumnName)) {
                        key = JdbcTools.getColumnValue(rs, i);
                        if (value != null) {
                            break;
                        }
                    }
                    if (name.equals(valueColumnName)) {
                        value = JdbcTools.getColumnValue(rs, i);
                        if (key != null) {
                            break;
                        }
                    }
                }
                result.put((K) key, (V) value);
            }
        }
        return result;
    }

}
