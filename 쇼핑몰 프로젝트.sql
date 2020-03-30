DROP TABLE ADMIN_TB;
DROP TABLE QNA_TB;
DROP TABLE BASKETS_TB;
DROP TABLE BOARD_TB;
DROP TABLE REVIEWS_TB;
DROP TABLE ORDER_DETAIL_TB;
DROP TABLE ORDERS_TB;
DROP TABLE MEMBERS_TB;
DROP TABLE PRODUCTS_TB;
DROP TABLE CATEGORY_TB;

select * from admin_tb;
select * from members_tb;
select * from products_tb order by pd_no desc;
select * from baskets_tb;
select * from orders_tb;
select * from order_detail_tb;
select * from reviews_tb;

delete from order_detail_tb;
delete from orders_tb;
delete from reviews_tb;
commit;
rollback;


SELECT O.ORD_NO, O.ORD_DT, P.PD_NM, O.ORD_PRICE, O.ORD_STATUS
FROM ORDERS_TB O, ORDER_DETAIL_TB OD, PRODUCTS_TB P
WHERE O.ORD_NO = OD.ORD_NO AND OD.PD_NO = P.PD_NO AND O.MB_ID='helloman'
ORDER BY O.ORD_NO DESC;

SELECT P.PD_IMG, P.PD_NM, OD.ORDDT_QTY BSK_QTY, OD.ORDDT_PRICE PD_TAG
FROM ORDER_DETAIL_TB OD, PRODUCTS_TB P
WHERE OD.PD_NO = P.PD_NO AND OD.ORD_NO = 2;

delete from products_tb where pd_no=5;
commit;

CREATE TABLE ADMIN_TB(
    ADM_ID          VARCHAR2(15)        PRIMARY KEY,
    ADM_PW          VARCHAR2(60)        NOT NULL,
    ADM_NM          VARCHAR2(30)        NOT NULL,
    ADM_CONDT       DATE                DEFAULT SYSDATE
);

CREATE TABLE MEMBERS_TB(
    MB_ID         VARCHAR2(15)        PRIMARY KEY,
    MB_PW         VARCHAR2(60)        NOT NULL,
    MB_NM         VARCHAR2(30)        NOT NULL,
    MB_NICK       VARCHAR2(20)        NOT NULL UNIQUE,
    MB_PHONE      VARCHAR2(15)        NOT NULL,
    MB_EMAIL      VARCHAR2(50)        NOT NULL,
    MB_ZIP        CHAR(5)             NOT NULL,
    MB_ADDR       VARCHAR2(50)        NOT NULL,
    MB_DEADDR     VARCHAR2(50)        NOT NULL,
    MB_MILE       NUMBER              DEFAULT 0,
    MB_REGDT      DATE                DEFAULT SYSDATE,
    MB_CONDT      DATE                NULL,
    MB_AUTHKEY    VARCHAR2(60)        NOT NULL,
    MB_AUTH       CHAR(1)             DEFAULT 'N'
);

CREATE TABLE CATEGORY_TB(
    CTGY_CD         NUMBER        PRIMARY KEY,
    CTGY_PARENT     NUMBER,
    CTGY_NM         VARCHAR2(50)        UNIQUE,
    FOREIGN KEY (CTGY_PARENT) REFERENCES CATEGORY_TB (CTGY_CD)
);
CREATE TABLE PRODUCTS_TB(
    PD_NO           NUMBER              PRIMARY KEY,
    CTGY_PTCD       NUMBER              NOT NULL,
    CTGY_PTNM       VARCHAR2(50)        NOT NULL,
    CTGY_CD         NUMBER              NOT NULL,
    CTGY_NM         VARCHAR2(50)        NOT NULL,
    PD_NM           VARCHAR2(50)        NOT NULL,
    PD_TAG          NUMBER              NOT NULL,
    PD_SALE         NUMBER              DEFAULT 0,
    PD_STATUS       CHAR(1)             DEFAULT 'N',
    PD_IMG          VARCHAR2(100)       NULL,
    PD_DETL         VARCHAR2(4000)      NOT NULL,
    PD_STOCK        NUMBER              NOT NULL,
    PD_ENLDT        DATE                DEFAULT SYSDATE,
    PD_UPDDT        DATE                DEFAULT SYSDATE,
    FOREIGN KEY (CTGY_CD) REFERENCES CATEGORY_TB (CTGY_CD),
    FOREIGN KEY (CTGY_NM) REFERENCES CATEGORY_TB (CTGY_NM)
);

