CREATE TABLE XXCCMS.XXCCMS_NOTIFICATION_NOTES_V (
    NOTE_ID NUMBER NOT NULL PRIMARY KEY,
    NOTIFICATION_ID NUMBER NOT NULL,
    NOTE_DATE DATE NOT NULL,
    NOTE_TEXT VARCHAR2(2000) NOT NULL,
    NOTE_BY VARCHAR2(360)
);

CREATE TABLE XXCCMS.XXCCMS_NOTIFICATION_DOCS_V (
    DOCUMENT_ID NUMBER NOT NULL PRIMARY KEY,
    NOTIFICATION_ID NUMBER NOT NULL,
    DOCUMENT_CHANNEL VARCHAR2(30) NOT NULL,
    DOCUMENT_TYPE VARCHAR2(30),
    DOCUMENT_DESCRIPTION VARCHAR2(255),
    DOCUMENT_STATUS VARCHAR2(80) NOT NULL,
    EDRMS_DOCUMENT_ID VARCHAR2(50)
);

CREATE TABLE XXCCMS.XXCCMS_NOTIFICATION_ATTMNTS_V (
    ATTACHMENT_ID NUMBER NOT NULL PRIMARY KEY,
    NOTIFICATION_ID NUMBER NOT NULL,
    ATTACHMENT_TITLE VARCHAR2(80) NOT NULL,
    ATTACHMENT_DESCRIPTION VARCHAR2(255)
);

CREATE TABLE XXCCMS.XXCCMS_NOTIFICATION_ACTIONS_V (
    NEXT_ACTION_ID NUMBER NOT NULL PRIMARY KEY,
    NOTIFICATION_ID NUMBER NOT NULL,
    NEXT_ACTION VARCHAR2(150)
);



INSERT INTO XXCCMS.XXCCMS_NOTIFICATION_NOTES_V (NOTE_ID,
                                                NOTIFICATION_ID,
                                                NOTE_DATE,
                                                NOTE_TEXT,
                                                NOTE_BY)
VALUES(1, 1,
       TO_DATE('2025-01-01', 'YYYY-MM-DD'),
       'Here is the body of text for this note',
       'Jamie Briggs');

INSERT INTO XXCCMS.XXCCMS_NOTIFICATION_DOCS_V (DOCUMENT_ID,
                                               NOTIFICATION_ID,
                                               DOCUMENT_CHANNEL,
                                               DOCUMENT_TYPE,
                                               DOCUMENT_DESCRIPTION,
                                               DOCUMENT_STATUS,
                                               EDRMS_DOCUMENT_ID)
VALUES(1,
       1,
       'Channel',
       'Type',
       'Document description',
       'Status',
       'EDRMS ID');

INSERT INTO XXCCMS.XXCCMS_NOTIFICATION_ATTMNTS_V (NOTIFICATION_ID,
                                                  ATTACHMENT_ID,
                                                  ATTACHMENT_TITLE,
                                                  ATTACHMENT_DESCRIPTION)
VALUES (1,
        1,
        'Attachment Title',
        'Attachment description');

INSERT INTO XXCCMS.XXCCMS_NOTIFICATION_ACTIONS_V (NOTIFICATION_ID,
                                                  NEXT_ACTION_ID,
                                                  NEXT_ACTION)
VALUES (1, 1,
        'Action to complete');