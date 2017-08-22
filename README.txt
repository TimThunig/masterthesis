Um das Plugin im Camunda Cockpit zu deployen sind folgende Schritte notwenig:

-- Datenbank aufsetzen und deployen --
1. MySQL Datenbank erstellen und die Camunda Create Statements für die gewünschte Version auf dieser ausführen(Datenbankname: db1; user:root; password:socialdatabase) 
2. SQL Create Skript aus dem Verzeichnis sql/ auf der Datenbank ausführen, welche in Camunda eingebunden wird
3. Die Tomcat server.xml Datenbankresource (2. Eintrag unter GlobalNamingResources) mit folgenden Code ersetzen:
		<Resource name="jdbc/ProcessEngine"
					  auth="Container"
					  type="javax.sql.DataSource" 
					  factory="org.apache.tomcat.jdbc.pool.DataSourceFactory"
					  uniqueResourceName="process-engine"
					  driverClassName="com.mysql.jdbc.Driver"
					  url="jdbc:mysql://localhost:3306/db1?autoReconnect=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC&amp;nullNamePatternMatchesAll=true"
					  defaultTransactionIsolation="READ_COMMITTED"
					  username="root"  
					  password="socialdatabase"
					  maxActive="20"
					  minIdle="5" />
4. In der Tomcat bpm-platform.xml <property name="databaseSchemaUpdate">false</property> auf false setzen
5. Den passenden JDBC Connector unter Camunda/server/apache-tomcat-8.0.24/lib abspeichern
-- Plugin deployen --
5. Das Plugin JAR File Camunda/server/apache-tomcat-8.0.24/webapps/camunda/WEB-INF/lib einfügen 
6. Den Tomcat unter Camunda/ starten


Das Plugin ist getestet unter folgenden Voraussetzungen

	-Camunda Tomcat Distribution 7.7
	-Java JDK 1.8.0_131
	-MySQL 5.7
	-MySQL JDBC Connector 6.0.6
	
Dem Benutzer voll voller Zugriff sowohl aus das Cockpit, als auch auf die Admin Applikation gegeben werden.	