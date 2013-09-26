package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

/**
 * Created with IntelliJ IDEA.
 * User: ntoro
 * Date: 15/08/2013
 * Time: 13:47
 * To change this template use File | Settings | File Templates.
 */
public enum ProteinGroupType {
    ISOFORM("ISOFORM");

    private final String value;

    private ProteinGroupType(String value) {
        this.value = value;
    }

    public String getGroupName() {
        return value;
    }

}
