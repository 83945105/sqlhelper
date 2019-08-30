package pub.avalon.sqlhelper.generator.options;

/**
 * @author baichao
 */
public final class OutputOptions {

    private String folderPath;

    public OutputOptions(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getFolderPath() {
        return folderPath;
    }
}