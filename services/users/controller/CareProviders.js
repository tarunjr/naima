const mongoose = require('mongoose');
// native promises
mongoose.Promise = global.Promise;

exports.get = function(req, res) {
	var query = {};
	// Filteration based on address and id
	if (req.query.locality != null){
		 query = {'address.locality': req.query.locality };
	} else if (req.query.subdistrict != null){
		 query = {'address.subDistrict': req.query.subdistrict };
	} else if (req.query.district != null){
		 query = {'address.district': req.query.district };
	} else if (req.params.patientId != null) {
		query = {'id': req.params.patientId};
	}

	var CareProvider = mongoose.model('CareProvider');
	var promise = CareProvider.find(query).exec();
	promise.then(function(provider){
		res.send(JSON.stringify(provider, null,'\t'));
	}).catch(function(err){
		res.send(JSON.stringify({}));
	});
}
