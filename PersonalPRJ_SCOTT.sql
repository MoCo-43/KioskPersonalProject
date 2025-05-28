-- �޴� ����Ʈ
CREATE TABLE tbl_menu
(menu_no NUMBER(4),
menu_name VARCHAR2(30),
menu_price NUMBER(4),
menu_info VARCHAR2(20)
);

-- �޴� order_name �� order_info ũ�� �ø�
ALTER TABLE tbl_menu MODIFY(menu_name VARCHAR2(100));
ALTER TABLE tbl_menu MODIFY(menu_info VARCHAR2(100));

ALTER TABLE tbl_order MODIFY(order_name VARCHAR2(100));
ALTER TABLE tbl_order MODIFY(order_info VARCHAR2(100));

-- �ֹ�����
CREATE TABLE tbl_order
(order_no NUMBER(4),
order_name VARCHAR2(30),
order_price NUMBER(4),
order_info VARCHAR2(20)
);

-- menu ����
INSERT INTO tbl_menu (menu_no, menu_name, menu_price, menu_info)
VALUES (1, 'Americano Coffee', 20, '�ͼ���, ģ���� �ε巯�� Ŀ��');

INSERT INTO tbl_menu (menu_no, menu_name, menu_price, menu_info)
VALUES (2, 'Cold Brew Coffee', 40, '�ε巯�� ǳ��');

INSERT INTO tbl_menu (menu_no, menu_name, menu_price, menu_info)
VALUES (3, 'Caramel Macchiato Coffee', 45, '������ ī���, �ε巯�� ����');

INSERT INTO tbl_menu (menu_no, menu_name, menu_price, menu_info)
VALUES (4, 'Green Tea', 25, '������ ��, ���� ����');

INSERT INTO tbl_menu (menu_no, menu_name, menu_price, menu_info)
VALUES (5, 'Yuja Tea', 30, '���޽Խ�, ��ŭ��');

INSERT INTO tbl_menu (menu_no, menu_name, menu_price, menu_info)
VALUES (6, 'Chamomile Tea', 35, '������ ����, ������ �ʿ��� ��');

-- menu ������Ʈ
--�׽�Ʈ1 �Ƹ޸�ī�� -> �� �Ƹ޸�ī��� ����
UPDATE tbl_menu 
SET menu_name = 'Hot Americano Coffee', menu_price = 10, menu_info = '�߰ſ�Ŀ��'
WHERE menu_no = 1;

-- menu ����
-- �׽�Ʈ1 �� �Ƹ޸�ī�� ����
DELETE FROM tbl_menu
WHERE menu_no = 1;