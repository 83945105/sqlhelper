package pub.avalonframework.sqlhelper.core.exception;

/**
 * @author baichao
 */
public class ParameterNullException extends RuntimeException {

    public ParameterNullException(Class methodClass, String methodName, String message) {
        super(methodClass.getName() + "." + methodName + " : the parameter cannot be null." + message);
    }
}
