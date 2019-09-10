package pub.avalon.sqlhelper.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractGeneratorMojo extends AbstractMojo {

    @Parameter(required = true)
    protected List<DataSource> dataSources;
}