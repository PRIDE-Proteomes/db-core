-- Generated by Oracle SQL Developer Data Modeler 3.1.0.699
--   at:        2013-08-15 16:41:46 BST
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g

--  WARNING: Remove the line below from the script when we are in oracle.
--  It needs to be added for compatibility with the test
CREATE SCHEMA PRIDEPROT AUTHORIZATION DBA;

CREATE TABLE PRIDEPROT.ASSAY
(
  ASSAY_ACCESSION NVARCHAR2 (45)  NOT NULL ,
  PROJECT_ACCESSION NVARCHAR2 (45)  NOT NULL ,
  TAXID NUMBER  NOT NULL
)
;



COMMENT ON COLUMN PRIDEPROT.ASSAY.PROJECT_ACCESSION IS 'Accession for the assay in PRIDEARCH'
;

ALTER TABLE PRIDEPROT.ASSAY
ADD CONSTRAINT ASSAY_PK PRIMARY KEY ( ASSAY_ACCESSION ) ;



CREATE TABLE PRIDEPROT.ASSAY_CV
(
  ASSAY_FK_PK NVARCHAR2 (45)  NOT NULL ,
  CV_PARAM_FK_PK NVARCHAR2 (45)  NOT NULL
)
;


CREATE INDEX PRIDEPROT.ASSAY_CV_ASSAY_FK_PK_IDX ON PRIDEPROT.ASSAY_CV
(
  ASSAY_FK_PK ASC
)
;
CREATE INDEX PRIDEPROT.ASSAY_CV_CV_PARAM_FK_PK_IDX ON PRIDEPROT.ASSAY_CV
(
  CV_PARAM_FK_PK ASC
)
;

ALTER TABLE PRIDEPROT.ASSAY_CV
ADD CONSTRAINT ASSAY_CV_PK PRIMARY KEY ( ASSAY_FK_PK, CV_PARAM_FK_PK ) ;



CREATE TABLE PRIDEPROT.CV_PARAM
(
  CV_TERM NVARCHAR2 (45)  NOT NULL ,
  CV_NAME NVARCHAR2 (200)  NOT NULL ,
  CV_TYPE NVARCHAR2 (45)  NOT NULL CHECK ( CV_TYPE IN ('CELL_TYPE', 'DISEASE', 'TISSUE')) ,
  DESCRIPTION NVARCHAR2 (500)
)
;



COMMENT ON COLUMN PRIDEPROT.CV_PARAM.CV_TERM IS 'The CV_PARAM_PK should be the CV_TERM'
;

ALTER TABLE PRIDEPROT.CV_PARAM
ADD CONSTRAINT CV_PARAM_PK PRIMARY KEY ( CV_TERM ) ;



CREATE TABLE PRIDEPROT.GENE
(
  GENE_ACCESSION NVARCHAR2 (45)  NOT NULL ,
  DESCRIPTION NVARCHAR2 (200) ,
  TAXID NUMBER  NOT NULL
)
;



ALTER TABLE PRIDEPROT.GENE
ADD CONSTRAINT GENE_PK PRIMARY KEY ( GENE_ACCESSION ) ;



CREATE TABLE PRIDEPROT.GENE_PGRP
(
  P_GROUP_FK_PK NUMBER  NOT NULL ,
  GENE_FK_PK NVARCHAR2 (45)  NOT NULL
)
;


CREATE INDEX PRIDEPROT.GENE_PGRP_P_GROUP_FK_PK_IDX ON PRIDEPROT.GENE_PGRP
(
  P_GROUP_FK_PK ASC
)
;
CREATE INDEX PRIDEPROT.GENE_PGRP_GENE_FK_PK_IDX ON PRIDEPROT.GENE_PGRP
(
  GENE_FK_PK ASC
)
;

ALTER TABLE PRIDEPROT.GENE_PGRP
ADD CONSTRAINT GENE_PGRP_PK PRIMARY KEY ( GENE_FK_PK, P_GROUP_FK_PK ) ;



CREATE TABLE PRIDEPROT.MOD
(
  CV_TERM NVARCHAR2 (45)  NOT NULL ,
  CV_NAME NVARCHAR2 (200)  NOT NULL ,
  MONO_DELTA NUMBER ,
  BIOLOGICAL_SIGNIFICANT CHAR (1) ,
  DESCRIPTION NVARCHAR2 (500)
)
;



