package pub.avalon.sqlhelper.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import javax.tools.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mojo(name = "generator", defaultPhase = LifecyclePhase.COMPILE)
public class GeneratorMojo extends AbstractMojo {

    @Parameter(required = true)
    private DataSource dataSource;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {


    }

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\git_store\\sqlhelper\\sqlhelper-core\\src\\test\\java\\pub\\avalon\\sqlhelper\\readme\\entity\\SysUserDTO.java");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        StringBuilder code = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            code.append(line).append("\n");
        }
        reader.close();
        System.out.println(code.toString());

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null,
                null);

        JavaFileObject javaFileObject = new StringJavaObject("SysUserDTO", code.toString());

        List<String> optionsList = new ArrayList<String>();
        optionsList.addAll(Arrays.asList(new String[]{"-d", "D://test"}));
        List<JavaFileObject> javaFileObjects = Arrays.asList(new JavaFileObject[]{javaFileObject});
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardFileManager, null,
                optionsList, null, javaFileObjects);
        if (task.call()) {
            // 编译成功
        }

    }

    public static class StringJavaObject extends SimpleJavaFileObject {
        /**
         * 源代码
         */
        private String content;

        /**
         * 遵循Java规范的类名及文件
         */
        public StringJavaObject(String javaFileName, String content) {
            super(_createStringJavaObjectUri(javaFileName), Kind.SOURCE);
            this.content = content;
        }

        /**
         * 产生一个URL资源路径
         */
        private static URI _createStringJavaObjectUri(String javaFileName) {
            //注意此处未设置包名
            return URI.create("String:///" + javaFileName + Kind.SOURCE.extension);
        }

        /**
         * 文本文件代码
         */
        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors)
                throws IOException {
            return content;
        }
    }
}
