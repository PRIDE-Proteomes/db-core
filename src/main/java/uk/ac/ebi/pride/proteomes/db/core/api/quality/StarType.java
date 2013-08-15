package uk.ac.ebi.pride.proteomes.db.core.api.quality;

/**
 * Created with IntelliJ IDEA.
 * User: ntoro
 * Date: 15/08/2013
 * Time: 13:47
 * To change this template use File | Settings | File Templates.
 */
public enum StarType {
    NONE("none"),
    BRONZE("bronze"),
    SILVER("silver"),
    GOLD("gold");

    private final String value;

    private StarType(String value) {
        this.value = value;
    }

    public String getStarName() {
        return value;
    }

}
