--  WARNING: This script generates the same tables that the original one for HSQLDB.
--  It needs to be added for compatibility with the test
CREATE SCHEMA PRIDEPROT AUTHORIZATION DBA;

-- CREATE SEQUENCE PRIDEPROT.PEPTIDE_PEPTIDE_ID_SEQ;
-- IT SHOULD START WITH 1 WHEN WE INSERT THE TEST DATA PROPERLY

CREATE SEQUENCE PRIDEPROT.CLUSTER_PSM_PSM_ID_SEQ START WITH 10000;

CREATE SEQUENCE PRIDEPROT.FEATURE_FEATURE_ID_SEQ START WITH 10000;

CREATE SEQUENCE PRIDEPROT.PEPTIDE_PEPTIDE_ID_SEQ START WITH 10000;

CREATE SEQUENCE PRIDEPROT.Q_METHOD_QUALITY_METHOD_ID_SEQ START WITH 10000;

CREATE SEQUENCE PRIDEPROT.SCORE_SCORE_ID_SEQ START WITH 10000;

CREATE SEQUENCE PRIDEPROT.STAR_STAR_ID_SEQ START WITH 10000;

CREATE TABLE PRIDEPROT.ASSAY
(
  ASSAY_ACCESSION NVARCHAR2 (45) CONSTRAINT NNC_ASSAY_ASSAY_ACCESSION NOT NULL ,
--  Accession for the assay in PRIDEARCH
  PROJECT_ACCESSION NVARCHAR2 (45) CONSTRAINT NNC_ASSAY_PROJECT_ACCESSION NOT NULL ,
  TAXID NUMBER CONSTRAINT NNC_ASSAY_TAXID NOT NULL
) ;
COMMENT ON COLUMN PRIDEPROT.ASSAY.PROJECT_ACCESSION
IS
'Accession for the assay in PRIDEARCH' ;
CREATE UNIQUE INDEX PRIDEPROT.ASSAY_ASSAY_ACCESSION_IDX ON PRIDEPROT.ASSAY
(
  ASSAY_ACCESSION ASC
) ;
ALTER TABLE PRIDEPROT.ASSAY ADD CONSTRAINT ASSAY_PK PRIMARY KEY ( ASSAY_ACCESSION ) ;

CREATE TABLE PRIDEPROT.ASSAY_CV
(
  ASSAY_ACCESSION NVARCHAR2 (45) CONSTRAINT NNC_ASSAY_CV_ASSAY_ACCESSION NOT NULL ,
  CV_TERM NVARCHAR2 (45) CONSTRAINT NNC_ASSAY_CV_CV_TERM NOT NULL
) ;
CREATE INDEX PRIDEPROT.ASSAY_CV_ASSAY_ACCESSION_IDX ON PRIDEPROT.ASSAY_CV
(
  ASSAY_ACCESSION ASC
) ;
CREATE INDEX PRIDEPROT.ASSAY_CV_CV_TERM_IDX ON PRIDEPROT.ASSAY_CV
(
  CV_TERM ASC
) ;
CREATE UNIQUE INDEX PRIDEPROT.ASSAY_CV_PK_IDX ON PRIDEPROT.ASSAY_CV
(
  ASSAY_ACCESSION ASC , CV_TERM ASC
) ;
ALTER TABLE PRIDEPROT.ASSAY_CV ADD CONSTRAINT ASSAY_CV_PK PRIMARY KEY ( ASSAY_ACCESSION, CV_TERM ) ;

-- This table is not mapped in the Java model, because it is only a temporary table to store the peptiforms generated
-- in the cluster until a definitive store is created
CREATE TABLE PRIDEPROT.CLUSTER_PSM
(
  SEQUENCE NVARCHAR2 (2000) CONSTRAINT NNC_CLUSTER_PSM_SEQUENCE NOT NULL ,
  MODS NVARCHAR2 (2000) ,
  TAXID NVARCHAR2 (250) CONSTRAINT NNC_CLUSTER_PSM_TAXID NOT NULL ,
  ASSAY_ACCESSION NVARCHAR2 (50) CONSTRAINT NNC_CLUSTER_PSM_ASSAY_ACCESSION NOT NULL ,
  PROJECT_ACCESSION NVARCHAR2 (20) CONSTRAINT NNC_CLUSTER_PSM_PROJECT_ACCESSION NOT NULL ,
  CLUSTER_ID NUMBER CONSTRAINT NNC_CLUSTER_PSM_CLUSTER_ID NOT NULL ,
  PSM_ID     NUMBER CONSTRAINT NNC_CLUSTER_PSM_PSM_ID NOT NULL
) ;
CREATE INDEX PRIDEPROT.CLUSTER_PSM_SEQUENCE_IDX ON PRIDEPROT.CLUSTER_PSM
(
  SEQUENCE ASC
) ;
CREATE INDEX PRIDEPROT.CLUSTER_PSM_COMBINE_IDX ON PRIDEPROT.CLUSTER_PSM
(
  SEQUENCE DESC ,
  TAXID DESC ,
  MODS DESC ,
  CLUSTER_ID DESC
) ;

