package pub.avalon.sqlhelper.generator.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * 类型转换器
 *
 * @author 白超
 * @date 2019/6/5
 */
public class TypeConverter {

    private static Map<JdbcType, JavaType> DefaultTypeMap = new HashMap<>();
    private Map<JdbcType, JavaType> typeMap = new HashMap<>(DefaultTypeMap);

    static {
        DefaultTypeMap.put(JdbcType.ARRAY, JavaType.Array);
        DefaultTypeMap.put(JdbcType.BIGINT, JavaType.Long);
        DefaultTypeMap.put(JdbcType.BINARY, JavaType.byteArr);
        DefaultTypeMap.put(JdbcType.BIT, JavaType.Boolean);
        DefaultTypeMap.put(JdbcType.BLOB, JavaType.Blob);
        DefaultTypeMap.put(JdbcType.BOOLEAN, JavaType._boolean);
        DefaultTypeMap.put(JdbcType.CHAR, JavaType.String);
        DefaultTypeMap.put(JdbcType.CLOB, JavaType.Clob);
        DefaultTypeMap.put(JdbcType.CURSOR, JavaType.Integer);
        DefaultTypeMap.put(JdbcType.DATALINK, JavaType.URL);
        DefaultTypeMap.put(JdbcType.DATE, JavaType.Date);
        DefaultTypeMap.put(JdbcType.DATETIMEOFFSET, JavaType.String);
        DefaultTypeMap.put(JdbcType.DECIMAL, JavaType.BigDecimal);
        DefaultTypeMap.put(JdbcType.DISTINCT, JavaType.String);
        DefaultTypeMap.put(JdbcType.DOUBLE, JavaType.Double);
        DefaultTypeMap.put(JdbcType.FLOAT, JavaType.Double);
        DefaultTypeMap.put(JdbcType.INTEGER, JavaType.Integer);
        DefaultTypeMap.put(JdbcType.JAVA_OBJECT, JavaType.String);
        DefaultTypeMap.put(JdbcType.LONGNVARCHAR, JavaType.String);
        DefaultTypeMap.put(JdbcType.LONGVARBINARY, JavaType.byteArr);
        DefaultTypeMap.put(JdbcType.LONGVARCHAR, JavaType.String);
        DefaultTypeMap.put(JdbcType.NCHAR, JavaType.String);
        DefaultTypeMap.put(JdbcType.NCLOB, JavaType.String);
        DefaultTypeMap.put(JdbcType.NULL, JavaType.String);
        DefaultTypeMap.put(JdbcType.NUMERIC, JavaType.BigDecimal);
        DefaultTypeMap.put(JdbcType.NVARCHAR, JavaType.String);
        DefaultTypeMap.put(JdbcType.OTHER, JavaType.String);
        DefaultTypeMap.put(JdbcType.REAL, JavaType.Float);
        DefaultTypeMap.put(JdbcType.REF, JavaType.Ref);
        DefaultTypeMap.put(JdbcType.ROWID, JavaType.RowId);
        DefaultTypeMap.put(JdbcType.SMALLINT, JavaType.Short);
        DefaultTypeMap.put(JdbcType.SQLXML, JavaType.SQLXML);
        DefaultTypeMap.put(JdbcType.STRUCT, JavaType.Struct);
        DefaultTypeMap.put(JdbcType.TIME, JavaType.Time);
        DefaultTypeMap.put(JdbcType.TIMESTAMP, JavaType.Timestamp);
        DefaultTypeMap.put(JdbcType.TINYINT, JavaType._byte);
        DefaultTypeMap.put(JdbcType.UNDEFINED, JavaType.String);
        DefaultTypeMap.put(JdbcType.VARBINARY, JavaType.byteArr);
        DefaultTypeMap.put(JdbcType.VARCHAR, JavaType.String);
    }

    public TypeConverter setTypeOverride(JdbcType jdbcType, JavaType javaType) {
        this.typeMap.put(jdbcType, javaType);
        return this;
    }

    public TypeConverter setTypeOverride(JdbcType jdbcType, String javaType) {
        for (JavaType value : JavaType.values()) {
            if (value.name().equals(javaType)) {
                this.typeMap.put(jdbcType, value);
                return this;
            }
        }
        return this;
    }

    public TypeConverter setTypeOverride(String jdbcType, JavaType javaType) {
        for (JdbcType value : JdbcType.values()) {
            if (value.name().equals(jdbcType)) {
                this.typeMap.put(value, javaType);
                return this;
            }
        }
        return this;
    }

    public TypeConverter setTypeOverride(String jdbcType, String javaType) {
        for (JdbcType value : JdbcType.values()) {
            if (!value.name().equals(jdbcType)) {
                continue;
            }
            for (JavaType type : JavaType.values()) {
                if (type.name().equals(javaType)) {
                    this.typeMap.put(value, type);
                    return this;
                }
            }
        }
        return this;
    }

    public JavaType converterJdbcType(JdbcType jdbcType) {
        JavaType javaType = this.typeMap.get(jdbcType);
        return javaType == null ? JavaType.String : javaType;
    }

}
