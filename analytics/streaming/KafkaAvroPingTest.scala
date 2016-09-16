package org.naima.analytics.streaming

import kafka.serializer.StringDecoder
import kafka.serializer.DefaultDecoder

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.kafka._
import org.apache.spark.SparkConf

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import org.naima.avro.Ping
import com.twitter.bijection.Injection
import com.twitter.bijection.avro.SpecificAvroCodecs

import java.util.HashMap
import scala.util.{Failure, Success}

object KafkaAvroPingTest {
  def run() {
    val brokers = "localhost:9092"
    val topics = "pingin"

    val sparkConf = new SparkConf().setAppName("KafkaAvroPingTest")
    val ssc = new StreamingContext(sparkConf, Seconds(2))
    ssc.checkpoint("/home/vagrant/checkpoint")

    val topicsSet = topics.split(",").toSet
    val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers)

    val kafkaStream = KafkaUtils.createDirectStream[String, Array[Byte], StringDecoder, DefaultDecoder](
      ssc, kafkaParams, topicsSet
      ).map(_._2)


    // We also use a broadcast variable for Bijection/Injection.
    //val converter = ssc.sparkContext.broadcast(specificAvroBinaryInjectionForPet)

    kafkaStream.map{ bytes =>
      implicit val converter = SpecificAvroCodecs.toBinary[Ping]
      converter.invert(bytes) match {
        case Success(ping) => ping
        case Failure(e) =>
      }
    }
    .foreachRDD{ rdd =>
          rdd.foreachPartition{ partitionOfRecords =>
                val brokers2 = "localhost:9092"

                // Zookeeper connection properties
                val props = new HashMap[String, Object]()
                props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers2)
                props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                  "org.apache.kafka.common.serialization.StringSerializer")
                props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                  "org.apache.kafka.common.serialization.StringSerializer")

                val producer = new KafkaProducer[String, String](props)

                partitionOfRecords.foreach { case ping: Ping =>
                    val message=new ProducerRecord[String, String]("pingout","key",ping.getName().toString())
                    producer.send(message)
                }
        }
    }
    ssc.start()
    ssc.awaitTermination()
  }
}
