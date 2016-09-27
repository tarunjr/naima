const mongoose = require('mongoose');
// native promises
mongoose.Promise = global.Promise;

exports.get = function(req, res) {
	var query = {};
	if (req.query.speciality != null) {
			query = { 'speciality.id' : req.query.speciality };
	} else if (req.query.locality != null){
		 query = {'address.locality': req.query.locality };
	} else if (req.query.subdistrict != null){
		 query = {'address.subDistrict': req.query.subdistrict };
	} else if (req.query.district != null){
		 query = {'address.district': req.query.district };
	}  else if (req.params.doctorId != null) {
		query = {'id': req.params.doctorId};
	}
	var Doctor = mongoose.model('Doctor');
	Doctor.find(query, function(err, doctor) {
    if (err)
      res.send(JSON.stringify({}));
    else {
			res.send(JSON.stringify(doctor, null,'\t'));
		}
  });
}
