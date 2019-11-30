package pub.avalonframework.sqlhelper.core.rules;

import pub.avalonframework.sqlhelper.core.callback.SubQueryCallback;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public interface ToSubQueryComparisonOperator<T> {

    /**
     * 等于
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T equalToSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 不等于
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T notEqualToSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 大于
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T greaterThanSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 大于等于
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T greaterThanAndEqualToSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 小于
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T lessThanSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 小于等于
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T lessThanAndEqualToSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 模糊匹配
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T likeSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 在...内
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T inSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 不在...内
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T notInSubQuery(SubQueryCallback subQueryCallback);
}