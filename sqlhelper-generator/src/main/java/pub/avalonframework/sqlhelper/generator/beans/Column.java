package pub.avalonframework.sqlhelper.generator.beans;

/**
 * The column.
 *
 * @author baichao
 */
public interface Column {

    /**
     * Get name.
     *
     * @return The name.
     */
    String getName();

    /**
     * Get type.
     *
     * @return The type.
     */
    String getType();

    /**
     * Get comment.
     *
     * @return The comment.
     */
    String getComment();
}