CREATE TABLE PRIDEPROT.CV_PARAM
(
--  The CV_PARAM_PK should be the CV_TERM
  CV_TERM NVARCHAR2 (45) CONSTRAINT NNC_CV_PARAM_CV_TERM NOT NULL ,
  CV_NAME NVARCHAR2 (200) CONSTRAINT NNC_CV_PARAM_CV_NAME NOT NULL ,
  CV_TYPE NVARCHAR2 (45) CONSTRAINT NNC_CV_PARAM_CV_TYPE NOT NULL ,
  DESCRIPTION NVARCHAR2 (500)
) ;
ALTER TABLE PRIDEPROT.CV_PARAM ADD CONSTRAINT CK_CV_PARAM_CV_TYPE CHECK ( CV_TYPE IN ('CELL_TYPE', 'DISEASE', 'TISSUE','FEATURE_TYPE')) ;
COMMENT ON COLUMN PRIDEPROT.CV_PARAM.CV_TERM
IS
'The CV_PARAM_PK should be the CV_TERM' ;
CREATE UNIQUE INDEX PRIDEPROT.CV_PARAM_CV_TERM_IDX ON PRIDEPROT.CV_PARAM ( CV_TERM ASC ) ;
ALTER TABLE PRIDEPROT.CV_PARAM ADD CONSTRAINT CV_PARAM_PK PRIMARY KEY ( CV_TERM ) ;

CREATE TABLE PRIDEPROT.FEATURE
  (
    FEATURE_ID NUMBER CONSTRAINT NNC_FEATURE_FEATURE_ID NOT NULL ,
    PROTEIN_ID NVARCHAR2 (45) CONSTRAINT NNC_FEATURE_PROTEIN_ID NOT NULL ,
    CV_TERM NVARCHAR2 (45) CONSTRAINT NNC_FEATURE_CV_TERM NOT NULL ,
    START_POSITION SMALLINT CONSTRAINT NNC_FEATURE_START_POSITION NOT NULL ,
    END_POSITION   SMALLINT CONSTRAINT NNC_FEATURE_END_POSITION NOT NULL ,
    DESCRIPTION NVARCHAR2 (1000)
  ) ;

CREATE UNIQUE INDEX PRIDEPROT.FEATURE_FEATURE_ID_IDX ON PRIDEPROT.FEATURE
  (
    FEATURE_ID ASC
  ) ;
CREATE INDEX PRIDEPROT.FEATURE_PROTEIN_ID_IDX ON PRIDEPROT.FEATURE
  (
    PROTEIN_ID ASC
  ) ;
CREATE INDEX PRIDEPROT.FEATURE_CV_TERM_IDX ON PRIDEPROT.FEATURE
  (
    CV_TERM ASC
  ) ;
ALTER TABLE PRIDEPROT.FEATURE ADD CONSTRAINT FEATURE_PK PRIMARY KEY ( FEATURE_ID ) ;

CREATE TABLE PRIDEPROT.MOD
(
--  The primary key for the modification should be the CV_TERM
  MOD_ID NVARCHAR2 (45) CONSTRAINT NNC_MOD_MOD_ID NOT NULL ,
  MOD_NAME NVARCHAR2 (200) CONSTRAINT NNC_MOD_MOD_NAME NOT NULL ,
  MONO_DELTA             NUMBER ,
  BIOLOGICAL_SIGNIFICANT CHAR (1) ,
  DESCRIPTION NVARCHAR2 (500)
) ;
COMMENT ON COLUMN PRIDEPROT.MOD.MOD_ID
IS
'The primary key for the modification should be the CV_TERM' ;
CREATE UNIQUE INDEX PRIDEPROT.MOD_MOD_ID_IDX ON PRIDEPROT.MOD
(
  MOD_ID ASC
) ;
ALTER TABLE PRIDEPROT.MOD ADD CONSTRAINT MOD_PK PRIMARY KEY ( MOD_ID ) ;

CREATE TABLE PRIDEPROT.PEPTIDE
(
  PEPTIDE_ID NUMBER CONSTRAINT NNC_PEPTIDE_PEPTIDE_ID NOT NULL ,
  SEQUENCE NVARCHAR2 (500) CONSTRAINT NNC_PEPTIDE_SEQUENCE NOT NULL ,
  TAXID NUMBER CONSTRAINT NNC_PEPTIDE_TAXID NOT NULL ,
  REPRESENTATION NVARCHAR2 (500) CONSTRAINT NNC_PEPTIDE_REPRESENTATION NOT NULL ,
  SYMBOLIC NVARCHAR2 (10) CONSTRAINT NNC_PEPTIDE_SYMBOLIC NOT NULL ,
  MISSED_CLEAVAGES NUMBER ,
  DESCRIPTION NVARCHAR2 (1000) ,
  SCORE_ID NUMBER DEFAULT 1 CONSTRAINT NNC_PEPTIDE_SCORE_ID NOT NULL
) ;
ALTER TABLE PRIDEPROT.PEPTIDE ADD CONSTRAINT CK_PEPTIDE_SYMBOLIC CHECK ( SYMBOLIC IN ('FALSE', 'TRUE')) ;
CREATE INDEX PRIDEPROT.PEPTIDE_SCORE_ID_IDX ON PRIDEPROT.PEPTIDE
(
  SCORE_ID ASC
) ;
CREATE INDEX PRIDEPROT.PEPTIDE_SEQUENCE_IDX ON PRIDEPROT.PEPTIDE
(
  SEQUENCE ASC
) ;
CREATE UNIQUE INDEX PRIDEPROT.PEPTIDE_REPRESENTATION_IDX ON PRIDEPROT.PEPTIDE
(
  REPRESENTATION ASC
) ;
CREATE UNIQUE INDEX PRIDEPROT.PEPTIDE_PEPTIDE_ID_IDX ON PRIDEPROT.PEPTIDE
(
  PEPTIDE_ID ASC
) ;
ALTER TABLE PRIDEPROT.PEPTIDE ADD CONSTRAINT PEPTIDE_PK PRIMARY KEY ( PEPTIDE_ID ) ;
ALTER TABLE PRIDEPROT.PEPTIDE ADD CONSTRAINT PEPTIDE_REPRESENTATION_UN UNIQUE ( REPRESENTATION ) ;

