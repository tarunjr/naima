const avro = require('avsc');
var kafka = require('kafka-node'),
    Producer = kafka.HighLevelProducer,
    client = new kafka.Client(),
    producer = new Producer(client);

var connected = false;

producer.on('ready', function() {
  connected = true;
});

producer.on('error', function (err) {
  connected = false;
  console.log('connected')
  const type = avro.parse({
    name: 'Pet',
    type: 'record',
    fields: [
      {name: 'kind', type: {name: 'Kind', type: 'enum', symbols: ['CAT', 'DOG']}},
      {name: 'name', type: 'string'}
    ]
  });

  const buf = type.toBuffer({kind: 'CAT', name: 'Albert'}); // Encoded buffer.
  var km = new kafka.KeyedMessage('key', buf);
  var payloads = [{topic:'naima', messages:[km]}];

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