COMMENT ON COLUMN PRIDEPROT.MOD.CV_TERM IS 'The primary key for the modification should be the CV_TERM'
;

ALTER TABLE PRIDEPROT.MOD
ADD CONSTRAINT MOD_PK PRIMARY KEY ( CV_TERM ) ;



CREATE TABLE PRIDEPROT.PEPTIDE
(
  PEPTIDE_PK NUMBER  NOT NULL ,
  SEQUENCE NVARCHAR2 (500)  NOT NULL ,
  TAXID NUMBER  NOT NULL,
  REPRESENTATION NVARCHAR2 (500) NOT NULL,
  DESCRIPTION NVARCHAR2 (1000) ,
  SYMBOLIC NVARCHAR2 (10)  NOT NULL CHECK ( SYMBOLIC IN ('FALSE', 'TRUE')) ,
  SCORE_FK NUMBER DEFAULT 1 NOT NULL
)
;


CREATE INDEX PRIDEPROT.PEPTIDE_SCORE_FK_IDX ON PRIDEPROT.PEPTIDE
(
  SCORE_FK ASC
)
;

ALTER TABLE PRIDEPROT.PEPTIDE
ADD CONSTRAINT PEPTIDE_PK PRIMARY KEY ( PEPTIDE_PK ) ;

ALTER TABLE PRIDEPROT.PEPTIDE
ADD CONSTRAINT PEPTIDE_REPRESENTATION_UN UNIQUE (REPRESENTATION);

CREATE TABLE PRIDEPROT.PEPTIDE_MOD
(
  MOD_FK_PK NVARCHAR2 (45)  NOT NULL ,
  PEPTIDE_FK_PK NUMBER  NOT NULL ,
  POSITION NUMBER  NOT NULL
)
;


CREATE INDEX PRIDEPROT.PEPTIDE_MOD_MOD_FK_PK_IDX ON PRIDEPROT.PEPTIDE_MOD
(
  MOD_FK_PK ASC
)
;
CREATE INDEX PRIDEPROT.PEPTIDE_MOD_PEPTIDE_FK_PK_IDX ON PRIDEPROT.PEPTIDE_MOD
(
  PEPTIDE_FK_PK ASC
)
;

ALTER TABLE PRIDEPROT.PEPTIDE_MOD
ADD CONSTRAINT PEPTIDE_MOD_PK PRIMARY KEY ( POSITION, MOD_FK_PK, PEPTIDE_FK_PK ) ;



CREATE TABLE PRIDEPROT.PEP_ASSAY
(
  PEPTIDE_FK_PK NUMBER  NOT NULL ,
  ASSAY_FK_PK NVARCHAR2 (45)  NOT NULL
)
;


CREATE INDEX PRIDEPROT.PEP_ASSAY_PEPTIDE_FK_PK_IDX ON PRIDEPROT.PEP_ASSAY
(
  PEPTIDE_FK_PK ASC
)
;
CREATE INDEX PRIDEPROT.PEP_ASSAY_ASSAY_FK_PK_IDX ON PRIDEPROT.PEP_ASSAY
(
  ASSAY_FK_PK ASC
)
;

ALTER TABLE PRIDEPROT.PEP_ASSAY
ADD CONSTRAINT PEP_ASSAY_PK PRIMARY KEY ( PEPTIDE_FK_PK, ASSAY_FK_PK ) ;



CREATE TABLE PRIDEPROT.PEP_CV
(
  PEPTIDE_FK_PK NUMBER  NOT NULL ,
  CV_PARAM_FK_PK NVARCHAR2 (45)  NOT NULL
)
;


CREATE INDEX PRIDEPROT.PEP_CV_PEPTIDE_FK_PK_IDX ON PRIDEPROT.PEP_CV
(
  PEPTIDE_FK_PK ASC
)
;
CREATE INDEX PRIDEPROT.PEP_CV_CV_PARAM_FK_PK_IDX ON PRIDEPROT.PEP_CV
(
  CV_PARAM_FK_PK ASC
)
;

ALTER TABLE PRIDEPROT.PEP_CV
ADD CONSTRAINT PEP_CV_PK PRIMARY KEY ( PEPTIDE_FK_PK, CV_PARAM_FK_PK ) ;



