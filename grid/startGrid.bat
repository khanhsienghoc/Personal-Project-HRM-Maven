@echo off
echo Starting multiple Selenium Nodes...

REM Node 1 - Chrome
start cmd /k java -jar selenium-server-4.35.0.jar node --detect-drivers true --publish-events tcp://localhost:4442 --subscribe-events tcp://localhost:4443 --port 5555

REM Node 2 - Firefox
start cmd /k java -jar selenium-server-4.35.0.jar node --detect-drivers true --publish-events tcp://localhost:4442 --subscribe-events tcp://localhost:4443 --port 5556

REM Node 3 - Edge
start cmd /k java -jar selenium-server-4.35.0.jar node --detect-drivers true --publish-events tcp://localhost:4442 --subscribe-events tcp://localhost:4443 --port 5557

echo All nodes started successfully!
pause