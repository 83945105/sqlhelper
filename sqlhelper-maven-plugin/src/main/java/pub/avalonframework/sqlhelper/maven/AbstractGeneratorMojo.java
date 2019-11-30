package pub.avalonframework.sqlhelper.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;
import pub.avalonframework.sqlhelper.generator.beans.StringConverter;
import pub.avalonframework.sqlhelper.generator.beans.TypeConverter;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractGeneratorMojo extends AbstractMojo {

    @Parameter(required = true)
    protected List<DataSource> dataSources;

    protected pub.avalonframework.sqlhelper.generator.options.GenerateOptions convertGenerateOptions(GenerateOptions generateOptions) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (generateOptions == null) {
            return null;
        }

        TableHelperOptions tableHelperOptions = generateOptions.getTableHelperOptions();
        pub.avalonframework.sqlhelper.generator.options.TableHelperOptions tho = new pub.avalonframework.sqlhelper.generator.options.TableHelperOptions()
                .setClassNameSuffix(tableHelperOptions.getClassNameSuffix())
                .setConstantComment(tableHelperOptions.isConstantComment());

        EntityOptions entityOptions = generateOptions.getEntityOptions();
        pub.avalonframework.sqlhelper.generator.options.EntityOptions eo = new pub.avalonframework.sqlhelper.generator.options.EntityOptions()
                .setClassNameSuffix(entityOptions.getClassNameSuffix())
                .setChainStyle(entityOptions.isChainStyle())
                .setFieldComment(entityOptions.isFieldComment());

        pub.avalonframework.sqlhelper.generator.options.GenerateOptions go = new pub.avalonframework.sqlhelper.generator.options.GenerateOptions();
        go.setTableHelperOptions(tho)
                .setEntityOptions(eo)
                .setTypeConverter((TypeConverter) Class.forName(generateOptions.getTypeConverter()).newInstance())
                .setStringConverter((StringConverter) Class.forName(generateOptions.getStringConverter()).newInstance())
                .setPackagePath(generateOptions.getPackagePath())
                .addAllClassPath(generateOptions.getClassPaths());
        return go;
    }

    protected pub.avalonframework.sqlhelper.generator.options.OutputOptions convertOutputOptions(OutputOptions outputOptions) {
        if (outputOptions == null) {
            return null;
        }
        return new pub.avalonframework.sqlhelper.generator.options.OutputOptions(outputOptions.getFolderPath());
    }
}