CREATE TABLE PRIDEPROT.PROTEIN
(
  PROTEIN_ACCESSION NVARCHAR2 (45)  NOT NULL ,
  SEQUENCE CLOB  NOT NULL ,
  TAXID NUMBER  NOT NULL,
  DESCRIPTION NVARCHAR2 (1000) ,
  SCORE_FK NUMBER DEFAULT 1 NOT NULL

)
;



COMMENT ON COLUMN PRIDEPROT.PROTEIN.PROTEIN_ACCESSION IS 'Accession for Uniprot'
;
CREATE INDEX PRIDEPROT.PROTEIN_SCORE_FK_IDX ON PRIDEPROT.PROTEIN
(
  SCORE_FK ASC
)
;
CREATE INDEX PRIDEPROT.PROTEIN_TAXID_IDX ON PRIDEPROT.PROTEIN
(
  TAXID ASC
)
;

ALTER TABLE PRIDEPROT.PROTEIN
ADD CONSTRAINT PROTEIN_PK PRIMARY KEY ( PROTEIN_ACCESSION ) ;



CREATE TABLE PRIDEPROT.PROTEIN_GENE
(
  PROTEIN_FK_PK NVARCHAR2 (45)  NOT NULL ,
  GENE_FK_PK NVARCHAR2 (45)  NOT NULL
)
;


CREATE INDEX PRIDEPROT.PROTEIN_GENE_PROTEIN_FK_PK_IDX ON PRIDEPROT.PROTEIN_GENE
(
  PROTEIN_FK_PK ASC
)
;
CREATE INDEX PRIDEPROT.PROTEIN_GENE_GENE_FK_PK_IDX ON PRIDEPROT.PROTEIN_GENE
(
  GENE_FK_PK ASC
)
;

ALTER TABLE PRIDEPROT.PROTEIN_GENE
ADD CONSTRAINT PROTEIN_GENE_PK PRIMARY KEY ( PROTEIN_FK_PK, GENE_FK_PK ) ;



CREATE TABLE PRIDEPROT.PROTEIN_MOD
(
  MOD_FK_PK NVARCHAR2 (45)  NOT NULL ,
  PROTEIN_FK_PK NVARCHAR2 (45)  NOT NULL ,
  POSITION NUMBER  NOT NULL
)
;


CREATE INDEX PRIDEPROT.PROTEIN_MOD_PROTEIN_FK_PK_IDX ON PRIDEPROT.PROTEIN_MOD
(
  PROTEIN_FK_PK ASC
)
;
CREATE INDEX PRIDEPROT.PROTEIN_MOD_MOD_FK_PK_IDX ON PRIDEPROT.PROTEIN_MOD
(
  MOD_FK_PK ASC
)
;

ALTER TABLE PRIDEPROT.PROTEIN_MOD
ADD CONSTRAINT PROTEIN_MOD_PK PRIMARY KEY ( POSITION, MOD_FK_PK, PROTEIN_FK_PK ) ;



CREATE TABLE PRIDEPROT.PROT_GROUP
(
  PROT_GROUP_PK NUMBER  NOT NULL ,
  PROT_GROUP_TYPE NVARCHAR2 (45)  NOT NULL ,
  DESCRIPTION NVARCHAR2 (500),
  TAXID NUMBER  NOT NULL
)
;


ALTER TABLE PRIDEPROT.PROT_GROUP
ADD CONSTRAINT PROT_GROUP_TYPE
CHECK ( PROT_GROUP_TYPE IN ('ISOFORM'))
;

ALTER TABLE PRIDEPROT.PROT_GROUP
ADD CONSTRAINT PROT_GROUP_PK PRIMARY KEY ( PROT_GROUP_PK ) ;



CREATE TABLE PRIDEPROT.PROT_PEP
(
  PROTEIN_FK_PK NVARCHAR2 (45)  NOT NULL ,
  PEPTIDE_FK_PK NUMBER  NOT NULL ,
  START_POSITION NUMBER ,
  TRYPTIC_SCORE NUMBER ,
  UNIQUENESS NUMBER
)
;


CREATE INDEX PRIDEPROT.PROT_PEP_PEPTIDE_FK_PK_IDX ON PRIDEPROT.PROT_PEP
(
  PEPTIDE_FK_PK ASC
)
;
CREATE INDEX PRIDEPROT.PROT_PEP_PROTEIN_FK_PK_IDX ON PRIDEPROT.PROT_PEP
(
  PROTEIN_FK_PK ASC
)
;

