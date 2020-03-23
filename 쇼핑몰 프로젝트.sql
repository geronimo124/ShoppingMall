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
select * from members_tb   ;
select * from products_tb order by pd_no desc;
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
    ORD_DTADDR      VARCHAR2(50)        NOT NULL,
    ORD_PHONE       VARCHAR2(20)        NOT NULL,
    ORD_PRICE       NUMBER              NOT NULL,
    ORD_DT          DATE                DEFAULT SYSDATE,
    FOREIGN KEY (MB_ID) REFERENCES MEMBERS_TB (MB_ID)
);

CREATE TABLE ORDER_DETAIL_TB(
    ORD_NO          NUMBER              UNIQUE,
    PD_NO           NUMBER              UNIQUE,
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
    REV_CONTENT     VARCHAR2(4000)      NOT NULL,
    REV_GRADE       NUMBER              NOT NULL,
    REV_DT          DATE                DEFAULT SYSDATE,
    FOREIGN KEY (MB_ID) REFERENCES MEMBERS_TB (MB_ID),
    FOREIGN KEY (ORDDT_PD_NO) REFERENCES ORDER_DETAIL_TB (PD_NO),
    FOREIGN KEY (ORDDT_ORD_NO) REFERENCES ORDER_DETAIL_TB (ORD_NO)
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

-- ������(pw:1234)
INSERT INTO ADMIN_TB VALUES('admin', '$2a$10$gze1/SwRC7AVCOMhJ6VC0ealimLNQfaQykGKbBi7vKERGHuD5zsye', '������', sysdate);

-- ȸ��
INSERT INTO MEMBERS_TB VALUES('hello', '$2a$10$gze1/SwRC7AVCOMhJ6VC0ealimLNQfaQykGKbBi7vKERGHuD5zsye', '���λ�', '�λ��',
'010-1234-5678', 'helloman@naver.com', '12345', '����Ư���� ��걸 �λ絿', '�λ����Ʈ 110�� 101ȣ', 0, SYSDATE, NULL, '123', 'Y'); 

-- ī�װ�
INSERT INTO CATEGORY_TB VALUES ('1', NULL, '��ü');
INSERT INTO CATEGORY_TB VALUES ('2', '1', '�ƿ���');
INSERT INTO CATEGORY_TB VALUES ('3', '1', 'Ƽ����');
INSERT INTO CATEGORY_TB VALUES ('4', '1', '����');
INSERT INTO CATEGORY_TB VALUES ('5', '1', '����');
INSERT INTO CATEGORY_TB VALUES ('6', '1', '�Ź�');
INSERT INTO CATEGORY_TB VALUES ('7', '2', '�����');
INSERT INTO CATEGORY_TB VALUES ('8', '2', '����');
INSERT INTO CATEGORY_TB VALUES ('9', '2', '��Ʈ');
INSERT INTO CATEGORY_TB VALUES ('10', '3', '������');
INSERT INTO CATEGORY_TB VALUES ('11', '3', '�ĵ�');
INSERT INTO CATEGORY_TB VALUES ('12', '3', '��Ʈ');
INSERT INTO CATEGORY_TB VALUES ('13', '4', '������/����');
INSERT INTO CATEGORY_TB VALUES ('14', '4', '��Ʈ������');
INSERT INTO CATEGORY_TB VALUES ('15', '4', 'üũ/����');
INSERT INTO CATEGORY_TB VALUES ('16', '5', '����');
INSERT INTO CATEGORY_TB VALUES ('17', '5', '������');
INSERT INTO CATEGORY_TB VALUES ('18', '5', '�����');
INSERT INTO CATEGORY_TB VALUES ('19', '6', '����Ŀ��');
INSERT INTO CATEGORY_TB VALUES ('20', '6', '����');
INSERT INTO CATEGORY_TB VALUES ('21', '6', '����');

-- ��ǰ
INSERT INTO PRODUCTS_TB VALUES((SELECT NVL(MAX(PD_NO),0)+1 FROM PRODUCTS_TB),
7, '�����', '�αٵα� ���̳� ������ �����', 22000, 5, NULL, '�αٵα� ���̳� ������ ������Դϴ�.', 24, SYSDATE, SYSDATE);
INSERT INTO PRODUCTS_TB VALUES((SELECT NVL(MAX(PD_NO),0)+1 FROM PRODUCTS_TB),
8, '����', '���� ����� ���� ����', 32000, 15, NULL, '���� ����� ���� �����Դϴ�.', 40, SYSDATE, SYSDATE);
INSERT INTO PRODUCTS_TB VALUES((SELECT NVL(MAX(PD_NO),0)+1 FROM PRODUCTS_TB),
9, '��Ʈ', 'Ÿ�� �� �̱� ��Ʈ', 29000, 10, NULL, 'Ÿ�� �� �̱� ��Ʈ�Դϴ�.', 31, SYSDATE, SYSDATE);
INSERT INTO PRODUCTS_TB VALUES((SELECT NVL(MAX(PD_NO),0)+1 FROM PRODUCTS_TB),
10, '������', '����Ȧ�� �׿����� ������', 24900, 10, NULL, '����Ȧ�� �׿����� �������Դϴ�.', 93, SYSDATE, SYSDATE);
INSERT INTO PRODUCTS_TB VALUES((SELECT NVL(MAX(PD_NO),0)+1 FROM PRODUCTS_TB),
11, '�ĵ�', '����޴� 15�÷� �ĵ�', 19800, 0, NULL, '����޴� 15�÷� �ĵ��Դϴ�.', 82, SYSDATE, SYSDATE);
INSERT INTO PRODUCTS_TB VALUES((SELECT NVL(MAX(PD_NO),0)+1 FROM PRODUCTS_TB),
12, '��Ʈ', '����� ��Ƽġ ��Ʈ', 29700, 0, NULL, '����� ��Ƽġ ��Ʈ�Դϴ�.', 24, SYSDATE, SYSDATE);
INSERT INTO PRODUCTS_TB VALUES((SELECT NVL(MAX(PD_NO),0)+1 FROM PRODUCTS_TB),
13, '������/����', '������ �������� ����', 12700, 0, NULL, '������ �������� �����Դϴ�.', 55, SYSDATE, SYSDATE);
INSERT INTO PRODUCTS_TB VALUES((SELECT NVL(MAX(PD_NO),0)+1 FROM PRODUCTS_TB),
14, '��Ʈ������', '�뷱 ���� ��Ʈ������ ����', 19700, 0, NULL, '�뷱 ���� ��Ʈ������ �����Դϴ�.', 15, SYSDATE, SYSDATE);
INSERT INTO PRODUCTS_TB VALUES((SELECT NVL(MAX(PD_NO),0)+1 FROM PRODUCTS_TB),
15, 'üũ/����', '���� ������ üũ����', 22700, 0, NULL, '���� ������ üũ�����Դϴ�.', 35, SYSDATE, SYSDATE);
INSERT INTO PRODUCTS_TB VALUES((SELECT NVL(MAX(PD_NO),0)+1 FROM PRODUCTS_TB),
16, '����', 'Ʈ�� ��� ���� ��������', 52800, 50, NULL, 'Ʈ�� ��� ���� ���������Դϴ�.', 105, SYSDATE, SYSDATE);
INSERT INTO PRODUCTS_TB VALUES((SELECT NVL(MAX(PD_NO),0)+1 FROM PRODUCTS_TB),
17, '������', '�������� ��� ����', 9900, 0, NULL, '�������� ��� �����Դϴ�.', 76, SYSDATE, SYSDATE);
INSERT INTO PRODUCTS_TB VALUES((SELECT NVL(MAX(PD_NO),0)+1 FROM PRODUCTS_TB),
18, '�����', '�ӹ�� ���� ���� ������', 9000, 0, NULL, '�ӹ�� ���� ���� �������Դϴ�.', 79, SYSDATE, SYSDATE);
INSERT INTO PRODUCTS_TB VALUES((SELECT NVL(MAX(PD_NO),0)+1 FROM PRODUCTS_TB),
19, '����Ŀ��', '�������� ���ϱ� ��� ����Ŀ��', 37000, 0, NULL, '�������� ���ϱ� ��� ����Ŀ���Դϴ�.', 23, SYSDATE, SYSDATE);
INSERT INTO PRODUCTS_TB VALUES((SELECT NVL(MAX(PD_NO),0)+1 FROM PRODUCTS_TB),
20, '����', '��� ��Ŭ �����', 39900, 0, NULL, '��� ��Ŭ ������Դϴ�.', 44, SYSDATE, SYSDATE);
INSERT INTO PRODUCTS_TB VALUES((SELECT NVL(MAX(PD_NO),0)+1 FROM PRODUCTS_TB),
21, '����', '��Ŭ ��� ������', 27000, 20, NULL, '��Ŭ ��� �������Դϴ�.', 114, SYSDATE, SYSDATE);

SELECT * FROM PRODUCTS_TB;
commit;



INSERT INTO BASKET_TB VALUES (10, 123, 'bird', 2);
INSERT INTO BASKET_TB VALUES (20, 123, 'hello', 2);
INSERT INTO BASKET_TB VALUES (30, 97, 'world', 1);
INSERT INTO BASKET_TB VALUES (40, 1234, 'car', 5);
INSERT INTO BASKET_TB VALUES (50, 40, 'computer', 1);


INSERT INTO ORDER_TB VALUES (3, 'hello', '��ȳ�', '12345', '��⵵ �ȳ��', '�ȳ絿 1', '010-1234-5678', 100000, SYSDATE);
INSERT INTO ORDER_TB VALUES (5, 'world', '�輼��', '12346', '��⵵ �����', '���赿 2', '010-2345-6789', 28000, SYSDATE);
INSERT INTO ORDER_TB VALUES (30, 'bird', '���', '12345', '��⵵ ����', '���� 3', '031-444-4885', 100000, SYSDATE);


INSERT INTO ORDER_DETAIL_TB VALUES (3, 123, 2, 50000);
INSERT INTO ORDER_DETAIL_TB VALUES (5, 97, 1, 28000);
INSERT INTO ORDER_DETAIL_TB VALUES (30, 123, 2, 50000);

CREATE SEQUENCE BOARD_NO_SEQ START WITH 0 MINVALUE 0 INCREMENT BY 1; 
INSERT INTO BOARD_TB VALUES (BOARD_NO_SEQ.NEXTVAL, 'world', '���� ��Ʈ�� ��µ�...', '��¥ �ʹ� ���Ƽ� �ٶ��� �Ҿ ������ϴ�. ȯ������', SYSDATE);
INSERT INTO BOARD_TB VALUES (BOARD_NO_SEQ.NEXTVAL, 'computer', '���θ� ����', '�� �ĺ��� ������ �ʹ� ����', SYSDATE);
INSERT INTO BOARD_TB VALUES (BOARD_NO_SEQ.NEXTVAL, 'car', '�� ���ʹ�', '�� ��°� �ҿ��̶� ���̵� ���� ���� ����', SYSDATE);

INSERT INTO REVIEW_TB VALUES (1, 'world', 97, '������ ���� �������������', 1, SYSDATE);
INSERT INTO REVIEW_TB VALUES (2, 'hello', 123, 'Į�� ���ŵ� �ȶ�����׿�', 5, SYSDATE);
INSERT INTO REVIEW_TB VALUES (3, 'bird', 123, '�ʹ� ���ܼ� ¥���̳��ϴ�', 3, SYSDATE);