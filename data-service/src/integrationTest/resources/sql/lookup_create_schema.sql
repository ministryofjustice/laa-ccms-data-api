CREATE TABLE XXCCMS_COMMON_LOV_V (
                                     LOV_TYPE VARCHAR2(255) NOT NULL,
                                     CODE VARCHAR2(255) NOT NULL,
                                     DESCRIPTION VARCHAR2(255),
                                     START_DATE_ACTIVE TIMESTAMP,
                                     ATTRIBUTE11 VARCHAR2(255),
                                     ATTRIBUTE12 VARCHAR2(255),
                                     ENABLED_FLAG VARCHAR2(255),
                                     DEFAULT_CODE VARCHAR2(255),
                                     PRIMARY KEY (LOV_TYPE, CODE)
);

CREATE TABLE XXCCMS_APP_AMEND_TYPES_V (
                                          APP_TYPE_CODE          VARCHAR2(255 CHAR) PRIMARY KEY,
                                          APP_TYPE_DESCRIPTION   VARCHAR2(255 CHAR),
                                          COST_LIMIT_CAP         VARCHAR2(255 CHAR),
                                          DEVOLVED_POWERS_IND    VARCHAR2(255 CHAR),
                                          DEFAULT_LAR_SCOPE_FLAG VARCHAR2(255 CHAR)
);

CREATE TABLE XXCCMS_APP_CASE_STATUS_V (
                                          STATUS_CODE VARCHAR2(15) PRIMARY KEY,
                                          STATUS_DESCRIPTION VARCHAR2(30),
                                          COPY_ALLOWED_IND VARCHAR2(1)
);

CREATE TABLE XXCCMS_COUNTRY_V (
                                  CODE VARCHAR2(255) NOT NULL,
                                  DESCRIPTION VARCHAR2(255),
                                  PRIMARY KEY (CODE, DESCRIPTION)
);

CREATE TABLE XXCCMS_OUTCOME_RESULTS_V (
                                OUTCOME_RESULT	VARCHAR2(30),
                                OUTCOME_RESULT_DESCRIPTION	VARCHAR2(240),
                                ENABLED_FLAG	VARCHAR2(1),
                                PROCEEDING_CODE	VARCHAR2(30) NOT NULL,
                                OUTCOME_RESULT_LOV	VARCHAR2(150)
);

CREATE TABLE XXCCMS_STAGE_END_V (
                                STAGE_END	VARCHAR2(30),
                                STAGE_END_DESCRIPTION	VARCHAR2(240),
                                ENABLED_FLAG	VARCHAR2(1),
                                PROCEEDING_CODE	VARCHAR2(30),
                                STAGE_END_LOV	VARCHAR2(150)
);

CREATE TABLE XXCCMS_PER_RELTOCASE_V (
                                    CODE	        VARCHAR2(50),
                                    DESCRIPTION	    VARCHAR2(240),
                                    DEFAULT_CODE	VARCHAR2(1),
                                    OPPONENT_IND	VARCHAR2(1),
                                    DOB_MANDATORY	VARCHAR2(1),
                                    COPY_PARTY	    VARCHAR2(1)
);

CREATE TABLE XXCCMS_ORG_RELTOCASE_V (
                                    CODE          VARCHAR2(50),
                                    DESCRIPTION   VARCHAR2(240),
                                    DEFAULT_CODE  VARCHAR2(1),
                                    OPPONENT_IND  VARCHAR2(1),
                                    COPY_PARTY    VARCHAR2(1)
);

CREATE TABLE XXCCMS_AWARD_TYPE_V (
                                    CODE	VARCHAR2(30),
                                    DESCRIPTION	VARCHAR2(240),
                                    AWARD_TYPE	VARCHAR2(150),
                                    START_DATE_ACTIVE	DATE,
                                    END_DATE_ACTIVE	DATE,
                                    ENABLED_FLAG	VARCHAR2(1)
);

CREATE TABLE XXCCMS_CATEGORY_OF_LAW_V (
                                    CATEGORY_OF_LAW_CODE	VARCHAR2(30),
                                    MATTER_TYPE_DESCRIPTION	VARCHAR2(80),
                                    COPY_COST_LIMIT_IND	VARCHAR2(150)
);

CREATE TABLE XXCCMS_EVIDENCE_DOC_TYPE_V (
                                          LOV_TYPE	VARCHAR2(30),
                                          CODE VARCHAR2(30),
                                          DESCRIPTION	VARCHAR2(80),
                                          START_DATE_ACTIVE	DATE,
                                          END_DATE_ACTIVE	DATE
);

CREATE TABLE XXCCMS_PUI_OPA_ENTITIES_V (
                                           OPA_ENTITY_NAME      		VARCHAR(150),
                                           OPA_ENTITY_DISPLAY_NAME	VARCHAR(80),
                                           RELATIONSHIP_TEXT		VARCHAR(150),
                                           REV_RELATIONSHIP_TEXT	VARCHAR(150),
                                           INSTANCE_NAME_COLUMN		VARCHAR(150),
                                           PRIMARY_KEY 		VARCHAR(150),
                                           ENTITY_LEVEL      VARCHAR(1),
                                           PARENT_ENTITY_CODE    VARCHAR(150),
                                           ENTITY_DISPLAY_SEQUENCE  VARCHAR(50)

);

CREATE TABLE XXCCMS_PUI_OPA_ATTRIBUTE_V (
                                            OPA_ENTITY_NAME      		VARCHAR2(50),
                                            OPA_ENTITY_DISPLAY_NAME	VARCHAR2(150),
                                            OPA_ATTRIBUTE_NAME		VARCHAR2(50),
                                            OPA_ATTRIBUTE_DISPLAY_NAME	VARCHAR2(150),
                                            DISPLAY_SEQUENCE		VARCHAR(50),
                                            ENTITY_DISPLAY_SEQUENCE    VARCHAR2(50),
                                            SUMMARY_DISPLAY_FLAG 		VARCHAR(1)
);

CREATE TABLE XXCCMS_PROVIDER_REQTYPES_V (
                                            REQUEST_TYPE VARCHAR(50) PRIMARY KEY,
                                            REQUEST_NAME VARCHAR(100),
                                            CASE_RELATED_FLAG CHAR(1),
                                            ADDITIONAL_INFO_PROMPT VARCHAR(255),
                                            TASK_TYPE_ID VARCHAR(150),
                                            FILE_UPLD_ENABLED CHAR(1),
                                            ACCESS_FUNC_CODE VARCHAR(50),
                                            FILE_UPLD_PROMPT VARCHAR(255)
);

CREATE TABLE XXCCMS_PROVIDER_REQUEST_DATA_V (
                                            REQUEST_TYPE VARCHAR(255) NOT NULL,
                                            DATA_ITEM_CODE VARCHAR(255) NOT NULL,
                                            DATA_ITEM_LABEL VARCHAR(255),
                                            DATA_ITEM_TYPE VARCHAR(255) NOT NULL,
                                            MANDATORY_FLAG VARCHAR(255) NOT NULL,
                                            DATA_ITEM_SEQ VARCHAR(255) NOT NULL,
                                            LOV_LOOKUP_TYPE VARCHAR(255),
                                            PRIMARY KEY (REQUEST_TYPE, DATA_ITEM_CODE)
);



