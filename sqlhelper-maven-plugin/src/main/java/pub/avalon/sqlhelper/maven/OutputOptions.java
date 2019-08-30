package pub.avalon.sqlhelper.maven;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author baichao
 */
public class OutputOptions {

    @Parameter(required = true, defaultValue = "/")
    private String folderPath;
}