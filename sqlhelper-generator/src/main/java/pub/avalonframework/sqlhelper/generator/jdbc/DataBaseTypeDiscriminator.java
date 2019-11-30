package pub.avalonframework.sqlhelper.generator.jdbc;

import pub.avalonframework.sqlhelper.generator.beans.DatabaseType;

/**
 * Database type discriminator.
 *
 * @author baichao
 */
@FunctionalInterface
public interface DataBaseTypeDiscriminator {

    /**
     * discriminator database type.
     *
     * @param driverClassName The driver class name.
     * @param jdbcUrl         The jdbc url.
     * @return The database type.
     */
    DatabaseType apply(String driverClassName, String jdbcUrl);
}