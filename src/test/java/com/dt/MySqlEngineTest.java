package com.dt;

import com.dt.factory.MySqlEngine;
import com.shiro.model.JurRoleModel;
import com.shiro.model.JurRoleResModel;
import com.shiro.model.JurRoleUserModel;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * Created by 白超 on 2018/8/22.
 */
public class MySqlEngineTest {

    @Test
    void method01() {
        String sql = MySqlEngine.query(JurRoleModel.class)
                .queryByPrimaryKey("666").getPreparedStatementSql();
        System.out.println(sql);
    }

    @Test
    void method02() {
        for (int i = 0; i < 1; i++) {
            Long st = System.nanoTime();
            String sql = MySqlEngine.query(JurRoleModel.class)
                    .innerJoin(JurRoleUserModel.class, (on, joinTable, mainTable) -> on
                            .and(joinTable.roleId().equalTo(mainTable.id())))
                    .innerJoin(JurRoleResModel.class, (on, joinTable, mainTable) -> on
                            .and(joinTable.roleId().equalTo(mainTable.id())))
                    .where((condition, mainTable) -> condition
                            .and(mainTable.id().equalTo("")))
                    .group(table -> table.id().createTime())
                    .sort(table -> table.id().asc())
                    .limit(0, 10)
                    .query().getPreparedStatementSql();
            Long et = System.nanoTime() - st;
            System.out.println("耗时:" + et + " 纳秒 " + et / 1000000 + "毫秒");
            System.out.println(sql);
        }
    }

    @Test
    void method03() {
        for (int i = 0; i < 1; i++) {
            Long st = System.nanoTime();
            String sql = MySqlEngine.query(JurRoleModel.class)
                    .innerJoin(JurRoleUserModel.class, (on, joinTable, mainTable) -> on
                            .and(joinTable.roleId().equalTo(mainTable.id())))
                    .innerJoin(JurRoleResModel.class, (on, joinTable, mainTable) -> on
                            .and(joinTable.roleId().equalTo(mainTable.id())))
                    .where((condition, mainTable) -> condition
                            .and(mainTable.id().equalTo("")))
                    .group(table -> table.id().createTime())
                    .sort(table -> table.id().asc())
                    .limit(0, 10)
                    .queryCount().getPreparedStatementSql();
            Long et = System.nanoTime() - st;
            System.out.println("耗时:" + et + " 纳秒 " + et / 1000000 + "毫秒");
            System.out.println(sql);
        }
    }

    @Test
    void method04() {
        String sql = MySqlEngine.insert(JurRoleModel.class)
                .insertJavaBean(new HashMap<>())
                .getPreparedStatementSql();
        System.out.println(sql);
    }
}
