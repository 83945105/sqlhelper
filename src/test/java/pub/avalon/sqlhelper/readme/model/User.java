package pub.avalon.sqlhelper.readme.model;

/**
 * Created by 白超 on 2019/5/9.
 */
public class User {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public static class Model<T> implements Model2<Model<T>> {



        public T id() {
            return null;
        }

        public T name() {
            return null;
        }
    }

    public <K extends Model<K>> Model<K> column() {
        return new Model<>();
    }

    public <K extends WhereDataBuilder2<Model<K>>> Model<K> where() {
        return null;
    }

    public static void main(String[] args) {
        new User().column().id().id().id().id().id().id().id().id().id().id().id().id().id();
        new User().where().id().equalTo("").id().equalTo("").name().equalTo("");
    }

}
