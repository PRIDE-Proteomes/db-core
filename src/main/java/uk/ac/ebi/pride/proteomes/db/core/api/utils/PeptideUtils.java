package uk.ac.ebi.pride.proteomes.db.core.api.utils;

import uk.ac.ebi.pride.proteomes.db.core.api.modification.ModificationLocation;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.Peptide;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.Peptiform;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: ntoro
 * Date: 26/09/2013
 * Time: 10:18
 */
public class PeptideUtils {

    private static final String MISSED_CLEAVAGES_REGEXP = "[KR][^P]";

    public static String peptideRepresentationGenerator(Peptide peptide) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        stringBuilder.append(peptide.getSequence());
        stringBuilder.append("|");
        stringBuilder.append(peptide.getTaxid());

        if (peptide instanceof Peptiform) {
            stringBuilder.append("|");
            stringBuilder.append(serializeModifications(((Peptiform) peptide).getModificationLocations()));
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
                stringBuilder.append(modificationLocation.getModId());
                stringBuilder.append(")");

            }
        }

        return stringBuilder.toString();

    }

    public static int numMissedCleavageSites(String peptideSequence) {

        Pattern pattern = Pattern.compile(MISSED_CLEAVAGES_REGEXP);

        //We don't take into account the last AA because if it is a tryptic peptide without degradation in going to be always K or R
//        String peptideSequenceWithoutLastAA = peptideSequence.substring(0,peptideSequence.length()-1);


        Matcher peptideMatcher = pattern.matcher(peptideSequence);

        int missedCleavages = 0;

        //Start looking in the next amino acid
        boolean hasNext = peptideMatcher.find();
        while (hasNext) {
            //The index of start is staring on zero, we need to shift it to 1
            missedCleavages++;
            hasNext = peptideMatcher.find();
        }

        return missedCleavages;
    }


    public void printRepresentation(Iterable<Peptide> peptides) {

        for (Peptide peptide : peptides) {
            System.out.print("\nINSERT INTO PRIDEPROT.PEPTIDE (PEPTIDE_PK, SEQUENCE, REPRESENTATION, DESCRIPTION, SCORE_FK, SYMBOLIC, TAXID)");
            System.out.print("\n  VALUES (" + peptide.getPeptideId() + ", N'" + peptide.getSequence() + "', N'" +
                    PeptideUtils.peptideRepresentationGenerator(peptide) + "', N'', 1.0, N'" +
                    isSymbolic(peptide) + "', " + peptide.getTaxid() + ");\n");
        }
    }

    private String isSymbolic(Peptide peptide) {
        if (peptide instanceof Peptiform) {
            return "FALSE";
        }
        return "TRUE";
    }
}
