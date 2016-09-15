import org.apache.spark.mllib.fpm.FPGrowth
import org.apache.spark.rdd.RDD

object AssociatedSymptomsTask extends App {

	def generateRules(data: RDD[Array[String]]) : RDD[String] = {
		val fpg = new FPGrowth().setMinSupport(0.3).setNumPartitions(1)
		val model = fpg.run(data)

		val minConfidence = 0.1
		println("generateRules");
		model.generateAssociationRules(minConfidence).collect().foreach { rule =>
	  		println(
	    		rule.antecedent.mkString("[", ",", "]")
	      		+ " => " + rule.consequent .mkString("[", ",", "]")
	      		+ ", " + rule.confidence)
	  	}

		model.generateAssociationRules(minConfidence).map { rule =>
	      rule.antecedent.mkString(",") + ":" + rule.consequent .mkString(",") + ":" + rule.confidence
		}
	}


	val raw = sc.textFile("hdfs://naima/processed/symptoms")
	val data: RDD[Array[String]] = raw.map(s => s.trim.split(','))
	data.cache()
	println(data.count());
	generateRules(data).saveAsTextFile("hdfs://naima/processed/association_rules")

}
AssociatedSymptomsTask.main(Array())
