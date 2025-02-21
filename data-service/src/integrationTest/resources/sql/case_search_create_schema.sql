CREATE TABLE XXCCMS.XXCCMS_CASE_SEARCH_V (
    CASE_PARTY_ID NUMBER(15,0) NOT NULL PRIMARY KEY,
    APP_OR_CERT_SR_ID NUMBER,
    LSC_CASE_REFERENCE VARCHAR2(360) NOT NULL,
    CIS_CASE_REFERENCE VARCHAR2(150),
    CLIENT_PARTY_ID NUMBER (15, 0),
    PERSON_FIRST_NAME VARCHAR2(150),
    PERSON_LAST_NAME VARCHAR2(150),
    PROVIDER_CASE_REFERENCE VARCHAR2(150),
    PROVIDER_FIRM_PARTY_ID NUMBER(15, 0),
    PROVIDER_OFFICE_PARTY_ID NUMBER(15, 0),
    FEE_EARNER_PARTY_ID NUMBER(15, 0),
    FEE_EARNER VARCHAR2(360),
    CATEGORY_OF_LAW VARCHAR2(150),
    CATEGORY_OF_LAW_DESC VARCHAR(80),
    ACTUAL_CASE_STATUS VARCHAR2(30),
    DISPLAY_CASE_STATUS VARCHAR2(150)
);


INSERT INTO XXCCMS.XXCCMS_CASE_SEARCH_V (CASE_PARTY_ID,
                                         APP_OR_CERT_SR_ID,
                                         LSC_CASE_REFERENCE,
                                         CIS_CASE_REFERENCE,
                                         CLIENT_PARTY_ID,
                                         PERSON_FIRST_NAME,
                                         PERSON_LAST_NAME,
                                         PROVIDER_CASE_REFERENCE,
                                         PROVIDER_FIRM_PARTY_ID,
                                         PROVIDER_OFFICE_PARTY_ID,
                                         FEE_EARNER_PARTY_ID,
                                         FEE_EARNER,
                                         CATEGORY_OF_LAW,
                                         CATEGORY_OF_LAW_DESC,
                                         ACTUAL_CASE_STATUS,
                                         DISPLAY_CASE_STATUS)
VALUES (1001, -- CASE_PARTY_ID
        2001, -- APP_OR_CERT_SR_ID
        '3001', -- LSC_CASE_REFERENCE
        '4001', -- CIS_CASE_REFERENCE
        5001, -- CLIENT_PARTY_ID
        'First', -- PERSON_FIRST_NAME
        'Last', -- PERSON_LAST_NAME
        '6001', -- PROVIDER_CASE_REFERENCE
        100, -- PROVIDER_FIRM_PARTY_ID
        8001, -- PROVIDER_OFFICE_PARTY_ID
        9001, -- FEE_EARNER_PARTY_ID
        'Fee One', -- FEE_EARNER
        'Category One', -- CATEGORY_OF_LAW
        'Category One Description', -- CATEGORY_OF_LAW_DESC
        'ONE', -- ACTUAL_CASE_STATUS
        'Display Status One' -- DISPLAY_CASE_STATUS
       );


INSERT INTO XXCCMS.XXCCMS_CASE_SEARCH_V (CASE_PARTY_ID,
                                         APP_OR_CERT_SR_ID,
                                         LSC_CASE_REFERENCE,
                                         CIS_CASE_REFERENCE,
                                         CLIENT_PARTY_ID,
                                         PERSON_FIRST_NAME,
                                         PERSON_LAST_NAME,
                                         PROVIDER_CASE_REFERENCE,
                                         PROVIDER_FIRM_PARTY_ID,
                                         PROVIDER_OFFICE_PARTY_ID,
                                         FEE_EARNER_PARTY_ID,
                                         FEE_EARNER,
                                         CATEGORY_OF_LAW,
                                         CATEGORY_OF_LAW_DESC,
                                         ACTUAL_CASE_STATUS,
                                         DISPLAY_CASE_STATUS)
VALUES (1002, -- CASE_PARTY_ID
        2002, -- APP_OR_CERT_SR_ID
        '3002', -- LSC_CASE_REFERENCE
        '4002', -- CIS_CASE_REFERENCE
        5002, -- CLIENT_PARTY_ID
        'First Two', -- PERSON_FIRST_NAME
        'Last Two', -- PERSON_LAST_NAME
        '6002', -- PROVIDER_CASE_REFERENCE
        100, -- PROVIDER_FIRM_PARTY_ID
        8002, -- PROVIDER_OFFICE_PARTY_ID
        9002, -- FEE_EARNER_PARTY_ID
        'Fee Two', -- FEE_EARNER
        'Category Two', -- CATEGORY_OF_LAW
        'Category Two Description', -- CATEGORY_OF_LAW_DESC
        'TWO', -- ACTUAL_CASE_STATUS
        'Display Status Two' -- DISPLAY_CASE_STATUS
       );
