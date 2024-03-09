@echo off
title Run Application

cd ProcessMonitor\process-monitor\src\main\java\com\process_monitor\processmonitor
start cmd /k mvn spring-boot:run
cd ..\..\sysmon-plus\public
start cmd /k npm start

