package pub.avalonframework.sqlhelper.generator.options;

import pub.avalonframework.sqlhelper.generator.beans.HumpConverter;
import pub.avalonframework.sqlhelper.generator.beans.StringConverter;
import pub.avalonframework.sqlhelper.generator.beans.TypeConverter;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author baichao
 */
public final class GenerateOptions {

    private final static String PACKAGE_PATH_REGEX = "^([a-z,A-Z,$,_][a-z,A-Z,0-9,$,_,]*.?[a-z,A-Z,$,_][a-z,A-Z,0-9,$,_,]*|[a-z,A-Z,$,_][a-z,A-Z,0-9,$,_,]*)+";

    public final static GenerateOptions DEFAULT_GENERATE_OPTIONS = new GenerateOptions();

    /**
     * The table helper options.
     */
    private TableHelperOptions tableHelperOptions = TableHelperOptions.DEFAULT_TABLE_HELPER_OPTIONS;

    /**
     * The entity options.
     */
    private EntityOptions entityOptions = EntityOptions.DEFAULT_ENTITY_OPTIONS;

    /**
     * The type converter.
     */
    private TypeConverter typeConverter = new TypeConverter();

    /**
     * The string converter.
     */
    private StringConverter stringConverter = new HumpConverter();

    /**
     * The package path.
     */
    private String packagePath;

    /**
     * The class paths.
     */
    private Set<String> classPaths = new LinkedHashSet<>();

    public TableHelperOptions getTableHelperOptions() {
        return tableHelperOptions;
    }

    public GenerateOptions setTableHelperOptions(TableHelperOptions tableHelperOptions) {
        this.tableHelperOptions = tableHelperOptions;
        return this;
    }

    public EntityOptions getEntityOptions() {
        return entityOptions;
    }

    public GenerateOptions setEntityOptions(EntityOptions entityOptions) {
        this.entityOptions = entityOptions;
        return this;
    }

    public TypeConverter getTypeConverter() {
        return typeConverter;
    }

    public GenerateOptions setTypeConverter(TypeConverter typeConverter) {
        this.typeConverter = typeConverter;
        return this;
    }

    public StringConverter getStringConverter() {
        return stringConverter;
    }

    public GenerateOptions setStringConverter(StringConverter stringConverter) {
        this.stringConverter = stringConverter;
        return this;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public GenerateOptions setPackagePath(String packagePath) {
        if (packagePath == null) {
            return this;
        }
        if (!Pattern.matches(PACKAGE_PATH_REGEX, packagePath)) {
            throw new RuntimeException("packagePath format error.");
        }
        this.packagePath = packagePath;
        return this;
    }

    public GenerateOptions addClassPath(String classPath) {
        if (classPath == null) {
            return this;
        }
        this.classPaths.add(classPath);
        return this;
    }

    public GenerateOptions addAllClassPath(Collection<String> classPaths) {
        if (classPaths == null) {
            return this;
        }
        this.classPaths.addAll(classPaths);
        return this;
    }

    public Set<String> getClassPaths() {
        return classPaths;
    }
}