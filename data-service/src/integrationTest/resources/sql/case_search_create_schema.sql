CREATE TABLE XXCCMS.XXCCMS_CASE_SEARCH_V (
    LSC_CASE_REFERENCE VARCHAR2(360) NOT NULL PRIMARY KEY,
    PERSON_FIRST_NAME VARCHAR2(150),
    PERSON_LAST_NAME VARCHAR2(150),
    PROVIDER_CASE_REFERENCE VARCHAR2(150),
    FIRM_PARTY_ID NUMBER(15, 0),
    FIRM_OFFICE_ID NUMBER(15, 0),
    FEE_EARNER_ID NUMBER(15, 0),
    FEE_EARNER_NAME VARCHAR2(360),
    CATEGORY_OF_LAW_DESCRIPTION VARCHAR(80),
    ACTUAL_CASE_STATUS VARCHAR2(30),
    DISPLAY_CASE_STATUS VARCHAR2(150)
);


INSERT INTO XXCCMS.XXCCMS_CASE_SEARCH_V (LSC_CASE_REFERENCE,
                                         PERSON_FIRST_NAME,
                                         PERSON_LAST_NAME,
                                         PROVIDER_CASE_REFERENCE,
                                         FIRM_PARTY_ID,
                                         FIRM_OFFICE_ID,
                                         FEE_EARNER_ID,
                                         FEE_EARNER_NAME,
                                         CATEGORY_OF_LAW_DESCRIPTION,
                                         ACTUAL_CASE_STATUS,
                                         DISPLAY_CASE_STATUS)
VALUES ('3001', -- LSC_CASE_REFERENCE
        'First', -- PERSON_FIRST_NAME
        'Last', -- PERSON_LAST_NAME
        '6001', -- PROVIDER_CASE_REFERENCE
        100, -- PROVIDER_FIRM_PARTY_ID
        8001, -- PROVIDER_OFFICE_PARTY_ID
        9001, -- FEE_EARNER_PARTY_ID
        'Fee One', -- FEE_EARNER
        'Category One Description', -- CATEGORY_OF_LAW_DESCRIPTION
        'ONE', -- ACTUAL_CASE_STATUS
        'Display Status One' -- DISPLAY_CASE_STATUS
       );


INSERT INTO XXCCMS.XXCCMS_CASE_SEARCH_V (LSC_CASE_REFERENCE,
                                         PERSON_FIRST_NAME,
                                         PERSON_LAST_NAME,
                                         PROVIDER_CASE_REFERENCE,
                                         FIRM_PARTY_ID,
                                         FIRM_OFFICE_ID,
                                         FEE_EARNER_ID,
                                         FEE_EARNER_NAME,
                                         CATEGORY_OF_LAW_DESCRIPTION,
                                         ACTUAL_CASE_STATUS,
                                         DISPLAY_CASE_STATUS)
VALUES ('3002', -- LSC_CASE_REFERENCE
        'First Two', -- PERSON_FIRST_NAME
        'Last Two', -- PERSON_LAST_NAME
        '6002', -- PROVIDER_CASE_REFERENCE
        100, -- FIRM_PARTY_ID
        8002, -- FIRM_OFFICE_ID
        9002, -- FEE_EARNER_ID
        'Fee Two', -- FEE_EARNER_NAME
        'Category Two Description', -- CATEGORY_OF_LAW_DESCRIPTION
        'TWO', -- ACTUAL_CASE_STATUS
        'Display Status Two' -- DISPLAY_CASE_STATUS
       );
