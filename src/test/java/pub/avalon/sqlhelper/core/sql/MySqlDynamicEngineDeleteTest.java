package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.SysUser;

/**
 * MySql动态引擎 - 删除 - 条件删除
 */
public class MySqlDynamicEngineDeleteTest extends AbstractTest {

    @Test
    void TestDelete() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .delete();
        setSqlBuilder(sqlBuilder, "delete SysUser from `sys_user` SysUser where SysUser.`user_name` = ?");
    }
}
