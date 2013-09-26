package uk.ac.ebi.pride.proteomes.db.core.api.utils;

import uk.ac.ebi.pride.proteomes.db.core.api.modification.ModificationLocation;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.Peptide;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.PeptideVariant;

import java.util.Set;

/**
 * User: ntoro
 * Date: 26/09/2013
 * Time: 10:18
 */
public class PeptideUtils {

    public static String peptideRepresentationGenerator(Peptide peptide) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        stringBuilder.append(peptide.getSequence());
        stringBuilder.append("|");
        stringBuilder.append(peptide.getTaxid());

        if (peptide instanceof PeptideVariant) {
            stringBuilder.append("|");
            stringBuilder.append(serializeModifications(((PeptideVariant) peptide).getModificationLocations()));
        }
        stringBuilder.append("]");

        return stringBuilder.toString();

    }

    private static String serializeModifications(Set<ModificationLocation> modificationLocations) {

        StringBuilder stringBuilder = new StringBuilder();

        if (modificationLocations != null) {

            for (ModificationLocation modificationLocation : modificationLocations) {
                stringBuilder.append("(");
                stringBuilder.append(modificationLocation.getPosition());
                stringBuilder.append(",");
                stringBuilder.append(modificationLocation.getModCvTerm());
                stringBuilder.append(")");

            }
        }

        return stringBuilder.toString();

    }

}
