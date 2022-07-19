CREATE TABLE guestbook_message (
    message_id NUMBER(10) PRIMARY KEY,
    guest_name VARCHAR2(50 CHAR) NOT NULL,
    password VARCHAR2(10 CHAR) NOT NULL,
    message VARCHAR2(1000 CHAR) NOT NULL
);

CREATE SEQUENCE questbook_seq 
START with 1
INCREMENT BY 1
MAXVALUE 99999
NOCYCLE;

SELECT *  
FROM (SELECT ROWNUM,  
             m.*,  
             FLOOR((ROWNUM - 1) / 10 + 1) page  
      FROM (
             SELECT * FROM guestbook_message  
             ORDER BY message_id  ASC
           ) m  
      )  
WHERE page = 1;