CREATE TABLE PRIDEPROT.PEP_ASSAY
(
  PEPTIDE_ID NUMBER CONSTRAINT NNC_PEP_ASSAY_PEPTIDE_ID NOT NULL ,
  ASSAY_ACCESSION NVARCHAR2 (45) CONSTRAINT NNC_PEP_ASSAY_ASSAY_ACCESSION NOT NULL
) ;
CREATE INDEX PRIDEPROT.PEP_ASSAY_PEPTIDE_ID_IDX ON PRIDEPROT.PEP_ASSAY
(
  PEPTIDE_ID ASC
) ;
CREATE INDEX PRIDEPROT.PEP_ASSAY_ASSAY_ACCESSION_IDX ON PRIDEPROT.PEP_ASSAY
(
  ASSAY_ACCESSION ASC
) ;
CREATE UNIQUE INDEX PRIDEPROT.PEP_ASSAY_PK_IDX ON PRIDEPROT.PEP_ASSAY
(
  PEPTIDE_ID ASC , ASSAY_ACCESSION ASC
) ;
ALTER TABLE PRIDEPROT.PEP_ASSAY ADD CONSTRAINT PEP_ASSAY_PK PRIMARY KEY ( PEPTIDE_ID, ASSAY_ACCESSION ) ;

CREATE TABLE PRIDEPROT.PEP_CLUSTER
(
  PEPTIDE_ID NUMBER CONSTRAINT NNC_PEP_CLUSTER_PEPTIDE_ID NOT NULL ,
  CLUSTER_ID NUMBER CONSTRAINT NNC_PEP_CLUSTER_CLUSTER_ID NOT NULL
) ;
CREATE UNIQUE INDEX PRIDEPROT.PEP_CLUSTER_PK_IDX ON PRIDEPROT.PEP_CLUSTER
(
  PEPTIDE_ID ASC , CLUSTER_ID ASC
) ;
CREATE INDEX PRIDEPROT.PEP_CLUSTER_PEPTIDE_ID_IDX ON PRIDEPROT.PEP_CLUSTER
(
  PEPTIDE_ID ASC
) ;
CREATE INDEX PRIDEPROT.PEP_CLUSTER_CLUSTER_ID_IDX ON PRIDEPROT.PEP_CLUSTER
(
  CLUSTER_ID ASC
) ;
ALTER TABLE PRIDEPROT.PEP_CLUSTER ADD CONSTRAINT PEP_CLUSTER_PK PRIMARY KEY ( PEPTIDE_ID, CLUSTER_ID ) ;

CREATE TABLE PRIDEPROT.PEP_CV
(
  PEPTIDE_ID NUMBER CONSTRAINT NNC_PEP_CV_PEPTIDE_ID NOT NULL ,
  CV_TERM NVARCHAR2 (45) CONSTRAINT NNC_PEP_CV_CV_PARAM_ID NOT NULL
) ;
CREATE INDEX PRIDEPROT.PEP_CV_PEPTIDE_ID_IDX ON PRIDEPROT.PEP_CV
(
  PEPTIDE_ID ASC
) ;
CREATE INDEX PRIDEPROT.PEP_CV_CV_TERM_IDX ON PRIDEPROT.PEP_CV
(
  CV_TERM ASC
) ;
CREATE UNIQUE INDEX PRIDEPROT.PEP_CV_PK_IDX ON PRIDEPROT.PEP_CV
(
  CV_TERM ASC , PEPTIDE_ID ASC
) ;
ALTER TABLE PRIDEPROT.PEP_CV ADD CONSTRAINT PEP_CV_PK PRIMARY KEY ( PEPTIDE_ID, CV_TERM ) ;

CREATE TABLE PRIDEPROT.PEP_MOD
(
  MOD_ID     NVARCHAR2 (45) CONSTRAINT NNC_PEP_MOD_MOD_ID NOT NULL ,
  PEPTIDE_ID NUMBER CONSTRAINT NNC_PEP_MOD_PEPTIDE_ID NOT NULL ,
  POSITION   NUMBER CONSTRAINT NNC_PEP_MOD_POSITION NOT NULL
) ;
CREATE INDEX PRIDEPROT.PEP_MOD_MOD_ID_IDX ON PRIDEPROT.PEP_MOD
(
  MOD_ID ASC
) ;
CREATE INDEX PRIDEPROT.PEP_MOD_PEPTIDE_ID_IDX ON PRIDEPROT.PEP_MOD
(
  PEPTIDE_ID ASC
) ;
CREATE UNIQUE INDEX PRIDEPROT.PEP_MOD_PK_IDX ON PRIDEPROT.PEP_MOD
(
  MOD_ID ASC , PEPTIDE_ID ASC , POSITION ASC
) ;
ALTER TABLE PRIDEPROT.PEP_MOD ADD CONSTRAINT PEP_MOD_PK PRIMARY KEY ( POSITION, MOD_ID, PEPTIDE_ID ) ;

