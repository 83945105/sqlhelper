package pub.avalon.sqlhelper.core.generator;

import pub.avalon.beans.HumpConverter;
import pub.avalon.sqlhelper.core.jdbc.JdbcSourceEngine;
import pub.avalon.sqlhelper.core.model.ModelTemplateEngine;

import java.sql.SQLException;

/**
 * Created by 白超 on 2019/6/3.
 */
public class SqlHelperTest {

    public static void main(String[] args) throws SQLException {
        JdbcSourceEngine engine = JdbcSourceEngine.newMySqlEngine(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/sqlhelper?useSSL=false",
                "root", "root");

        new ModelTemplateEngine(engine, new HumpConverter())
                .addTable("role_resource", "RoleResource")
                .addTable("sys_user", "SysUser")
                .addTable("user_role", "UserRole")
                .process("/", "pub.avalon.sqlhelper.readme.entity");
    }

}
