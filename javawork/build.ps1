
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21"
$env:PATH = $env:JAVA_HOME + "\bin;" + $env:PATH
.\mvnw.cmd clean compile
