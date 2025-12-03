@echo off
echo Starting Selenium Hub...
start cmd /k java -jar selenium-server-4.35.0.jar hub
timeout /t 5

echo Starting multiple Selenium Nodes...

REM Node 1 - Chrome
start cmd /k java -jar selenium-server-4.35.0.jar node --detect-drivers true --hub http://localhost:4444 --port 5555

REM Node 2 - Firefox
start cmd /k java -jar selenium-server-4.35.0.jar node --detect-drivers true --hub http://localhost:4444 --port 5556

REM Node 3 - Edge
start cmd /k java -jar selenium-server-4.35.0.jar node --detect-drivers true --hub http://localhost:4444 --port 5557

echo All nodes started successfully!
pause