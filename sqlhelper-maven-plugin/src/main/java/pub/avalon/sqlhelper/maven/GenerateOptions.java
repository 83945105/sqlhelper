package pub.avalon.sqlhelper.maven;

import org.apache.maven.plugins.annotations.Parameter;

import java.util.Set;

/**
 * @author baichao
 */
public class GenerateOptions {

    @Parameter(defaultValue = "DTO")
    private String entitySuffix;

    @Parameter(defaultValue = "true")
    private boolean entityChainStyle;

    @Parameter(defaultValue = "true")
    private boolean entityFieldComment;

    @Parameter(defaultValue = "true")
    private boolean helperConstantComment;

    @Parameter(defaultValue = "Helper")
    private String helperClassName = "Helper";

    @Parameter(defaultValue = "pub.avalon.sqlhelper.generator.beans.TypeConverter")
    private String typeConverter;

    @Parameter(defaultValue = "pub.avalon.sqlhelper.generator.beans.HumpConverter")
    private String stringConverter;

    @Parameter
    private String packagePath;

    @Parameter
    private Set<String> classPaths;
}