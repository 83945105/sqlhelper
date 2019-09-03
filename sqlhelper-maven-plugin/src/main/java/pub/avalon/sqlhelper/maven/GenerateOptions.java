package pub.avalon.sqlhelper.maven;

import org.apache.maven.plugins.annotations.Parameter;

import java.util.Set;

/**
 * @author baichao
 */
public class GenerateOptions {

    public final static GenerateOptions DEFAULT_GENERATE_OPTIONS = new GenerateOptions();

    @Parameter(defaultValue = "DTO")
    private String entitySuffix = "DTO";

    @Parameter(defaultValue = "true")
    private boolean entityChainStyle = true;

    @Parameter(defaultValue = "true")
    private boolean entityFieldComment = true;

    @Parameter(defaultValue = "true")
    private boolean helperConstantComment = true;

    @Parameter(defaultValue = "Helper")
    private String helperClassName = "Helper";

    @Parameter(defaultValue = "pub.avalon.sqlhelper.generator.beans.TypeConverter")
    private String typeConverter = "pub.avalon.sqlhelper.generator.beans.TypeConverter";

    @Parameter(defaultValue = "pub.avalon.sqlhelper.generator.beans.HumpConverter")
    private String stringConverter = "pub.avalon.sqlhelper.generator.beans.HumpConverter";

    @Parameter
    private String packagePath;

    @Parameter
    private Set<String> classPaths;

    public String getEntitySuffix() {
        return entitySuffix;
    }

    public boolean isEntityChainStyle() {
        return entityChainStyle;
    }

    public boolean isEntityFieldComment() {
        return entityFieldComment;
    }

    public boolean isHelperConstantComment() {
        return helperConstantComment;
    }

    public String getHelperClassName() {
        return helperClassName;
    }

    public String getTypeConverter() {
        return typeConverter;
    }

    public String getStringConverter() {
        return stringConverter;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public Set<String> getClassPaths() {
        return classPaths;
    }
}