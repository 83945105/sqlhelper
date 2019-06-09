package pub.avalon.sqlhelper.generator.beans;

/**
 * @author 白超
 * @date 2019/6/5
 */
public class MySqlColumn implements Column {

    private String name;

    private String type;

    private String comment;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
