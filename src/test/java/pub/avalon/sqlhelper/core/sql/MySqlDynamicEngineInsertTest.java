package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.SysUserDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MySql动态引擎 - 新增 - 参数新增、实体类新增
 */
public class MySqlDynamicEngineInsertTest extends AbstractTest {

    @Test
    void TestInsertArgs01() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .insertArgs(arg("1"), arg("2"), arg("3"));
        setSqlBuilder(sqlBuilder, "insert into `sys_user` (`id`,`user_name`,`login_name`) values (?,?,?)");
    }

    @Test
    void TestInsertArgs02() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table("sys_user", SysUserDTO.Helper.class)
                .insertArgs(arg("1"), arg("2"), arg("3"));
        setSqlBuilder(sqlBuilder, "insert into `sys_user` (`id`,`user_name`,`login_name`) values (?,?,?)");
    }

    @Test
    void TestInsertArgs03() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(SysUserDTO.Helper.Column::id)
                .insertArgs(arg("1"), arg("2"), arg("3"));
        setSqlBuilder(sqlBuilder, "insert into `sys_user` (`id`) values (?)");
    }

    @Test
    void TestInsertJavaBean01() {
        SysUserDTO javaBean = new SysUserDTO();
        javaBean.setId(arg("1"));
        javaBean.setUserName(arg("2"));
        javaBean.setLoginName(arg("3"));
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .insertJavaBean(javaBean);
        setSqlBuilder(sqlBuilder, "insert into `sys_user` (`id`,`user_name`,`login_name`) values (?,?,?)");
    }

    @Test
    void TestInsertJavaBean02() {
        SysUserDTO javaBean = new SysUserDTO();
        javaBean.setId(arg("1"));
        javaBean.setLoginName(arg("3"));
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(table -> table.id().loginName())
                .insertJavaBean(javaBean);
        setSqlBuilder(sqlBuilder, "insert into `sys_user` (`id`,`login_name`) values (?,?)");
    }

    @Test
    void TestInsertJavaBeanSelective01() {
        SysUserDTO javaBean = new SysUserDTO();
        javaBean.setId(arg("1"));
        javaBean.setUserName(arg("2"));
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .insertJavaBeanSelective(javaBean);
        setSqlBuilder(sqlBuilder, "insert into `sys_user` (`id`,`user_name`) values (?,?)");
    }

    @Test
    void TestInsertJavaBeanSelective02() {
        SysUserDTO javaBean = new SysUserDTO();
        javaBean.setId(arg("1"));
        javaBean.setUserName("2");
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(table -> table.id().loginName())
                .insertJavaBeanSelective(javaBean);
        setSqlBuilder(sqlBuilder, "insert into `sys_user` (`id`) values (?)");
    }

    @Test
    void TestBatchInsertJavaBeans01() {
        List<SysUserDTO> javaBeans = new ArrayList<>();
        SysUserDTO javaBean = new SysUserDTO();
        arg(javaBean.getId());
        arg(javaBean.getUserName());
        arg(javaBean.getLoginName());
        javaBeans.add(javaBean);
        javaBean = new SysUserDTO();
        javaBean.setId(arg());
        javaBean.setUserName(arg());
        arg(javaBean.getLoginName());
        javaBeans.add(javaBean);

        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .batchInsertJavaBeans(javaBeans);
        setSqlBuilder(sqlBuilder, "insert into `sys_user` (`id`,`user_name`,`login_name`) values (?,?,?),(?,?,?)");
    }

}
