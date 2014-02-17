package uk.ac.ebi.pride.proteomes.db.core.api.utils;

import org.junit.Test;
import uk.ac.ebi.pride.proteomes.db.core.api.RepositoryTest;
import uk.ac.ebi.pride.proteomes.db.core.api.modification.ModificationLocation;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.Peptiform;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.SymbolicPeptide;

import java.util.TreeSet;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * User: ntoro
 * Date: 26/09/2013
 * Time: 10:45
 */
public class PeptideUtilsTest extends RepositoryTest {

    protected static final String SEQUENCE = "HCGATSAGLR";
    protected static final Integer TAXID = 9606;
    protected static final String A_MOD_TERM = "MOD:00216";
    protected static final Integer A_MOD_POS = 2;
    protected static final String B_MOD_TERM = "MOD:01782";
    protected static final Integer B_MOD_POS = 19;
    protected static final String C_MOD_TERM = "MOD:00046";
    protected static final Integer C_MOD_POS = 20;
    protected static final String D_MOD_TERM = "MOD:00047";
    protected static final Integer D_MOD_POS = 20;
    private static String PEP_X = "[HCGATSAGLR|9606|(2,MOD:00216)(19,MOD:01782)(20,MOD:00046)(20,MOD:00047)]";
    private static String PEP_Y = "[HCGATSAGLR|9606]";
    private static String PEP_Z = "[HCGATSAGLR|9606|]";


    private static final String TRYPTIC = "MEMEQHGMVGSEISSSR";
    private static final String TRYPTIC_WITH_RP = "RPHTLNSTSTSK";
    private static final String NO_TRYPTIC_ONE = "LMQEVDRQR";
    private static final String NO_TRYPTIC_TWO = "LMQEVDRQRALQQR";
    private static final String NO_TRYPTIC_THREE = "LMQEVDRQRALQQRMEMEQHGMVGSEISSSR";
    private static final String NO_TRYPTIC_FOUR = "LMQEVDRQRALQQRMEMEQHGMVGSEISSSRTSVS" +
            "QIPFYSSDLPCDFMQPLGPLQQSPQHQQQMGQVLQ" +
            "QQNIQQGSINSPSTQTFMQTNER";


    @Test
    public void testPeptideVariantRepresentationGenerator() throws Exception {

        //Peptides
        Peptiform peptideX = new Peptiform();

        peptideX.setTaxid(TAXID);
        peptideX.setSequence(SEQUENCE);

        //PeptideModifications

        TreeSet<ModificationLocation> modificationLocations = new TreeSet<ModificationLocation>();

        ModificationLocation pepModC = new ModificationLocation();
        pepModC.setModId(C_MOD_TERM);
        pepModC.setPosition(C_MOD_POS);
        modificationLocations.add(pepModC);

        ModificationLocation pepModB = new ModificationLocation();
        pepModB.setModId(B_MOD_TERM);
        pepModB.setPosition(B_MOD_POS);
        modificationLocations.add(pepModB);

        ModificationLocation pepModD = new ModificationLocation();
        pepModD.setModId(D_MOD_TERM);
        pepModD.setPosition(D_MOD_POS);
        modificationLocations.add(pepModD);

        ModificationLocation pepModA = new ModificationLocation();
        pepModA.setModId(A_MOD_TERM);
        pepModA.setPosition(A_MOD_POS);
        modificationLocations.add(pepModA);

        peptideX.setModificationLocations(modificationLocations);

        String peptideRepresentation = PeptideUtils.peptideRepresentationGenerator(peptideX);

        assertThat(peptideRepresentation, is(PEP_X));

    }

    @Test
    public void testSymbolicPeptideRepresentationGenerator() throws Exception {

        //Peptides
        SymbolicPeptide peptideY = new SymbolicPeptide();

        peptideY.setTaxid(TAXID);
        peptideY.setSequence(SEQUENCE);

        String peptideRepresentation = PeptideUtils.peptideRepresentationGenerator(peptideY);

        assertThat(peptideRepresentation, is(PEP_Y));

    }

    @Test
    public void testPeptideVariantNoModsRepresentationGenerator() throws Exception {

        //Peptides
        Peptiform peptideZ = new Peptiform();

        peptideZ.setTaxid(TAXID);
        peptideZ.setSequence(SEQUENCE);

        String peptideRepresentation = PeptideUtils.peptideRepresentationGenerator(peptideZ);

        assertThat(peptideRepresentation, is(PEP_Z));

    }

    @Test
    public void testTrypticNumMissedCleavageSites() throws Exception {
        assertEquals(0, PeptideUtils.numMissedCleavageSites(TRYPTIC));
    }

    @Test
    public void testTrypticWithRPNumMissedCleavageSites() throws Exception {
        assertEquals(0, PeptideUtils.numMissedCleavageSites(TRYPTIC_WITH_RP));
    }

    @Test
    public void testNoTrypticOneNumMissedCleavageSites() throws Exception {
        assertEquals(1, PeptideUtils.numMissedCleavageSites(NO_TRYPTIC_ONE));
    }

    @Test
    public void testNoTrypticTwoNumMissedCleavageSites() throws Exception {
        assertEquals(2, PeptideUtils.numMissedCleavageSites(NO_TRYPTIC_TWO));
    }

    @Test
    public void testNoTrypticThreeNumMissedCleavageSites() throws Exception {
        assertEquals(3, PeptideUtils.numMissedCleavageSites(NO_TRYPTIC_THREE));
    }

    @Test
    public void testNoTrypticFourNumMissedCleavageSites() throws Exception {
        assertEquals(4, PeptideUtils.numMissedCleavageSites(NO_TRYPTIC_FOUR));
    }
}
