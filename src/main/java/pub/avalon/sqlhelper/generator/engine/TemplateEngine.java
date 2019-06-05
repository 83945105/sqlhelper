package pub.avalon.sqlhelper.generator.engine;

import pub.avalon.sqlhelper.generator.beans.TypeConverter;

/**
 * 模板引擎
 *
 * @author 白超
 * @date 2019/6/5
 */
public class TemplateEngine {


    private TypeConverter typeConverter = new TypeConverter();

    public TemplateEngine addTable(String tableName, String tableAlias) {
        return this;
    }

    public TemplateEngine addTable(String tableName, String tableAlias, TypeConverter typeConverter) {
        return this;
    }


}
