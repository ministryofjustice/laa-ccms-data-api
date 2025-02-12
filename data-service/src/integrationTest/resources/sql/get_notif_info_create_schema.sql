CREATE TABLE XXCCMS.XXCCMS_GET_NOTIF_INFO_V (
    NOTIFICATION_ID NUMBER NOT NULL PRIMARY KEY,
    USER_ID VARCHAR2(100),
    USER_LOGIN_ID VARCHAR2(100),
    PROVIDERFIRM_ID NUMBER NOT NULL,
    DATE_ASSIGNED DATE,
    SUBJECT VARCHAR2(320),
    DUE_DATE DATE,
    ASSIGNED_TO VARCHAR2(360),
    STATUS VARCHAR2(150),
    LSC_CASE_REF_REFERENCE VARCHAR2(360),
    PROVIDER_CASE_REFERENCE VARCHAR2(150),
    CLIENT_NAME VARCHAR2(301),
    FEE_EARNER VARCHAR2(360),
    PERSON_LAST_NAME VARCHAR2(150),
    FEE_EARNER_PARTY_ID NUMBER,
    ACTION_NOTIFICATION_IND VARCHAR2(150),
    IS_OPEN VARCHAR2(5));


INSERT INTO XXCCMS.XXCCMS_GET_NOTIF_INFO_V (NOTIFICATION_ID,
                                            USER_ID,
                                            USER_LOGIN_ID,
                                            PROVIDERFIRM_ID,
                                            DATE_ASSIGNED,
                                            SUBJECT,
                                            DUE_DATE,
                                            ASSIGNED_TO,
                                            STATUS,
                                            LSC_CASE_REF_REFERENCE,
                                            PROVIDER_CASE_REFERENCE,
                                            CLIENT_NAME,
                                            FEE_EARNER,
                                            PERSON_LAST_NAME,
                                            FEE_EARNER_PARTY_ID,
                                            ACTION_NOTIFICATION_IND,
                                            IS_OPEN)
VALUES (1, -- NOTIFICATION_ID
        'test_user', -- USER_ID
        'test_login', -- USER_LOGIN_ID
        10, -- PROVIDERFIRM_ID
        TO_DATE('2025-01-01', 'YYYY-MM-DD'), -- DATE_ASSIGNED
        'Subject', -- SUBJECT
        TO_DATE('2027-01-01', 'YYYY-MM-DD'), -- DUE_DATE
        'JBriggs', -- ASSIGNED_TO
        'open', -- STATUS
        '1001', -- LSC_CASE_REF_REFERENCE
        'First Case Reference', -- PROVIDER_CASE_REFERENCE
        'Jamie Briggs', -- CLIENT_NAME
        'Fee', -- FEE_EARNER
        'Briggs', -- PERSON_LAST_NAME
        3001, -- FEE_EARNER_PARTY_ID
        'N', -- ACTION_NOTIFICATION_IND
        'true' -- IS_OPEN
);

INSERT INTO XXCCMS.XXCCMS_GET_NOTIF_INFO_V (NOTIFICATION_ID,
                                            USER_ID,
                                            USER_LOGIN_ID,
                                            PROVIDERFIRM_ID,
                                            DATE_ASSIGNED,
                                            SUBJECT,
                                            DUE_DATE,
                                            ASSIGNED_TO,
                                            STATUS,
                                            LSC_CASE_REF_REFERENCE,
                                            PROVIDER_CASE_REFERENCE,
                                            CLIENT_NAME,
                                            FEE_EARNER,
                                            PERSON_LAST_NAME,
                                            FEE_EARNER_PARTY_ID,
                                            ACTION_NOTIFICATION_IND,
                                            IS_OPEN)
VALUES (2, -- NOTIFICATION_ID
        'test_user', -- USER_ID
        'test_login', -- USER_LOGIN_ID
        10, -- PROVIDERFIRM_ID
        TO_DATE('2026-01-01', 'YYYY-MM-DD'), -- DATE_ASSIGNED
        'Subject', -- SUBJECT
        TO_DATE('2027-01-01', 'YYYY-MM-DD'), -- DUE_DATE
        'SMonday', -- ASSIGNED_TO
        'open', -- STATUS
        '2100', -- LSC_CASE_REF_REFERENCE
        'Second Case Reference', -- PROVIDER_CASE_REFERENCE
        'Ski Monday', -- CLIENT_NAME
        'Fee', -- FEE_EARNER
        'Bri-Monday', -- PERSON_LAST_NAME
        3002, -- FEE_EARNER_PARTY_ID
        'O', -- ACTION_NOTIFICATION_IND
        'false' -- IS_OPEN
       );