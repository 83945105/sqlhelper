package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.model.SysUserModel;

/**
 * MySql动态引擎 - 删除 - 测试
 */
public class MySqlDynamicEngineDeleteTest extends AbstractTest {

    @Test
    void TestDelete() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.delete(SysUserModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .delete();

        setSqlBuilder(sqlBuilder, "delete SysUser from sys_user SysUser where SysUser.`user_name` = ?");
    }
}
