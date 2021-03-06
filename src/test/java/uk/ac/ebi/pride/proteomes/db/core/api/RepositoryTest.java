package uk.ac.ebi.pride.proteomes.db.core.api;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.ac.ebi.pride.proteomes.db.core.api.assay.AssayProteomesRepository;
import uk.ac.ebi.pride.proteomes.db.core.api.cluster.ClusterProteomesRepository;
import uk.ac.ebi.pride.proteomes.db.core.api.feature.FeatureProteomesRepository;
import uk.ac.ebi.pride.proteomes.db.core.api.modification.ModificationProteomesRepository;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CvParamProteomesRepository;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.PeptideRepository;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.group.PeptideGroupRepository;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.protein.PeptideProteinRepository;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.ProteinRepository;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.ProteinGroupRepository;
import uk.ac.ebi.pride.proteomes.db.core.api.quality.ScoreRepository;

import javax.persistence.EntityManagerFactory;

/**
 * User: ntoro
 * Date: 24/09/2013
 * Time: 10:40
 */
@ContextConfiguration(locations = {"/test-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class RepositoryTest {

    protected static final String PROJECT_ACCESSION = "PRD000269";
    protected static final String ASSAY_ACCESSION = "13565";

    protected static final int NUM_ASSAY_PROJECT = 22;

    protected static final Integer TAXID_HUMAN = 9606;
    protected static final Integer TAXID_MOUSE = 10090;

    protected static final String NO_DESCRIPTION = null;

    protected static final String CV_NAME = "aorta thoracica";
    protected static final String CV_TERM = "BTO:0000157";

    protected static final String TISSUE_TERM = "BTO:0000157";
    protected static final String TISSUE_NAME = "aorta thoracica";
    protected static final String CELL_TYPE_TERM = "CL:0000182";
    protected static final String CELL_TYPE_NAME = "hepatocyte";
    protected static final String DISEASE_TERM = "DOID:0050127";
    protected static final String DISEASE_NAME = "sinusitis";

    protected static final String MOD_TERM_RO = "MOD:00105";
    protected static final String MOD_NAME_RO = "L-serine amide";

    protected static final String MOD_TERM = "MOD:00902";
    protected static final String MOD_NAME = "modified L-arginine residue";

    protected static final String MOD_ID = "MOD:01214";

    protected static final Integer MODS_BIO_SIGNIFICANT = 1;
    protected static final Integer MODS_NO_BIO_SIGNIFICANT = 0;
    protected static final Double MIN_DELTA = 100.5;
    protected static final Double MAX_DELTA = 120.5;
    protected static final Integer MODS_BETWEEN_DELTA = 1;
    protected static final Double MONO_DELTA = 103.050752;
    protected static final boolean BIO_SIGN = Boolean.TRUE;

    protected static final String SEQUENCE = "HCGATSAGLR";

    protected static final Integer PEPS_WITH_SEQUENCE = 2;
    protected static final Integer PEPS_VAR_WITH_SEQUENCE = 1;
    protected static final Integer PEPS_SYM_WITH_SEQUENCE = 1;
    protected static final Integer DISTINCT_SEQUENCES_9606 = 31;
    protected static final Integer NUM_SYMBOLIC = 32;
    protected static final Integer NUM_VARIANTS = 44;


    protected static final String NEW_MOD_TERM = "MOD:00696";
    protected static final Integer NEW_MOD_POS = 7;
    protected static final Integer SEC_MOD_POS = 9;
    protected static final String NEW_MOD_NAME = "phosphorylated residue";

    protected static final long SCORE_ID = 1;

    protected static final String GENE_GROUP_ID = "XGENE";
    protected static final int PROTS_IN_GENE = 2;
    protected static final int NUM_GENES = 1;
    protected static final int NUM_PROT_GROUPS_FOR_PROTEIN = 1;

    protected static final String PROTEIN_ACCESSION = "X12345";
    protected static final String ISOFORM_ACCESSION = "X12345-1";
    protected static final String NEW_PROTEIN_ACCESSION = "X12345-2";

    protected static final String NEW_PROTEIN_SEQUENCE =
            "DIDDNSNGTYEKPTTVDPYSQQPQTPRPSTQTDLFVTPVTNQRPDELKVTVKLKYSQQPQTPRPSTQTDL";

    protected static final int SYMBOLIC_PEP_IN_PROTEIN = 3;

    protected static final Double SCORE_VALUE = 2.0;
    protected static final Integer STAR_COUNT = 2;
    protected static final Integer NUM_SCORES_GREATER_THAN = 5;
    protected static final Integer NUM_SCORES_LESS_THAN = 3;
    protected static final Integer SCORE_WITH_TWO_STARS = 3;
    protected static final Integer TWO_STARS = 2;
    protected static final Integer SCORES_WITH_GOLD = 3;
    protected static final Integer SCORES_WITH_SILVER_AND_VALUE_GREATER_THAT = 1;
    protected static final Integer SCORES_WITH_SILVER_AND_VALUE_LESS_THAT = 1;
    protected static final Integer NUM_SCORES_GREATER_THAN_TWO_STARS_GOLD = 1;
    protected static final Integer NUM_SCORES_LESS_THAN_TWO_STARS_GOLD = 1;
    protected static final Integer NUM_SCORES_BETWEEN_TWO_STARS_GOLD = 2;
    protected static final Double MIN = 1.0;
    protected static final Double MAX = 3.0;

    protected static final int NUM_PROTS = 2;

    protected static final long DEFAULT_SCORE_ID = 1;
    protected static final int DEFAULT_QMETHOD_ID = 1;
    protected static final int DEAFULT_STAR_ID = 1;
    protected static final String DEFAULT_QMETHOD_DESC = "Default value to initialize the database";
    protected static final String DEFAULT_QMETHOD_NAME = "default";
    protected static final int DEAFULT_STAR_COUNT = 0;
    protected static final double DEFAULT_SCORE_VALUE = 0.0;

    protected static final long PEPTIDE_TWO = 2;
    protected static final long PEPTIDE_SEVEN = 7;
    protected static final long PEPTIDE_TEN = 10;

    protected static final long FEATURE_ID = 1000;
    protected static final long FEATURE_ID_SAVE = 500000;
    protected static final String FEATURE_TYPE_CV_TERM = "FT:000002";
    protected static final String FEATURE_TYPE_CV_NAME = "TOPO_DOM";
    protected static final int FT_START_POS = 5;
    protected static final int FT_END_POS = 10;


    @Autowired
    protected EntityManagerFactory entityManagerFactory;

    @Autowired
    protected AssayProteomesRepository assayProteomesRepository;

    @Autowired
    protected ClusterProteomesRepository clusterProteomesRepository;

    @Autowired
    protected FeatureProteomesRepository featureProteomesRepository;

    @Autowired
    protected PeptideRepository peptideRepository;

    @Autowired
    protected CvParamProteomesRepository cvParamProteomesRepository;

    @Autowired
    protected ModificationProteomesRepository modificationProteomesRepository;

    @Autowired
    protected ProteinGroupRepository proteinGroupRepository;

    @Autowired
    protected ProteinRepository proteinRepository;

    @Autowired
    protected PeptideProteinRepository peptideProteinRepository;

    @Autowired
    protected PeptideGroupRepository peptideGroupRepository;

    @Autowired
    protected ScoreRepository scoreRepository;
}
