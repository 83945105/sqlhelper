package pub.avalonframework.sqlhelper.core.rules;

import pub.avalonframework.sqlhelper.core.builder.AbstractComparisonSqlPartDatumBuilder;
import pub.avalonframework.sqlhelper.core.builder.SqlPartDatumBuilder;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public interface ToSqlPartBuilderComparisonOperator<T extends Helper, SB extends AbstractComparisonSqlPartDatumBuilder> {

    /**
     * equal to
     *
     * @param sqlPartDatumBuilder {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T equalTo(SB sqlPartDatumBuilder);

    /**
     * not equal to
     *
     * @param sqlPartDatumBuilder {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T notEqualTo(SB sqlPartDatumBuilder);

    /**
     * greater than
     *
     * @param sqlPartDatumBuilder {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T greaterThan(SB sqlPartDatumBuilder);

    /**
     * greater than or equal to
     *
     * @param sqlPartDatumBuilder {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T greaterThanAndEqualTo(SB sqlPartDatumBuilder);

    /**
     * less than
     *
     * @param sqlPartDatumBuilder {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T lessThan(SB sqlPartDatumBuilder);

    /**
     * less than or equal to
     *
     * @param sqlPartDatumBuilder {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T lessThanAndEqualTo(SB sqlPartDatumBuilder);

    /**
     * between ... and ...
     *
     * @param sqlPartDatumBuilder       {@link SqlPartDatumBuilder}
     * @param secondSqlPartDatumBuilder {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T between(SB sqlPartDatumBuilder, SB secondSqlPartDatumBuilder);

    /**
     * like
     *
     * @param sqlPartDatumBuilder {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T like(SB sqlPartDatumBuilder);

    /**
     * in
     *
     * @param sqlPartDatumBuilders {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T in(SB... sqlPartDatumBuilders);

    /**
     * not in
     *
     * @param sqlPartDatumBuilders {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T notIn(SB... sqlPartDatumBuilders);
}