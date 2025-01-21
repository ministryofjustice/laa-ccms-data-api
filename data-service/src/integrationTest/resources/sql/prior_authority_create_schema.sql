CREATE TABLE XXCCMS.XXCCMS_PRIOR_AUTHORITY_TYPE_V (
        CODE	VARCHAR2(30) PRIMARY KEY,
        DESCRIPTION	VARCHAR2(80),
        VALUE_REQUIRED_FLAG	VARCHAR2(150)
);

CREATE TABLE XXCCMS.XXCCMS_PRIOR_AUTH_DETAILS_V (
        CODE	VARCHAR2(30),
        DESCRIPTION	VARCHAR2(240),
        DATA_TYPE	VARCHAR2(150),
        LOV_CODE	VARCHAR2(150),
        MANDATORY_FLAG	VARCHAR2(150),
        PRIOR_AUTHORITY_TYPE_CODE	VARCHAR2(150)
);
