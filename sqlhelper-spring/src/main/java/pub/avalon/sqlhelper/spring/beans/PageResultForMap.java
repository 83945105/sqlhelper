package pub.avalon.sqlhelper.spring.beans;

import java.util.List;
import java.util.Map;

/**
 * 分页结果集
 *
 * @author 白超
 * @date 2018/8/6
 */
public class PageResultForMap extends AbstractPageResult {

    private List<Map<String, Object>> result;

    public List<Map<String, Object>> getResult() {
        return result;
    }

    public void setResult(List<Map<String, Object>> result) {
        this.result = result;
    }
}
