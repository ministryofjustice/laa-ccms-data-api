CREATE TABLE XXCCMS_USER_FIRMS_V (
                                     USER_LOGIN_ID VARCHAR2(100),
                                     PROVIDERFIRM_ID NUMBER(15),
                                     PROVIDER_NAME VARCHAR2(360),
                                     USER_END_DATE TIMESTAMP,
                                     PRIMARY_FLAG VARCHAR2(1),
                                     CONSTRAINT XXCCMS_USER_FIRMS_PK PRIMARY KEY (USER_LOGIN_ID, PROVIDERFIRM_ID)
);


CREATE TABLE XXCCMS_USERS_V (
                                USER_ID NUMBER(15),
                                USER_LOGIN_ID VARCHAR2(100) PRIMARY KEY,
                                USER_PARTY_ID NUMBER(15),
                                USER_NAME VARCHAR2(360),
                                USER_TYPE VARCHAR2(8),
                                PROVIDER_NAME VARCHAR2(360),
                                PROVIDERFIRM_ID NUMBER(15),
                                FOREIGN KEY (USER_LOGIN_ID, PROVIDERFIRM_ID) REFERENCES XXCCMS_USER_FIRMS_V (USER_LOGIN_ID, PROVIDERFIRM_ID)
);

CREATE TABLE XXCCMS_USER_ROLES_V (
                                     USER_LOGIN_ID VARCHAR2(100),
                                     FUNCTION VARCHAR2(2000),
                                     PRIMARY KEY (USER_LOGIN_ID, FUNCTION),
                                     FOREIGN KEY (USER_LOGIN_ID) REFERENCES XXCCMS_USERS_V (USER_LOGIN_ID)
);