CREATE TABLE PRIDEPROT.PEP_PROT
(
  PROTEIN_ID        NVARCHAR2 (45) CONSTRAINT NNC_PEP_PROT_PROTEIN_ID NOT NULL ,
  PEPTIDE_ID        NUMBER CONSTRAINT NNC_PEP_PROT_PEPTIDE_ID NOT NULL ,
  START_POSITION    NUMBER CONSTRAINT NNC_PEP_PROT_START_POSITION NOT NULL ,
  NTERM_DEGRADATION NUMBER ,
  CTERM_DEGRADATION NUMBER ,
  UNIQUENESS        NUMBER ,
  SCORE_ID          NUMBER CONSTRAINT NNC_PEP_PROT_SCORE_ID NOT NULL
) ;
CREATE INDEX PRIDEPROT.PEP_PROT_PEPTIDE_ID_IDX ON PRIDEPROT.PEP_PROT
(
  PEPTIDE_ID ASC
) ;
CREATE INDEX PRIDEPROT.PEP_PROT_PROTEIN_ID_IDX ON PRIDEPROT.PEP_PROT
(
  PROTEIN_ID ASC
) ;
CREATE UNIQUE INDEX PRIDEPROT.PEP_PROT_PK_IDX ON PRIDEPROT.PEP_PROT
(
  PROTEIN_ID ASC , PEPTIDE_ID ASC , START_POSITION ASC
) ;
ALTER TABLE PRIDEPROT.PEP_PROT ADD CONSTRAINT PEP_PROT_PK PRIMARY KEY ( PEPTIDE_ID, PROTEIN_ID, START_POSITION ) ;

CREATE TABLE PRIDEPROT.PRIDE_CLUSTER
(
  CLUSTER_ID NUMBER CONSTRAINT NNC_PRIDE_CLUSTER_CLUSTER_ID NOT NULL
) ;
CREATE UNIQUE INDEX PRIDEPROT.PRIDE_CLUSTER_CLUSTER_ID_IDX ON PRIDEPROT.PRIDE_CLUSTER
(
  CLUSTER_ID ASC
) ;
ALTER TABLE PRIDEPROT.PRIDE_CLUSTER ADD CONSTRAINT PRIDE_CLUSTER_PK PRIMARY KEY ( CLUSTER_ID  ) ;

-- The sequence is a NCLOB in Oracle
CREATE TABLE PRIDEPROT.PROTEIN
(
--  Accession for Uniprot
  PROTEIN_ID NVARCHAR2 (45) CONSTRAINT NNC_PROTEIN_PROTEIN_ID NOT NULL ,
  SEQUENCE CLOB CONSTRAINT NNC_PROTEIN_SEQUENCE NOT NULL ,
  TAXID NUMBER CONSTRAINT NNC_PROTEIN_TAXID NOT NULL ,
  CURATION_LEVEL NVARCHAR2 (200) ,
  NAME NVARCHAR2 (1000) ,
  ALT_NAME NVARCHAR2 (1000) ,
  DESCRIPTION NVARCHAR2 (1000) ,
  GENE_SYMBOL NVARCHAR2 (1000) ,
  EVIDENCE    NUMBER ,
  CONTAMINANT CHAR (1) DEFAULT 'F' CONSTRAINT NNC_PROTEIN_CONTAMINANT NOT NULL ,
  SCORE_ID NUMBER DEFAULT 1 CONSTRAINT NNC_PROTEIN_SCORE_FK NOT NULL
) ;
COMMENT ON COLUMN PRIDEPROT.PROTEIN.PROTEIN_ID
IS
'Accession for Uniprot' ;
CREATE INDEX PRIDEPROT.PROTEIN_SCORE_ID_IDX ON PRIDEPROT.PROTEIN
(
  SCORE_ID ASC
) ;
CREATE INDEX PRIDEPROT.PROTEIN_TAXID_IDX ON PRIDEPROT.PROTEIN
(
  TAXID ASC
) ;
CREATE UNIQUE INDEX PRIDEPROT.PROTEIN_PROTEIN_ID_IDX ON PRIDEPROT.PROTEIN
(
  PROTEIN_ID ASC
) ;
ALTER TABLE PRIDEPROT.PROTEIN ADD CONSTRAINT PROTEIN_PK PRIMARY KEY ( PROTEIN_ID ) ;
ALTER TABLE PRIDEPROT.PROTEIN ADD CONSTRAINT CK_PROTEIN_CONTAMINANT CHECK ( CONTAMINANT IN ('F', 'T')) ;

