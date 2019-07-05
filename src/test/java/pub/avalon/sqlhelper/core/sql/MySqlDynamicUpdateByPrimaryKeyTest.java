package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.SysUserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * MySql动态引擎 - 更新 - 根据主键更新
 */
public class MySqlDynamicUpdateByPrimaryKeyTest extends AbstractTest {

    @Test
    void TestUpdateArgsByPrimaryKey01() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .updateArgsByPrimaryKey(1, 2, 3);
        arg(2);
        arg(3);
        arg(1);
        setSqlBuilder(sqlBuilder, "update `sys_user` set `user_name` = ?,`login_name` = ? where `id` = ?");
    }

    @Test
    void TestUpdateArgsByPrimaryKey02() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(SysUserDTO.Helper.Column::userName)
                .updateArgsByPrimaryKey(1, 2);
        arg(2);
        arg(1);
        setSqlBuilder(sqlBuilder, "update `sys_user` set `user_name` = ? where `id` = ?");
    }

    @Test
    void TestUpdateJavaBeanByPrimaryKey01() {
        SysUserDTO javaBean = new SysUserDTO();
        arg(javaBean.getUserName());
        arg(javaBean.getLoginName());
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .updateJavaBeanByPrimaryKey(arg(), javaBean);
        setSqlBuilder(sqlBuilder, "update `sys_user` set `user_name` = ?,`login_name` = ? where `id` = ?");
    }

    @Test
    void TestUpdateJavaBeanByPrimaryKey02() {
        SysUserDTO javaBean = new SysUserDTO();
        arg(javaBean.getUserName());
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(SysUserDTO.Helper.Column::userName)
                .updateJavaBeanByPrimaryKey(arg(), javaBean);
        setSqlBuilder(sqlBuilder, "update `sys_user` set `user_name` = ? where `id` = ?");
    }

    @Test
    void TestUpdateJavaBeanByPrimaryKeySelective01() {
        SysUserDTO javaBean = new SysUserDTO();
        javaBean.setId("666");
        javaBean.setUserName(arg());
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .updateJavaBeanByPrimaryKeySelective(arg(), javaBean);
        setSqlBuilder(sqlBuilder, "update `sys_user` set `user_name` = ? where `id` = ?");
    }

    @Test
    void TestUpdateJavaBeanByPrimaryKeySelective02() {
        SysUserDTO javaBean = new SysUserDTO();
        javaBean.setId("666");
        javaBean.setUserName(arg());
        javaBean.setLoginName("233");
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(SysUserDTO.Helper.Column::userName)
                .updateJavaBeanByPrimaryKeySelective(arg(), javaBean);
        setSqlBuilder(sqlBuilder, "update `sys_user` set `user_name` = ? where `id` = ?");
    }

    @Test
    void TestBatchUpdateJavaBeansByPrimaryKeys01() {
        List<SysUserDTO> javaBeans = new ArrayList<>();

        SysUserDTO sysUser = new SysUserDTO();
        sysUser.setId("1");
        sysUser.setUserName("1-1");
        sysUser.setLoginName("1-2");
        javaBeans.add(sysUser);

        sysUser = new SysUserDTO();
        sysUser.setId("2");
        sysUser.setUserName("2-1");
        sysUser.setLoginName("2-2");
        javaBeans.add(sysUser);

        sysUser = new SysUserDTO();
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

        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .batchUpdateJavaBeansByPrimaryKeys(javaBeans);
        setSqlBuilder(sqlBuilder, "update `sys_user` SysUser set SysUser.`user_name`=case SysUser.`id` when '1' then ? when '2' then ? when '3' then ?  end,SysUser.`login_name`=case SysUser.`id` when '1' then ? when '2' then ? when '3' then ?  end where SysUser.`id` in (?,?,?)");
    }

}
