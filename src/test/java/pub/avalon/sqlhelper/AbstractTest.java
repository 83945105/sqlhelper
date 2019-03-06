package pub.avalon.sqlhelper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import pub.avalon.sqlhelper.core.build.SqlBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试基类
 */
public abstract class AbstractTest {

    private static List<List<Object>> argListList;
    private static List<String> sqlList;
    private static List<SqlBuilder> sqlBuilderList;

    protected <T> T arg(T arg) {
        AbstractTest.argListList.get(AbstractTest.argListList.size() - 1).add(arg);
        return arg;
    }

    protected String arg() {
        String arg = "参数" + (AbstractTest.argListList.size() + 1);
        return arg(arg);
    }

    protected void setSqlBuilder(SqlBuilder sqlBuilder, String sql) {
        AbstractTest.argListList.add(new ArrayList<>());
        AbstractTest.sqlList.add(sql);
        AbstractTest.sqlBuilderList.add(sqlBuilder);
    }

    @BeforeAll
    static void beforeAll() {
        AbstractTest.argListList = new ArrayList<>();
        AbstractTest.argListList.add(new ArrayList<>());
        AbstractTest.sqlList = new ArrayList<>();
        AbstractTest.sqlBuilderList = new ArrayList<>();
    }

    @AfterEach
    void afterEach() {
        SqlBuilder sqlBuilder;
        for (int i = 0; i < AbstractTest.sqlBuilderList.size(); i++) {
            sqlBuilder = AbstractTest.sqlBuilderList.get(i);
            //产出预编译sql
            String sql = sqlBuilder.getPreparedStatementSql();
            Assertions.assertEquals(sql, AbstractTest.sqlList.get(i));
            //产出预编译sql参数
            List<Object> args = sqlBuilder.getPreparedStatementArgs();
            List<Object> argList = AbstractTest.argListList.get(i);
            Assertions.assertEquals(args.size(), argList.size());
            for (int j = 0; j < argList.size(); j++) {
                Assertions.assertEquals(args.get(j), argList.get(j));
            }
        }
    }
}
