package pub.avalon.sqlhelper.spring.beans;

import java.util.List;

/**
 * 分页结果集
 *
 * @author 白超
 * @date 2018/8/6
 */
public class PageResultForBean<T> extends AbstractPageResult {

    private List<T> result;

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
