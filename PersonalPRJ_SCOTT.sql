-- 메뉴 리스트
CREATE TABLE tbl_menu
(menu_no NUMBER(4),
menu_name VARCHAR2(30),
menu_price NUMBER(4),
menu_info VARCHAR2(20)
);

-- 메뉴 order_name 및 order_info 크기 늘림
ALTER TABLE tbl_menu MODIFY(menu_name VARCHAR2(100));
ALTER TABLE tbl_menu MODIFY(menu_info VARCHAR2(100));

ALTER TABLE tbl_order MODIFY(order_name VARCHAR2(100));
ALTER TABLE tbl_order MODIFY(order_info VARCHAR2(100));

-- 주문내역
CREATE TABLE tbl_order
(order_no NUMBER(4),
order_name VARCHAR2(30),
order_price NUMBER(4),
order_info VARCHAR2(20)
);

-- menu 삽입
INSERT INTO tbl_menu (menu_no, menu_name, menu_price, menu_info)
VALUES (1, 'Americano Coffee', 20, '익숙한, 친숙한 부드러운 커피');

INSERT INTO tbl_menu (menu_no, menu_name, menu_price, menu_info)
VALUES (2, 'Cold Brew Coffee', 40, '부드러운 풍미');

INSERT INTO tbl_menu (menu_no, menu_name, menu_price, menu_info)
VALUES (3, 'Caramel Macchiato Coffee', 45, '달콤한 카라멜, 부드러운 우유');

INSERT INTO tbl_menu (menu_no, menu_name, menu_price, menu_info)
VALUES (4, 'Green Tea', 25, '은은한 향, 몸에 좋은');

INSERT INTO tbl_menu (menu_no, menu_name, menu_price, menu_info)
VALUES (5, 'Yuja Tea', 30, '달콤쌉쌀, 상큼함');

INSERT INTO tbl_menu (menu_no, menu_name, menu_price, menu_info)
VALUES (6, 'Chamomile Tea', 35, '은은한 꽃향, 진정이 필요할 때');

-- menu 업데이트
--테스트1 아메리카노 -> 핫 아메리카노로 변경
UPDATE tbl_menu 
SET menu_name = 'Hot Americano Coffee', menu_price = 10, menu_info = '뜨거운커피'
WHERE menu_no = 1;

-- menu 삭제
-- 테스트1 핫 아메리카노 삭제
DELETE FROM tbl_menu
WHERE menu_no = 1;