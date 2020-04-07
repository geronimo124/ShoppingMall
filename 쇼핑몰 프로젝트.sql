/*
    프로젝트 : 쇼핑몰
    작성자 : 전일배
*/
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
    -- 'R' = 배송준비중 / 'D' = 배송중 / 'S' = 배송완료
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

CREATE TABLE NOTICE_TB(
    NT_NO           NUMBER              PRIMARY KEY,
    ADM_ID          VARCHAR2(15)        NOT NULL,
    ADM_NM          VARCHAR2(30)        NOT NULL,
    NT_TITLE        VARCHAR2(100)       NOT NULL,
    NT_CONTENT      VARCHAR2(4000)      NOT NULL,
    NT_DT           DATE                DEFAULT SYSDATE,
    FOREIGN KEY (ADM_ID) REFERENCES ADMIN_TB (ADM_ID)
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
    QNA_GROUP       NUMBER              NOT NULL,
    QNA_STEP        NUMBER              NOT NULL,
    QNA_LEVEL       NUMBER              NOT NULL,
    MB_ID           VARCHAR2(15)        NOT NULL,
    PD_NO           NUMBER              NOT NULL,
    QNA_TITLE       VARCHAR2(100)       NOT NULL,
    QNA_WRITER      VARCHAR2(20)        NOT NULL,
    QNA_CONTENT     VARCHAR2(4000)      NOT NULL,
    QNA_DT          DATE                DEFAULT SYSDATE,
    FOREIGN KEY (MB_ID) REFERENCES MEMBERS_TB (MB_ID),
    FOREIGN KEY (PD_NO) REFERENCES PRODUCTS_TB (PD_NO)
);


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

commit;



-- 주문상세 INSERT -> 물품 재고 감소 트리거
CREATE OR REPLACE TRIGGER trg_orddt_insert
   AFTER INSERT 
   ON ORDER_DETAIL_TB
   FOR EACH ROW 
DECLARE
   v_orddt_qty NUMBER;
   v_pd_no NUMBER;
   v_pd_stock NUMBER;
BEGIN
   SELECT :NEW.ORDDT_QTY INTO v_orddt_qty FROM DUAL;
   SELECT :NEW.PD_NO INTO v_pd_no FROM DUAL;
   SELECT PD_STOCK INTO v_pd_stock FROM PRODUCTS_TB WHERE PD_NO = v_pd_no;
   UPDATE PRODUCTS_TB SET PD_STOCK = v_pd_stock - v_orddt_qty WHERE PD_NO = v_pd_no;
   IF v_pd_stock - v_orddt_qty <= 0 THEN
        UPDATE PRODUCTS_TB SET PD_STATUS = 'N' WHERE PD_NO = v_pd_no;
   END IF;
END;

-- 주문상세 DELETE -> 물품 재고 증가 트리거
CREATE OR REPLACE TRIGGER trg_orddt_delete
   AFTER DELETE
   ON ORDER_DETAIL_TB
   FOR EACH ROW 
DECLARE
   v_orddt_qty NUMBER;
   v_pd_no NUMBER;
   v_pd_stock NUMBER;
BEGIN
   SELECT :OLD.ORDDT_QTY INTO v_orddt_qty FROM DUAL;
   SELECT :OLD.PD_NO INTO v_pd_no FROM DUAL;
   SELECT PD_STOCK INTO v_pd_stock FROM PRODUCTS_TB WHERE PD_NO = v_pd_no;
   UPDATE PRODUCTS_TB SET PD_STOCK = PD_STOCK + v_orddt_qty WHERE PD_NO = v_pd_no;
   IF v_pd_stock + v_orddt_qty > 0 THEN
        UPDATE PRODUCTS_TB SET PD_STATUS = 'Y' WHERE PD_NO = v_pd_no;
   END IF;
END;



-- 주문 DELETE -> 주문상세, 리뷰 DELETE 트리거
CREATE OR REPLACE TRIGGER trg_ord_delete
   BEFORE DELETE
   ON ORDERS_TB
   FOR EACH ROW 
DECLARE
   v_ord_no NUMBER;
BEGIN
   SELECT :OLD.ORD_NO INTO v_ord_no FROM DUAL;
   DELETE FROM ORDER_DETAIL_TB WHERE ORD_NO = v_ord_no;
   DELETE FROM REVIEWS_TB WHERE ORDDT_ORD_NO = v_ord_no;
END;


-- 새로운 주문 통계 프로시저
CREATE OR REPLACE PROCEDURE proc_stat_new_order
                ( p_adm_id IN ADMIN_TB.ADM_ID%TYPE,
                  p_ord_count OUT NUMBER )
IS
    v_condt_date ADMIN_TB.ADM_CONDT%TYPE := SYSDATE;
