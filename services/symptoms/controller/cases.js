const mongoose = require('mongoose');

exports.get = function(req, res) {
	var Cases = mongoose.model('Cases');
	Cases.find({}, function(err, caseInfo) {
    if (err) 
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(caseInfo));
  });
}