CREATE TABLE BASKETS_TB(
    MB_ID           VARCHAR2(15),
    PD_NO           NUMBER,
    BSK_QTY         NUMBER              NOT NULL,
    PRIMARY KEY (MB_ID, PD_NO),
    FOREIGN KEY (MB_ID) REFERENCES MEMBERS_TB (MB_ID),
    FOREIGN KEY (PD_NO) REFERENCES PRODUCTS_TB (PD_NO)
);


CREATE TABLE ORDERS_TB(
    ORD_NO          NUMBER              PRIMARY KEY,
    MB_ID           VARCHAR2(15)        NOT NULL,
    ORD_NM          VARCHAR2(30)        NOT NULL,
    ORD_ZIP         CHAR(5)             NOT NULL,
    ORD_ADDR        VARCHAR2(50)        NOT NULL,
    ORD_DEADDR      VARCHAR2(50)        NOT NULL,
    ORD_PHONE       VARCHAR2(20)        NOT NULL,
    ORD_PRICE       NUMBER              NOT NULL,
    ORD_MSG         VARCHAR2(500)       NULL,
    ORD_STATUS      CHAR(1)             DEFAULT 'R',
    ORD_DT          DATE                DEFAULT SYSDATE,
    FOREIGN KEY (MB_ID) REFERENCES MEMBERS_TB (MB_ID)
);

CREATE TABLE ORDER_DETAIL_TB(
    ORD_NO          NUMBER              NOT NULL,
    PD_NO           NUMBER              NOT NULL,
    ORDDT_QTY       NUMBER              NOT NULL,
    ORDDT_PRICE     NUMBER              NOT NULL,
    PRIMARY KEY (ORD_NO, PD_NO),
    FOREIGN KEY (ORD_NO) REFERENCES ORDERS_TB (ORD_NO),
    FOREIGN KEY (PD_NO) REFERENCES PRODUCTS_TB (PD_NO)
);

CREATE TABLE BOARD_TB(
    BD_NO           NUMBER              PRIMARY KEY,
    MB_ID           VARCHAR2(15)        NOT NULL,
    BD_TITLE        VARCHAR2(100)       NOT NULL,
    BD_CONTENT      VARCHAR2(4000)      NOT NULL,
    BD_DT           DATE                DEFAULT SYSDATE,
    FOREIGN KEY (MB_ID) REFERENCES MEMBERS_TB (MB_ID)
);

CREATE TABLE REVIEWS_TB(
    REV_NO          NUMBER              PRIMARY KEY,
    ORDDT_PD_NO     NUMBER              NOT NULL,
    ORDDT_ORD_NO    NUMBER              NOT NULL,
    MB_ID           VARCHAR2(15)        NOT NULL,
    REV_TITLE       VARCHAR2(100)       NOT NULL,
    REV_WRITER      VARCHAR2(20)        NOT NULL,
    REV_CONTENT     VARCHAR2(500)       NOT NULL,
    REV_GRADE       NUMBER              NOT NULL,
    REV_DT          DATE                DEFAULT SYSDATE,
    FOREIGN KEY (MB_ID) REFERENCES MEMBERS_TB (MB_ID)
);

CREATE TABLE QNA_TB(
    QNA_NO          NUMBER              PRIMARY KEY,
    MB_ID           VARCHAR2(15)        NOT NULL,
    PD_NO           NUMBER              NOT NULL,
    QNA_TITLE       VARCHAR2(100)       NOT NULL,
    QNA_CONTENT     VARCHAR2(4000)      NOT NULL,
    QNA_ANSWER      VARCHAR2(4000),
    QNA_DT          DATE                DEFAULT SYSDATE,
    QNA_STATUS      CHAR(1)             DEFAULT 'N',
    FOREIGN KEY (MB_ID) REFERENCES MEMBERS_TB (MB_ID),
    FOREIGN KEY (PD_NO) REFERENCES PRODUCTS_TB (PD_NO)
);

select * from products_tb order by pd_no;

-- 관리자(pw:1234)
INSERT INTO ADMIN_TB VALUES('admin', '$2a$10$gze1/SwRC7AVCOMhJ6VC0ealimLNQfaQykGKbBi7vKERGHuD5zsye', '관리자', sysdate);

-- 회원
INSERT INTO MEMBERS_TB VALUES('hello', '$2a$10$gze1/SwRC7AVCOMhJ6VC0ealimLNQfaQykGKbBi7vKERGHuD5zsye', '김인사', '인사맨',
'010-1234-5678', 'helloman@naver.com', '12345', '서울특별시 용산구 인사동', '인사아파트 110동 101호', 0, SYSDATE, NULL, '123', 'Y'); 

