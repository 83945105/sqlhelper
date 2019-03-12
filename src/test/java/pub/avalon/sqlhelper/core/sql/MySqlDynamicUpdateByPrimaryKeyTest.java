package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.SysUser;
import pub.avalon.sqlhelper.readme.model.SysUserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * MySql动态引擎 - 更新 - 根据主键更新
 */
public class MySqlDynamicUpdateByPrimaryKeyTest extends AbstractTest {

    @Test
    void TestUpdateArgsByPrimaryKey() {
        List<Object> args = new ArrayList<>();
        SqlBuilder sqlBuilder = MySqlDynamicEngine.update(SysUserModel.class)
                .updateArgsByPrimaryKey(arg(), args);
        setSqlBuilder(sqlBuilder, "update `sys_user` set `user_name` = ?,`login_name` = ? where `id` = ?");

        sqlBuilder = MySqlDynamicEngine.update(SysUserModel.class)
                .column(SysUserModel.Column::userName)
                .updateArgsByPrimaryKey(arg(), args);
        setSqlBuilder(sqlBuilder, "update `sys_user` set `user_name` = ? where `id` = ?");
    }

    @Test
    void TestUpdateJavaBeanByPrimaryKey() {
        SysUser javaBean = new SysUser();
        arg(javaBean.getUserName());
        arg(javaBean.getLoginName());
        SqlBuilder sqlBuilder = MySqlDynamicEngine.update(SysUserModel.class)
                .updateJavaBeanByPrimaryKey(arg(), javaBean);
        setSqlBuilder(sqlBuilder, "update `sys_user` set `user_name` = ?,`login_name` = ? where `id` = ?");

        javaBean = new SysUser();
        arg(javaBean.getUserName());
        sqlBuilder = MySqlDynamicEngine.update(SysUserModel.class)
                .column(SysUserModel.Column::userName)
                .updateJavaBeanByPrimaryKey(arg(), javaBean);
        setSqlBuilder(sqlBuilder, "update `sys_user` set `user_name` = ? where `id` = ?");
    }

    @Test
    void TestUpdateJavaBeanByPrimaryKeySelective() {
        SysUser javaBean = new SysUser();
        javaBean.setId("666");
        javaBean.setUserName(arg());
        SqlBuilder sqlBuilder = MySqlDynamicEngine.update(SysUserModel.class)
                .updateJavaBeanByPrimaryKeySelective(arg(), javaBean);
        setSqlBuilder(sqlBuilder, "update `sys_user` set `user_name` = ? where `id` = ?");

        javaBean = new SysUser();
        javaBean.setId("666");
        javaBean.setUserName(arg());
        javaBean.setLoginName("233");
        sqlBuilder = MySqlDynamicEngine.update(SysUserModel.class)
                .column(SysUserModel.Column::userName)
                .updateJavaBeanByPrimaryKeySelective(arg(), javaBean);
        setSqlBuilder(sqlBuilder, "update `sys_user` set `user_name` = ? where `id` = ?");
    }

    @Test
    void TestBatchUpdateJavaBeansByPrimaryKeys() {
        List<SysUser> javaBeans = new ArrayList<>();

        SysUser sysUser = new SysUser();
        sysUser.setId("1");
        sysUser.setUserName("1-1");
        sysUser.setLoginName("1-2");
        javaBeans.add(sysUser);

        sysUser = new SysUser();
        sysUser.setId("2");
        sysUser.setUserName("2-1");
        sysUser.setLoginName("2-2");
        javaBeans.add(sysUser);

        sysUser = new SysUser();
        sysUser.setId("3");
        sysUser.setUserName("3-1");
        sysUser.setLoginName("3-2");
        javaBeans.add(sysUser);

        arg("1-1");
        arg("2-1");
        arg("3-1");

        arg("1-2");
        arg("2-2");
        arg("3-2");

        arg("1");
        arg("2");
        arg("3");

        SqlBuilder sqlBuilder = MySqlDynamicEngine.update(SysUserModel.class)
                .batchUpdateJavaBeansByPrimaryKeys(javaBeans);
        setSqlBuilder(sqlBuilder, "update `sys_user` SysUser set SysUser.`user_name`=case SysUser.`id` when '1' then ? when '2' then ? when '3' then ?  end,SysUser.`login_name`=case SysUser.`id` when '1' then ? when '2' then ? when '3' then ?  end where SysUser.`id` in (?,?,?)");
    }

}
