const mongoose = require('mongoose');

exports.get = function(req, res) {
	var query = buildDBQuery(req.params);
	var Cases = mongoose.model('Case');

	console.log(query);

	Cases.find(query, function(err, cases) {
	if (err)
	  res.send(JSON.stringify({}));
	else
	  res.send(JSON.stringify(cases));
	});
}

function buildDBQuery(var params){
		var query = {};
		if (req.query.status != null)
				query['status'] = req.query.status;
		if (req.query.owner != null) {
			 query['owner'] = req.query.owner;
			 if (req.query.ownerid != null) {
				 	if(req.query.owner == 'doctor') {
						query['doctor'] = {};
						query['doctor']['id'] = req.query.ownerid;
					} else if (req.query.owner == 'careprovider') {
						query['provider'] = {};
						query['provider']['id'] = req.query.ownerid;
					}
			 }
		}
		if (req.query.patientid != null) {
			 query['patient'] = {};
			 query['patient']['id'] = req.query.patientid;
		}
		return query;
}

exports.put_case_data = function(req, res) {
	pid = req.params.id;
	console.log("Id is " + pid);
	var Cases = mongoose.model('Case');
	cases = req.body;
	Cases.findByIdAndUpdate(pid, cases, function(err, cases) {
		if (err)
		  res.send("failed");
		else{
			res.send("success");
		}
	});
}
exports.put_new_case_data = function(req, res) {
	data = req.body;
	console.log(data);
	case_data = { status : "open", owner :"doctor", data : data.data, provider: data.provider, patient: data.patient };

	var Cases = mongoose.model('Case');
	// TODO insert doctor recommendation here.

	var speciality = {};
	speciality['id'] = "2";
	speciality['name'] = "GastroEntrology";

	var address = {};
	address['locality'] = 'koramangala';
	address['subDistrict'] = 'Bangalore';
	address['district'] = 'Bangalore';

	case_data.doctor = {};
	case_data.doctor['id'] = 'DT-01';
	case_data.doctor['name'] = 'Dr Vikram Mehta';
	case_data.doctor['speciality'] = speciality;
	case_data.doctor['address'] = address;

	console.log("Hard coded doctor");
	var selectedDoctor = case_data.doctor;

	Cases.create(case_data, function(err, data){
		if(err)
			res.send("failed");
		else {
			res.send(JSON.stringify(selectedDoctor));
			console.log(JSON.stringify(selectedDoctor));
   	}
	});
}
