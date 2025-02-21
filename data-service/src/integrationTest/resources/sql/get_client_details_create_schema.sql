CREATE TABLE XXCCMS.XXCCMS_GET_CLIENT_DETAILS_V (
    CLIENT_REFERENCE_NUMBER NUMBER(15, 0) NOT NULL PRIMARY KEY,
    TITLE VARCHAR2(30),
    FIRSTNAME VARCHAR2(150),
    SURNAME VARCHAR2(150),
    SURNAME_AT_BIRTH VARCHAR2(150),
    DATE_OF_BIRTH DATE,
    GENDER VARCHAR2(30),
    -- CONTACT_TYPE SYS.XMLTYPE(64) --
    COUNTRY_OF_ORIGIN VARCHAR2(3),
    MARITAL_STATUS VARCHAR2(30),
    -- CONTACTS XML SYS.XMLTYPE(64) --
    ADDRESS CLOB,
    CORRESPONDENCE_METHOD VARCHAR2(150),
    DISABILITY_TYPE VARCHAR2(150),
    CORRESPONDENCE_LANGUAGE VARCHAR2(150),
    ETHNIC_MONITORING VARCHAR2(150),
    NO_FIX_ABODE VARCHAR2(150),
    NI_NUMBER VARCHAR2(60),
    HOME_OFFICE_NUMBER VARCHAR2(150)
    -- CASE_REFERENCE XML SYS.XMLTYPE(64)
);


INSERT INTO XXCCMS.XXCCMS_GET_CLIENT_DETAILS_V (CLIENT_REFERENCE_NUMBER, TITLE, FIRSTNAME, SURNAME,
                                                SURNAME_AT_BIRTH,
                                                DATE_OF_BIRTH, GENDER, COUNTRY_OF_ORIGIN,
                                                MARITAL_STATUS,
                                                CORRESPONDENCE_METHOD, DISABILITY_TYPE,
                                                CORRESPONDENCE_LANGUAGE,
                                                ETHNIC_MONITORING, NO_FIX_ABODE, NI_NUMBER,
                                                HOME_OFFICE_NUMBER)
VALUES (100000000000001, 'Mr.', 'John', 'Doe', 'Smithson',
        TO_DATE('1985-06-15', 'YYYY-MM-DD'), 'Male', 'USA', 'Single',
        'Email', 'None', 'English',
        'Not Recorded', 'No', 'AB123456C', 'HO123456');

INSERT INTO XXCCMS.XXCCMS_GET_CLIENT_DETAILS_V (CLIENT_REFERENCE_NUMBER, TITLE, FIRSTNAME, SURNAME,
                                                SURNAME_AT_BIRTH,
                                                DATE_OF_BIRTH, GENDER, COUNTRY_OF_ORIGIN,
                                                MARITAL_STATUS,
                                                CORRESPONDENCE_METHOD, DISABILITY_TYPE,
                                                CORRESPONDENCE_LANGUAGE,
                                                ETHNIC_MONITORING, NO_FIX_ABODE, NI_NUMBER,
                                                HOME_OFFICE_NUMBER)
VALUES (100000000000002, 'Ms.', 'Jane', 'Roe', 'Smith',
        TO_DATE('1990-03-21', 'YYYY-MM-DD'), 'Female', 'CAN', 'Married',
        'Phone', 'Visual Impairment', 'French',
        'Caucasian', 'No', 'CD123654E', 'HO987654');