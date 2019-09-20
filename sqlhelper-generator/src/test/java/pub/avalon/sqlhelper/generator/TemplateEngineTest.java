package pub.avalon.sqlhelper.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.generator.engine.TemplateEngine;
import pub.avalon.sqlhelper.generator.engine.TemplateEngineBuilder;
import pub.avalon.sqlhelper.generator.jdbc.JdbcTemplate;
import pub.avalon.sqlhelper.generator.jdbc.JdbcTemplateBuilder;
import pub.avalon.sqlhelper.generator.options.GenerateOptions;
import pub.avalon.sqlhelper.generator.options.OutputOptions;

import java.io.File;

/**
 * @author baichao
 */
public class TemplateEngineTest {

    @Test
    void Test_generateJavaFile_deleteFiles() {
        JdbcTemplate jdbcTemplate = JdbcTemplateBuilder.newJdbcTemplateBuilder()
                .setDriverClassName("com.mysql.cj.jdbc.Driver")
                .setJdbcUrl("jdbc:mysql://localhost:3306/sqlhelper?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false")
                .setUsername("root")
                .setPassword("root")
                .build();
        TemplateEngineBuilder.newTemplateEngineBuilder()
                .setJdbcTemplate(jdbcTemplate)
                .build()
                .setDefaultGenerateOptions(new GenerateOptions().setPackagePath("pub.avalon.sqlhelper.readme.entity"))
                .addTable("sys_user", "SysUser")
                .addTable("role_resource", "RoleResource")
                .addTable("user_role", "UserRole")
                .generateJavaFiles(new OutputOptions("D://generateFiles"));
        File file1 = new File("D://generateFiles\\pub\\avalon\\sqlhelper\\readme\\entity\\SysUserDTO.java");
        Assertions.assertTrue(file1.exists());
        File file2 = new File("D://generateFiles\\pub\\avalon\\sqlhelper\\readme\\entity\\RoleResourceDTO.java");
        Assertions.assertTrue(file2.exists());
        File file3 = new File("D://generateFiles\\pub\\avalon\\sqlhelper\\readme\\entity\\UserRoleDTO.java");
        Assertions.assertTrue(file3.exists());
        TemplateEngine.deleteAllFiles(new File("D://generateFiles"));
        Assertions.assertFalse(file1.exists());
        Assertions.assertFalse(file2.exists());
        Assertions.assertFalse(file3.exists());
    }

    @Test
    void Test_generateClassFile_deleteFiles() {
        JdbcTemplate jdbcTemplate = JdbcTemplateBuilder.newJdbcTemplateBuilder()
                .setDriverClassName("com.mysql.cj.jdbc.Driver")
                .setJdbcUrl("jdbc:mysql://localhost:3306/sqlhelper?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false")
                .setUsername("root")
                .setPassword("root")
                .build();
        TemplateEngineBuilder.newTemplateEngineBuilder()
                .setJdbcTemplate(jdbcTemplate)
                .build()
                .setDefaultGenerateOptions(new GenerateOptions().setPackagePath("pub.avalon.sqlhelper.readme.entity"))
                .addTable("sys_user", "SysUser")
                .addTable("role_resource", "RoleResource")
                .addTable("user_role", "UserRole")
                .generateClassFiles(new OutputOptions("D://generateFiles"));
        File file1 = new File("D://generateFiles\\pub\\avalon\\sqlhelper\\readme\\entity\\SysUserDTO.class");
        Assertions.assertTrue(file1.exists());
        File file2 = new File("D://generateFiles\\pub\\avalon\\sqlhelper\\readme\\entity\\RoleResourceDTO.class");
        Assertions.assertTrue(file2.exists());
        File file3 = new File("D://generateFiles\\pub\\avalon\\sqlhelper\\readme\\entity\\UserRoleDTO.class");
        Assertions.assertTrue(file3.exists());
        TemplateEngine.deleteAllFiles(new File("D://generateFiles"));
        Assertions.assertFalse(file1.exists());
        Assertions.assertFalse(file2.exists());
        Assertions.assertFalse(file3.exists());
    }

    @Test
    void Test_generateClassFile_customGenerateOptions_deleteFiles() {
        JdbcTemplate jdbcTemplate = JdbcTemplateBuilder.newJdbcTemplateBuilder()
                .setDriverClassName("com.mysql.cj.jdbc.Driver")
                .setJdbcUrl("jdbc:mysql://localhost:3306/sqlhelper?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false")
                .setUsername("root")
                .setPassword("root")
                .build();
        TemplateEngineBuilder.newTemplateEngineBuilder()
                .setJdbcTemplate(jdbcTemplate)
                .build()
                .setDefaultGenerateOptions(new GenerateOptions()
                        .setPackagePath("pub.avalon.sqlhelper.readme.entity")
                        .setEntitySuffix("Model")
                        .setHelperClassName("Model"))
                .addTable("sys_user", "SysUser")
                .addTable("role_resource", "RoleResource")
                .addTable("user_role", "UserRole")
                .generateClassFiles(new OutputOptions("D://generateFiles"));
        File file1 = new File("D://generateFiles\\pub\\avalon\\sqlhelper\\readme\\entity\\SysUserModel.class");
        Assertions.assertTrue(file1.exists());
        File file2 = new File("D://generateFiles\\pub\\avalon\\sqlhelper\\readme\\entity\\RoleResourceModel.class");
        Assertions.assertTrue(file2.exists());
        File file3 = new File("D://generateFiles\\pub\\avalon\\sqlhelper\\readme\\entity\\UserRoleModel.class");
        Assertions.assertTrue(file3.exists());
        TemplateEngine.deleteAllFiles(new File("D://generateFiles"));
        Assertions.assertFalse(file1.exists());
        Assertions.assertFalse(file2.exists());
        Assertions.assertFalse(file3.exists());
    }
}