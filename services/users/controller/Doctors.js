const mongoose = require('mongoose');

exports.get = function(req, res) {
	var Doctor = mongoose.model('Doctor');
	Doctor.find({}, function(err, doctor) {
    if (err)
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(doctor));
  });
}
exports.getById= function(req, res) {
	var Doctor = mongoose.model('Doctor');
	var doctorId = req.params.doctorId;
	Doctor.findOne({id:doctorId}, function(err, doctor) {
    if (err)
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(doctor));
  });
}
exports.getBySpeciality= function(req, res) {
	var Doctor = mongoose.model('Doctor');
	var specialityId = req.params.specialityId;
	Doctor.findOne({"speciality.id":specialityId}, function(err, doctor) {
    if (err)
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(doctor));
  });
}
