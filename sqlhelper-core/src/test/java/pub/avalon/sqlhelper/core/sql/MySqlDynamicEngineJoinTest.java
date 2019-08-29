package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.SysUserDTO;
import pub.avalon.sqlhelper.readme.entity.UserRoleDTO;

/**
 * MySql动态引擎 - 列测试
 */
public class MySqlDynamicEngineJoinTest {

    /**
     * 测试join - 默认
     */
    @Test
    void Test_join_default() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .join(JoinType.INNER, UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试join - 指定表名 & 指定表别名
     */
//    @Test
    void Test_join_assignTableName_assignTableAlias() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class, "A")
                .join(JoinType.INNER, "user_role_custom", UserRoleDTO.Helper.class, "B", (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }
}