CREATE TABLE PRIDEPROT.PROT_CV
(
  PROTEIN_ID NVARCHAR2 (45) CONSTRAINT NNC_PROT_CV_PROTEIN_ID NOT NULL ,
  CV_TERM NVARCHAR2 (45) CONSTRAINT NNC_PROT_CV_CV_PARAM_ID NOT NULL
) ;
CREATE INDEX PRIDEPROT.PROT_CV_CV_TERM_IDX ON PRIDEPROT.PROT_CV
(
  CV_TERM ASC
) ;
CREATE INDEX PRIDEPROT.PROT_CV_PROTEIN_ID_IDX ON PRIDEPROT.PROT_CV
(
  PROTEIN_ID ASC
) ;
CREATE UNIQUE INDEX PRIDEPROT.PROT_CV_PK_IDX ON PRIDEPROT.PROT_CV
(
  CV_TERM ASC , PROTEIN_ID ASC
) ;
ALTER TABLE PRIDEPROT.PROT_CV ADD CONSTRAINT PROT_CV_PK PRIMARY KEY ( CV_TERM, PROTEIN_ID ) ;

CREATE TABLE PRIDEPROT.PROT_GROUP
(
  PROT_GROUP_ID NVARCHAR2 (45) CONSTRAINT NNC_PROT_GROUP_PROT_GROUP_ID NOT NULL ,
  PROT_GROUP_TYPE NVARCHAR2 (45) CONSTRAINT NNC_PROT_GROUP_PROT_GROUP_TYPE NOT NULL ,
  DESCRIPTION NVARCHAR2 (500) ,
  TAXID NUMBER CONSTRAINT NNC_PROT_GROUP_TAXID NOT NULL
) ;
CREATE UNIQUE INDEX PRIDEPROT.PROT_GROUP_PROT_GROUP_ID_IDX ON PRIDEPROT.PROT_GROUP
(
  PROT_GROUP_ID ASC
) ;
ALTER TABLE PRIDEPROT.PROT_GROUP ADD CONSTRAINT PROT_GROUP_PK PRIMARY KEY ( PROT_GROUP_ID ) ;

CREATE TABLE PRIDEPROT.PROT_MOD
(
  MOD_ID NVARCHAR2 (45) CONSTRAINT NNC_PROT_MOD_MOD_ID NOT NULL ,
  PROTEIN_ID NVARCHAR2 (45) CONSTRAINT NNC_PROT_MOD_PROTEIN_ID NOT NULL ,
  POSITION NUMBER CONSTRAINT NNC_PROT_MOD_POSITION NOT NULL
) ;
CREATE INDEX PRIDEPROT.PROT_MOD_PROTEIN_ID_IDX ON PRIDEPROT.PROT_MOD
(
  PROTEIN_ID ASC
) ;
CREATE INDEX PRIDEPROT.PROT_MOD_MOD_ID_IDX ON PRIDEPROT.PROT_MOD
(
  MOD_ID ASC
) ;
CREATE UNIQUE INDEX PRIDEPROT.PROT_MOD_PK_IDX ON PRIDEPROT.PROT_MOD
(
  MOD_ID ASC , PROTEIN_ID ASC , POSITION ASC
) ;
ALTER TABLE PRIDEPROT.PROT_MOD ADD CONSTRAINT PROT_MOD_PK PRIMARY KEY ( POSITION, MOD_ID, PROTEIN_ID ) ;

CREATE TABLE PRIDEPROT.PROT_PGRP
(
  PROTEIN_ID NVARCHAR2 (45) CONSTRAINT NNC_PROT_PGRP_PROTEIN_ID NOT NULL ,
  PROT_GROUP_ID NVARCHAR2 (45) CONSTRAINT NNC_PROT_PGRP_PROT_GROUP_ID NOT NULL
) ;
CREATE INDEX PRIDEPROT.PROT_PGRP_PROTEIN_ID_IDX ON PRIDEPROT.PROT_PGRP
(
  PROTEIN_ID ASC
) ;
CREATE INDEX PRIDEPROT.PROT_PGRP_PROT_GROUP_ID_IDX ON PRIDEPROT.PROT_PGRP
(
  PROT_GROUP_ID ASC
) ;
CREATE UNIQUE INDEX PRIDEPROT.PROT_PGRP_PK_IDX ON PRIDEPROT.PROT_PGRP
(
  PROTEIN_ID ASC , PROT_GROUP_ID ASC
) ;
ALTER TABLE PRIDEPROT.PROT_PGRP ADD CONSTRAINT PROT_PGRP_PK PRIMARY KEY ( PROTEIN_ID, PROT_GROUP_ID ) ;

CREATE TABLE PRIDEPROT.Q_METHOD
(
  Q_METHOD_ID NUMBER CONSTRAINT NNC_Q_METHOD_Q_METHOD_ID NOT NULL ,
  NAME NVARCHAR2 (45) CONSTRAINT NNC_Q_METHOD_NAME NOT NULL ,
  DESCRIPTION NVARCHAR2 (200)
) ;
CREATE UNIQUE INDEX PRIDEPROT.Q_METHOD_Q_METHOD_ID_IDX ON PRIDEPROT.Q_METHOD
(
  Q_METHOD_ID ASC
) ;
ALTER TABLE PRIDEPROT.Q_METHOD ADD CONSTRAINT Q_METHOD_PK PRIMARY KEY ( Q_METHOD_ID ) ;

