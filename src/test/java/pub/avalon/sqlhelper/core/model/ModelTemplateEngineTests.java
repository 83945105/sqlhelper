package pub.avalon.sqlhelper.core.model;

import pub.avalon.beans.HumpConverter;
import pub.avalon.sqlhelper.core.jdbc.JdbcSourceEngine;

import java.sql.SQLException;

/**
 * Created by 白超 on 2018/8/25.
 */
public class ModelTemplateEngineTests {

//    @Test
    void createModelsTest() throws SQLException {
        JdbcSourceEngine engine = JdbcSourceEngine.newMySqlEngine(
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://192.168.3.3:3306/sqlhelper-junit5?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                "root", "root");

        new ModelTemplateEngine(engine, new HumpConverter())
                .addTable("jur_res", "JurRes")
                .addTable("jur_role", "JurRole")
                .addTable("jur_role_res", "JurRoleRes")
                .addTable("jur_role_user", "JurRoleUser")
                .addTable("zuul_route", "ZuulRoute")
                .process("/", "com.shiro.model");
    }


}
