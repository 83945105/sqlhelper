package pub.avalonframework.sqlhelper.generator.beans;

/**
 * @author baichao
 */
public enum JavaType {
    /**
     *
     */
    String("java.lang.String", "String"),
    _boolean(null, "boolean"),
    Boolean("java.lang.Boolean", "Boolean"),
    _byte(null, "byte"),
    Byte("java.lang.Byte", "Byte"),
    byteArr(null, "byte[]"),
    _short(null, "short"),
    Short("java.lang.Short", "Short"),
    _int(null, "int"),
    Integer("java.lang.Integer", "Integer"),
    _long(null, "long"),
    Long("java.lang.Long", "Long"),
    _float(null, "float"),
    Float("java.lang.Float", "Float"),
    _double(null, "double"),
    Double("java.lang.Double", "Double"),
    BigDecimal("java.math.BigDecimal", "BigDecimal"),
    Date("java.sql.Date", "Date"),
    Timestamp("java.sql.Timestamp", "Timestamp"),
    Time("java.sql.Time", "Time"),
    Clob("java.sql.Clob", "Clob"),
    Blob("java.sql.Blob", "Blob"),
    Array("java.sql.Array", "Array"),
    Struct("java.sql.Struct", "Struct"),
    SQLXML("java.sql.SQLXML", "SQLXML"),
    RowId("java.sql.RowId", "RowId"),
    Ref("java.sql.Ref", "Ref"),
    URL("java.net.URL", "URL");

    private String classPath;

    private String className;

    JavaType(String classPath, String className) {
        this.classPath = classPath;
        this.className = className;
    }

    public java.lang.String getClassPath() {
        return classPath;
    }

    public java.lang.String getClassName() {
        return className;
    }
}