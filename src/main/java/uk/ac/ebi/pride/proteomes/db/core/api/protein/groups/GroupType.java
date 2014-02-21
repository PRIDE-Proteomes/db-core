package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

/**
 * User: ntoro
 * Date: 15/08/2013
 * Time: 13:47
 */
public enum GroupType {
    ENTRY("ENTRY"),
    GENE("GENE");

    private final String groupName;

    private GroupType(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }
}
