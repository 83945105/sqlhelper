package pub.avalon.sqlhelper.generator.options;

import pub.avalon.sqlhelper.generator.beans.HumpConverter;
import pub.avalon.sqlhelper.generator.beans.StringConverter;
import pub.avalon.sqlhelper.generator.beans.TypeConverter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author baichao
 */
public final class GenerateOptions {

    public final static GenerateOptions DEFAULT_GENERATE_OPTIONS = new GenerateOptions();

    /**
     * 是否生成实体Bean
     */
    private boolean entity = true;

    /**
     * 生成的实体Bean后缀
     */
    private String entitySuffix = "DTO";

    /**
     * 生成的实体Bean是否使用链式风格
     */
    private boolean entityChain = true;

    /**
     * 是否生成单独的实体Bean
     * true - 每张表将生成2个类,一个是实体Bean,一个是sqlhelper类
     */
    private boolean entityAlone = false;

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
     * 类路径
     */
    private Set<String> classPaths = new LinkedHashSet<>();

    public boolean isEntity() {
        return entity;
    }

    public GenerateOptions setEntity(boolean entity) {
        this.entity = entity;
        return this;
    }

    public String getEntitySuffix() {
        return entitySuffix;
    }

    public GenerateOptions setEntitySuffix(String entitySuffix) {
        this.entitySuffix = entitySuffix;
        return this;
    }

    public boolean isEntityChain() {
        return entityChain;
    }

    public GenerateOptions setEntityChain(boolean entityChain) {
        this.entityChain = entityChain;
        return this;
    }

    public boolean isEntityAlone() {
        return entityAlone;
    }

    public GenerateOptions setEntityAlone(boolean entityAlone) {
        this.entityAlone = entityAlone;
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

    public GenerateOptions addClassPath(String classPath) {
        this.classPaths.add(classPath);
        return this;
    }

    public Set<String> getClassPaths() {
        return classPaths;
    }
}