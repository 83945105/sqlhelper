package pub.avalonframework.sqlhelper.generator.options;

/**
 * The entity options for generate.
 *
 * @author baichao
 */
public final class EntityOptions {

    public final static EntityOptions DEFAULT_ENTITY_OPTIONS = new EntityOptions();

    /**
     * The class name suffix.
     */
    private String classNameSuffix = "";

    /**
     * Is chain style.
     */
    private boolean chainStyle = true;

    /**
     * Is field comment.
     */
    private boolean fieldComment = true;

    public String getClassNameSuffix() {
        return classNameSuffix;
    }

    public EntityOptions setClassNameSuffix(String classNameSuffix) {
        this.classNameSuffix = classNameSuffix;
        return this;
    }

    public boolean isChainStyle() {
        return chainStyle;
    }

    public EntityOptions setChainStyle(boolean chainStyle) {
        this.chainStyle = chainStyle;
        return this;
    }

    public boolean isFieldComment() {
        return fieldComment;
    }

    public EntityOptions setFieldComment(boolean fieldComment) {
        this.fieldComment = fieldComment;
        return this;
    }
}