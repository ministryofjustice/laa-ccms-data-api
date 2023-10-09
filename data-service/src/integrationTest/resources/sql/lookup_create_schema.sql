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