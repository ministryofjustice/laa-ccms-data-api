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