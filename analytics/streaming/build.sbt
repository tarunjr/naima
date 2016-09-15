name := "org.naima.analytics.streaming"
version := "0.0.1"
scalaVersion := "2.11.8"




// additional libraries
libraryDependencies ++= Seq(
 "org.apache.spark" %% "spark-core" % "2.0.0" % "provided",
 "org.apache.spark" %% "spark-streaming" % "2.0.0" %  "provided",
 "org.apache.spark" %% "spark-streaming-kafka" % "1.6.2"
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
