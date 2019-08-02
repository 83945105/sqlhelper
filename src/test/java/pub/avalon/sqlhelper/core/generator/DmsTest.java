package pub.avalon.sqlhelper.core.generator;

import pub.avalon.sqlhelper.generator.engine.TemplateEngine;
import pub.avalon.sqlhelper.generator.jdbc.JdbcTemplate;
import pub.avalon.sqlhelper.generator.jdbc.MySqlJdbcTemplate;
import pub.avalon.sqlhelper.generator.option.GenerateOptions;

import java.sql.SQLException;

/**
 * Created by 白超 on 2019/6/3.
 */
public class DmsTest {

    public static void main(String[] args) throws SQLException {

        JdbcTemplate jdbcTemplate = new MySqlJdbcTemplate(
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/ds0?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false",
                "root",
                "root"
        );

        new TemplateEngine(jdbcTemplate)
                .setGenerateOptions(new GenerateOptions().setEntityAlone(false))
                .addTable("lgs_package", "LgsPackage")
                .addTable("lgs_package_item", "LgsPackageItem")
                .addTable("trd_trade", "TrdTrade")
                .addTable("trd_order", "TrdOrder")
                .addTable("trd_order_tag", "TrdOrderTag")
                .generate("/", "pub.avalon.test.dto");
    }

}
