@echo off
title Run Application

start cmd /k java -jar process-monitor-1.0.0.jar
start cmd /k call npm-install-and-serve.bat

REM Wait for the previous command to finish executing
timeout /t 5 /nobreak

start http://localhost:3000

