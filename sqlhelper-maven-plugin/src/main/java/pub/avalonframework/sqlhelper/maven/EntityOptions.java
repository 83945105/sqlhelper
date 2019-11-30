package pub.avalonframework.sqlhelper.maven;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author baichao
 */
public class EntityOptions {

    public final static EntityOptions DEFAULT_ENTITY_OPTIONS = new EntityOptions();

    @Parameter(defaultValue = "")
    private String classNameSuffix = "";

    @Parameter(defaultValue = "true")
    private boolean chainStyle = true;

    @Parameter(defaultValue = "true")
    private boolean fieldComment = true;

    public String getClassNameSuffix() {
        return classNameSuffix;
    }

    public boolean isChainStyle() {
        return chainStyle;
    }

    public boolean isFieldComment() {
        return fieldComment;
    }
}