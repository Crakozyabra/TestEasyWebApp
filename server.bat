copy .\target\TestEasyWebApp.war %CATALINA_HOME%\webapps\TestEasyWebApp.war
start %CATALINA_HOME%\bin\startup.bat
start chrome http://localhost:8080/