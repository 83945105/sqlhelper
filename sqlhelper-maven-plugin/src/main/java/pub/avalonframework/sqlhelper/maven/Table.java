package pub.avalonframework.sqlhelper.maven;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author baichao
 */
public class Table {

    @Parameter(required = true)
    private String tableName;

    @Parameter(required = true)
    private String tableAlias;

    @Parameter
    private GenerateOptions generateOptions;

    @Parameter
    private OutputOptions outputOptions;

    public String getTableName() {
        return tableName;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public GenerateOptions getGenerateOptions() {
        return generateOptions;
    }

    public OutputOptions getOutputOptions() {
        return outputOptions;
    }
}