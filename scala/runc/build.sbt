name := "runc"

version := "1.0"

scalaVersion := "2.10.4"

//libraryDependencies += "org.spark-project.hive" %% "hive-exec" % "0.12.0"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.4.0" //% "provided"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.4.0" //% "provided"

libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.4.0" //% "provided"

resolvers += "Maven Repository" at "https://repo1.maven.org/maven2"

resolvers += "Apache Repository" at "https://repository.apache.org/content/repositories/releases"

resolvers += "JBoss Repository" at "https://repository.jboss.org/nexus/content/repositories/releases"

resolvers += "MQTT Repository" at "https://repo.eclipse.org/content/repositories/paho-releases"

resolvers += "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos"

resolvers += "MapR Repository" at "http://repository.mapr.com/maven"

resolvers += "Spring Release Repository" at "https://repo.spring.io/libs-release"

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"
