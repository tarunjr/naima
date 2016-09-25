package org.naima.analytics.streaming

import kafka.serializer.StringDecoder
import kafka.serializer.DefaultDecoder

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming._
import org.apache.spark.streaming.dstream._
import org.apache.spark.streaming.kafka._
import org.apache.spark.SparkConf

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import org.naima.avro.Case
import com.twitter.bijection.Injection
import com.twitter.bijection.avro.SpecificAvroCodecs

import java.util.HashMap
import scala.util.{Failure, Success}


object TreadingConditionTask {
	def main(args: Array[String]) {

			val brokers = "localhost:9092"
			val topics = "newcase"

			val sparkConf = new SparkConf().setAppName("TreadingConditionTask")
			val ssc = new StreamingContext(sparkConf, Seconds(1))
			ssc.checkpoint("~/tmp/checkpoint")

			val topicsSet = topics.split(",").toSet
			val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers)

			val kafkaStream = KafkaUtils.createDirectStream[String, Array[Byte], StringDecoder, DefaultDecoder](
				ssc, kafkaParams, topicsSet).map(_._2)

			// transform data from bytes to POJO using Avro deserialization
			val cases:DStream[Case] = kafkaStream.map{ bytes =>
		      implicit val converter = SpecificAvroCodecs.toBinary[Case]
		      converter.invert(bytes) match {
		        case Success(caseObj) => caseObj
		        case Failure(e) => new Case()
		      }
		    }

			// get the winowed based counts
			val counts:DStream[String] = getAggregatedCounts(cases)

			// publish the counts
			publish(counts)

			// start the stream processing
			ssc.start()
		  ssc.awaitTermination()
	}

	def getAggregatedCounts(cases: DStream[Case]) : DStream[String] = {
			cases
			.map{	caseObj =>
					val compositeKey = Array(caseObj.getPatient().getDistrict(),
																 caseObj.getPatient().getSubdistrict(),
																 caseObj.getPatient().getLocality() ,
																 "CONDITION")
																 (compositeKey.mkString("#"), 1)
			}
			.reduceByKeyAndWindow(
						{(x,y) => x+y},
						{(x,y) => x-y},
						Seconds(60),
						Seconds(20)
			)
			.map { count =>
				val tokens = count._1.split("#")
				tokens.mkString(",") + "," + count._2
			}
	}
	def publish(counts: DStream[String]) {
			counts.saveAsTextFiles("~/tmp/naimaTrends")
	}

	/*
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
	*/
}
