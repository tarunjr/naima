val input = sc.textFile("hdfs://naima/raw/symptoms")

val conditions = input.map(line => line.split("\t")(0))
	.saveAsTextFile("hdfs://naima/processed/conditions")

val symptoms = input.map{line =>
  val tokens = line.split("\t")
  val count = tokens.length
  tokens.slice(1, count)
}

symptoms.map(_.mkString(","))
		.saveAsTextFile("hdfs://naima/processed/symptoms")

symptoms.flatMap(x => x)
		.saveAsTextFile("hdfs://naima/processed/symptoms_set")
