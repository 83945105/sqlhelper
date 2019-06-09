package pub.avalon.sqlhelper.generator.jdbc;

import pub.avalon.sqlhelper.generator.beans.DataBaseType;

/**
 * 数据库类型鉴别器
 *
 * @author 白超
 * @date 2019/6/9
 */
@FunctionalInterface
public interface DataBaseTypeDiscriminator {

    /**
     * 鉴别数据库类型
     *
     * @param driverClassName 驱动类名称
     * @param jdbcUrl         数据库地址
     * @return 数据库类型
     */
    DataBaseType apply(String driverClassName, String jdbcUrl);

}
