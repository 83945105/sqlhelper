package pub.avalonframework.sqlhelper.core.rules;

/**
 * @author baichao
 */
public interface GroupByOperator<T> {

    T min();

    T max();

    T count();

    T sum();

    T avg();

    T stddev();

    T variance();
}