ALTER TABLE PRIDEPROT.PROT_PEP
ADD CONSTRAINT PROT_PEP_PK PRIMARY KEY ( PEPTIDE_FK_PK, PROTEIN_FK_PK ) ;



CREATE TABLE PRIDEPROT.PROT_PGRP
(
  PROT_FK_PK NVARCHAR2 (45)  NOT NULL ,
  P_GROUP_FK_PK NUMBER  NOT NULL
)
;


CREATE INDEX PRIDEPROT.PROT_PGRP_PROT_FK_PK_IDX ON PRIDEPROT.PROT_PGRP
(
  PROT_FK_PK ASC
)
;
CREATE INDEX PRIDEPROT.PROT_PGRP_P_GROUP_FK_PK_IDX ON PRIDEPROT.PROT_PGRP
(
  P_GROUP_FK_PK ASC
)
;

ALTER TABLE PRIDEPROT.PROT_PGRP
ADD CONSTRAINT PROT_PGRP_PK PRIMARY KEY ( PROT_FK_PK, P_GROUP_FK_PK ) ;



CREATE TABLE PRIDEPROT.Q_METHOD
(
  QUALITY_METHOD_PK NUMBER  NOT NULL ,
  NAME NVARCHAR2 (45)  NOT NULL ,
  DESCRIPTION NVARCHAR2 (200)
)
;



ALTER TABLE PRIDEPROT.Q_METHOD
ADD CONSTRAINT Q_METHOD_PK PRIMARY KEY ( QUALITY_METHOD_PK ) ;



CREATE TABLE PRIDEPROT.REPRO
(
  REPROCESSED_ACCESSION NVARCHAR2 (45)  NOT NULL ,
  ASSAY_FK_PK NVARCHAR2 (45)  NOT NULL
)
;


CREATE INDEX PRIDEPROT.REPRO_ASSAY_FK_PK_IDX ON PRIDEPROT.REPRO
(
  ASSAY_FK_PK ASC
)
;

ALTER TABLE PRIDEPROT.REPRO
ADD CONSTRAINT REPRO_PK PRIMARY KEY ( REPROCESSED_ACCESSION, ASSAY_FK_PK ) ;



CREATE TABLE PRIDEPROT.SCORE
(
  SCORE_PK NUMBER  NOT NULL ,
  QUALITY_METHOD_FK NUMBER  NOT NULL ,
  STAR_FK NUMBER  NOT NULL ,
  VALUE NUMBER NOT NULL
)
;


CREATE INDEX PRIDEPROT.SCORE_STAR_FK_IDX ON PRIDEPROT.SCORE
(
  STAR_FK ASC
)
;
CREATE INDEX PRIDEPROT.SCORE_QUALITY_METHOD_FK_IDX ON PRIDEPROT.SCORE
(
  QUALITY_METHOD_FK ASC
)
;

ALTER TABLE PRIDEPROT.SCORE
ADD CONSTRAINT SCORE_PK PRIMARY KEY ( SCORE_PK ) ;



CREATE TABLE PRIDEPROT.STAR
(
  STAR_PK NUMBER  NOT NULL ,
  STAR_COUNT NUMBER ,
  STAR_TYPE NVARCHAR2 (45) DEFAULT 'NONE'
)
;



ALTER TABLE PRIDEPROT.STAR
ADD CONSTRAINT STAR_TYPE
CHECK ( STAR_TYPE IN ('BRONZE', 'GOLD', 'NONE', 'SILVER'))
;


ALTER TABLE PRIDEPROT.STAR
ADD CONSTRAINT STAR_PK PRIMARY KEY ( STAR_PK ) ;




ALTER TABLE PRIDEPROT.ASSAY_CV
ADD CONSTRAINT ASSAY_CV_ASSAY_FK FOREIGN KEY
  (
    ASSAY_FK_PK
  )
REFERENCES PRIDEPROT.ASSAY
  (
    ASSAY_ACCESSION
  )
;


ALTER TABLE PRIDEPROT.ASSAY_CV
ADD CONSTRAINT ASSAY_CV_CV_PARAM_FK FOREIGN KEY
  (
    CV_PARAM_FK_PK
  )
REFERENCES PRIDEPROT.CV_PARAM
  (
    CV_TERM
  )
;


