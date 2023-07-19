CREATE TABLE XXCCMS_PROVIDERFIRMS_V (
                                        PROVIDERFIRM_ID NUMBER(15) PRIMARY KEY,
                                        PROVIDERFIRM_NAME VARCHAR2(360)
);

CREATE TABLE XXCCMS_PROVIDER_OFFICES_V (
                                           OFFICE_ID NUMBER(15) PRIMARY KEY,
                                           OFFICE_NAME VARCHAR2(4000),
                                           PROVIDERFIRM_ID NUMBER(15)
);