BEGIN
    SELECT ADM_CONDT INTO v_condt_date FROM ADMIN_TB WHERE ADM_ID = p_adm_id;
    SELECT COUNT(*) INTO p_ord_count FROM ORDERS_TB WHERE v_condt_date <= ORD_DT;
END;

-- 새로운 멤버 통계 프로시저
CREATE OR REPLACE PROCEDURE proc_stat_new_member
                ( p_adm_id IN ADMIN_TB.ADM_ID%TYPE,
                  p_member_count OUT NUMBER )
IS
    v_condt_date ADMIN_TB.ADM_CONDT%TYPE := SYSDATE;
BEGIN
    SELECT ADM_CONDT INTO v_condt_date FROM ADMIN_TB WHERE ADM_ID = p_adm_id;
    SELECT COUNT(*) INTO p_member_count FROM MEMBERS_TB WHERE v_condt_date <= MB_REGDT;
END;


-- QNA INSERT 프로시저
-- 부모 글 작성시 group = 0 / step = 0 / level = 0
-- 자식 글 작성시 group = 부모 / step = 부모 / level = 부모
CREATE OR REPLACE PROCEDURE proc_qna_insert 
            ( p_qna_group IN QNA_TB.QNA_GROUP%TYPE,
              p_qna_step IN QNA_TB.QNA_STEP%TYPE,
              p_qna_level IN QNA_TB.QNA_LEVEL%TYPE,
              p_mb_id IN QNA_TB.MB_ID%TYPE,
              p_pd_no IN QNA_TB.PD_NO%TYPE,
              p_qna_title IN QNA_TB.QNA_TITLE%TYPE,
              p_qna_writer  IN QNA_TB.QNA_WRITER%TYPE,
              p_qna_content IN QNA_TB.QNA_CONTENT%TYPE )
IS
    v_qna_no QNA_TB.QNA_NO%TYPE;
    v_qna_group QNA_TB.QNA_GROUP%TYPE;
    v_qna_step QNA_TB.QNA_STEP%TYPE;
    v_qna_level QNA_TB.QNA_LEVEL%TYPE;
BEGIN
    SELECT NVL(MAX(QNA_NO), 0)+1 INTO v_qna_no FROM QNA_TB;

    -- QNA_GROUP
    IF p_qna_group = 0 THEN -- 부모 글이면
        v_qna_group := v_qna_no; -- 그룹은 qna_no와 같게 준다
        INSERT INTO QNA_TB VALUES (v_qna_no, v_qna_group, p_qna_step, p_qna_level, p_mb_id, p_pd_no,
            p_qna_title, p_qna_writer, p_qna_content, SYSDATE); 
    ELSE -- 자식 글이면
        -- QNA_STEP // 부모 글의 자식들중에(같은 그룹) STEP보다 큰놈들이 있으면 다 +1 UPDATE 해준다
        UPDATE QNA_TB SET QNA_STEP = QNA_STEP + 1 WHERE QNA_GROUP = p_qna_group AND QNA_STEP > p_qna_step;
        v_qna_step := p_qna_step + 1; -- QNA_STEP // 부모 글보다 +1
        v_qna_level := p_qna_level + 1; -- QNA_LEVEL // 부모 글보다 +1
        INSERT INTO QNA_TB VALUES (v_qna_no, p_qna_group, v_qna_step, v_qna_level, p_mb_id, p_pd_no,
            p_qna_title, p_qna_writer, p_qna_content, SYSDATE);
    END IF;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END;

-- QNA DELETE 프로시저
CREATE OR REPLACE PROCEDURE proc_qna_delete
            ( p_qna_group IN QNA_TB.QNA_GROUP%TYPE,
              p_qna_step IN QNA_TB.QNA_STEP%TYPE,
              p_qna_level IN QNA_TB.QNA_LEVEL%TYPE )
IS
    v_count NUMBER := 1;
BEGIN
    FOR r IN (SELECT * FROM QNA_TB WHERE QNA_GROUP = p_qna_group AND QNA_STEP > p_qna_step ORDER BY QNA_STEP) LOOP
        IF r.QNA_LEVEL = p_qna_level THEN
            EXIT;
        END IF;
        DELETE FROM QNA_TB WHERE QNA_NO = r.QNA_NO;
        v_count := v_count + 1;
    END LOOP;
    
    DELETE FROM QNA_TB WHERE QNA_GROUP = p_qna_group AND QNA_STEP = p_qna_step;
    
    FOR r IN (SELECT * FROM QNA_TB WHERE QNA_GROUP = p_qna_group AND QNA_STEP > p_qna_step ORDER BY QNA_STEP) LOOP
        UPDATE QNA_TB SET QNA_STEP = QNA_STEP - v_count WHERE QNA_NO = r.QNA_NO; 
    END LOOP;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END;
