package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.SysUserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * MySql动态引擎 - 新增
 */
public class MySqlDynamicEngineInsertTest {

    /**
     * 插入参数
     */
    @Test
    void Test_insertArgs() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .insertArgs("1", "2", "3");
        Assertions.assertEquals("insert into `sys_user` (`id`,`user_name`,`login_name`) values (?,?,?)", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1", "2", "3"}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入参数 - 指定表名
     */
    @Test
    void Test_insertArgs_assignTableName() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .insertArgs("1", "2", "3");
        Assertions.assertEquals("insert into `sys_user_custom` (`id`,`user_name`,`login_name`) values (?,?,?)", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1", "2", "3"}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入参数 - 指定列
     */
    @Test
    void Test_insertArgs_assignColumns() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(SysUserDTO.Helper.Column::id)
                .insertArgs("1", "2", "3");
        Assertions.assertEquals("insert into `sys_user` (`id`) values (?)", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1"}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入参数 - 指定表名 & 指定列
     */
    @Test
    void Test_insertArgs_assignTableName_assignColumns() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .column(SysUserDTO.Helper.Column::id)
                .insertArgs("1", "2", "3");
        Assertions.assertEquals("insert into `sys_user_custom` (`id`) values (?)", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1"}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入JavaBean
     */
    @Test
    void Test_insertJavaBean() {
        SysUserDTO javaBean = new SysUserDTO();
        javaBean.setId("1");
        javaBean.setLoginName("3");
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .insertJavaBean(javaBean);
        Assertions.assertEquals("insert into `sys_user` (`id`,`user_name`,`login_name`) values (?,?,?)", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1", null, "3"}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入JavaBean - 指定表名
     */
    @Test
    void Test_insertJavaBean_assignTableName() {
        SysUserDTO javaBean = new SysUserDTO();
        javaBean.setId("1");
        javaBean.setLoginName("3");
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .insertJavaBean(javaBean);
        Assertions.assertEquals("insert into `sys_user_custom` (`id`,`user_name`,`login_name`) values (?,?,?)", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1", null, "3"}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入JavaBean - 指定列
     */
    @Test
    void Test_insertJavaBean_assignColumns() {
        SysUserDTO javaBean = new SysUserDTO();
        javaBean.setId("1");
        javaBean.setUserName("2");
        javaBean.setLoginName("3");
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(table -> table.id().loginName())
                .insertJavaBean(javaBean);
        Assertions.assertEquals("insert into `sys_user` (`id`,`login_name`) values (?,?)", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1", "3"}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入JavaBean - 指定表名 & 指定列
     */
    @Test
    void Test_insertJavaBean_assignTableName_assignColumns() {
        SysUserDTO javaBean = new SysUserDTO();
        javaBean.setId("1");
        javaBean.setUserName("2");
        javaBean.setLoginName("3");
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .column(table -> table.id().loginName())
                .insertJavaBean(javaBean);
        Assertions.assertEquals("insert into `sys_user_custom` (`id`,`login_name`) values (?,?)", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1", "3"}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入JavaBean(空值不插入)
     */
    @Test
    void Test_insertJavaBeanSelective() {
        SysUserDTO javaBean = new SysUserDTO();
        javaBean.setId("1");
        javaBean.setUserName("2");
        javaBean.setLoginName(null);
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .insertJavaBeanSelective(javaBean);
        Assertions.assertEquals("insert into `sys_user` (`id`,`user_name`) values (?,?)", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1", "2"}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入JavaBean(空值不插入) - 指定表名
     */
    @Test
    void Test_insertJavaBeanSelective_assignTableName() {
        SysUserDTO javaBean = new SysUserDTO();
        javaBean.setId("1");
        javaBean.setUserName("2");
        javaBean.setLoginName(null);
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .insertJavaBeanSelective(javaBean);
        Assertions.assertEquals("insert into `sys_user_custom` (`id`,`user_name`) values (?,?)", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1", "2"}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入JavaBean(空值不插入) - 指定列
     */
    @Test
    void Test_insertJavaBeanSelective_assignColumns() {
        SysUserDTO javaBean = new SysUserDTO();
        javaBean.setId("1");
        javaBean.setUserName("2");
        javaBean.setLoginName(null);
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(table -> table.id().loginName())
                .insertJavaBeanSelective(javaBean);
        Assertions.assertEquals("insert into `sys_user` (`id`) values (?)", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1"}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入JavaBean(空值不插入) - 指定表名 & 指定列
     */
    @Test
    void Test_insertJavaBeanSelective_assignTableName_assignColumns() {
        SysUserDTO javaBean = new SysUserDTO();
        javaBean.setId("1");
        javaBean.setUserName("2");
        javaBean.setLoginName(null);
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .column(table -> table.id().loginName())
                .insertJavaBeanSelective(javaBean);
        Assertions.assertEquals("insert into `sys_user_custom` (`id`) values (?)", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1"}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 批量插入JavaBean
     */
    @Test
    void Test_batchInsertJavaBeans() {
        List<SysUserDTO> javaBeans = new ArrayList<>();

        SysUserDTO javaBean1 = new SysUserDTO();
        javaBean1.setId(null);
        javaBean1.setUserName(null);
        javaBean1.setLoginName("3");
        javaBeans.add(javaBean1);

        SysUserDTO javaBean2 = new SysUserDTO();
        javaBean2.setId("1");
        javaBean2.setUserName("2");
        javaBean2.setLoginName(null);
        javaBeans.add(javaBean2);

        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .batchInsertJavaBeans(javaBeans);
        Assertions.assertEquals("insert into `sys_user` (`id`,`user_name`,`login_name`) values (?,?,?),(?,?,?)", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{null, null, "3", "1", "2", null}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 批量插入JavaBean - 指定表名
     */
    @Test
    void Test_batchInsertJavaBeans_assignTableName() {
        List<SysUserDTO> javaBeans = new ArrayList<>();

        SysUserDTO javaBean1 = new SysUserDTO();
        javaBean1.setId(null);
        javaBean1.setUserName(null);
        javaBean1.setLoginName("3");
        javaBeans.add(javaBean1);

        SysUserDTO javaBean2 = new SysUserDTO();
        javaBean2.setId("1");
        javaBean2.setUserName("2");
        javaBean2.setLoginName(null);
        javaBeans.add(javaBean2);

        SqlBuilder sqlBuilder = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .batchInsertJavaBeans(javaBeans);
        Assertions.assertEquals("insert into `sys_user_custom` (`id`,`user_name`,`login_name`) values (?,?,?),(?,?,?)", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{null, null, "3", "1", "2", null}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 批量插入JavaBean - 指定列
     */
    @Test
    void Test_batchInsertJavaBeans_assignColumns() {
        List<SysUserDTO> javaBeans = new ArrayList<>();

        SysUserDTO javaBean1 = new SysUserDTO();
        javaBean1.setId(null);
        javaBean1.setUserName(null);
        javaBean1.setLoginName("3");
        javaBeans.add(javaBean1);

        SysUserDTO javaBean2 = new SysUserDTO();
        javaBean2.setId("1");
        javaBean2.setUserName("2");
        javaBean2.setLoginName(null);
        javaBeans.add(javaBean2);

        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(table -> table.id().loginName())
                .batchInsertJavaBeans(javaBeans);
        Assertions.assertEquals("insert into `sys_user` (`id`,`login_name`) values (?,?),(?,?)", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{null, "3", "1", null}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 批量插入JavaBean - 指定表名 & 指定列
     */
    @Test
    void Test_batchInsertJavaBeans_assignTableName_assignColumns() {
        List<SysUserDTO> javaBeans = new ArrayList<>();

        SysUserDTO javaBean1 = new SysUserDTO();
        javaBean1.setId(null);
        javaBean1.setUserName(null);
        javaBean1.setLoginName("3");
        javaBeans.add(javaBean1);

        SysUserDTO javaBean2 = new SysUserDTO();
        javaBean2.setId("1");
        javaBean2.setUserName("2");
        javaBean2.setLoginName(null);
        javaBeans.add(javaBean2);

        SqlBuilder sqlBuilder = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .column(table -> table.id().loginName())
                .batchInsertJavaBeans(javaBeans);
        Assertions.assertEquals("insert into `sys_user_custom` (`id`,`login_name`) values (?,?),(?,?)", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{null, "3", "1", null}, sqlBuilder.getPreparedStatementArgs().toArray());
    }
}
