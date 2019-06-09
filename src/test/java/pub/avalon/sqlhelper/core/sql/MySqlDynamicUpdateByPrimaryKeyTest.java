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
    void TestUpdateArgsByPrimaryKey() {
        List<Object> args = new ArrayList<>();
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .updateArgsByPrimaryKey(arg(), args);
        setSqlBuilder(sqlBuilder, "update `sys_user` set `user_name` = ?,`login_name` = ? where `id` = ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(SysUserDTO.Helper.Column::userName)
                .updateArgsByPrimaryKey(arg(), args);
        setSqlBuilder(sqlBuilder, "update `sys_user` set `user_name` = ? where `id` = ?");
    }

    @Test
    void TestUpdateJavaBeanByPrimaryKey() {
        SysUserDTO javaBean = new SysUserDTO();
        arg(javaBean.getUserName());
        arg(javaBean.getLoginName());
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .updateJavaBeanByPrimaryKey(arg(), javaBean);
        setSqlBuilder(sqlBuilder, "update `sys_user` set `user_name` = ?,`login_name` = ? where `id` = ?");

        javaBean = new SysUserDTO();
        arg(javaBean.getUserName());
        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(SysUserDTO.Helper.Column::userName)
                .updateJavaBeanByPrimaryKey(arg(), javaBean);
        setSqlBuilder(sqlBuilder, "update `sys_user` set `user_name` = ? where `id` = ?");
    }

    @Test
    void TestUpdateJavaBeanByPrimaryKeySelective() {
        SysUserDTO javaBean = new SysUserDTO();
        javaBean.setId("666");
        javaBean.setUserName(arg());
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .updateJavaBeanByPrimaryKeySelective(arg(), javaBean);
        setSqlBuilder(sqlBuilder, "update `sys_user` set `user_name` = ? where `id` = ?");

        javaBean = new SysUserDTO();
        javaBean.setId("666");
        javaBean.setUserName(arg());
        javaBean.setLoginName("233");
        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(SysUserDTO.Helper.Column::userName)
                .updateJavaBeanByPrimaryKeySelective(arg(), javaBean);
        setSqlBuilder(sqlBuilder, "update `sys_user` set `user_name` = ? where `id` = ?");
    }

    @Test
    void TestBatchUpdateJavaBeansByPrimaryKeys() {
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
        setSqlBuilder(sqlBuilder, "update `sys_user` SysUserDTO set SysUserDTO.`user_name`=case SysUserDTO.`id` when '1' then ? when '2' then ? when '3' then ?  end,SysUserDTO.`login_name`=case SysUserDTO.`id` when '1' then ? when '2' then ? when '3' then ?  end where SysUserDTO.`id` in (?,?,?)");
    }

}
