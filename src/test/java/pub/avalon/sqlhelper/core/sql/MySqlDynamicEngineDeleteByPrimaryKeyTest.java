package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.SysUser;

import java.util.Arrays;

/**
 * MySql动态引擎 - 删除 - 主键删除
 */
public class MySqlDynamicEngineDeleteByPrimaryKeyTest extends AbstractTest {

    @Test
    void TestDeleteByPrimaryKey() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .deleteByPrimaryKey(arg());
        setSqlBuilder(sqlBuilder, "delete from `sys_user` where `id` = ?");

        sqlBuilder = MySqlDynamicEngine.table("sys_user", SysUser.Helper.class)
                .deleteByPrimaryKey(arg());
        setSqlBuilder(sqlBuilder, "delete from `sys_user` where `id` = ?");
    }

    @Test
    void TestBatchDeleteByPrimaryKeys() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .batchDeleteByPrimaryKeys(Arrays.asList(arg(), arg()));
        setSqlBuilder(sqlBuilder, "delete from `sys_user` where `id` in (?,?)");
    }
}
