CREATE TABLE XXCCMS.XXCCMS_PROVIDERFIRMS_V (
                                        PROVIDERFIRM_ID NUMBER(15) PRIMARY KEY,
                                        PROVIDERFIRM_NAME VARCHAR2(360)
);

CREATE TABLE XXCCMS.XXCCMS_PROVIDER_OFFICES_V (
                                           OFFICE_ID NUMBER(15) PRIMARY KEY,
                                           OFFICE_NAME VARCHAR2(4000),
                                           PROVIDERFIRM_ID NUMBER(15)
);

CREATE TABLE XXCCMS.XXCCMS_FEE_EARNERS_V (
                                      CONTACT_ID NUMBER(15) PRIMARY KEY,
                                      CONTACT_NAME VARCHAR2(255) NOT NULL,
                                      PROVIDERFIRM_ID NUMBER(15)
);

CREATE TABLE XXCCMS.XXCCMS_FEE_EARNER_OFFICES_V (
                                      CONTACT_ID NUMBER(15) PRIMARY KEY,
                                      CONTACT_NAME VARCHAR2(255) NOT NULL,
                                      PROVIDERFIRM_ID NUMBER(15) NOT NULL,
                                      OFFICE_ID NUMBER(15) NOT NULL,
                                      OFFICE_NAME VARCHAR2(255) NOT NULL
);

CREATE TABLE XXCCMS.XXCCMS_PROVIDERCONTACTS_V (
                                      CONTACT_ID NUMBER(15) PRIMARY KEY,
                                      CONTACT_NAME VARCHAR2(255) NOT NULL,
                                      PROVIDERFIRM_ID NUMBER(15),
                                      PROVIDER_FIRM_NAME VARCHAR2(360)
);