var kafka = require('kafka-node'),
    HighLevelConsumer = kafka.HighLevelConsumer,
    client = new kafka.Client(),
    consumer = new HighLevelConsumer(
        client,
        [
            { topic: 'naimain' }
        ],
        {
            groupId: 'naima-loader'
        }
    )

consumer.on('message', function (message) {
  console.log(message.topic);
  console.log(message.value);
  console.log(message.key.toString('ascii'));
});

consumer.on('error', function (err) {
  console.log(err)
})
