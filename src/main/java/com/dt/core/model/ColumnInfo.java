package com.dt.core.model;

/**
 * 表字段信息
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class ColumnInfo {

    private String name;

    private String alias;

    private String catalog;

    private String label;

    private boolean currency;

    private boolean readOnly;

    private boolean autoIncrement;

    private int typeInt;

    private String typeString;

    private String collation;

    private String nullString;

    private String keyString;

    private String defaultString;

    private String extra;

    private String comment;

    public ColumnInfo() {
    }

    public ColumnInfo(String name, String alias, String catalog, String label, boolean currency, boolean readOnly, boolean autoIncrement, int typeInt, String typeString, String collation, String nullString, String keyString, String defaultString, String extra, String comment) {
        this.name = name;
        this.alias = alias;
        this.catalog = catalog;
        this.label = label;
        this.currency = currency;
        this.readOnly = readOnly;
        this.autoIncrement = autoIncrement;
        this.typeInt = typeInt;
        this.typeString = typeString;
        this.collation = collation;
        this.nullString = nullString;
        this.keyString = keyString;
        this.defaultString = defaultString;
        this.extra = extra;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isCurrency() {
        return currency;
    }

    public void setCurrency(boolean currency) {
        this.currency = currency;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public int getTypeInt() {
        return typeInt;
    }

    public void setTypeInt(int typeInt) {
        this.typeInt = typeInt;
    }

    public String getTypeString() {
        return typeString;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

    public String getCollation() {
        return collation;
    }

    public void setCollation(String collation) {
        this.collation = collation;
    }

    public String getNullString() {
        return nullString;
    }

    public void setNullString(String nullString) {
        this.nullString = nullString;
    }

    public String getKeyString() {
        return keyString;
    }

    public void setKeyString(String keyString) {
        this.keyString = keyString;
    }

    public String getDefaultString() {
        return defaultString;
    }

    public void setDefaultString(String defaultString) {
        this.defaultString = defaultString;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
