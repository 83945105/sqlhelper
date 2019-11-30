package pub.avalonframework.sqlhelper.core.exception;

/**
 * @author baichao
 */
public final class ComparisonRuleNullException extends ParameterNullException {

    public ComparisonRuleNullException(Class methodClass, String methodName, String message) {
        super(methodClass, methodName, message);
    }
}