CREATE TABLE PRIDEPROT.RELEASE_SUM
(
  TAXID NUMBER CONSTRAINT NNC_RELEASE_SUM_TAXID NOT NULL ,
  RELEASE_DATE NVARCHAR2 (100) CONSTRAINT NNC_RELEASE_SUM_RELEASE_DATE NOT NULL ,
  REFERENCE_DATABASE NVARCHAR2 (500) ,
  REFERENCE_DATABASE_VERSION NVARCHAR2 (100) ,
  NUM_ISOFORMS_PROTEINS         NUMBER ,
  NUM_PEPTIFORMS                NUMBER ,
  NUM_PEPTIDES                  NUMBER ,
  NUM_PROTEINS                  NUMBER ,
  NUM_CANONICAL_PROTEINS        NUMBER ,
  NUM_GENES                     NUMBER ,
  NUM_MAPPED_PEP_TO_PROT        NUMBER ,
  NUM_MAPPED_UNIQ_PEP_TO_PROT   NUMBER ,
  NUM_MAPPED_UNIQ_PEP_TO_I_PROT NUMBER ,
  NUM_MAPPED_UNIQ_PEP_TO_C_PROT NUMBER ,
  NUM_MAPPED_PROTEINS           NUMBER ,
  NUM_MAPPED_CANONICAL_PROTEINS NUMBER ,
  NUM_MAPPED_ISOFORM_PROTEINS   NUMBER ,
  NUM_MAPPED_PROTS_W_UNIQ_PEPS  NUMBER ,
  NUM_MAPPED_C_PROT_W_UNIQ_PEPS NUMBER ,
  NUM_MAPPED_I_PROT_W_UNIQ_PEPS NUMBER ,
  NUM_MAPPED_GENES              NUMBER ,
  NUM_MAPPED_UNIQ_PEP_TO_GENES  NUMBER ,
  NUM_MAPPED_GENES_W_UNIQ_PEPS  NUMBER ,
  NUM_MAPPED_PROTS_W_PE2        NUMBER ,
  NUM_MAPPED_PROTS_W_PE3        NUMBER ,
  NUM_MAPPED_PROTS_W_PE4        NUMBER ,
  NUM_MAPPED_PROTS_W_PE5        NUMBER
  );
ALTER TABLE PRIDEPROT.RELEASE_SUM ADD CONSTRAINT RELEASE_SUM_PK PRIMARY KEY ( TAXID, RELEASE_DATE ) ;

CREATE TABLE PRIDEPROT.REPRO
(
  REPRO_ACCESSION NVARCHAR2 (45) CONSTRAINT NNC_REPRO_REPRO_ACCESSION NOT NULL ,
  ASSAY_ACCESSION NVARCHAR2 (45) CONSTRAINT NNC_REPRO_ASSAY_ACCESSION NOT NULL
) ;
CREATE INDEX PRIDEPROT.REPRO_ASSAY_ACCESSION_IDX ON PRIDEPROT.REPRO
(
  ASSAY_ACCESSION ASC
) ;
CREATE UNIQUE INDEX PRIDEPROT.REPRO_PK_IDX ON PRIDEPROT.REPRO
(
  ASSAY_ACCESSION ASC , REPRO_ACCESSION ASC
) ;
ALTER TABLE PRIDEPROT.REPRO ADD CONSTRAINT REPRO_PK PRIMARY KEY ( REPRO_ACCESSION, ASSAY_ACCESSION ) ;

CREATE TABLE PRIDEPROT.SCORE
(
  SCORE_ID    NUMBER CONSTRAINT NNC_SCORE_SCORE_ID NOT NULL ,
  Q_METHOD_ID NUMBER CONSTRAINT NNC_SCORE_Q_METHOD_ID NOT NULL ,
  STAR_ID     NUMBER CONSTRAINT NNC_SCORE_STAR_ID NOT NULL ,
  VALUE       NUMBER CONSTRAINT NNC_SCORE_VALUE NOT NULL
) ;
CREATE INDEX PRIDEPROT.SCORE_STAR_ID_IDX ON PRIDEPROT.SCORE
( STAR_ID ASC
) ;
CREATE INDEX PRIDEPROT.SCORE_Q_METHOD_ID_IDX ON PRIDEPROT.SCORE
(
  Q_METHOD_ID ASC
) ;
CREATE UNIQUE INDEX PRIDEPROT.SCORE_SCORE_ID_IDX ON PRIDEPROT.SCORE
(
  SCORE_ID ASC
) ;
ALTER TABLE PRIDEPROT.SCORE ADD CONSTRAINT SCORE_PK PRIMARY KEY ( SCORE_ID ) ;

CREATE TABLE PRIDEPROT.STAR
(
  STAR_ID    NUMBER CONSTRAINT NNC_STAR_STAR_ID NOT NULL ,
  STAR_COUNT NUMBER DEFAULT 0 CONSTRAINT NNC_STAR_STAR_COUNT NOT NULL ,
  STAR_TYPE NVARCHAR2 (45) DEFAULT 'NONE' CONSTRAINT NNC_STAR_STAR_TYPE NOT NULL
) ;
ALTER TABLE PRIDEPROT.STAR ADD CONSTRAINT CK_STAR_STAR_TYPE CHECK ( STAR_TYPE IN ('BRONZE', 'GOLD', 'NONE', 'SILVER')) ;
CREATE UNIQUE INDEX PRIDEPROT.STAR_STAR_ID_IDX ON PRIDEPROT.STAR ( STAR_ID ASC ) ;
ALTER TABLE PRIDEPROT.STAR ADD CONSTRAINT STAR_PK PRIMARY KEY ( STAR_ID ) ;

