const mongoose = require('mongoose');


exports.get = function(req, res) {
	var Symptoms = mongoose.model('Symptoms');
	Symptoms.find({}, function(err, symptom) {
    if (err) 
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(symptom));
  });
}
exports.getInfo = function(req, res) {
	var Symptoms = mongoose.model('Symptoms');
	var symptomId = req.params.symptomId;
	Symptoms.findOne({id:symptomId}, function(err, symptom) {
    if (err) 
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(symptom));
  });
}
exports.getStandard = function(req, res) {
	var Symptoms = mongoose.model('Symptoms');
	Symptoms.find({format:"standard"}, function(err, symptom) {
    if (err) 
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(symptom));
  });
}
exports.getClinical = function(req, res) {
	var Symptoms = mongoose.model('Symptoms');
	Symptoms.find({format:"clinical"}, function(err, symptom) {
    if (err) 
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(symptom));
  });
}
exports.getDiagnostic = function(req, res) {
	var Symptoms = mongoose.model('Symptoms');
	Symptoms.find({format:"diagnsotic"}, function(err, symptom) {
    if (err) 
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(symptom));
  });
}
exports.getId = function(req, res) {
	var Symptoms = mongoose.model('Symptoms');
	var symptomId = req.symptomId;
	console.log("getID");
	console.log(symptomId);
	Symptoms.find({id: symptomId}, function(err, symptom) {
    if (err) 
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(symptom));
  });
}