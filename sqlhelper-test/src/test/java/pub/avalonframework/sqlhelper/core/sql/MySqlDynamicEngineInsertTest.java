package pub.avalonframework.sqlhelper.core.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalonframework.sqlhelper.readme.entity.SysUser;
import pub.avalonframework.sqlhelper.readme.entity.SysUserHelper;

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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .insertArgs("1", "2", "3");
        Assertions.assertEquals("insert into `sys_user` (`id`,`user_name`,`login_name`) values (?,?,?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1", "2", "3"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入参数 - 指定表名
     */
    @Test
    void Test_insertArgs_assignTableName() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .insertArgs("1", "2", "3");
        Assertions.assertEquals("insert into `sys_user_custom` (`id`,`user_name`,`login_name`) values (?,?,?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1", "2", "3"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入参数 - 指定列
     */
    @Test
    void Test_insertArgs_assignColumns() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(SysUserHelper.Column::id)
                .insertArgs("1", "2", "3");
        Assertions.assertEquals("insert into `sys_user` (`id`) values (?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入参数 - 指定表名 & 指定列
     */
    @Test
    void Test_insertArgs_assignTableName_assignColumns() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .column(SysUserHelper.Column::id)
                .insertArgs("1", "2", "3");
        Assertions.assertEquals("insert into `sys_user_custom` (`id`) values (?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入JavaBean
     */
    @Test
    void Test_insertJavaBean() {
        SysUser javaBean = new SysUser();
        javaBean.setId("1");
        javaBean.setLoginName("3");
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .insertJavaBean(javaBean);
        Assertions.assertEquals("insert into `sys_user` (`id`,`user_name`,`login_name`) values (?,?,?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1", null, "3"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入JavaBean - 指定表名
     */
    @Test
    void Test_insertJavaBean_assignTableName() {
        SysUser javaBean = new SysUser();
        javaBean.setId("1");
        javaBean.setLoginName("3");
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .insertJavaBean(javaBean);
        Assertions.assertEquals("insert into `sys_user_custom` (`id`,`user_name`,`login_name`) values (?,?,?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1", null, "3"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入JavaBean - 指定列
     */
    @Test
    void Test_insertJavaBean_assignColumns() {
        SysUser javaBean = new SysUser();
        javaBean.setId("1");
        javaBean.setUserName("2");
        javaBean.setLoginName("3");
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table.id().loginName())
                .insertJavaBean(javaBean);
        Assertions.assertEquals("insert into `sys_user` (`id`,`login_name`) values (?,?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1", "3"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入JavaBean - 指定表名 & 指定列
     */
    @Test
    void Test_insertJavaBean_assignTableName_assignColumns() {
        SysUser javaBean = new SysUser();
        javaBean.setId("1");
        javaBean.setUserName("2");
        javaBean.setLoginName("3");
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .column(table -> table.id().loginName())
                .insertJavaBean(javaBean);
        Assertions.assertEquals("insert into `sys_user_custom` (`id`,`login_name`) values (?,?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1", "3"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入JavaBean(空值不插入)
     */
    @Test
    void Test_insertJavaBeanSelective() {
        SysUser javaBean = new SysUser();
        javaBean.setId("1");
        javaBean.setUserName("2");
        javaBean.setLoginName(null);
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .insertJavaBeanSelective(javaBean);
        Assertions.assertEquals("insert into `sys_user` (`id`,`user_name`) values (?,?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1", "2"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入JavaBean(空值不插入) - 指定表名
     */
    @Test
    void Test_insertJavaBeanSelective_assignTableName() {
        SysUser javaBean = new SysUser();
        javaBean.setId("1");
        javaBean.setUserName("2");
        javaBean.setLoginName(null);
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .insertJavaBeanSelective(javaBean);
        Assertions.assertEquals("insert into `sys_user_custom` (`id`,`user_name`) values (?,?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1", "2"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入JavaBean(空值不插入) - 指定列
     */
    @Test
    void Test_insertJavaBeanSelective_assignColumns() {
        SysUser javaBean = new SysUser();
        javaBean.setId("1");
        javaBean.setUserName("2");
        javaBean.setLoginName(null);
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table.id().loginName())
                .insertJavaBeanSelective(javaBean);
        Assertions.assertEquals("insert into `sys_user` (`id`) values (?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 插入JavaBean(空值不插入) - 指定表名 & 指定列
     */
    @Test
    void Test_insertJavaBeanSelective_assignTableName_assignColumns() {
        SysUser javaBean = new SysUser();
        javaBean.setId("1");
        javaBean.setUserName("2");
        javaBean.setLoginName(null);
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .column(table -> table.id().loginName())
                .insertJavaBeanSelective(javaBean);
        Assertions.assertEquals("insert into `sys_user_custom` (`id`) values (?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{"1"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 批量插入JavaBean
     */
    @Test
    void Test_batchInsertJavaBeans() {
        List<SysUser> javaBeans = new ArrayList<>();

        SysUser javaBean1 = new SysUser();
        javaBean1.setId(null);
        javaBean1.setUserName(null);
        javaBean1.setLoginName("3");
        javaBeans.add(javaBean1);

        SysUser javaBean2 = new SysUser();
        javaBean2.setId("1");
        javaBean2.setUserName("2");
        javaBean2.setLoginName(null);
        javaBeans.add(javaBean2);

        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .batchInsertJavaBeans(javaBeans);
        Assertions.assertEquals("insert into `sys_user` (`id`,`user_name`,`login_name`) values (?,?,?),(?,?,?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{null, null, "3", "1", "2", null}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 批量插入JavaBean - 指定表名
     */
    @Test
    void Test_batchInsertJavaBeans_assignTableName() {
        List<SysUser> javaBeans = new ArrayList<>();

        SysUser javaBean1 = new SysUser();
        javaBean1.setId(null);
        javaBean1.setUserName(null);
        javaBean1.setLoginName("3");
        javaBeans.add(javaBean1);

        SysUser javaBean2 = new SysUser();
        javaBean2.setId("1");
        javaBean2.setUserName("2");
        javaBean2.setLoginName(null);
        javaBeans.add(javaBean2);

        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .batchInsertJavaBeans(javaBeans);
        Assertions.assertEquals("insert into `sys_user_custom` (`id`,`user_name`,`login_name`) values (?,?,?),(?,?,?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{null, null, "3", "1", "2", null}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 批量插入JavaBean - 指定列
     */
    @Test
    void Test_batchInsertJavaBeans_assignColumns() {
        List<SysUser> javaBeans = new ArrayList<>();

        SysUser javaBean1 = new SysUser();
        javaBean1.setId(null);
        javaBean1.setUserName(null);
        javaBean1.setLoginName("3");
        javaBeans.add(javaBean1);

        SysUser javaBean2 = new SysUser();
        javaBean2.setId("1");
        javaBean2.setUserName("2");
        javaBean2.setLoginName(null);
        javaBeans.add(javaBean2);

        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table.id().loginName())
                .batchInsertJavaBeans(javaBeans);
        Assertions.assertEquals("insert into `sys_user` (`id`,`login_name`) values (?,?),(?,?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{null, "3", "1", null}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 批量插入JavaBean - 指定表名 & 指定列
     */
    @Test
    void Test_batchInsertJavaBeans_assignTableName_assignColumns() {
        List<SysUser> javaBeans = new ArrayList<>();

        SysUser javaBean1 = new SysUser();
        javaBean1.setId(null);
        javaBean1.setUserName(null);
        javaBean1.setLoginName("3");
        javaBeans.add(javaBean1);

        SysUser javaBean2 = new SysUser();
        javaBean2.setId("1");
        javaBean2.setUserName("2");
        javaBean2.setLoginName(null);
        javaBeans.add(javaBean2);

        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .column(table -> table.id().loginName())
                .batchInsertJavaBeans(javaBeans);
        Assertions.assertEquals("insert into `sys_user_custom` (`id`,`login_name`) values (?,?),(?,?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{null, "3", "1", null}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }
}