ALTER TABLE PRIDEPROT.ASSAY_CV ADD CONSTRAINT ASSAY_CV_ASSAY_FK FOREIGN KEY ( ASSAY_ACCESSION ) REFERENCES PRIDEPROT.ASSAY ( ASSAY_ACCESSION ) ;

ALTER TABLE PRIDEPROT.ASSAY_CV ADD CONSTRAINT ASSAY_CV_CV_PARAM_FK FOREIGN KEY ( CV_TERM ) REFERENCES PRIDEPROT.CV_PARAM ( CV_TERM ) ;

ALTER TABLE PRIDEPROT.PEPTIDE ADD CONSTRAINT PEPTIDE_SCORE_FK FOREIGN KEY ( SCORE_ID ) REFERENCES PRIDEPROT.SCORE ( SCORE_ID ) ;

ALTER TABLE PRIDEPROT.PEP_ASSAY ADD CONSTRAINT PEP_ASSAY_ASSAY_FK FOREIGN KEY ( ASSAY_ACCESSION ) REFERENCES PRIDEPROT.ASSAY ( ASSAY_ACCESSION ) ;

ALTER TABLE PRIDEPROT.PEP_ASSAY ADD CONSTRAINT PEP_ASSAY_PEPTIDE_FK FOREIGN KEY ( PEPTIDE_ID ) REFERENCES PRIDEPROT.PEPTIDE ( PEPTIDE_ID ) ;

ALTER TABLE PRIDEPROT.FEATURE ADD CONSTRAINT FEATURE_CV_PARAM_FK FOREIGN KEY ( CV_TERM ) REFERENCES PRIDEPROT.CV_PARAM ( CV_TERM ) ;

ALTER TABLE PRIDEPROT.FEATURE ADD CONSTRAINT FEATURE_PROTEIN_FK FOREIGN KEY ( PROTEIN_ID ) REFERENCES PRIDEPROT.PROTEIN ( PROTEIN_ID ) ;

ALTER TABLE PRIDEPROT.PEP_CLUSTER ADD CONSTRAINT PEP_CLUSTER_PEPTIDE_FK FOREIGN KEY ( PEPTIDE_ID ) REFERENCES PRIDEPROT.PEPTIDE ( PEPTIDE_ID ) ;

ALTER TABLE PRIDEPROT.PEP_CLUSTER ADD CONSTRAINT PEP_CLUSTER_PRIDE_CLUSTER_FK FOREIGN KEY ( CLUSTER_ID ) REFERENCES PRIDEPROT.PRIDE_CLUSTER ( CLUSTER_ID ) ;

ALTER TABLE PRIDEPROT.PEP_CV ADD CONSTRAINT PEP_CV_CV_PARAM_FK FOREIGN KEY ( CV_TERM ) REFERENCES PRIDEPROT.CV_PARAM ( CV_TERM ) ;

ALTER TABLE PRIDEPROT.PEP_CV ADD CONSTRAINT PEP_CV_PEPTIDE_FK FOREIGN KEY ( PEPTIDE_ID ) REFERENCES PRIDEPROT.PEPTIDE ( PEPTIDE_ID ) ;

ALTER TABLE PRIDEPROT.PEP_MOD ADD CONSTRAINT PEP_MOD_MOD_FK FOREIGN KEY ( MOD_ID ) REFERENCES PRIDEPROT.MOD ( MOD_ID ) ;

ALTER TABLE PRIDEPROT.PEP_MOD ADD CONSTRAINT PEP_MOD_PEPTIDE_FK FOREIGN KEY ( PEPTIDE_ID ) REFERENCES PRIDEPROT.PEPTIDE ( PEPTIDE_ID ) ;

ALTER TABLE PRIDEPROT.PEP_PROT ADD CONSTRAINT PEP_PROT_PEPTIDE_FK FOREIGN KEY ( PEPTIDE_ID ) REFERENCES PRIDEPROT.PEPTIDE ( PEPTIDE_ID ) ;

ALTER TABLE PRIDEPROT.PEP_PROT ADD CONSTRAINT PEP_PROT_PROTEIN_FK FOREIGN KEY ( PROTEIN_ID ) REFERENCES PRIDEPROT.PROTEIN ( PROTEIN_ID ) ;

ALTER TABLE PRIDEPROT.PEP_PROT ADD CONSTRAINT PEP_PROT_SCORE_FK FOREIGN KEY ( SCORE_ID ) REFERENCES PRIDEPROT.SCORE ( SCORE_ID ) ;

ALTER TABLE PRIDEPROT.PROTEIN ADD CONSTRAINT PROTEIN_SCORE_FK FOREIGN KEY ( SCORE_ID ) REFERENCES PRIDEPROT.SCORE ( SCORE_ID ) ;

