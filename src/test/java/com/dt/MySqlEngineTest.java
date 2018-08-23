package com.dt;

import com.dt.factory.MySqlEngine;
import com.shiro.model.JurRoleModel;
import org.junit.jupiter.api.Test;

/**
 * Created by 白超 on 2018/8/22.
 */
public class MySqlEngineTest {

    @Test
    void method01() {
        String sql = MySqlEngine.main(JurRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.createTime().equalTo("")))
                .queryByPrimaryKey("666").getPreparedStatementSql();
        System.out.println(sql);
    }
}