ALTER TABLE PRIDEPROT.GENE_PGRP
ADD CONSTRAINT GENE_PGRP_GENE_FK FOREIGN KEY
  (
    GENE_FK_PK
  )
REFERENCES PRIDEPROT.GENE
  (
    GENE_ACCESSION
  )
;


ALTER TABLE PRIDEPROT.GENE_PGRP
ADD CONSTRAINT GENE_PGRP_PROT_GROUP_FK FOREIGN KEY
  (
    P_GROUP_FK_PK
  )
REFERENCES PRIDEPROT.PROT_GROUP
  (
    PROT_GROUP_PK
  )
;


ALTER TABLE PRIDEPROT.PEPTIDE_MOD
ADD CONSTRAINT PEPTIDE_MOD_MOD_FK FOREIGN KEY
  (
    MOD_FK_PK
  )
REFERENCES PRIDEPROT.MOD
  (
    CV_TERM
  )
;


ALTER TABLE PRIDEPROT.PEPTIDE_MOD
ADD CONSTRAINT PEPTIDE_MOD_PEPTIDE_FK FOREIGN KEY
  (
    PEPTIDE_FK_PK
  )
REFERENCES PRIDEPROT.PEPTIDE
  (
    PEPTIDE_PK
  )
;


ALTER TABLE PRIDEPROT.PEPTIDE
ADD CONSTRAINT PEPTIDE_SCORE_FK FOREIGN KEY
  (
    SCORE_FK
  )
REFERENCES PRIDEPROT.SCORE
  (
    SCORE_PK
  )
;


ALTER TABLE PRIDEPROT.PEP_ASSAY
ADD CONSTRAINT PEP_ASSAY_ASSAY_FK FOREIGN KEY
  (
    ASSAY_FK_PK
  )
REFERENCES PRIDEPROT.ASSAY
  (
    ASSAY_ACCESSION
  )
;


ALTER TABLE PRIDEPROT.PEP_ASSAY
ADD CONSTRAINT PEP_ASSAY_PEPTIDE_FK FOREIGN KEY
  (
    PEPTIDE_FK_PK
  )
REFERENCES PRIDEPROT.PEPTIDE
  (
    PEPTIDE_PK
  )
;


ALTER TABLE PRIDEPROT.PEP_CV
ADD CONSTRAINT PEP_CV_CV_PARAM_FK FOREIGN KEY
  (
    CV_PARAM_FK_PK
  )
REFERENCES PRIDEPROT.CV_PARAM
  (
    CV_TERM
  )
;


ALTER TABLE PRIDEPROT.PEP_CV
ADD CONSTRAINT PEP_CV_PEPTIDE_FK FOREIGN KEY
  (
    PEPTIDE_FK_PK
  )
REFERENCES PRIDEPROT.PEPTIDE
  (
    PEPTIDE_PK
  )
;


ALTER TABLE PRIDEPROT.PROTEIN_GENE
ADD CONSTRAINT PROTEIN_GENE_GENE_FK FOREIGN KEY
  (
    GENE_FK_PK
  )
REFERENCES PRIDEPROT.GENE
  (
    GENE_ACCESSION
  )
;


ALTER TABLE PRIDEPROT.PROTEIN_GENE
ADD CONSTRAINT PROTEIN_GENE_PROTEIN_FK FOREIGN KEY
  (
    PROTEIN_FK_PK
  )
REFERENCES PRIDEPROT.PROTEIN
  (
    PROTEIN_ACCESSION
  )
;


ALTER TABLE PRIDEPROT.PROTEIN_MOD
ADD CONSTRAINT PROTEIN_MOD_MOD_FK FOREIGN KEY
  (
    MOD_FK_PK
  )
REFERENCES PRIDEPROT.MOD
  (
    CV_TERM
  )
;


ALTER TABLE PRIDEPROT.PROTEIN_MOD
ADD CONSTRAINT PROTEIN_MOD_PROTEIN_FK FOREIGN KEY
  (
    PROTEIN_FK_PK
  )
REFERENCES PRIDEPROT.PROTEIN
  (
    PROTEIN_ACCESSION
  )
;


ALTER TABLE PRIDEPROT.PROTEIN
ADD CONSTRAINT PROTEIN_SCORE_FK FOREIGN KEY
  (
    SCORE_FK
  )
