package pub.avalonframework.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalonframework.sqlhelper.core.option.SqlPrintOptions;

/**
 * MySql动态引擎 - 配置测试
 */
public class MySqlDynamicEngineOptionTest {

    @Test
    void Test_modifyDefaultSqlBuilderOptions() {
        SqlBuilderOptions.getDefaultSqlBuilderOptions()
                .setSqlPrintOptions(new SqlPrintOptions());
    }

}
