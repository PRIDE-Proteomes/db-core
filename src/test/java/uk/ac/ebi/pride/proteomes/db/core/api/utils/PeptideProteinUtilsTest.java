package uk.ac.ebi.pride.proteomes.db.core.api.utils;

import org.junit.Test;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.Peptide;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.protein.PeptideProtein;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.Protein;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * User: ntoro
 * Date: 10/02/2014
 * Time: 15:24
 */
public class PeptideProteinUtilsTest {

    private static final String TRYPTIC = "MEMEQHGMVGSEISSSR";
    private static final String TRYPTIC_WITH_RP = "RPHTLNSTSTSK";

    private static final String PROTEIN_SEQUENCE =
            "LMQEVDRQRALQQRMEMEQHGMVGSEISSSRTSVSQIPFYSSDLPCDFMQPLGPLQQSPQ" +
            "HQQQMGQVLQQQNIQQGSINSPSTQTFMQTNERRQVGPPSFVPDSPSIPVGSPNFSSVKQ" +
            "GHGNLSGTSFQQSPVRPSFTPALPAAPPVANSSLPCGQDSTITHGHSYPGSTQSLIQLYS" +
            "DIIPEEKGKKKRTRKKKRDDDAESTKAPSTPHSDITAPPTPGISETTSTPAVSTPSELPQ" +
            "QADQESVEPVGPSTPNMAAGQLCTELENKLPNSDFSQATPNQQTYANSEVDKLSMETPAK" +
            "TEEIKLEKAETESCPGQEEPKLEEQNGSKVEGNAVACPVSSAQSPPHSAGAPAAKGDSGN" +
            "ELLKHLLKNKKSSSLLNQKPEGSICSEDDCTKDNKLVEKQNPAEGLQTLGAQMQGGFGCG" +
            "NQLPKTDGGSETKKQRSKRTQRTGEKAAPRSKKRKKDEEEKQAMYSSTDTFTHLKQQLSL" +
            "LPLMEPIIGVNFAHFLPYGSGQFNSGNRLLGTFGSATLEGVSDYYSQLIYKKMANGFATT" +
            "EELAGKAGVLVSHEVTKTLGPKPFQLPFRPQDDLLARALAQGPKTVDVPASLPTPPHNNQ" +
            "EELRIQDHCGDRDTPDSFVPSSSPESVVGVEVSRYPDLSLVKEEPPEPVPSPIIPILPST" +
            "AGKSSESRRNDIKTEPGTLYFASPFGPSPNGPRSGLISVAITLHPTAAENISSVVAAFSD" +
            "LLHVRIPNSYEVSSAPDVPSMGLVSSHRINPGLEYRQHLLLRGPPPGSANPPRLVSSYRL" +
            "KQPNVPFPPTSNGLSGYKDSSHGIAESAALRPQWCCHCKVVILGSGVRKSFKDLTLLNKD" +
            "SRESTKRVEKDIVFCSNNCFILYSSTAQAKNSENKESIPSLPQSPMRETPSKAFHQYSNN" +
            "ISTLDVHCLPQLPEKASPPASPPIAFPPAFEAAQVEAKPDELKVTVKLKPRLRAVHGGFE" +
            "DCRPLNKKWRGMKWKKWSIHIVIPKGTFKPPCEDEIDEFLKKLGTSLKPDPVPKDYRKCC" +
            "FCHEEGDGLTDGPARLLNLDLDLWVHLNCALWSTEVYETQAGALINVELALRRGLQMKCV" +
            "FCHKTGATSGCHRFRCTNIYHFTCAIKAQCMFFKDKTMLCPMHKPKGIHEQELSYFAVFR" +
            "RVYVQRDEVRQIASIVQRGERDHTFRVGSLIFHTIGQLLPQQMQAFHSPKALFPVGYEAS" +
            "RLYWSTRYANRRCRYLCSIEEKDGRPVFVIRIVEQGHEDLVLSDISPKGVWDKILEPVAC" +
            "VRKKSEMLQLFPAYLKGEDLFGLTVSAVARIAESLPGVEACENYTFRYGRNPLMELPLAV" +
            "NPTGCARSEPKMSAHVKRPHTLNSTSTSKSFQSTVTGELNAPYSKQFVHSKSSQYRKMKT" +
            "EWKSNVYLARSRIQGLGLYAARDIEKHTMVIEYIGTIIRNEVANRKEKLYESQNRGVYMF" +
            "RMDNDHVIDATLTGGPARYINHSCAPNCVAEVVTFERGHKIIISSSRRIQKGEELCYDYK" +
            "FDFEDDQHKIPCHCGAVNCRKWMN";