ALTER TABLE PRIDEPROT.PROT_CV ADD CONSTRAINT PROT_CV_CV_PARAM_FK FOREIGN KEY ( CV_TERM ) REFERENCES PRIDEPROT.CV_PARAM ( CV_TERM ) ;

ALTER TABLE PRIDEPROT.PROT_CV ADD CONSTRAINT PROT_CV_PROTEIN_FK FOREIGN KEY ( PROTEIN_ID ) REFERENCES PRIDEPROT.PROTEIN ( PROTEIN_ID ) ;

ALTER TABLE PRIDEPROT.PROT_MOD ADD CONSTRAINT PROT_MOD_MOD_FK FOREIGN KEY ( MOD_ID ) REFERENCES PRIDEPROT.MOD ( MOD_ID ) ;

ALTER TABLE PRIDEPROT.PROT_MOD ADD CONSTRAINT PROT_MOD_PROTEIN_FK FOREIGN KEY ( PROTEIN_ID ) REFERENCES PRIDEPROT.PROTEIN ( PROTEIN_ID ) ;

ALTER TABLE PRIDEPROT.PROT_PGRP ADD CONSTRAINT PROT_PGRP_PROTEIN_FK FOREIGN KEY ( PROTEIN_ID ) REFERENCES PRIDEPROT.PROTEIN ( PROTEIN_ID ) ;

ALTER TABLE PRIDEPROT.PROT_PGRP ADD CONSTRAINT PROT_PGRP_PROT_GROUP_FK FOREIGN KEY ( PROT_GROUP_ID ) REFERENCES PRIDEPROT.PROT_GROUP ( PROT_GROUP_ID ) ;

ALTER TABLE PRIDEPROT.REPRO ADD CONSTRAINT REPRO_ASSAY_FK FOREIGN KEY ( ASSAY_ACCESSION ) REFERENCES PRIDEPROT.ASSAY ( ASSAY_ACCESSION ) ;

ALTER TABLE PRIDEPROT.SCORE ADD CONSTRAINT SCORE_Q_METHOD_FK FOREIGN KEY ( Q_METHOD_ID ) REFERENCES PRIDEPROT.Q_METHOD ( Q_METHOD_ID ) ;

ALTER TABLE PRIDEPROT.SCORE ADD CONSTRAINT SCORE_STAR_FK FOREIGN KEY ( STAR_ID ) REFERENCES PRIDEPROT.STAR ( STAR_ID ) ;

CREATE VIEW PRIDEPROT.PEP_PGROUP  AS
SELECT
  DISTINCT
  PEP_UNIQ.PEPTIDE_ID,
  PEP_UNIQ.UNIQUENESS,
  PROT_PGRP.PROT_GROUP_ID,
  PROT_GROUP.PROT_GROUP_TYPE,
  PROT_GROUP.TAXID
FROM
  (
    SELECT
      PEP_GROUP.PEPTIDE_ID  AS PEPTIDE_ID,
      COUNT(PEP_GROUP.PEPTIDE_ID) AS UNIQUENESS,
      PEP_GROUP.PROT_GROUP_TYPE AS PROT_GROUP_TYPE
    FROM
      (
        SELECT
          PEPTIDE.PEPTIDE_ID,
          PROT_PGRP.PROT_GROUP_ID,
          PROT_GROUP.PROT_GROUP_TYPE
        FROM
          PRIDEPROT.PEPTIDE,
          PRIDEPROT.PEP_PROT,
          PRIDEPROT.PROT_PGRP,
          PRIDEPROT.PROT_GROUP
        WHERE PEPTIDE.PEPTIDE_ID   = PEP_PROT.PEPTIDE_ID
              AND PEP_PROT.PROTEIN_ID = PROT_PGRP.PROTEIN_ID
              AND PEPTIDE.SYMBOLIC       = 'TRUE'
              AND PROT_PGRP.PROT_GROUP_ID = PROT_GROUP.PROT_GROUP_ID
        GROUP BY
          PEPTIDE.PEPTIDE_ID,
          PROT_PGRP.PROT_GROUP_ID,
          PROT_GROUP.PROT_GROUP_TYPE
      ) PEP_GROUP
    GROUP BY PEP_GROUP.PEPTIDE_ID,PEP_GROUP.PROT_GROUP_TYPE
  )PEP_UNIQ,
  PRIDEPROT.PEP_PROT,
  PRIDEPROT.PROT_PGRP,
  PRIDEPROT.PROT_GROUP
WHERE PEP_UNIQ.PEPTIDE_ID = PEP_PROT.PEPTIDE_ID
      AND PEP_PROT.PROTEIN_ID   = PROT_PGRP.PROTEIN_ID
      AND PROT_GROUP.PROT_GROUP_ID = PROT_PGRP.PROT_GROUP_ID
      AND PEP_UNIQ.PROT_GROUP_TYPE = PROT_GROUP.PROT_GROUP_TYPE
ORDER BY PEP_UNIQ.PEPTIDE_ID;

-- Oracle SQL Developer Data Modeler Summary Report:
--
-- CREATE TABLE                            22
-- CREATE INDEX                            52
-- ALTER TABLE                             53
-- CREATE VIEW                              1
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          6
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        2
-- CREATE USER                              1
--
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
--
-- REDACTION POLICY                         0
--
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
--
-- ERRORS                                   0
-- WARNINGS                                 2
