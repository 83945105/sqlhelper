package pub.avalon.sqlhelper.core.data;

import java.util.Objects;

/**
 * 分组数据
 *
 * @author 白超
 * @date 2019/5/6
 */
public class GroupDatum {

    private String ownerTableName;

    private String ownerTableAlias;

    private String ownerColumnName;

    private String ownerColumnAlias;

    public GroupDatum(String ownerTableName, String ownerTableAlias, String ownerColumnName, String ownerColumnAlias) {
        this.ownerTableName = ownerTableName;
        this.ownerTableAlias = ownerTableAlias;
        this.ownerColumnName = ownerColumnName;
        this.ownerColumnAlias = ownerColumnAlias;
    }

    public String getOwnerTableName() {
        return ownerTableName;
    }

    public void setOwnerTableName(String ownerTableName) {
        this.ownerTableName = ownerTableName;
    }

    public String getOwnerTableAlias() {
        return ownerTableAlias;
    }

    public void setOwnerTableAlias(String ownerTableAlias) {
        this.ownerTableAlias = ownerTableAlias;
    }

    public String getOwnerColumnName() {
        return ownerColumnName;
    }

    public void setOwnerColumnName(String ownerColumnName) {
        this.ownerColumnName = ownerColumnName;
    }

    public String getOwnerColumnAlias() {
        return ownerColumnAlias;
    }

    public void setOwnerColumnAlias(String ownerColumnAlias) {
        this.ownerColumnAlias = ownerColumnAlias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupDatum that = (GroupDatum) o;
        return Objects.equals(getOwnerTableName(), that.getOwnerTableName()) &&
                Objects.equals(getOwnerTableAlias(), that.getOwnerTableAlias()) &&
                Objects.equals(getOwnerColumnName(), that.getOwnerColumnName()) &&
                Objects.equals(getOwnerColumnAlias(), that.getOwnerColumnAlias());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwnerTableName(), getOwnerTableAlias(), getOwnerColumnName(), getOwnerColumnAlias());
    }
}
