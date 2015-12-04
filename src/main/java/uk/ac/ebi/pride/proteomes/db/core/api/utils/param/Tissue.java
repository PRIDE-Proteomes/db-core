package uk.ac.ebi.pride.proteomes.db.core.api.utils.param;

/**
 * @author Florian Reisinger
 * @since 0.1
 */
//@JsonSerialize(using = TissueSerializer.class)
public enum Tissue {

    // ToDo: find better solution for tissue annotation!
    // For example use ontology accessions. Default to BTO.
    // Provide a string to 'known' type mapping function

    //select UPPER(REPLACE(REPLACE(CV_NAME, ' ', '_'), '-','_')) || '("' || CV_TERM ||'", "' || CV_NAME || '"),' AS TISSUES from CV_PARAM WHERE CV_TYPE='TISSUE' ORDER BY TISSUES;

    ALVEOLAR_BONE("BTO:0001383", "alveolar bone"),
    ANTERIOR_SILK_GLAND("BTO:0001795", "anterior silk gland"),
    AORTA_THORACICA("BTO:0000157", "aorta thoracica"),
    ASCITES("BTO:0000091", "ascites"),
    A_549_CELL("BTO:0000018", "A-549 cell"),
    BA_F3_CELL("BTO:0001516", "BA/F3 cell"),
    BILE("BTO:0000121", "bile"),
    BLOOD("BTO:0000089", "blood"),
    BLOOD_PLASMA("BTO:0000131", "blood plasma"),
    BLOOD_PLATELET("BTO:0000132", "blood platelet"),
    BLOOD_SERUM("BTO:0000133", "blood serum"),
    BONE_MARROW("BTO:0000141", "bone marrow"),
    BONE_MARROW_CANCER_CELL("BTO:0000583", "bone marrow cancer cell"),
    BRAIN("BTO:0000142", "brain"),
    BREAST("BTO:0000149", "breast"),
    BREAST_CANCER_CELL_LINE("BTO:0000356", "breast cancer cell line"),
    BRONCHOALVEOLAR_LAVAGE("BTO:0000155", "bronchoalveolar lavage"),
    BURKITT_LYMPHOMA_CELL("BTO:0000164", "Burkitt lymphoma cell"),
    B_LYMPHOMA_CELL_LINE("BTO:0001518", "B-lymphoma cell line"),
    C1R_CELL("BTO:0003699", "C1R cell"),
    CALLUS("BTO:0001010", "callus"),
    CALU_3_CELL("BTO:0002750", "Calu-3 cell"),
    CARDIAC_MUSCLE("BTO:0000199", "cardiac muscle"),
    CARPEL("BTO:000072", "carpel"),
    FOBROSARCOMA_CELL("BTO:0000459", "fibrosarcoma cell"),
    CCD_18CO_CELL("BTO:0004059", "CCD-18Co cell"),
    CELL_CULTURE("BTO:0000214", "cell culture"),
    CELL_SUSPENSION_CULTURE("BTO:0000221", "cell suspension culture"),
    CEREBRAL_CORTEX("BTO:0000233", "cerebral cortex"),
    CEREBROSPINAL_FLUID("BTO:0000237", "cerebrospinal fluid"),
    CEREBROVASCULAR_ENDOTHELIAL_CELL("BTO:0000238", "cerebrovascular endothelial cell"),
    CHOLANGIOMA_CELL("BTO:0002842", "cholangioma cell"),
    COELOMIC_FLUID("BTO:0001708", "coelomic fluid"),
    COLON("BTO:0000269", "colon"),
    COLORECTAL_CANCER_CELL_LINE("BTO:0001616", "colorectal cancer cell line"),
    CONIDIOPHORE("BTO:0000281", "conidiophore"),
    CONIDIUM("BTO:0000283", "conidium"),
    CONJUNCTIVA("BTO:0003415", "conjunctiva"),
    CORNEA("BTO:0000286", "cornea"),
    COTYLEDON("BTO:0000300", "cotyledon"),
    CULTURE_SUPERNATANT("BTO:0002217", "culture supernatant"),
    DENTAL_PLAQUE("BTO:0000338", "dental plaque"),
    EGG("BTO:0000369", "Egg"),
    ELEMENTARY_BODY("BTO:0000377", "elementary body"),
    EMBRYO("BTO:0000379", "embryo"),
    EMBRYONIC_CELL_LINE("BTO:0000669", "embryonic cell line"),
    ENDOTHELIAL_CELL("BTO:0001176", "endothelial cell"),
    ERYTHROCYTE("BTO:0000424", "erythrocyte"),
    ES_E14_CELL("BTO:0005136", "ES-E14 cell"),
    EXCRETION("BTO:0000491", "excretion"),
    EYELID("BTO:0002241", "eyelid"),
    FLOWER("BTO:0000469", "flower"),
    FLOWER_BUD("BTO:0000470", "flower bud"),
    GASTROCNEMIUS("BTO:0000506", "gastrocnemius"),
    GILL("BTO:0000518", "gill"),
    GROWTH_PHASE_CULTURE("BTO:0001900", "growth phase culture"),
    HAUSTORIUM("BTO:0000515", "haustorium"),
    HEART("BTO:0000562", "heart"),
    HEART_VENTRICLE("BTO:0000862", "heart ventricle"),
    HEK_293T_CELL("BTO:0002181", "HEK-293T cell"),
    HEK_293_CELL("BTO:0000007", "HEK-293 cell"),
    HELA_CELL("BTO:0000567", "HeLa cell"),
    HEPG2_CELL("BTO:0000599", "HepG2 cell"),
    HEP_3B_CELL("BTO:0000972", "HEP-3B cell"),
    HIPPOCAMPUS("BTO:0000601", "hippocampus"),
    HL_1_CELL("BTO:0003264", "HL-1 cell"),
    HL_60_CELL("BTO:0000738", "HL-60 cell"),
    HUVEC_CELL("BTO:0001949", "HUVEC cell"),
    HYPHA("BTO:0000612", "hypha"),
    HYPOCOTYL("BTO:0000613", "hypocotyl"),
    JURKAT_CELL("BTO:0000661", "JURKAT cell"),
    JURKAT_E_6_1_CELL("BTO:0001948", "JURKAT E-6.1 cell"),
    JUVENILE_LEAF("BTO:0003147", "juvenile leaf"),
    KIDNEY("BTO:0000671", "kidney"),
    K_562_CELL("BTO:0000664", "K-562 cell"),
    LEAF("BTO:0000713", "leaf"),
    LIVER("BTO:0000759", "liver"),
    LOWER_EPIDERMIS("BTO:0000488", "lower epidermis"),
    LUNG("BTO:0000763", "lung"),
    LUNG_CANCER_CELL_LINE("BTO:0000762", "lung cancer cell line"),
    LYMPH_NODE("BTO:0000784", "lymph node"),
    MACROPHAGE_CELL_LINE("BTO:0002278", "macrophage cell line"),
    MA_MEL_11_CELL("BTO:0005339", "Ma-Mel-11 cell"),
    MCF_10A_CELL("BTO:0001939", "MCF-10A cell"),
    MCF_7_CELL("BTO:0000093", "MCF-7 cell"),
    MELANOMA_CELL_LINE("BTO:0000849", "melanoma cell line"),
    MESOPHYLL("BTO:0000858", "mesophyll"),
    MONONUCLEAR_CELL("BTO:0000878", "mononuclear cell"),
    MYELOCYTIC_LEUKEMIA_CELL_LINE("BTO:0000740", "myelocytic leukemia cell line"),
    NERVE_CORD("BTO:0001656", "nerve cord"),
    NEUROBLASTOMA_CELL_LINE("BTO:0000932", "neuroblastoma cell line"),
    ORAL_MUCOSA("BTO:0002860", "oral mucosa"),
    ORBIT("BTO:0004687", "orbit"),
    OSTEOSARCOMA_CELL_LINE("BTO:0000407", "osteosarcoma cell line"),
    OVARY_EPITHELIUM_CELL_LINE("BTO:0004129", "ovary epithelium cell line"),
    PERIPHERAL_BLOOD("BTO:0000553", "peripheral blood"),
    PERITROPHIC_MEMBRANE("BTO:0001038", "peritrophic membrane"),
    PEYERS_GLAND("BTO:0001784", "Peyers gland"),
    PLACENTA("BTO:0001078", "placenta"),
    POLLEN("BTO:0001097", "pollen"),
    PRIMARY_CELL("BTO:0001413", "primary cell"),
    PRIMARY_ROOT("BTO:0001182", "primary root"),
    PROSTATE_CANCER_CELL("BTO:0001130", "prostate cancer cell"),
    RAMOS_CELL("BTO:0003076", "RAMOS cell"),
    RENAL_CELL_CARCINOMA_CELL_LINE("BTO:0000383", "renal cell carcinoma cell line"),
    ROOT("BTO:0001188", "root"),
    ROOT_TIP("BTO:0001191", "root tip"),
    ROSETTE("BTO:0001201", "rosette"),
    SALIVA("BTO:0001202", "saliva"),
    SALIVARY_GLAND("BTO:0001203", "salivary gland"),
    SCHIZONT("BTO:0001002", "schizont"),
    SECRETION("BTO:0002979", "secretion"),
    SEED("BTO:0001226", "seed"),
    SEEDLING("BTO:0001228", "seedling"),
    SEED_COAT("BTO:0001227", "seed coat"),
    SEMEN("BTO:0001230", "semen"),
    SEMINAL_PLASMA("BTO:0001232", "seminal plasma"),
    SERUM("BTO:0001239", "serum"),
    SHOOT("BTO:0001243", "shoot"),
    SILIQUE("BTO:00005592", "silique"),
    SILK_GLAND("BTO:0001250", "silk gland"),
    SKELETAL_MUSCLE("BTO:0001103", "skeletal muscle"),
    SKIN("BTO:0001253", "skin"),
    SMALL_INTESTINE_EPITHELIUM("BTO:0001258", "small intestine epithelium"),
    SPERMATOZOON("BTO:0001277", "spermatozoon"),
    SPLEEN("BTO:0001281", "spleen"),
    STATIONARY_PHASE_CULTURE("BTO:0001899", "stationary phase culture"),
    STOMACH("BTO:0001307", "stomach"),
    SW_480_CELL("BTO:0000038", "SW-480 cell"),
    SW_620_CELL("BTO:0000675", "SW-620 cell"),
    TESTIS("BTO:0001363", "testis"),
    TH2_CELL("BTO:0001679", "Th2-cell"),
    THORAX("BTO:0001368", "thorax"),
    THP_1_CELL("BTO:0001370", "THP-1 cell"),
    TISSUE_CULTURE("BTO:0001384", "tissue culture"),
    TOOTH("BTO:0000397", "tooth"),
    URINE("BTO:0001419", "urine"),
    U_87MG_CELL("BTO:0002036", "U-87MG cell"),
    VASCULAR_CAMBIUM("BTO:0004502", "vascular cambium"),
    VASCULAR_SMOOTH_MUSCLE_CELL("BTO:0002488", "vascular smooth muscle cell"),
    VENOM("BTO:0001439", "venom"),
    VENOM_GLAND("BTO:0001440", "venom gland"),
    WEHI_231_CELL("BTO:0001093", "WEHI-231 cell"),
    WHOLE_BODY("BTO:0001489", "whole body"),
    XYLEM("BTO:0001468", "xylem"),
    YEAST("BTO:0002307", "yeast"),
    NONE("none", "none"),
    UNKNOWN("unknown", "unknown");

    public static final String defaultValue = "any";
    private final String cvTerm;
    private final String cvName;

    Tissue(String cvTerm, String cvName) {
        this.cvTerm = cvTerm;
        this.cvName = cvName;
    }

    public static Tissue getTissue(String tissueIdentifier) {

        if (tissueIdentifier == null || tissueIdentifier.equalsIgnoreCase(defaultValue)) {
            return NONE;
        }

        for (Tissue tissue : Tissue.values()) {
            if (tissueIdentifier.equalsIgnoreCase(tissue.getCvTerm())
                    || tissueIdentifier.equalsIgnoreCase(tissue.getCvName())
                    || tissueIdentifier.equalsIgnoreCase(tissue.name())) {
                return tissue;
            }
        }

        return UNKNOWN;

    }

    public String getCvTerm() {
        return cvTerm;
    }

    public String getCvName() {
        return cvName;
    }

    @Override
    public String toString() {
        return "Tissue{" +
                "cvTerm='" + cvTerm + '\'' +
                ", cvName='" + cvName + '\'' +
                '}';
    }
}