REFERENCES PRIDEPROT.SCORE
  (
    SCORE_PK
  )
ON DELETE CASCADE
;


ALTER TABLE PRIDEPROT.PROT_PEP
ADD CONSTRAINT PROT_PEP_PEPTIDE_FK FOREIGN KEY
  (
    PEPTIDE_FK_PK
  )
REFERENCES PRIDEPROT.PEPTIDE
  (
    PEPTIDE_PK
  )
;


ALTER TABLE PRIDEPROT.PROT_PEP
ADD CONSTRAINT PROT_PEP_PROTEIN_FK FOREIGN KEY
  (
    PROTEIN_FK_PK
  )
REFERENCES PRIDEPROT.PROTEIN
  (
    PROTEIN_ACCESSION
  )
;


ALTER TABLE PRIDEPROT.PROT_PGRP
ADD CONSTRAINT PROT_PGRP_PROTEIN_FK FOREIGN KEY
  (
    PROT_FK_PK
  )
REFERENCES PRIDEPROT.PROTEIN
  (
    PROTEIN_ACCESSION
  )
;


ALTER TABLE PRIDEPROT.PROT_PGRP
ADD CONSTRAINT PROT_PGRP_PROT_GROUP_FK FOREIGN KEY
  (
    P_GROUP_FK_PK
  )
REFERENCES PRIDEPROT.PROT_GROUP
  (
    PROT_GROUP_PK
  )
;


ALTER TABLE PRIDEPROT.REPRO
ADD CONSTRAINT REPRO_ASSAY_FK FOREIGN KEY
  (
    ASSAY_FK_PK
  )
REFERENCES PRIDEPROT.ASSAY
  (
    ASSAY_ACCESSION
  )
;


ALTER TABLE PRIDEPROT.SCORE
ADD CONSTRAINT SCORE_Q_METHOD_FK FOREIGN KEY
  (
    QUALITY_METHOD_FK
  )
REFERENCES PRIDEPROT.Q_METHOD
  (
    QUALITY_METHOD_PK
  )
;


ALTER TABLE PRIDEPROT.SCORE
ADD CONSTRAINT SCORE_STAR_FK FOREIGN KEY
  (
    STAR_FK
  )
REFERENCES PRIDEPROT.STAR
  (
    STAR_PK
  )
;

-- CREATE SEQUENCE PRIDEPROT.PEPTIDE_PEPTIDE_PK_SEQ;
-- IT SHOULD START WITH 1 WHEN WE INSERT THE TEST DATA PROPERLY
CREATE SEQUENCE PRIDEPROT.PEPTIDE_PEPTIDE_PK_SEQ START WITH 10000;

-- NOCACHE AND ORDER OPTIONS ARE NOT SUPPORTED IN HSQLDB
--     NOCACHE
--     ORDER ;
--

CREATE SEQUENCE PRIDEPROT.PROT_GROUP_PROT_GROUP_PK_SEQ START WITH 10000;
-- NOCACHE AND ORDER OPTIONS ARE NOT SUPPORTED IN HSQLDB
--     NOCACHE
--     ORDER ;
--

CREATE SEQUENCE PRIDEPROT.Q_METHOD_QUALITY_METHOD_PK_SEQ START WITH 10000;
-- NOCACHE AND ORDER OPTIONS ARE NOT SUPPORTED IN HSQLDB
--     NOCACHE
--     ORDER ;

CREATE SEQUENCE PRIDEPROT.SCORE_SCORE_PK_SEQ START WITH 10000;
-- NOCACHE AND ORDER OPTIONS ARE NOT SUPPORTED IN HSQLDB
--     NOCACHE
--     ORDER ;
--

CREATE SEQUENCE PRIDEPROT.STAR_STAR_PK_SEQ START WITH 10000;
-- NOCACHE AND ORDER OPTIONS ARE NOT SUPPORTED IN HSQLDB
--     NOCACHE
--     ORDER ;
--


-- Oracle SQL Developer Data Modeler Summary Report:
--
-- CREATE TABLE                            20
-- CREATE INDEX                            24
-- ALTER TABLE                             44
-- CREATE VIEW                              0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           5
-- ALTER TRIGGER                            0
-- CREATE STRUCTURED TYPE                   0
-- CREATE COLLECTION TYPE                   0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          5
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
--
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
--
-- ERRORS                                   0
-- WARNINGS                                 0
