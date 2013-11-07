package uk.ac.ebi.pride.proteomes.db.core.api.utils;

import uk.ac.ebi.pride.proteomes.db.core.api.modification.ModificationLocation;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.Peptide;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.PeptideVariant;

import java.util.Collection;

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

    private static String serializeModifications(Collection<ModificationLocation> modificationLocations) {

        StringBuilder stringBuilder = new StringBuilder();

        //We assume that in this point the modifications were inserted or retrieved in a sorted collection
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

    public void printRepresentation(Iterable<Peptide> peptides) {

        for (Peptide peptide : peptides) {
            System.out.print("\nINSERT INTO PRIDEPROT.PEPTIDE (PEPTIDE_PK, SEQUENCE, REPRESENTATION, DESCRIPTION, SCORE_FK, SYMBOLIC, TAXID)");
            System.out.print("\n  VALUES (" + peptide.getPeptideId() + ", N'" + peptide.getSequence() + "', N'" +
                    PeptideUtils.peptideRepresentationGenerator(peptide) + "', N'', 1.0, N'" +
                    isSymbolic(peptide) + "', 9606.0);\n");
        }
    }

    private String isSymbolic(Peptide peptide) {
        if (peptide instanceof PeptideVariant) {
            return "FALSE";
        }
        return "TRUE";
    }
}
