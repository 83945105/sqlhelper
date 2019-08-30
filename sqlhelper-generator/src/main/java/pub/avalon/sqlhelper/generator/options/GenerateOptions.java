package pub.avalon.sqlhelper.generator.options;

import pub.avalon.sqlhelper.generator.beans.HumpConverter;
import pub.avalon.sqlhelper.generator.beans.StringConverter;
import pub.avalon.sqlhelper.generator.beans.TypeConverter;

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
     * 生成的实体Bean后缀
     */
    private String entitySuffix = "DTO";

    /**
     * 生成的实体Bean是否使用链式风格
     */
    private boolean entityChainStyle = true;

    /**
     * 是否生成实体属性注释
     */
    private boolean entityFieldComment = true;

    /**
     * 助手类常量注释
     */
    private boolean helperConstantComment = true;

    /**
     * helper类名
     */
    private String helperClassName = "Helper";

    /**
     * 类型转换器
     */
    private TypeConverter typeConverter = new TypeConverter();

    /**
     * 字符串转换器
     */
    private StringConverter stringConverter = new HumpConverter();

    /**
     * 包路径
     */
    private String packagePath;

    /**
     * 类路径
     */
    private Set<String> classPaths = new LinkedHashSet<>();

    public String getEntitySuffix() {
        return entitySuffix;
    }

    public GenerateOptions setEntitySuffix(String entitySuffix) {
        this.entitySuffix = entitySuffix;
        return this;
    }

    public boolean isEntityChainStyle() {
        return entityChainStyle;
    }

    public GenerateOptions setEntityChainStyle(boolean entityChainStyle) {
        this.entityChainStyle = entityChainStyle;
        return this;
    }

    public boolean isEntityFieldComment() {
        return entityFieldComment;
    }

    public GenerateOptions setEntityFieldComment(boolean entityFieldComment) {
        this.entityFieldComment = entityFieldComment;
        return this;
    }

    public String getHelperClassName() {
        return helperClassName;
    }

    public GenerateOptions setHelperClassName(String helperClassName) {
        this.helperClassName = helperClassName;
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
        if (!Pattern.matches(PACKAGE_PATH_REGEX, packagePath)) {
            throw new RuntimeException("packagePath format error.");
        }
        this.packagePath = packagePath;
        return this;
    }

    public GenerateOptions addClassPath(String classPath) {
        this.classPaths.add(classPath);
        return this;
    }

    public Set<String> getClassPaths() {
        return classPaths;
    }
}