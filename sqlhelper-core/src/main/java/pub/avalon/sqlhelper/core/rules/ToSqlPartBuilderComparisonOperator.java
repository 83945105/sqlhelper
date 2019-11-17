package pub.avalon.sqlhelper.core.rules;

import pub.avalon.sqlhelper.core.builder.SqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public interface ToSqlPartBuilderComparisonOperator<T, S extends SqlPartDatumBuilder> {

    /**
     * equal to
     *
     * @param sqlPartDatumBuilder {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T equalTo(S sqlPartDatumBuilder);

    /**
     * not equal to
     *
     * @param sqlPartDatumBuilder {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T notEqualTo(S sqlPartDatumBuilder);

    /**
     * greater than
     *
     * @param sqlPartDatumBuilder {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T greaterThan(S sqlPartDatumBuilder);

    /**
     * greater than or equal to
     *
     * @param sqlPartDatumBuilder {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T greaterThanAndEqualTo(S sqlPartDatumBuilder);

    /**
     * less than
     *
     * @param sqlPartDatumBuilder {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T lessThan(S sqlPartDatumBuilder);

    /**
     * less than or equal to
     *
     * @param sqlPartDatumBuilder {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T lessThanAndEqualTo(S sqlPartDatumBuilder);

    /**
     * between ... and ...
     *
     * @param sqlPartDatumBuilder       {@link SqlPartDatumBuilder}
     * @param secondSqlPartDatumBuilder {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T between(S sqlPartDatumBuilder, S secondSqlPartDatumBuilder);

    /**
     * like
     *
     * @param sqlPartDatumBuilder {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T like(S sqlPartDatumBuilder);

    /**
     * in
     *
     * @param sqlPartDatumBuilders {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T in(S... sqlPartDatumBuilders);

    /**
     * not in
     *
     * @param sqlPartDatumBuilders {@link SqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T notIn(S... sqlPartDatumBuilders);
}