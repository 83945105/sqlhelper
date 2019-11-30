package pub.avalonframework.sqlhelper.maven;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author baichao
 */
public class OutputOptions {

    public final static OutputOptions DEFAULT_OUTPUT_OPTIONS = new OutputOptions();

    @Parameter(required = true, defaultValue = "/")
    private String folderPath = "/";

    public String getFolderPath() {
        return folderPath;
    }
}