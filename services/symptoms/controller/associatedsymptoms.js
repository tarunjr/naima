const mongoose = require('mongoose');

exports.get = function(req, res) {
	var Rules = mongoose.model('AssociationRules');
	Rules.find({}, function(err, rule) {
    if (err) 
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(rule));
  });
}