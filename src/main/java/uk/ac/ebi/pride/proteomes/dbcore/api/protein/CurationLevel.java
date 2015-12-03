package uk.ac.ebi.pride.proteomes.db.core.api.protein;

/**
 * User: ntoro
 * Date: 06/11/2013
 * Time: 14:48
 */
public enum CurationLevel {
    PREDICTED("PREDICTED"),
    CURATED("CURATED");

    private final String level;

    CurationLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}
