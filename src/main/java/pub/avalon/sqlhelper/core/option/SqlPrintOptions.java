package pub.avalon.sqlhelper.core.option;

/**
 * sql打印配置
 *
 * @author 白超
 * @date 2019/5/20
 */
public final class SqlPrintOptions {

    /**
     * 是否开启
     */
    private boolean enabled = false;

    /**
     * 是否打印颜色
     */
    private boolean colour = false;

    /**
     * 是否开启打印sql
     */
    private boolean sqlEnabled = true;

    /**
     * 是否开启打印参数
     */
    private boolean argsEnabled = true;

    /**
     * 标题颜色
     */
    private Colour titleColour = Colour.CYAN;

    /**
     * sql颜色
     */
    private Colour sqlColour = Colour.YELLOW;

    /**
     * 参数颜色
     */
    private Colour argsColour = Colour.RED;

    public boolean isEnabled() {
        return enabled;
    }

    public SqlPrintOptions setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public boolean isColour() {
        return colour;
    }

    public SqlPrintOptions setColour(boolean colour) {
        this.colour = colour;
        return this;
    }

    public boolean isSqlEnabled() {
        return sqlEnabled;
    }

    public SqlPrintOptions setSqlEnabled(boolean sqlEnabled) {
        this.sqlEnabled = sqlEnabled;
        return this;
    }

    public boolean isArgsEnabled() {
        return argsEnabled;
    }

    public SqlPrintOptions setArgsEnabled(boolean argsEnabled) {
        this.argsEnabled = argsEnabled;
        return this;
    }

    public Colour getTitleColour() {
        return titleColour;
    }

    public SqlPrintOptions setTitleColour(Colour titleColour) {
        this.titleColour = titleColour;
        return this;
    }

    public Colour getSqlColour() {
        return sqlColour;
    }

    public SqlPrintOptions setSqlColour(Colour sqlColour) {
        this.sqlColour = sqlColour;
        return this;
    }

    public Colour getArgsColour() {
        return argsColour;
    }

    public SqlPrintOptions setArgsColour(Colour argsColour) {
        this.argsColour = argsColour;
        return this;
    }

    public enum Colour {

        /**
         * 黑色
         */
        BLACK(0, "BLACK"),
        /**
         * 红色
         */
        RED(1, "RED"),
        /**
         * 绿色
         */
        GREEN(2, "GREEN"),
        /**
         * 黄色
         */
        YELLOW(3, "YELLOW"),
        /**
         * 蓝色
         */
        BLUE(4, "BLUE"),
        /**
         * 深红色
         */
        MAGENTA(5, "MAGENTA"),
        /**
         * 青色
         */
        CYAN(6, "CYAN"),
        /**
         * 白色
         */
        WHITE(7, "WHITE"),
        /**
         * 默认
         */
        DEFAULT(9, "DEFAULT");

        private final int value;
        private final String name;

        Colour(int index, String name) {
            this.value = index;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public int value() {
            return value;
        }

    }

}
