const mongoose = require('mongoose');


exports.get = function(req, res) {
	var Symptoms = mongoose.model('Symptoms');
	Symptoms.findOne({id: 'STD-02'}, function(err, symptom) {
    if (err) 
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(symptom));
  });
}