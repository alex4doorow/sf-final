/*-------------------------------------------------------------*/
/*                           SEQUENCE                          */
/*-------------------------------------------------------------*/

CREATE SEQUENCE IF NOT EXISTS SF_SEQUENCE START 1;

/*-------------------------------------------------------------*/
/*                 ПОЛЬЗОВАТЕЛЬСКИЕ СПРАВОЧНИКИ                */
/*-------------------------------------------------------------*/

CREATE TABLE IF NOT EXISTS D_SUB_SYSTEMS (
  ID BIGINT PRIMARY KEY NOT NULL,
  CODE VARCHAR(64) NOT NULL,
  NAME VARCHAR(256) NOT NULL,
  REC_STATUS CHAR(1) NOT NULL,
  CR_DT TIMESTAMP NOT NULL,
  UPD_DT TIMESTAMP NOT NULL);

COMMENT ON TABLE D_SUB_SYSTEMS IS 'SUB_SYSTEMS';
COMMENT ON COLUMN D_SUB_SYSTEMS.ID IS 'Primary Key';
COMMENT ON COLUMN D_SUB_SYSTEMS.CODE IS 'Code of sub-system';
COMMENT ON COLUMN D_SUB_SYSTEMS.NAME IS 'Name of sub-system';
COMMENT ON COLUMN D_SUB_SYSTEMS.REC_STATUS IS 'A – Active, D - Deleted';
COMMENT ON COLUMN D_SUB_SYSTEMS.CR_DT IS 'Creation date';
COMMENT ON COLUMN D_SUB_SYSTEMS.UPD_DT IS 'Modification date';

--CHECK CONSTRAINTS
ALTER TABLE D_SUB_SYSTEMS ADD CONSTRAINT CC_SUBSYS_REC_STATUS CHECK (REC_STATUS IN ('A', 'D'));