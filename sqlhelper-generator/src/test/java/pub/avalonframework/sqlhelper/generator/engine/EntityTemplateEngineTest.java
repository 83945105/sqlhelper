package pub.avalonframework.sqlhelper.generator.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalonframework.sqlhelper.generator.jdbc.JdbcTemplate;
import pub.avalonframework.sqlhelper.generator.jdbc.JdbcTemplateBuilder;
import pub.avalonframework.sqlhelper.generator.options.EntityOptions;
import pub.avalonframework.sqlhelper.generator.options.GenerateOptions;
import pub.avalonframework.sqlhelper.generator.options.OutputOptions;

import java.io.File;

/**
 * @author baichao
 */
public class EntityTemplateEngineTest {

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
                .buildEntityTemplateEngine()
                .setDefaultGenerateOptions(new GenerateOptions().setPackagePath("pub.avalon.sqlhelper.readme.entity"))
                .addTable("sys_user", "SysUser")
                .addTable("role_resource", "RoleResource")
                .addTable("user_role", "UserRole")
                .generateJavaFiles(new OutputOptions("D://generateFiles"));
        File file1 = new File("D://generateFiles\\pub\\avalon\\sqlhelper\\readme\\entity\\SysUser.java");
        Assertions.assertTrue(file1.exists());
        File file2 = new File("D://generateFiles\\pub\\avalon\\sqlhelper\\readme\\entity\\RoleResource.java");
        Assertions.assertTrue(file2.exists());
        File file3 = new File("D://generateFiles\\pub\\avalon\\sqlhelper\\readme\\entity\\UserRole.java");
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
                .buildEntityTemplateEngine()
                .setDefaultGenerateOptions(new GenerateOptions().setPackagePath("pub.avalon.sqlhelper.readme.entity"))
                .addTable("sys_user", "SysUser")
                .addTable("role_resource", "RoleResource")
                .addTable("user_role", "UserRole")
                .generateClassFiles(new OutputOptions("D://generateFiles"));
        File file1 = new File("D://generateFiles\\pub\\avalon\\sqlhelper\\readme\\entity\\SysUser.class");
        Assertions.assertTrue(file1.exists());
        File file2 = new File("D://generateFiles\\pub\\avalon\\sqlhelper\\readme\\entity\\RoleResource.class");
        Assertions.assertTrue(file2.exists());
        File file3 = new File("D://generateFiles\\pub\\avalon\\sqlhelper\\readme\\entity\\UserRole.class");
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
                .buildEntityTemplateEngine()
                .setDefaultGenerateOptions(new GenerateOptions()
                        .setPackagePath("pub.avalon.sqlhelper.readme.entity")
                        .setEntityOptions(new EntityOptions().setClassNameSuffix("Model")))
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