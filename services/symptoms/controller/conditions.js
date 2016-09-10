const mongoose = require('mongoose');

exports.get = function(req, res) {
	var Conditions = mongoose.model('Conditions');
	Conditions.find({}, function(err, condition) {
    if (err) 
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(condition));
  });
}
exports.getInfo = function(req, res) {
	var Conditions = mongoose.model('Conditions');
	var conditionId = req.params.conditionId;
	Conditions.findOne({id:conditionId}, function(err, condition) {
    if (err) 
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(condition));
  });
}

