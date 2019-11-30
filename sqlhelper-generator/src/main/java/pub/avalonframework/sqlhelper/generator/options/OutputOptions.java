package pub.avalonframework.sqlhelper.generator.options;

import java.util.List;

/**
 * @author baichao
 */
public final class OutputOptions {

    private String folderPath;

    private List<String> compileOptions;

    public OutputOptions(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public List<String> getCompileOptions() {
        return compileOptions;
    }

    public OutputOptions setCompileOptions(List<String> compileOptions) {
        this.compileOptions = compileOptions;
        return this;
    }
}