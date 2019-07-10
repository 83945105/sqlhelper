package pub.avalon.sqlhelper.core.logical;

/**
 * 逻辑控制
 *
 * @author 白超
 * @date 2019/7/10
 */
public interface LogicalControl<T> {

    T If();

    T end();
}
