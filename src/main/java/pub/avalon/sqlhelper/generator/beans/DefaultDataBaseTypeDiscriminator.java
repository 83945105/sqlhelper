package pub.avalon.sqlhelper.generator.beans;

import pub.avalon.sqlhelper.generator.jdbc.DataBaseTypeDiscriminator;

/**
 * @author 白超
 * @date 2019/6/9
 */
public class DefaultDataBaseTypeDiscriminator implements DataBaseTypeDiscriminator {
    @Override
    public DataBaseType apply(String driverClassName, String jdbcUrl) {
        if (jdbcUrl.contains("jdbc:mysql")) {
            return DataBaseType.MySql;
        }
        throw new RuntimeException("can not discriminate this dataBaseType.");
    }
}
