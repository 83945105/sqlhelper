package pub.avalon.sqlhelper.core.sql;

import com.shiro.JurRoleModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;

/**
 * Created by 白超 on 2018/8/27.
 */
public class MySqlDynamicDeleteTest {

    @Test
    void TestDelete() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.delete(JurRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.role().equalTo(6)))
                .delete();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "delete JurRole from jur_role JurRole where JurRole.`role` = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), 6);

        System.out.println(sqlBuilder.getPreparedStatementSql());
    }
}
