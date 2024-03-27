@echo off
title Run Application

cd ProcessMonitor\process-monitor\target
start cmd /k java -jar process-monitor-0.0.1-SNAPSHOT.jar
cd ..\..\sysmon-plus
start cmd /k npm install -g serve & serve -s build
start http://localhost:3000

