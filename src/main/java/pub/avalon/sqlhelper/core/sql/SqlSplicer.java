package pub.avalon.sqlhelper.core.sql;

/**
 * sql拼接器
 *
 * @author 白超
 * @date 2018/8/22
 */
public class SqlSplicer {

    private StringBuilder sql;

    public SqlSplicer() {
        this.sql = new StringBuilder();
    }

    public SqlSplicer(int capacity) {
        this.sql = new StringBuilder(capacity);
    }

    public SqlSplicer clear() {
        this.sql.replace(0, this.sql.length(), "");
        return this;
    }

    public SqlSplicer append(String sqlPart) {
        this.sql.append(sqlPart);
        return this;
    }

    public SqlSplicer insert(int offset, String str) {
        this.sql.insert(offset, str);
        return this;
    }

    public int indexOf(String str) {
        return this.sql.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return this.sql.indexOf(str, fromIndex);
    }

    public SqlSplicer delete(int start, int end) {
        this.sql.delete(start, end);
        return this;
    }

    public String getSql() {
        return this.sql.toString();
    }

    public int length() {
        return this.sql.length();
    }
}
