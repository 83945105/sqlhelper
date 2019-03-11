package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.model.SysUserModel;

import java.util.Arrays;

/**
 * MySql动态引擎 - 根据主键删除 - 测试
 */
public class MySqlDynamicEngineDeleteByPrimaryKeyTest extends AbstractTest {

    @Test
    void TestDeleteByPrimaryKey() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.delete(SysUserModel.class)
                .deleteByPrimaryKey(arg());

        setSqlBuilder(sqlBuilder, "delete from `sys_user` where `id` = ?");

        sqlBuilder = MySqlDynamicEngine.delete("sys_user", SysUserModel.class)
                .deleteByPrimaryKey(arg());

        setSqlBuilder(sqlBuilder, "delete from `sys_user` where `id` = ?");
    }

    @Test
    void TestBatchDeleteByPrimaryKeys() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.delete(SysUserModel.class)
                .batchDeleteByPrimaryKeys(Arrays.asList(arg(), arg()));

        setSqlBuilder(sqlBuilder, "delete from `sys_user` where `id` in (?,?)");
    }
}
