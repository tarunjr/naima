//spark$ bin/spark-shell --jars /src/naima/data/streaming/spark-streaming-kafka-0-8-assembly_2.11-2.0.0.jar --master local[2]

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.Minutes
import org.apache.spark.streaming.kafka.KafkaUtils
import kafka.serializer.StringDecoder
import java.util.HashMap


object KafkaWordCount {
  def main(args: Array[String]) {
    if (args.length < 2) {
      System.err.println("DirectKafkaWordCount <brokers> <topics>")
      System.exit(1)
      }
    val Array(brokers, topics) = args
    //val sparkConf = new SparkConf().setAppName("KafkaWordCount")
    val ssc = new StreamingContext(sc, Seconds(2))
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

          val brokers = "localhost:9092"

          // Zookeeper connection properties
          val props = new HashMap[String, Object]()
          props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
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
val args = new Array[String](2)
args(0) = "localhost:9092";
args(1) = "naima";


KafkaWordCount.main(args)
