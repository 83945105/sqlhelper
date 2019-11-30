package pub.avalonframework.sqlhelper.generator.engine;

import pub.avalonframework.sqlhelper.generator.jdbc.JdbcTemplate;
import pub.avalonframework.sqlhelper.generator.options.GenerateOptions;

/**
 * @author baichao
 */
public final class TemplateEngineBuilder {

    private JdbcTemplate jdbcTemplate;

    private GenerateOptions generateOptions = GenerateOptions.DEFAULT_GENERATE_OPTIONS;

    private TemplateEngineBuilder() {
    }

    public static TemplateEngineBuilder newTemplateEngineBuilder() {
        return new TemplateEngineBuilder();
    }

    public TableHelperTemplateEngine buildTableHelperTemplateEngine() {
        if (jdbcTemplate == null) {
            throw new RuntimeException("JdbcTemplate can not be null.");
        }
        return new TableHelperTemplateEngine(jdbcTemplate, generateOptions);
    }

    public EntityTemplateEngine buildEntityTemplateEngine() {
        if (jdbcTemplate == null) {
            throw new RuntimeException("JdbcTemplate can not be null.");
        }
        return new EntityTemplateEngine(jdbcTemplate, generateOptions);
    }

    public TemplateEngineBuilder setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        return this;
    }

    public TemplateEngineBuilder setGenerateOptions(GenerateOptions generateOptions) {
        this.generateOptions = generateOptions;
        return this;
    }
}