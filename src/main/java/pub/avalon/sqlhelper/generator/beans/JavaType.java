package pub.avalon.sqlhelper.generator.beans;

/**
 * @author 白超
 * @date 2019/6/4
 */
public enum JavaType {
    /**
     *
     */
    Boolean("java.lang.Boolean", "Boolean"),
    byteArr(null, "byte[]"),
    Integer("java.lang.Integer", "Integer"),
    Long("java.lang.Long", "Long"),
    Float("java.lang.Float", "Float"),
    Double("java.lang.Double", "Double"),
    BigDecimal("java.math.BigDecimal", "BigDecimal"),
    Date("java.sql.Date", "Date"),
    Timestamp("java.sql.Timestamp", "Timestamp"),
    Time("java.sql.Time", "Time");

    public String classPath;

    public String className;

    JavaType(String classPath, String className) {
        this.classPath = classPath;
        this.className = className;
    }

}
