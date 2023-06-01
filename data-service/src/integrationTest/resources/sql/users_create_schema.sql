CREATE TABLE XXCCMS_USER_FIRMS_V (
                                     PROVIDERFIRM_ID NUMBER(10,0) PRIMARY KEY,
                                     PROVIDER_NAME VARCHAR2(255),
                                     USER_END_DATE TIMESTAMP
);


CREATE TABLE XXCCMS_USERS_V (
                                USER_ID INT,
                                USER_LOGIN_ID VARCHAR2(255) PRIMARY KEY,
                                USER_PARTY_ID INT,
                                USER_NAME VARCHAR2(255),
                                USER_TYPE VARCHAR2(255),
                                PROVIDER_NAME VARCHAR2(255),
                                PROVIDERFIRM_ID INT,
                                FOREIGN KEY (PROVIDERFIRM_ID) REFERENCES XXCCMS_USER_FIRMS_V (PROVIDERFIRM_ID)
);

CREATE TABLE XXCCMS_USER_ROLES_V (
                                     USER_LOGIN_ID VARCHAR2(255),
                                     FUNCTION VARCHAR2(255),
                                     PRIMARY KEY (USER_LOGIN_ID, FUNCTION),
                                     FOREIGN KEY (USER_LOGIN_ID) REFERENCES XXCCMS_USERS_V (USER_LOGIN_ID)
);