    private static final String ONE_NTERM_DEGRADATION = "MQEVDRQR";
    private static final String TWO_NTERM_DEGRADATION = "QEVDRQR";

    private static final String ONE_CTERM_DEGRADATION = "LMQEVDRQ";
    private static final String TWO_CTERM_DEGRADATION = "MEMEQHGMVGSEISS";
    private static final String TWO_CTERM_DEGRADATION_AT_THE_END = "IPCHCGAVNCRKW";



    @Test
    public void testNTermNoDegradation() throws Exception {

        final int position = 15;

        PeptideProtein peptideProtein = mock(PeptideProtein.class);
        Peptide peptide = mock(Peptide.class);
        Protein protein = mock(Protein.class);
        when(peptide.getSequence()).thenReturn(TRYPTIC);
        when(protein.getSequence()).thenReturn(PROTEIN_SEQUENCE);
        when(peptideProtein.getStartPosition()).thenReturn(position);
        when(peptideProtein.getPeptide()).thenReturn(peptide);
        when(peptideProtein.getProtein()).thenReturn(protein);

        assertEquals(0, PeptideProteinUtils.countNTermDegradation(peptideProtein));
    }

    @Test
    public void testNTermOneDegradation() throws Exception {

        final int position = 2;

        PeptideProtein peptideProtein = mock(PeptideProtein.class);
        Peptide peptide = mock(Peptide.class);
        Protein protein = mock(Protein.class);
        when(peptide.getSequence()).thenReturn(ONE_NTERM_DEGRADATION);
        when(protein.getSequence()).thenReturn(PROTEIN_SEQUENCE);
        when(peptideProtein.getStartPosition()).thenReturn(position);
        when(peptideProtein.getPeptide()).thenReturn(peptide);
        when(peptideProtein.getProtein()).thenReturn(protein);

        assertEquals(1, PeptideProteinUtils.countNTermDegradation(peptideProtein));
    }


    @Test
    public void testNTermTwoDegradation() throws Exception {

        final int position = 3;

        PeptideProtein peptideProtein = mock(PeptideProtein.class);
        Peptide peptide = mock(Peptide.class);
        Protein protein = mock(Protein.class);
        when(peptide.getSequence()).thenReturn(TWO_NTERM_DEGRADATION);
        when(protein.getSequence()).thenReturn(PROTEIN_SEQUENCE);
        when(peptideProtein.getStartPosition()).thenReturn(position);
        when(peptideProtein.getPeptide()).thenReturn(peptide);
        when(peptideProtein.getProtein()).thenReturn(protein);

        assertEquals(2, PeptideProteinUtils.countNTermDegradation(peptideProtein));
    }


    @Test
    public void testCTermNoDegradation() throws Exception {

        final int position = 15;

        PeptideProtein peptideProtein = mock(PeptideProtein.class);
        Peptide peptide = mock(Peptide.class);
        Protein protein = mock(Protein.class);
        when(peptide.getSequence()).thenReturn(TRYPTIC);
        when(protein.getSequence()).thenReturn(PROTEIN_SEQUENCE);
        when(peptideProtein.getStartPosition()).thenReturn(position);
        when(peptideProtein.getPeptide()).thenReturn(peptide);
        when(peptideProtein.getProtein()).thenReturn(protein);

        assertEquals(0, PeptideProteinUtils.countCTermDegradation(peptideProtein));
    }

    @Test
    public void testCTermRPNoDegradation() throws Exception {

        final int position = 1338;

        PeptideProtein peptideProtein = mock(PeptideProtein.class);
        Peptide peptide = mock(Peptide.class);
        Protein protein = mock(Protein.class);
        when(peptide.getSequence()).thenReturn(TRYPTIC_WITH_RP);
        when(protein.getSequence()).thenReturn(PROTEIN_SEQUENCE);
        when(peptideProtein.getStartPosition()).thenReturn(position);
        when(peptideProtein.getPeptide()).thenReturn(peptide);
        when(peptideProtein.getProtein()).thenReturn(protein);

        assertEquals(0, PeptideProteinUtils.countCTermDegradation(peptideProtein));
    }

