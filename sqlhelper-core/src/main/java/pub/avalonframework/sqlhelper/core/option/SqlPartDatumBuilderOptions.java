package pub.avalonframework.sqlhelper.core.option;

import pub.avalonframework.sqlhelper.core.beans.ComparisonRule;

/**
 * @author baichao
 */
public final class SqlPartDatumBuilderOptions {

    public final static SqlPartDatumBuilderOptions SQL_PART_DATUM_BUILDER_OPTIONS = new SqlPartDatumBuilderOptions();

    private boolean selectAllColumnForMainTable = false;

    private boolean selectAllColumnForJoinTable = false;

    private ComparisonRule defaultWhereComparisonRule = ComparisonRule.NULL_SKIP;

    private ComparisonRule defaultOnComparisonRule = ComparisonRule.NULL_SKIP;

    private ComparisonRule defaultHavingComparisonRule = ComparisonRule.NULL_SKIP;

    public ComparisonRule getDefaultWhereComparisonRule() {
        return defaultWhereComparisonRule;
    }

    public SqlPartDatumBuilderOptions setDefaultWhereComparisonRule(ComparisonRule defaultWhereComparisonRule) {
        this.defaultWhereComparisonRule = defaultWhereComparisonRule;
        return this;
    }

    public ComparisonRule getDefaultOnComparisonRule() {
        return defaultOnComparisonRule;
    }

    public SqlPartDatumBuilderOptions setDefaultOnComparisonRule(ComparisonRule defaultOnComparisonRule) {
        this.defaultOnComparisonRule = defaultOnComparisonRule;
        return this;
    }

    public ComparisonRule getDefaultHavingComparisonRule() {
        return defaultHavingComparisonRule;
    }

    public void setDefaultHavingComparisonRule(ComparisonRule defaultHavingComparisonRule) {
        this.defaultHavingComparisonRule = defaultHavingComparisonRule;
    }

    public boolean isSelectAllColumnForMainTable() {
        return selectAllColumnForMainTable;
    }

    public SqlPartDatumBuilderOptions setSelectAllColumnForMainTable(boolean selectAllColumnForMainTable) {
        this.selectAllColumnForMainTable = selectAllColumnForMainTable;
        return this;
    }

    public boolean isSelectAllColumnForJoinTable() {
        return selectAllColumnForJoinTable;
    }

    public SqlPartDatumBuilderOptions setSelectAllColumnForJoinTable(boolean selectAllColumnForJoinTable) {
        this.selectAllColumnForJoinTable = selectAllColumnForJoinTable;
        return this;
    }
}