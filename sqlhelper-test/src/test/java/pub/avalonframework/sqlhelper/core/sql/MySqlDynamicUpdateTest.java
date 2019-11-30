package pub.avalonframework.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalonframework.sqlhelper.AbstractTest;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalonframework.sqlhelper.readme.entity.SysUser;
import pub.avalonframework.sqlhelper.readme.entity.SysUserHelper;
import pub.avalonframework.sqlhelper.readme.entity.UserRoleHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * MySql动态引擎 - 更新 - 条件更新
 */
public class MySqlDynamicUpdateTest extends AbstractTest {

    @Test
    void TestUpdateArgsByPrimaryKey01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .updateArgsByPrimaryKey(1, 2, 3);
        arg(2);
        arg(3);
        arg(1);
        setSqlBuilder(sqlBuilderResult, "update `sys_user` set `user_name` = ?,`login_name` = ? where `id` = ?");
    }

    @Test
    void TestUpdateArgsByPrimaryKey02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(SysUserHelper.Column::userName)
                .updateArgsByPrimaryKey(1, 2);
        arg(2);
        arg(1);
        setSqlBuilder(sqlBuilderResult, "update `sys_user` set `user_name` = ? where `id` = ?");
    }

    @Test
    void TestUpdateJavaBeanByPrimaryKey01() {
        SysUser javaBean = new SysUser();
        arg(javaBean.getUserName());
        arg(javaBean.getLoginName());
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .updateJavaBeanByPrimaryKey(arg(), javaBean);
        setSqlBuilder(sqlBuilderResult, "update `sys_user` set `user_name` = ?,`login_name` = ? where `id` = ?");
    }

    @Test
    void TestUpdateJavaBeanByPrimaryKey02() {
        SysUser javaBean = new SysUser();
        arg(javaBean.getUserName());
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(SysUserHelper.Column::userName)
                .updateJavaBeanByPrimaryKey(arg(), javaBean);
        setSqlBuilder(sqlBuilderResult, "update `sys_user` set `user_name` = ? where `id` = ?");
    }

    @Test
    void TestUpdateJavaBeanByPrimaryKeySelective01() {
        SysUser javaBean = new SysUser();
        javaBean.setId("666");
        javaBean.setUserName(arg());
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .updateJavaBeanByPrimaryKeySelective(arg(), javaBean);
        setSqlBuilder(sqlBuilderResult, "update `sys_user` set `user_name` = ? where `id` = ?");
    }

    @Test
    void TestUpdateJavaBeanByPrimaryKeySelective02() {
        SysUser javaBean = new SysUser();
        javaBean.setId("666");
        javaBean.setUserName(arg());
        javaBean.setLoginName("233");
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(SysUserHelper.Column::userName)
                .updateJavaBeanByPrimaryKeySelective(arg(), javaBean);
        setSqlBuilder(sqlBuilderResult, "update `sys_user` set `user_name` = ? where `id` = ?");
    }

    @Test
    void TestBatchUpdateJavaBeansByPrimaryKeys01() {
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

        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .batchUpdateJavaBeansByPrimaryKeys(javaBeans);
        setSqlBuilder(sqlBuilderResult, "update `sys_user` SysUser set SysUser.`user_name`=case SysUser.`id` when '1' then ? when '2' then ? when '3' then ?  end,SysUser.`login_name`=case SysUser.`id` when '1' then ? when '2' then ? when '3' then ?  end where SysUser.`id` in (?,?,?)");
    }

    @Test
    void TestUpdateJavaBean01() {
        SysUser javaBean = new SysUser();
        javaBean.setId(arg());
        javaBean.setUserName(arg());
        arg(null);

        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .updateJavaBean(javaBean);
        setSqlBuilder(sqlBuilderResult, "update `sys_user` SysUser set SysUser.`id` = ?,SysUser.`user_name` = ?,SysUser.`login_name` = ?");
    }

    @Test
    void TestUpdateJavaBeanAndJoinAndWhere01() {
        SysUser javaBean = new SysUser();
        javaBean.setId("666");
        javaBean.setUserName("1");
        arg("1");
        arg("1");

        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table.userName().userName())
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where(UserRoleHelper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .updateJavaBean(javaBean);
        setSqlBuilder(sqlBuilderResult, "update `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` set SysUser.`user_name` = ?,SysUser.`user_name` = ? where UserRole.`role_name` = ?");
    }

    @Test
    void TestUpdateJavaBeanAndJoinAndWhereSelective01() {
        SysUser javaBean = new SysUser();
        javaBean.setUserName(arg());

        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where(UserRoleHelper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .updateJavaBeanSelective(javaBean);
        setSqlBuilder(sqlBuilderResult, "update `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` set SysUser.`user_name` = ? where UserRole.`role_name` = ?");
    }

    @Test
    void TestUpdateOrInsertJavaBeans01() {
        List<SysUser> javaBeans = new ArrayList<>();
        SysUser sysUser = new SysUser();
        arg(sysUser.getId());
        sysUser.setUserName(arg());
        arg(sysUser.getLoginName());
        javaBeans.add(sysUser);
        sysUser = new SysUser();
        arg(sysUser.getId());
        sysUser.setUserName(arg());
        arg(sysUser.getLoginName());
        javaBeans.add(sysUser);
        sysUser = new SysUser();
        arg(sysUser.getId());
        sysUser.setUserName(arg());
        arg(sysUser.getLoginName());
        javaBeans.add(sysUser);

        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .updateOrInsertJavaBeans(javaBeans);
        setSqlBuilder(sqlBuilderResult, "insert into sys_user (`id`,`user_name`,`login_name`) values (?,?,?),(?,?,?),(?,?,?) on duplicate key update `id` = values(`id`),`user_name` = values(`user_name`),`login_name` = values(`login_name`)");
    }

    @Test
    void TestUpdateOrInsertJavaBeans02() {
        List<SysUser> javaBeans = new ArrayList<>();
        SysUser sysUser = new SysUser();
        javaBeans.add(sysUser);
        sysUser.setUserName(arg());

        sysUser = new SysUser();
        sysUser.setUserName(arg());
        javaBeans.add(sysUser);

        sysUser = new SysUser();
        sysUser.setUserName(arg());
        javaBeans.add(sysUser);
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(SysUserHelper.Column::userName)
                .updateOrInsertJavaBeans(javaBeans);
        setSqlBuilder(sqlBuilderResult, "insert into sys_user (`user_name`) values (?),(?),(?) on duplicate key update `user_name` = values(`user_name`)");
    }
}
