package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

/**
 * User: ntoro
 * Date: 15/08/2013
 * Time: 13:47
 */
public enum ProteinGroupType {
    ENTRY("ENTRY"),
    GENE("GENE");

    private final String groupName;

    private ProteinGroupType(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }
}
