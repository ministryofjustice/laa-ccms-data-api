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