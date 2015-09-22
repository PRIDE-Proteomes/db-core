package uk.ac.ebi.pride.proteomes.db.core.api.utils.param;

/**
 * @author Florian Reisinger
 * @since 0.1
 */
//@JsonSerialize(using = ModificationSerializer.class)
public enum Modification {

    // ToDo: complete and perhaps extend with attributes like (description, full name, type, cv accession, etc...)

    ACETYLATION("PRDMOD:1", "Acetylation", 42.010565, true),
    AMIDATION("PRDMOD:2", "Amidation", -0.984016, true),
    BIOTINYLATION("PRDMOD:3", "Biotinylation", 226.077599, true),
    PHOSPHORYLATION("PRDMOD:4", "Phosphorylation", 79.966331, true),
    CARBAMIDOMETHYL("PRDMOD:5", "Carbamidomethyl", 57.021464, false),
    CARBAMYLATION("PRDMOD:6", "Carbamylation", 43.005814, false),
    CARBOXYMETHYL("PRDMOD:7", "Carboxymethyl", 58.005479, false),
    DEAMIDATION("PRDMOD:8", "Deamidation", 0.984016, true),
    HOMOSERINE("PRDMOD:9", "Homoserine", -29.992806, false),
    HOMOSERINE_LACTONE("PRDMOD:10", "Homoserine lactone", -48.003371, false),
    NIPCAM_C("PRDMOD:11", "NIPCAM(C)", 99.068414, false),
    DEHYDRATATION("PRDMOD:12", "Dehydratation", -18.010565, true),
    PROPIONAMIDE_C("PRDMOD:13", "Propionamide(C)", 71.037114, false),
    PYRO_CARBAMIDOMETHYL("PRDMOD:14", "Pyro-carbamidomethyl", 39.994915, false),
    OXIDATION("PRDMOD:15", "Oxidation", 15.994915, true),
    DEAMINATION("PRDMOD:16", "Deamination", -17.026549, true),
    SMA("PRDMOD:17", "SMA", 127.063329, false),
    CATION_NA("PRDMOD:18", "Cation:Na", 21.981944, false),
    PYRIDYLETHYL("PRDMOD:19", "Pyridylethyl", 105.057849, false),
    MONOMETHYLATION("PRDMOD:20", "Monomethylation", 14.01565, true),
    METHYLTHIO("PRDMOD:21", "Methylthio", 45.987721, true),
    SULFO("PRDMOD:22", "Sulfo", 79.956815, true),
    LIPOYL("PRDMOD:23", "Lipoyl", 188.032957, true),
    FARNESYLATION("PRDMOD:24", "Farnesylation", 204.187801, true),
    MYRISTOYLATION("PRDMOD:25", "Myristoylation", 210.198365, true),
    PYRIDOXAL_PHOSPHATE("PRDMOD:26", "Pyridoxal phosphate", 229.014009, true),
    PALMITOYLATION("PRDMOD:27", "Palmitoylation", 238.229666, true),
    GERANYL_GERANYL("PRDMOD:28", "Geranyl-geranyl", 272.250401, true),
    PHOSPHOPANTETHEINE("PRDMOD:29", "Phosphopantetheine", 340.085794, true),
    FLAVIN_ADENINE_DINUCLEOTIDE("PRDMOD:30", "Flavin adenine dinucleotide", 783.141485, true),
    GUANIDINATION("PRDMOD:31", "Guanidination", 42.021798, false),
    FORMYLATION("PRDMOD:32", "Formylation", 27.994915, true),
    ICAT_D_2H_8("PRDMOD:33", "ICAT-D:2H(8)", 450.275205, false),
    ICAT_D("PRDMOD:34", "ICAT-D", 442.224991, false),
    ICAT_C("PRDMOD:35", "ICAT-C", 227.126991, false),
    ICAT_C_13C_9("PRDMOD:36", "ICAT-C:13C(9)", 236.157185, false),
    SILAC_LABEL_13_C_6_("PRDMOD:37", "13C(6) Silac label", 6.020129, false),
    LABEL_18O_2("PRDMOD:38", "Label:18O(2)", 4.008493, false),
    CARBOXYLATION("PRDMOD:39", "Carboxylation", 43.989829, true),
    DIOXIDATION("PRDMOD:40", "Dioxidation", 31.989829, true),
    ITRAQ4PLEX("PRDMOD:41", "iTRAQ4plex", 144.102062, false),
    ITRAQ8PLEX("PRDMOD:42", "iTRAQ8plex", 304.205359, false),
    TMT6PLEX("PRDMOD:43", "TMT6plex", 229.162932, false),
    NONE("none", "none", 0, false), // mapped to null String or defaultValue (used to specify that no modification is selected)
    UNKNOWN("unknown", "unknown", 0, false); // anything that can not be mapped to the 'known' modifications above

    public static final String defaultValue = "any";
    private final String modId;
    private final String modName;
    private final double monoDelta;
    private final boolean biologicalSignificant;

    Modification(String modId, String modName, double monoDelta, boolean biologicalSignificant) {
        this.modId = modId;
        this.modName = modName;
        this.monoDelta = monoDelta;
        this.biologicalSignificant = biologicalSignificant;
    }

    public static Modification getModification(String modIdentifier) {

        if (modIdentifier == null || modIdentifier.equalsIgnoreCase(defaultValue)) {
            return NONE;
        }

        for (Modification mod : Modification.values()) {
            if (modIdentifier.equalsIgnoreCase(mod.getModId()) || modIdentifier.equalsIgnoreCase(mod.getModName())) {
                return mod;
            }
        }

        return UNKNOWN;

    }

    public String getModId() {
        return modId;
    }

    public String getModName() {
        return modName;
    }

    public double getMonoDelta() {
        return monoDelta;
    }

    public boolean isBiologicalSignificant() {
        return biologicalSignificant;
    }

    @Override
    public String toString() {
        return "Modification{" +
                "modId='" + modId + '\'' +
                ", modName='" + modName + '\'' +
                ", monoDelta=" + monoDelta +
                ", biologicalSignificant=" + biologicalSignificant +
                '}';
    }
}
