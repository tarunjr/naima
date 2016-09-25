const mongoose = require('mongoose');

exports.get = function(req, res) {
	var CareProvider = mongoose.model('CareProvider');
	CareProvider.find({}, function(err, provider) {
    if (err)
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(provider));
  });
}
exports.getById= function(req, res) {
	var CareProvider = mongoose.model('CareProvider');
	var providerId = req.params.providerId;
	CareProvider.findOne({id:patientId}, function(err, provider) {
    if (err)
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(provider));
  });
}
