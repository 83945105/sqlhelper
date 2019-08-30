package pub.avalon.sqlhelper.maven;

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
}