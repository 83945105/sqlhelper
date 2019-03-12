package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.SysUser;
import pub.avalon.sqlhelper.readme.model.SysUserModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MySql动态引擎 - 新增 - 参数新增、实体类新增
 */
public class MySqlDynamicEngineInsertTest extends AbstractTest {

    @Test
    void TestInsertArgs() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.insert(SysUserModel.class)
                .insertArgs(Arrays.asList(arg("1"), arg("2")));
        setSqlBuilder(sqlBuilder, "insert into `sys_user` (`id`,`user_name`,`login_name`) values (?,?,?)");

        sqlBuilder = MySqlDynamicEngine.insert("sys_user", SysUserModel.class)
                .insertArgs(Arrays.asList(arg("1"), arg("2"), arg("3")));
        setSqlBuilder(sqlBuilder, "insert into `sys_user` (`id`,`user_name`,`login_name`) values (?,?,?)");

        // 插入指定列
        sqlBuilder = MySqlDynamicEngine.insert(SysUserModel.class)
                .column(SysUserModel.Column::id)
                .insertArgs(Arrays.asList(arg("1"), arg("2"), arg("3")));
        setSqlBuilder(sqlBuilder, "insert into `sys_user` (`id`) values (?)");
    }

    @Test
    void TestInsertJavaBean() {
        SysUser javaBean = new SysUser();
        javaBean.setId(arg("1"));
        javaBean.setUserName(arg("2"));
        javaBean.setLoginName(arg("3"));
        SqlBuilder sqlBuilder = MySqlDynamicEngine.insert(SysUserModel.class)
                .insertJavaBean(javaBean);
        setSqlBuilder(sqlBuilder, "insert into `sys_user` (`id`,`user_name`,`login_name`) values (?,?,?)");

        javaBean = new SysUser();
        javaBean.setId(arg("1"));
        javaBean.setLoginName(arg("3"));

        sqlBuilder = MySqlDynamicEngine.insert(SysUserModel.class)
                .column(table -> table.id().loginName())
                .insertJavaBean(javaBean);
        setSqlBuilder(sqlBuilder, "insert into `sys_user` (`id`,`login_name`) values (?,?)");
    }

    @Test
    void TestInsertJavaBeanSelective() {
        SysUser javaBean = new SysUser();
        javaBean.setId(arg("1"));
        javaBean.setUserName(arg("2"));
        SqlBuilder sqlBuilder = MySqlDynamicEngine.insert(SysUserModel.class)
                .insertJavaBeanSelective(javaBean);
        setSqlBuilder(sqlBuilder, "insert into `sys_user` (`id`,`user_name`) values (?,?)");

        javaBean = new SysUser();
        javaBean.setId(arg("1"));
        javaBean.setUserName("2");
        sqlBuilder = MySqlDynamicEngine.insert(SysUserModel.class)
                .column(table -> table.id().loginName())
                .insertJavaBeanSelective(javaBean);
        setSqlBuilder(sqlBuilder, "insert into `sys_user` (`id`) values (?)");
    }

    @Test
    void TestBatchInsertJavaBeans() {
        List<SysUser> javaBeans = new ArrayList<>();
        SysUser javaBean = new SysUser();
        arg(javaBean.getId());
        arg(javaBean.getUserName());
        arg(javaBean.getLoginName());
        javaBeans.add(javaBean);
        javaBean = new SysUser();
        javaBean.setId(arg());
        javaBean.setUserName(arg());
        arg(javaBean.getLoginName());
        javaBeans.add(javaBean);

        SqlBuilder sqlBuilder = MySqlDynamicEngine.insert(SysUserModel.class)
                .batchInsertJavaBeans(javaBeans);
        setSqlBuilder(sqlBuilder, "insert into `sys_user` (`id`,`user_name`,`login_name`) values (?,?,?),(?,?,?)");
    }

}
