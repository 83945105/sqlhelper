package pub.avalon.sqlhelper.core.model;

import org.junit.jupiter.api.Test;
import pub.avalon.beans.HumpConverter;
import pub.avalon.sqlhelper.core.jdbc.JdbcSourceEngine;

import java.sql.SQLException;

/**
 * Created by 白超 on 2018/8/25.
 */
public class ModelTemplateEngineTests {

    @Test
    void createModelsTest() throws SQLException {
        JdbcSourceEngine engine = JdbcSourceEngine.newMySqlEngine(
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://192.168.3.3:3306/shiro-manage-spring-2.x.x?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                "root", "root");

        new ModelTemplateEngine(engine, new HumpConverter())
                .addTable("aut_role", "JurRes")
                .process("/", "com.shiro.model");
    }

}
