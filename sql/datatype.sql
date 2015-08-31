-- Actions --
insert into sysaction(ACTNID,ACTNLBL,ATCNKY,TLTP,ICN,STNO,DEF1,DEF2,DEF3,DEF4,DEF5)
values('btnRefresh', '刷新', '', '', '', '1', '', '', '', '', '');
insert into sysaction(ACTNID,ACTNLBL,ATCNKY,TLTP,ICN,STNO,DEF1,DEF2,DEF3,DEF4,DEF5)
values('btnView', '查看', '', '', '', '2', '', '', '', '', '');

-- Functions --- moduleID - basicdata 1, account 2, index 3, equipment 4, GIS 5, report 6  

-- BasicData Module --
 
insert into sysfunction(FNCTNID, FNCTNNM, MDLID, PAGE, SYSTP, PRNTID, ICN, PGTP, STNO, STS)
values('BasicData', '基础数据', '1', '', '1', '0', 'icon-cogs', '0', '10', '1');
insert into sysfunction(FNCTNID, FNCTNNM, MDLID, PAGE, SYSTP, PRNTID, ICN, PGTP, STNO, STS)
values('CompanyMgr', '公司管理', '1', 'sysDataType/toPage.do?type=1', '1', 'BasicData', 'icon-cogs', '0', '1', '1');
insert into sysfunction(FNCTNID, FNCTNNM, MDLID, PAGE, SYSTP, PRNTID, ICN, PGTP, STNO, STS)
values('IndustryMgr', '行业管理', '1', 'sysDataType/toPage.do?type=2', '1', 'BasicData', 'icon-cogs', '0', '2', '1');
insert into sysfunction(FNCTNID, FNCTNNM, MDLID, PAGE, SYSTP, PRNTID, ICN, PGTP, STNO, STS)
values('StationTypeMgr', '监测点类别管理', '1', 'sysDataType/toPage.do?type=3', '1', 'BasicData', 'icon-cogs', '0', '3', '1');
insert into sysfunction(FNCTNID, FNCTNNM, MDLID, PAGE, SYSTP, PRNTID, ICN, PGTP, STNO, STS)
values('StationMgr', '监测点管理', '1', '', '1', 'BasicData', 'icon-cogs', '0', '3', '1');

-- Account Module --

insert into sysfunction(FNCTNID, FNCTNNM, MDLID, PAGE, SYSTP, PRNTID, ICN, PGTP, STNO, STS)
values('Account', '用户管理', '2', '', '1', '0', 'icon-user', '0', '10', '1');
insert into sysfunction(FNCTNID, FNCTNNM, MDLID, PAGE, SYSTP, PRNTID, ICN, PGTP, STNO, STS)
values('UserMgr', '用户信息', '2', 'account/userPage.do', '1', 'Account', '', '1', '1', '1');
insert into sysfunction(FNCTNID, FNCTNNM, MDLID, PAGE, SYSTP, PRNTID, ICN, PGTP, STNO, STS)
values('RoleMgr', '角色管理', '2', 'account/rolePage.do', '1', 'Account', '', '1', '2', '1');
insert into sysfunction(FNCTNID, FNCTNNM, MDLID, PAGE, SYSTP, PRNTID, ICN, PGTP, STNO, STS)
values('PermissionMgr', '权限管理', '2', 'account/permissionPage.do', '1', 'Account', '', '1', '3', '1');

-- Action_Function_Map ---

insert into sysactionfunctionmap(FNCTNID,ACTNID)
values('BasicData','btnView');
insert into sysactionfunctionmap(FNCTNID,ACTNID)
values('Account','btnView');
insert into sysactionfunctionmap(FNCTNID,ACTNID)
values('UserMgr','btnRefresh');
insert into sysactionfunctionmap(FNCTNID,ACTNID)
values('RoleMgr','btnRefresh');
insert into sysactionfunctionmap(FNCTNID,ACTNID)
values('PermissionMgr','btnRefresh');
insert into sysactionfunctionmap(FNCTNID,ACTNID)
values('CompanyMgr','btnRefresh');
insert into sysactionfunctionmap(FNCTNID,ACTNID)
values('IndustryMgr','btnRefresh');
insert into sysactionfunctionmap(FNCTNID,ACTNID)
values('StationTypeMgr','btnRefresh');



-- Permission ---
insert into syspermission(RLID,MPID)
values(1,1);
insert into syspermission(RLID,MPID)
values(1,2);
insert into syspermission(RLID,MPID)
values(1,3);
insert into syspermission(RLID,MPID)
values(1,4);
insert into syspermission(RLID,MPID)
values(1,5);
insert into syspermission(RLID,MPID)
values(1,6);
insert into syspermission(RLID,MPID)
values(1,7);
