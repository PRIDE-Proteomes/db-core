package uk.ac.ebi.pride.proteomes.db.core.api.utils;

/**
 * @author ntoro
 * @since 20/05/15 11:05
 */
public enum Uniqueness {

    //NOTE: Don't reorder the values, it is used to calculate the type of uniqueness in the peptide

    NON_UNIQUE,
    UNIQUE_TO_PROTEIN,
    UNIQUE_TO_GENE;

}
