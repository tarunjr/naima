const avro = require('avsc');
const caseAvro = require('./CaseAvroSchema');

var kafka = require('kafka-node'),
    Producer = kafka.HighLevelProducer,
    client = new kafka.Client(),
    producer = new Producer(client);

var connected = false;

producer.on('error', function() {
  connected = false;
});

producer.on('ready', function (err) {
  connected = true;
});

module.exports.publish = function(caseObj) {
  console.log('connected')

  const buf = caseAvro.toBuffer(JSON.stringify(caseObj)); // Encoded buffer.
  var km = new kafka.KeyedMessage('key', buf);
  var payloads = [{topic:'newcase', messages:[km]}];
  producer.send(payloads, function(err, data){
      if (err){
        console.log(err);
      }
      else {
        console.log(data);
      }
  });
}