    @Test
    public void testCTermOneDegradation() throws Exception {

        final int position = 1;

        PeptideProtein peptideProtein = mock(PeptideProtein.class);
        Peptide peptide = mock(Peptide.class);
        Protein protein = mock(Protein.class);
        when(peptide.getSequence()).thenReturn(ONE_CTERM_DEGRADATION);
        when(protein.getSequence()).thenReturn(PROTEIN_SEQUENCE);
        when(peptideProtein.getStartPosition()).thenReturn(position);
        when(peptideProtein.getPeptide()).thenReturn(peptide);
        when(peptideProtein.getProtein()).thenReturn(protein);

        assertEquals(1, PeptideProteinUtils.countCTermDegradation(peptideProtein));
    }


    @Test
    public void testCTermTwoDegradation() throws Exception {

        final int position = 15;

        PeptideProtein peptideProtein = mock(PeptideProtein.class);
        Peptide peptide = mock(Peptide.class);
        Protein protein = mock(Protein.class);
        when(peptide.getSequence()).thenReturn(TWO_CTERM_DEGRADATION);
        when(protein.getSequence()).thenReturn(PROTEIN_SEQUENCE);
        when(peptideProtein.getStartPosition()).thenReturn(position);
        when(peptideProtein.getPeptide()).thenReturn(peptide);
        when(peptideProtein.getProtein()).thenReturn(protein);

        assertEquals(2, PeptideProteinUtils.countCTermDegradation(peptideProtein));
    }

    @Test
    public void testCTermDegradationAtTheEnd() throws Exception {

        final int position = 1510;

        PeptideProtein peptideProtein = mock(PeptideProtein.class);
        Peptide peptide = mock(Peptide.class);
        Protein protein = mock(Protein.class);
        when(peptide.getSequence()).thenReturn(TWO_CTERM_DEGRADATION_AT_THE_END);
        when(protein.getSequence()).thenReturn(PROTEIN_SEQUENCE);
        when(peptideProtein.getStartPosition()).thenReturn(position);
        when(peptideProtein.getPeptide()).thenReturn(peptide);
        when(peptideProtein.getProtein()).thenReturn(protein);

        assertEquals(2, PeptideProteinUtils.countCTermDegradation(peptideProtein));
    }

//    @Test
//    public void testNTermDegradationWithRP() throws Exception {
//
//        final int position = 3;
//
//        PeptideProtein peptideProtein = mock(PeptideProtein.class);
//        Peptide peptide = mock(Peptide.class);
//        Protein protein = mock(Protein.class);
//        when(peptide.getSequence()).thenReturn(TWO_NTERM_DEGRADATION);
//        when(protein.getSequence()).thenReturn(PROTEIN_SEQUENCE);
//        when(peptideProtein.getStartPosition()).thenReturn(position);
//        when(peptideProtein.getPeptide()).thenReturn(peptide);
//        when(peptideProtein.getProtein()).thenReturn(protein);
//
//        assertEquals(2, PeptideProteinUtils.countNTermDegradation(peptideProtein));
//    }
//
//    @Test
//    public void testCTermDegradationWithRP() throws Exception {
//
//        final int position = 3;
//
//        PeptideProtein peptideProtein = mock(PeptideProtein.class);
//        Peptide peptide = mock(Peptide.class);
//        Protein protein = mock(Protein.class);
//        when(peptide.getSequence()).thenReturn(TWO_NTERM_DEGRADATION);
//        when(protein.getSequence()).thenReturn(PROTEIN_SEQUENCE);
//        when(peptideProtein.getStartPosition()).thenReturn(position);
//        when(peptideProtein.getPeptide()).thenReturn(peptide);
//        when(peptideProtein.getProtein()).thenReturn(protein);
//
//        assertEquals(3, PeptideProteinUtils.countCTermDegradation(peptideProtein));
//    }
}
