package pub.avalon.sqlhelper.generator.engine;

import pub.avalon.sqlhelper.generator.options.GenerateOptions;
import pub.avalon.sqlhelper.generator.options.OutputOptions;

import java.util.Map;

/**
 * @author baichao
 */
public interface TemplateEngine {

    TemplateEngine addTable(String tableName, String tableAlias);

    TemplateEngine addTable(String tableName, String tableAlias, GenerateOptions generateOptions);

    TemplateEngine setTables(Map<String, String> tableNameAliasMap);

    String generateJavaCode(String tableName, OutputOptions outputOptions);

    void generateJavaFile(String tableName, OutputOptions outputOptions);

    void generateJavaFiles(OutputOptions outputOptions);

    void generateClassFile(String tableName, OutputOptions outputOptions);

    void generateClassFiles(OutputOptions outputOptions);
}