::要使用简体中文编码

@echo off 
Set OrclSid=SMS
Set ExpUser=PtSoft
Set ExpPass=PartnerSoft_1234
Set FileDir=E:\OrclBackUp
Set SysDate=%date:~0,4%%date:~5,2%%date:~8,2%.%time:~0,2%%time:~3,2%
Set FileName=%ExpUser%~%OrclSid%_%SysDate%

IF NOT EXIST %FileDir% MD %FileDir%

@echo * * * * * * * * * * * * * * * * * * * * 
@echo * * * * * * * * * * * * * * * * * * * * 
@echo * * * * * ORACLE 数据库备份 * * * * * * 
@echo * * * * * * * * * * * * * * * * * * * * 
@echo * * * * * * * * * * * * * * * * * * * *

@echo 服务名 = %OrclSid% 
@echo 用户名 = %ExpUser% 
@echo 密  码 = %ExpPass% 
@echo 目  录 = %FileDir% 
@echo 时  间 = %SysDate% 
@echo 文件名 = %FileName%.rar

@echo 开始备份数据库... 
exp %ExpUser%/%ExpPass%@%OrclSid% file=%FileDir%/%FileName%.dmp
@echo 数据库备份完成!

@echo 开始压缩数据库备份... 
"%~dp0Rar.exe" a %FileDir%/%FileName%.rar %FileDir%/%FileName%.dmp 

@echo 数据库备份压缩完成!

::  注释符号 
rem 注释符号

rem 如想手动删除备份文件,请将下面的代码删除或者注释掉 
:: 开始删除数据库备份文件... 

del %FileDir%\%FileName%.dmp
:: 数据库备份文件删除完成!

exit