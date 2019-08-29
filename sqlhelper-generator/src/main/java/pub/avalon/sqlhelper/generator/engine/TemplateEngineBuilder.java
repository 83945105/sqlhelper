package pub.avalon.sqlhelper.generator.engine;

import pub.avalon.sqlhelper.generator.jdbc.JdbcTemplate;
import pub.avalon.sqlhelper.generator.options.GenerateOptions;

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

    public TemplateEngine build() {
        if (jdbcTemplate == null) {
            throw new RuntimeException("JdbcTemplate can not be null.");
        }
        return new DefaultTemplateEngine(jdbcTemplate, generateOptions);
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