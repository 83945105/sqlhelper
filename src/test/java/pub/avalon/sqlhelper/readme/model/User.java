package pub.avalon.sqlhelper.readme.model;

import pub.avalon.sqlhelper.core.builder.ModelDataBuilder;

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

        private T model;
        private ModelDataBuilder modelDataBuilder;

        public Model(Class<T> model) {
            if(model == this.getClass()) {
                this.model = (T) this;
            }else if(model == WhereDataBuilder2.class) {
                this.modelDataBuilder = new WhereDataBuilder2(this);
                this.model = (T) this.modelDataBuilder;
            }
        }

        public T id() {
            return this.model;
        }

        public T name() {
            return this.model;
        }
    }

    public <K extends Model<K>> Model<K> column() {
        return new Model(Model.class);
    }

    public <K extends WhereDataBuilder2<Model<K>>> Model<K> where() {
        return new Model(WhereDataBuilder2.class);
    }

}
