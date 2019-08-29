package pub.avalon.sqlhelper.generator.options;

import java.io.File;
import java.util.regex.Pattern;

/**
 * @author baichao
 */
public final class OutputOptions {

    private final static String PACKAGE_PATH_REGEX = "^([a-z,A-Z,$,_][a-z,A-Z,0-9,$,_,]*.?[a-z,A-Z,$,_][a-z,A-Z,0-9,$,_,]*|[a-z,A-Z,$,_][a-z,A-Z,0-9,$,_,]*)+";

    private final static String PROJECT_ROOT_PATH_SIGN = "/";

    private final static String PACKAGE_LINK_REGEX = "\\.";

    private String folderPath;

    public OutputOptions(String folderPath) {
        this.folderPath = folderPath;
    }

    public OutputOptions(String projectPath, String packagePath) {
        if (projectPath == null || projectPath.trim().length() == 0) {
            throw new RuntimeException("projectPath can not be null or empty.");
        }
        if (!Pattern.matches(PACKAGE_PATH_REGEX, packagePath)) {
            throw new RuntimeException("packagePath format error.");
        }
        if (PROJECT_ROOT_PATH_SIGN.equals(projectPath.trim())) {
            projectPath = System.getProperty("user.dir");
        }
        StringBuilder path = new StringBuilder(projectPath)
                .append(File.separator)
                .append("src")
                .append(File.separator)
                .append("main")
                .append(File.separator)
                .append("java");
        for (String s : packagePath.split(PACKAGE_LINK_REGEX)) {
            path.append(File.separator).append(s);
        }
        this.folderPath = path.append(File.separator).toString();
    }

    public String getFolderPath() {
        return folderPath;
    }
}