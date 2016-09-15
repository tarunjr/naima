//spark$ bin/spark-shell --jars /src/naima/analytics/streaming/spark-streaming-kafka-0-8-assembly_2.11-2.0.0.jar,
//org.apache.spark:spark-streaming_2.10:1.4.1,
//com.databricks:spark-avro_2.11:3.0.0 --master local[2]

package org.naima.analytics.streaming

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.Minutes
import org.apache.spark.streaming.kafka._
import kafka.serializer.StringDecoder
import java.util.HashMap
/*
import org.naima.avro.Pet
import com.twitter.bijection.Injection
import com.twitter.bijection.avro.SpecificAvroCodecs
*/

object KafkaWordCount {
  def main(args: Array[String]) {
    val brokers = "localhost:9092"
    val topics = "newcase"

    val sparkConf = new SparkConf().setAppName("KafkaWordCount")
    val ssc = new StreamingContext(sparkConf, Seconds(2))
    ssc.checkpoint("/home/vagrant/checkpoint")

    val topicsSet = topics.split(",").toSet
    val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers)

    val messages = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
      ssc, kafkaParams, topicsSet)

    messages.foreachRDD{ rdd =>
          rdd.foreach{
            w => println(w)
          }
    }

    val replies = messages.map(w => w._2)

    replies.foreachRDD{ rdd =>
          rdd.foreachPartition{ partition =>
          // Create a kafka produce and send the response

          val brokers2 = "localhost:9092"

          // Zookeeper connection properties
          val props = new HashMap[String, Object]()
          props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers2)
          props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
              "org.apache.kafka.common.serialization.StringSerializer")
          props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
              "org.apache.kafka.common.serialization.StringSerializer")

          val producer = new KafkaProducer[String, String](props)

          partition.foreach { out =>
            println(out)
            val message=new ProducerRecord[String, String]("naimain","key",out)
            producer.send(message)
          }
        }
    }

    ssc.start()
    ssc.awaitTermination()
  }
}
//val args = new Array[String](2)
//args(0) = "localhost:9092";
//args(1) = "newcase";

//implicit val specificAvroBinaryInjectionForPet = SpecificAvroCodecs.toBinary[Pet]


//KafkaWordCount.main(args)
