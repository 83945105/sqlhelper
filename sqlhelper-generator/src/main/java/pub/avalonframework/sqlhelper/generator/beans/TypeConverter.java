package pub.avalonframework.sqlhelper.generator.beans;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Type converter.
 *
 * @author baichao
 */
public class TypeConverter {

    private static Map<String, JavaType> DefaultTypeMap = new HashMap<>();
    private Map<String, JavaType> typeMap = new HashMap<>(DefaultTypeMap);

    static {
        DefaultTypeMap.put(JdbcType.ARRAY.name(), JavaType.Array);
        DefaultTypeMap.put(JdbcType.BIGINT.name(), JavaType.Long);
        DefaultTypeMap.put(JdbcType.BINARY.name(), JavaType.byteArr);
        DefaultTypeMap.put(JdbcType.BIT.name(), JavaType.Boolean);
        DefaultTypeMap.put(JdbcType.BLOB.name(), JavaType.Blob);
        DefaultTypeMap.put(JdbcType.BOOLEAN.name(), JavaType._boolean);
        DefaultTypeMap.put(JdbcType.CHAR.name(), JavaType.String);
        DefaultTypeMap.put(JdbcType.CLOB.name(), JavaType.Clob);
        DefaultTypeMap.put(JdbcType.CURSOR.name(), JavaType.Integer);
        DefaultTypeMap.put(JdbcType.DATALINK.name(), JavaType.URL);
        DefaultTypeMap.put(JdbcType.DATE.name(), JavaType.Date);
        DefaultTypeMap.put(JdbcType.DATETIME.name(), JavaType.String);
        DefaultTypeMap.put(JdbcType.DATETIMEOFFSET.name(), JavaType.String);
        DefaultTypeMap.put(JdbcType.DECIMAL.name(), JavaType.BigDecimal);
        DefaultTypeMap.put(JdbcType.DISTINCT.name(), JavaType.String);
        DefaultTypeMap.put(JdbcType.DOUBLE.name(), JavaType.Double);
        DefaultTypeMap.put(JdbcType.FLOAT.name(), JavaType.Double);
        DefaultTypeMap.put(JdbcType.INTEGER.name(), JavaType.Integer);
        DefaultTypeMap.put(JdbcType.JAVA_OBJECT.name(), JavaType.String);
        DefaultTypeMap.put(JdbcType.LONGNVARCHAR.name(), JavaType.String);
        DefaultTypeMap.put(JdbcType.LONGVARBINARY.name(), JavaType.byteArr);
        DefaultTypeMap.put(JdbcType.LONGVARCHAR.name(), JavaType.String);
        DefaultTypeMap.put(JdbcType.NCHAR.name(), JavaType.String);
        DefaultTypeMap.put(JdbcType.NCLOB.name(), JavaType.String);
        DefaultTypeMap.put(JdbcType.NULL.name(), JavaType.String);
        DefaultTypeMap.put(JdbcType.NUMERIC.name(), JavaType.BigDecimal);
        DefaultTypeMap.put(JdbcType.NVARCHAR.name(), JavaType.String);
        DefaultTypeMap.put(JdbcType.OTHER.name(), JavaType.String);
        DefaultTypeMap.put(JdbcType.REAL.name(), JavaType.Float);
        DefaultTypeMap.put(JdbcType.REF.name(), JavaType.Ref);
        DefaultTypeMap.put(JdbcType.ROWID.name(), JavaType.RowId);
        DefaultTypeMap.put(JdbcType.SMALLINT.name(), JavaType.Short);
        DefaultTypeMap.put(JdbcType.SQLXML.name(), JavaType.SQLXML);
        DefaultTypeMap.put(JdbcType.STRUCT.name(), JavaType.Struct);
        DefaultTypeMap.put(JdbcType.TIME.name(), JavaType.Time);
        DefaultTypeMap.put(JdbcType.TIMESTAMP.name(), JavaType.Timestamp);
        DefaultTypeMap.put(JdbcType.TINYINT.name(), JavaType._byte);
        DefaultTypeMap.put(JdbcType.UNDEFINED.name(), JavaType.String);
        DefaultTypeMap.put(JdbcType.VARBINARY.name(), JavaType.byteArr);
        DefaultTypeMap.put(JdbcType.VARCHAR.name(), JavaType.String);
    }

    public TypeConverter overrideTypeConverter(JdbcType jdbcType, JavaType javaType) {
        this.typeMap.put(jdbcType.name(), javaType);
        return this;
    }

    public TypeConverter overrideTypeConverter(JdbcType jdbcType, String javaType) {
        for (JavaType value : JavaType.values()) {
            if (value.name().equals(javaType)) {
                this.typeMap.put(jdbcType.name(), value);
                return this;
            }
        }
        return this;
    }

    public TypeConverter overrideTypeConverter(String jdbcType, JavaType javaType) {
        this.typeMap.put(jdbcType, javaType);
        return this;
    }

    public TypeConverter overrideTypeConverter(String jdbcType, String javaType) {
        for (JavaType value : JavaType.values()) {
            if (value.name().equals(javaType)) {
                this.typeMap.put(jdbcType, value);
                return this;
            }
        }
        return this;
    }

    public JavaType converterJdbcType(JdbcType jdbcType) {
        JavaType javaType = this.typeMap.get(jdbcType.name());
        return javaType == null ? JavaType.String : javaType;
    }

    public JavaType converterJdbcType(String jdbcType) {
        JavaType javaType = this.typeMap.get(jdbcType);
        return javaType == null ? JavaType.String : javaType;
    }

    public Set<String> getClassPaths() {
        Set<String> classPaths = new LinkedHashSet<>();
        for (Map.Entry<String, JavaType> entry : typeMap.entrySet()) {
            classPaths.add(entry.getValue().getClassPath());
        }
        return classPaths;
    }
}