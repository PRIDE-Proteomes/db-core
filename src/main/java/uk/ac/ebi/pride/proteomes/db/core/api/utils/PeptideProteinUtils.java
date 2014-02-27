package uk.ac.ebi.pride.proteomes.db.core.api.utils;

import uk.ac.ebi.pride.proteomes.db.core.api.peptide.protein.PeptideProtein;

/**
 * User: ntoro
 * Date: 10/02/2014
 * Time: 15:23
 */
public class PeptideProteinUtils {

    /**
     * Count the number of n-terminal amino acids degraded
     * @param peptideProtein peptide protein matching
     * @return number of n-terminal amino acids degraded
     */
    public static int countNTermDegradation(PeptideProtein peptideProtein) {

        int counter = 0;

        String protSequence = peptideProtein.getProtein().getSequence();
        int pos = peptideProtein.getStartPosition()-2; //previous aa in the protein sequence when we star the index at 0
        while (pos >= 0 && pos < protSequence.length()-1 && protSequence.charAt(pos)!='K' && protSequence.charAt(pos)!='R'){
               counter++;
               pos--;
        }

        return counter;
    }

    /**
     * Count the number of c-terminal amino acids degraded
     * @param peptideProtein peptide protein matching
     * @return number of c-terminal amino acids degraded
     */
    public static int countCTermDegradation(PeptideProtein peptideProtein) {

        int counter = 0;

        String pepSequence = peptideProtein.getPeptide().getSequence();
        String protSequence = peptideProtein.getProtein().getSequence();
        int pos = peptideProtein.getStartPosition() + pepSequence.length() - 2;  //last aa in the peptide sequence when we star the index at 0
        while (pos >= 0 && pos < protSequence.length()-1 && protSequence.charAt(pos)!='K' && protSequence.charAt(pos)!='R'){
            counter++;
            pos++;
        }



        return counter;
    }
}
