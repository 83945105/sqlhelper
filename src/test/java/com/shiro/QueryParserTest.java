package com.shiro;

import com.dt.core.data.ParseData;
import com.dt.factory.MySqlEngine;
import com.dt.jdbc.parser.QueryParser;
import com.shiro.model.JurRoleModel;
import com.shiro.model.JurRoleUserModel;
import org.junit.jupiter.api.Test;

/**
 * Created by 白超 on 2018/8/15.
 */
public class QueryParserTest {

    @Test
    void queryForListTest() {
        QueryParser queryParser = new QueryParser();

        int startCount = 100;
        int count = 1100;
        int doCount = 0;
        Long tt = 0L;

        for (int i = 0; i < count; i++) {

            Long startTime = System.nanoTime();

            ParseData data = queryParser.selectList(MySqlEngine.main(JurRoleModel.class)
                    .innerJoin(JurRoleUserModel.class, (on, joinTable, mainTable) -> on.and(joinTable.roleId().equalTo(mainTable.id())))
                    .where((condition, mainTable) -> condition
                            .and(mainTable.id().equalTo("").createTime().greaterThan(""))
                            .or(mainTable.index().between("", "")))
                    .sort(table -> table.createTime().asc()));

            Long time = System.nanoTime() - startTime;
            if (i >= startCount) {
                tt += time;
                doCount++;
            }
//            System.out.println(sqlData.getSql());
            System.out.println("耗时:" + time + "纳秒 " + time / 1000000 + "毫秒");

        }
        System.out.println("累计耗时:" + tt + "纳秒 " + tt / 1000000 + "毫秒 共计算" + doCount + "次");
        System.out.println("累计平均耗时:" + tt / (count - startCount) + "纳秒 " + tt / 1000000 / (count - startCount) + "毫秒 共计算" + doCount + "次");

    }
}
