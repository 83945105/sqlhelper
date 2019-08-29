package pub.avalon.sqlhelper.spring.core;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import pub.avalon.holygrail.utils.ClassUtil;
import pub.avalon.sqlhelper.spring.utils.JdbcTools;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 白超
 * @date 2018/7/3
 */
public final class ColumnObjectListResultSetExtractor<K, T> implements ResultSetExtractor<Map<K, List<T>>> {

    /**
     * index mode 1 => name mode
     */
    private int mode = 0;

    private int keyIndex = 1;

    private String keyColumnName;

    private Class<T> valueType;

    public ColumnObjectListResultSetExtractor(int keyIndex, Class<T> valueType) {
        this.keyIndex = keyIndex;
        this.valueType = valueType;
    }

    public ColumnObjectListResultSetExtractor(String keyColumnName, Class<T> valueType) {
        this.keyColumnName = keyColumnName;
        this.mode = 1;
        this.valueType = valueType;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<K, List<T>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<K, List<T>> result = new LinkedHashMap<>();
        Object key;
        T javaBean;
        if (mode == 0) {
            String name;
            while (rs.next()) {
                key = null;
                javaBean = BeanUtils.instantiateClass(this.valueType);
                ResultSetMetaData rsd = rs.getMetaData();
                int columnCount = rsd.getColumnCount();
                if (this.keyIndex <= columnCount) {
                    key = JdbcTools.getColumnValue(rs, this.keyIndex);
                }
                for (int i = 1; i <= columnCount; i++) {
                    name = JdbcTools.getColumnKey(JdbcUtils.lookupColumnName(rsd, i));
                    ClassUtil.setProperty(javaBean, name, JdbcTools.getColumnValue(rs, i));
                }
                result.computeIfAbsent((K) key, k -> new ArrayList<>()).add(javaBean);
            }
        } else if (mode == 1) {
            String name;
            while (rs.next()) {
                key = null;
                javaBean = BeanUtils.instantiateClass(this.valueType);
                ResultSetMetaData rsd = rs.getMetaData();
                int columnCount = rsd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    name = JdbcTools.getColumnKey(JdbcUtils.lookupColumnName(rsd, i));
                    if (name.equals(keyColumnName)) {
                        key = JdbcTools.getColumnValue(rs, i);
                    }
                    ClassUtil.setProperty(javaBean, name, JdbcTools.getColumnValue(rs, i));
                }
                result.computeIfAbsent((K) key, k -> new ArrayList<>()).add(javaBean);
            }
        }
        return result;
    }

}
