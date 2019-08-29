package pub.avalon.sqlhelper.spring.core;

import pub.avalon.sqlhelper.spring.utils.JdbcTools;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import pub.avalon.holygrail.utils.ClassUtil;

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
public final class ListJavaBeanResultSetExtractor<T> implements ResultSetExtractor<List<T>> {

    private Class<T> valueType;
    private final int rowsExpected;

    public ListJavaBeanResultSetExtractor(Class<T> valueType) {
        this.valueType = valueType;
        this.rowsExpected = 0;
    }

    public ListJavaBeanResultSetExtractor(Class<T> valueType, int rowsExpected) {
        this.valueType = valueType;
        this.rowsExpected = rowsExpected;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<T> results = (this.rowsExpected > 0 ? new ArrayList<>(this.rowsExpected) : new ArrayList<>());
        T javaBean;
        String name;
        while (rs.next()) {
            javaBean = BeanUtils.instantiateClass(this.valueType);
            ResultSetMetaData rsd = rs.getMetaData();
            int columnCount = rsd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                name = JdbcTools.getColumnKey(JdbcUtils.lookupColumnName(rsd, i));
                ClassUtil.setProperty(javaBean, name, JdbcTools.getColumnValue(rs, i));
            }
            results.add(javaBean);
        }
        return results;
    }

}
