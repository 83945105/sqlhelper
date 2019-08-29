package pub.avalon.sqlhelper.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

/**
 * Created by 白超 on 2018/8/27.
 */
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class MySqlDynamicQueryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Test
    void Test() {
        List<Map<String, Object>> results = jdbcTemplate.queryForList("select * from jur_role");
        System.out.println(results.toString());
    }
}
