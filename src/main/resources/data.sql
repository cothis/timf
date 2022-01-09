alter sequence COMPANY_SEQ increment by 1;

insert into company (ID, DTYPE, NAME)
values (COMPANY_SEQ.nextval, 'SELLER', '고객사1');
insert into seller (ID, MANAGER_NAME, MANAGER_PHONE)
values (COMPANY_SEQ.currval, 'manager1', '010-1234-1111');

insert into company (ID, DTYPE, NAME)
values (COMPANY_SEQ.nextval, 'SELLER', '고객사2');
insert into seller (ID, MANAGER_NAME, MANAGER_PHONE)
values (COMPANY_SEQ.currval, 'manager2', '010-1234-2222');

insert into company (ID, DTYPE, NAME)
values (COMPANY_SEQ.nextval, 'SELLER', '고객사3');
insert into seller (ID, MANAGER_NAME, MANAGER_PHONE)
values (COMPANY_SEQ.currval, 'manager3', '010-1234-3333');

insert into company (ID, DTYPE, NAME)
values (COMPANY_SEQ.nextval, 'SELLER', '고객사4');
insert into seller (ID, MANAGER_NAME, MANAGER_PHONE)
values (COMPANY_SEQ.currval, 'manager3', '010-1234-4444');

insert into company (ID, DTYPE, NAME)
values (COMPANY_SEQ.nextval, 'SHIPPING', '배송사1');
insert into shipping (ID, DRIVER)
values (COMPANY_SEQ.currval, 'driver1');

insert into company (ID, DTYPE, NAME)
values (COMPANY_SEQ.nextval, 'SHIPPING', '배송사2');
insert into shipping (ID, DRIVER)
values (COMPANY_SEQ.currval, 'driver2');

insert into company (ID, DTYPE, NAME)
values (COMPANY_SEQ.nextval, 'SHIPPING', '배송사3');
insert into shipping (ID, DRIVER)
values (COMPANY_SEQ.currval, 'driver3');

insert into company (ID, DTYPE, NAME)
values (COMPANY_SEQ.nextval, 'SHIPPING', '배송사4');
insert into shipping (ID, DRIVER)
values (COMPANY_SEQ.currval, 'driver4');

alter sequence COMPANY_SEQ increment by 50;

alter sequence VOC_SEQ increment by 1;
alter sequence COMPENSATION_SEQ increment by 1;

insert into VOC (ID, CONTENT, PARTY, PENALTY_ID)
VALUES (VOC_SEQ.nextval, 'test voc1', 'SELLER', null);
insert into COMPENSATION (ID, AMOUNT, VOC_ID)
VALUES (COMPENSATION_SEQ.nextval, 1000, VOC_SEQ.currval);

insert into VOC (ID, CONTENT, PARTY, PENALTY_ID)
VALUES (VOC_SEQ.nextval, 'test voc2', 'SELLER', null);
insert into COMPENSATION (ID, AMOUNT, VOC_ID)
VALUES (COMPENSATION_SEQ.nextval, 1500, VOC_SEQ.currval);


insert into VOC (ID, CONTENT, PARTY, PENALTY_ID)
VALUES (VOC_SEQ.nextval, 'test voc3', 'SELLER', null);
insert into COMPENSATION (ID, AMOUNT, VOC_ID)
VALUES (COMPENSATION_SEQ.nextval, 2000, VOC_SEQ.currval);

insert into VOC (ID, CONTENT, PARTY, PENALTY_ID)
VALUES (VOC_SEQ.nextval, 'test voc4', 'SELLER', null);
insert into COMPENSATION (ID, AMOUNT, VOC_ID)
VALUES (COMPENSATION_SEQ.nextval, 2500, VOC_SEQ.currval);

insert into VOC (ID, CONTENT, PARTY, PENALTY_ID)
VALUES (VOC_SEQ.nextval, 'test voc5', 'SHIPPING', null),
       (VOC_SEQ.nextval, 'test voc6', 'SHIPPING', null),
       (VOC_SEQ.nextval, 'test voc7', 'SHIPPING', null),
       (VOC_SEQ.nextval, 'test voc8', 'SHIPPING', null);

alter sequence COMPENSATION_SEQ increment by 50;
alter sequence VOC_SEQ increment by 50;