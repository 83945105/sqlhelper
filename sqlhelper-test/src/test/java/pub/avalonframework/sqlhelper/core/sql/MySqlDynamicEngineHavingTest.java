package pub.avalonframework.sqlhelper.core.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalonframework.sqlhelper.core.beans.GroupType;
import pub.avalonframework.sqlhelper.core.engine.builder.SqlColumn;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalonframework.sqlhelper.readme.entity.SysUserHelper;
import pub.avalonframework.sqlhelper.readme.entity.UserRoleHelper;

/**
 * MySql动态引擎 - Having测试
 */
public class MySqlDynamicEngineHavingTest {

    /**
     * 测试列 - 默认列
     */
    @Test
    void Test_having_default() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(SysUserHelper.Column::id)
                .having((having, mainTable) -> having.and(mainTable.id().max().equalTo("1")))
                .query();
        Assertions.assertEquals("select SysUser.`id` `id` from `sys_user` SysUser having max(SysUser.`id`) = ?", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{"1"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

}