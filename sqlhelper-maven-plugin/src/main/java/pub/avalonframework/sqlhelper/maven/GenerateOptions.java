package pub.avalonframework.sqlhelper.maven;

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

    @Parameter
    private TableHelperOptions tableHelperOptions = TableHelperOptions.DEFAULT_TABLE_HELPER_OPTIONS;

    @Parameter
    private EntityOptions entityOptions = EntityOptions.DEFAULT_ENTITY_OPTIONS;

    @Parameter(defaultValue = "pub.avalonframework.sqlhelper.generator.beans.TypeConverter")
    private String typeConverter = "pub.avalonframework.sqlhelper.generator.beans.TypeConverter";

    @Parameter(defaultValue = "pub.avalonframework.sqlhelper.generator.beans.HumpConverter")
    private String stringConverter = "pub.avalonframework.sqlhelper.generator.beans.HumpConverter";

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

    public TableHelperOptions getTableHelperOptions() {
        return tableHelperOptions;
    }

    public EntityOptions getEntityOptions() {
        return entityOptions;
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