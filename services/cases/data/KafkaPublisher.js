const avro = require('avsc');
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
  console.log('connected')
  const type = avro.parse({
    name: 'Pet',
    type: 'record',
    fields: [
      {name: 'name', type: 'string'}
    ]
  });

  const buf = type.toBuffer({name: 'Albert'}); // Encoded buffer.
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


  console.log(err)
});
