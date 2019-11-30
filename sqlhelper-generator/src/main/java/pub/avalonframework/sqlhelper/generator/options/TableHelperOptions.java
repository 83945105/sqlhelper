package pub.avalonframework.sqlhelper.generator.options;

/**
 * The table helper options for generate.
 *
 * @author baichao
 */
public final class TableHelperOptions {

    public final static TableHelperOptions DEFAULT_TABLE_HELPER_OPTIONS = new TableHelperOptions();

    /**
     * Is constant comment.
     */
    private boolean constantComment = true;

    /**
     * The class name suffix.
     */
    private String classNameSuffix = "Helper";

    public boolean isConstantComment() {
        return constantComment;
    }

    public TableHelperOptions setConstantComment(boolean constantComment) {
        this.constantComment = constantComment;
        return this;
    }

    public String getClassNameSuffix() {
        return classNameSuffix;
    }

    public TableHelperOptions setClassNameSuffix(String classNameSuffix) {
        this.classNameSuffix = classNameSuffix;
        return this;
    }
}