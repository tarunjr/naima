import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.rdd.{BlockRDD, RDD}
import org.apache.spark.streaming.dstream._
import scala.collection._
import scala.collection.mutable.Queue._



object TreadingDiseaseTask extends App {
	def getTestDStream(ssc : StreamingContext ): DStream[String] = {
		val batches = new mutable.Queue[RDD[String]]
		val batch1 = sc.textFile("file:///vagrant/nalanda/data/test/disease_locations_batch1.csv")
		val batch2 = sc.textFile("file:///vagrant/nalanda/data/test/disease_locations_batch2.csv")

		batches += batch1
		batches += batch2

		ssc.queueStream[String](batches)
	}
	def generateVillageAggreagte(data: DStream[String]) : DStream[String] = {
		data
			.map{x =>
				val tokens = x.split(",")
				val count = tokens.length
				val compositeKey = Array(tokens(2),tokens(4),tokens(6) ,tokens(count-1))
				(compositeKey.mkString("#"), 1)
			}
			.reduceByKey(_ + _).map{ count =>
				val tokens = count._1.split("#")
				tokens.mkString(",") + "," + count._2
			}
	}

	def generateSubDistrictAggreagte(data: DStream[String]) : DStream[String] = {
		data
			.map{x =>
				val tokens = x.split(",")
				val count = tokens.length
				val compositeKey = Array(tokens(2),tokens(4),tokens(count-1))
				(compositeKey.mkString("#"), 1)
			}
			.reduceByKey(_ + _).map{ count =>
				val tokens = count._1.split("#")
				tokens.mkString(",") + "," + count._2
			}
	}
	def generateDistrictAggreagte(data: DStream[String]) : DStream[String] = {
		data
			.map{x =>
				val tokens = x.split(",")
				val count = tokens.length
				val compositeKey = Array(tokens(2),tokens(count-1))
				(compositeKey.mkString("#"), 1)
			}
			.reduceByKey(_ + _).map{ count =>
				val tokens = count._1.split("#")
				tokens.mkString(",") + "," + count._2
			}
	}
	val ssc = new StreamingContext(sc, Seconds(10))

	val data = getTestDStream(ssc)

	generateVillageAggreagte(data)
		.saveAsTextFiles("file:///vagrant/nalanda/data/analyzed/dis_aggr","village")

	generateSubDistrictAggreagte(data)
		.saveAsTextFiles("file:///vagrant/nalanda/data/analyzed/dis_aggr","subdist")

	generateDistrictAggreagte(data)
		.saveAsTextFiles("file:///vagrant/nalanda/data/analyzed/dis_aggr","dist")

	ssc.start()
	ssc.awaitTermination()
}
TreadingDiseaseTask.main(Array())