-- 카테고리
INSERT INTO CATEGORY_TB VALUES ('1', NULL, '전체');
INSERT INTO CATEGORY_TB VALUES ('2', '1', '아우터');
INSERT INTO CATEGORY_TB VALUES ('3', '1', '티셔츠');
INSERT INTO CATEGORY_TB VALUES ('4', '1', '셔츠');
INSERT INTO CATEGORY_TB VALUES ('5', '1', '팬츠');
INSERT INTO CATEGORY_TB VALUES ('6', '1', '신발');
INSERT INTO CATEGORY_TB VALUES ('7', '2', '가디건');
INSERT INTO CATEGORY_TB VALUES ('8', '2', '자켓');
INSERT INTO CATEGORY_TB VALUES ('9', '2', '코트');
INSERT INTO CATEGORY_TB VALUES ('10', '3', '맨투맨');
INSERT INTO CATEGORY_TB VALUES ('11', '3', '후드');
INSERT INTO CATEGORY_TB VALUES ('12', '3', '니트');
INSERT INTO CATEGORY_TB VALUES ('13', '4', '베이직/데님');
INSERT INTO CATEGORY_TB VALUES ('14', '4', '스트라이프');
INSERT INTO CATEGORY_TB VALUES ('15', '4', '체크/패턴');
INSERT INTO CATEGORY_TB VALUES ('16', '5', '데님');
INSERT INTO CATEGORY_TB VALUES ('17', '5', '슬랙스');
INSERT INTO CATEGORY_TB VALUES ('18', '5', '면바지');
INSERT INTO CATEGORY_TB VALUES ('19', '6', '스니커즈');
INSERT INTO CATEGORY_TB VALUES ('20', '6', '로퍼');
INSERT INTO CATEGORY_TB VALUES ('21', '6', '샌들');

-- 상품
INSERT INTO BASKETS_TB VALUES('helloman', 7, 3);
INSERT INTO BASKETS_TB VALUES('helloman', 3, 2);
commit;



INSERT INTO BASKET_TB VALUES (10, 123, 'bird', 2);
INSERT INTO BASKET_TB VALUES (20, 123, 'hello', 2);
INSERT INTO BASKET_TB VALUES (30, 97, 'world', 1);
INSERT INTO BASKET_TB VALUES (40, 1234, 'car', 5);
INSERT INTO BASKET_TB VALUES (50, 40, 'computer', 1);


INSERT INTO ORDER_TB VALUES (3, 'hello', '김안녕', '12345', '경기도 안녕시', '안녕동 1', '010-1234-5678', 100000, SYSDATE);
INSERT INTO ORDER_TB VALUES (5, 'world', '김세계', '12346', '경기도 세계시', '세계동 2', '010-2345-6789', 28000, SYSDATE);
INSERT INTO ORDER_TB VALUES (30, 'bird', '김새', '12345', '경기도 세시', '동동 3', '031-444-4885', 100000, SYSDATE);


INSERT INTO ORDER_DETAIL_TB VALUES (3, 123, 2, 50000);
INSERT INTO ORDER_DETAIL_TB VALUES (5, 97, 1, 28000);
INSERT INTO ORDER_DETAIL_TB VALUES (30, 123, 2, 50000);

CREATE SEQUENCE BOARD_NO_SEQ START WITH 0 MINVALUE 0 INCREMENT BY 1; 
INSERT INTO BOARD_TB VALUES (BOARD_NO_SEQ.NEXTVAL, 'world', '낡은 코트를 샀는데...', '진짜 너무 낡아서 바람만 불어도 뜯어집니다. 환불좀요', SYSDATE);
INSERT INTO BOARD_TB VALUES (BOARD_NO_SEQ.NEXTVAL, 'computer', '쇼핑몰 서버', '내 컴보다 구린듯 너무 느림', SYSDATE);
INSERT INTO BOARD_TB VALUES (BOARD_NO_SEQ.NEXTVAL, 'car', '차 사고싶다', '차 사는게 소원이라 아이디도 차로 만들어봄 ㅎㅎ', SYSDATE);

INSERT INTO REVIEW_TB VALUES (1, 'world', 97, '개낡음 ㄹㅇ 절대사지마세요', 1, SYSDATE);
INSERT INTO REVIEW_TB VALUES (2, 'hello', 123, '칼로 쑤셔도 안뜯어지네요', 5, SYSDATE);
INSERT INTO REVIEW_TB VALUES (3, 'bird', 123, '너무 질겨서 짜증이납니다', 3, SYSDATE);