package pub.avalon.sqlhelper.spring.core;

import pub.avalon.sqlhelper.spring.utils.JdbcTools;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 白超
 * @version 1.0
 * @since 2018/7/18
 */
public final class ListMapResultSetExtractor implements ResultSetExtractor<List<Map<String, Object>>> {

    private final int rowsExpected;

    public ListMapResultSetExtractor() {
        this.rowsExpected = 0;
    }

    public ListMapResultSetExtractor(int rowsExpected) {
        this.rowsExpected = rowsExpected;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Map<String, Object>> results = (this.rowsExpected > 0 ? new ArrayList<>(this.rowsExpected) : new ArrayList<>());
        Map<String, Object> result;
        String name;
        while (rs.next()) {
            result = new HashMap<>(16);
            ResultSetMetaData rsd = rs.getMetaData();
            int columnCount = rsd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                name = JdbcTools.getColumnKey(JdbcUtils.lookupColumnName(rsd, i));
                result.put(name, JdbcTools.getColumnValue(rs, i));
            }
            results.add(result);
        }
        return results;
    }

}
