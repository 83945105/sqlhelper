package pub.avalonframework.sqlhelper.maven;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author baichao
 */
public class TableHelperOptions {

    public final static TableHelperOptions DEFAULT_TABLE_HELPER_OPTIONS = new TableHelperOptions();

    @Parameter(defaultValue = "true")
    private boolean constantComment = true;

    @Parameter(defaultValue = "Helper")
    private String classNameSuffix = "Helper";

    public boolean isConstantComment() {
        return constantComment;
    }

    public String getClassNameSuffix() {
        return classNameSuffix;
    }
}