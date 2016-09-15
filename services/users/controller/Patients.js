const mongoose = require('mongoose');

exports.get = function(req, res) {
	var Patient = mongoose.model('Patient');
	Patient.find({}, function(err, patient) {
    if (err)
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(patient));
  });
}
exports.getById= function(req, res) {
	var Patient = mongoose.model('Patient');
	var patientId = req.params.patientId;
	Patient.findOne({id:patientId}, function(err, patient) {
    if (err)
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(patient));
  });
}
