var kafkaPublisher = require('./data/KafkaPublisher');
const mongoose = require('mongoose');
var db = require('./model/models')();

var Cases = mongoose.model('Case');
Cases.find({}, function(err, cases) {
  if (err)
    console.log(err);
  else {
      cases.patient.locality = 'locality';
      cases.patient.subdistrict = 'subdistrict';
      cases.patient.district = 'district';
      kafkaPublisher.publish(JSON.stringify(cases));
  }
});
