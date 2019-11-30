package pub.avalonframework.sqlhelper.core.rules;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public interface ToColumnComparisonOperator<T> {

    /**
     * equal to
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T equalTo(ColumnHelper<?> columnHelper);

    /**
     * not equal to
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T notEqualTo(ColumnHelper<?> columnHelper);

    /**
     * greater than
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T greaterThan(ColumnHelper<?> columnHelper);

    /**
     * greater than or equal to
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T greaterThanAndEqualTo(ColumnHelper<?> columnHelper);

    /**
     * less than
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T lessThan(ColumnHelper<?> columnHelper);

    /**
     * less than or equal to
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T lessThanAndEqualTo(ColumnHelper<?> columnHelper);

    /**
     * between ... and ...
     *
     * @param columnHelper       extends {@link ColumnHelper} object
     * @param secondColumnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T between(ColumnHelper<?> columnHelper, ColumnHelper<?> secondColumnHelper);

    /**
     * like
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T like(ColumnHelper<?> columnHelper);

    /**
     * in
     *
     * @param columnHelpers extends {@link ColumnHelper} objects
     * @return extends {@link Helper} object
     */
    T in(ColumnHelper<?>... columnHelpers);

    /**
     * not in
     *
     * @param columnHelpers extends {@link ColumnHelper} objects
     * @return extends {@link Helper} object
     */
    T notIn(ColumnHelper<?>... columnHelpers);
}