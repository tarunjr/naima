const mongoose = require('mongoose');

exports.get = function(req, res) {
	var Conditions = mongoose.model('Condition');
	Conditions.find({}, function(err, condition) {
    if (err)
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(condition));
  });
}
exports.getInfo = function(req, res) {
	var Conditions = mongoose.model('Condition');
	var conditionId = req.params.conditionId;
	Conditions.findOne({id:conditionId}, function(err, condition) {
    if (err)
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(condition));
  });
}
exports.getBySpeciality = function(req, res) {
	var Conditions = mongoose.model('Condition');
	var specialityId = req.params.specialityId;
	Conditions.find({"speciality.id":specialityId}, function(err, condition) {
    if (err)
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(condition));
  });
}
