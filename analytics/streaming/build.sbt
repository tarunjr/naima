name := "org.naima.analytics.streaming"
version := "0.0.1"
scalaVersion := "2.11.8"


val bijectionVersion = "0.7.1"
val sparkVersion = "2.0.0"
// additional libraries
libraryDependencies ++= Seq(
  "com.twitter" %% "bijection-core" % bijectionVersion,
  "com.twitter" %% "bijection-avro" % bijectionVersion,
  "org.apache.kafka" % "kafka_2.11" % "0.9.0.1"
    exclude("javax.jms", "jms")
    exclude("com.sun.jdmk", "jmxtools")
    exclude("com.sun.jmx", "jmxri")
    exclude("org.slf4j", "slf4j-simple")
    exclude("log4j", "log4j")
    exclude("org.apache.zookeeper", "zookeeper")
    exclude("com.101tec", "zkclient"),
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided"
    exclude("org.apache.zookeeper", "zookeeper")
    exclude("org.slf4j", "slf4j-api")
    exclude("org.slf4j", "slf4j-log4j12")
    exclude("org.slf4j", "jul-to-slf4j")
    exclude("org.slf4j", "jcl-over-slf4j")
    exclude("com.twitter", "chill_2.10")
    exclude("log4j", "log4j"),
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-streaming-kafka" % "1.6.2"
      exclude("org.apache.zookeeper", "zookeeper")
)



jarName in assembly := "naima-streaming.jar"

assemblyOption in assembly :=
	(assemblyOption in assembly).value.copy(includeScala = false)

  mergeStrategy in assembly := {
    case m if m.toLowerCase.endsWith("manifest.mf")          => MergeStrategy.discard
    case m if m.toLowerCase.matches("meta-inf.*\\.sf$")      => MergeStrategy.discard
    case "log4j.properties"                                  => MergeStrategy.discard
    case m if m.toLowerCase.startsWith("meta-inf/services/") => MergeStrategy.filterDistinctLines
    case "reference.conf"                                    => MergeStrategy.concat
    case _                                                   => MergeStrategy.first
  }
