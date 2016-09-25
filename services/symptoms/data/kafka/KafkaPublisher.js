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
  console.log(err)
});

module.exports.publish = function(type, msg){
  if (connected == false)
    return;

  var km = new kafka.KeyedMessage('key', msg);
  var payloads = [{topic:'naima', messages:[km]}];

  producer.send(payloads, function(err, data){
      if (err){
        console.log(err);
      }
      else {
        console.log(data);
      }
  });
}
