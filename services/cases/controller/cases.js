const mongoose = require('mongoose');

exports.get_open_cp_data = function(req, res) {
	var provider_id = req.params.id;
	var Cases = mongoose.model('Case');
	console.log(provider_id)
	Cases.find({ status:'open', 'provider.id': provider_id, owner:"cp"}, function(err, cases) {
	if (err)
	  res.send(JSON.stringify({}));
	else
	  res.send(JSON.stringify(cases));
	});

}

exports.get_all_data = function(req, res) {
	var Cases = mongoose.model('Case');
	Cases.find({ }, function(err, cases) {
	if (err)
	  res.send(JSON.stringify({}));
	else
	  res.send(JSON.stringify(cases));
	});
}

exports.get_open_doc_data = function(req, res) {
	var provider_id = req.params.id;
	var Cases = mongoose.model('Case');
	Cases.find({ status:'open', 'doctor.id': provider_id, owner:"doctor"}, function(err, cases) {
	if (err)
	  res.send(JSON.stringify({}));
	else
	  res.send(JSON.stringify(cases));
	});
}

exports.get_one_case = function(req, res) {
	var provider_id = req.params.id;
	var Cases = mongoose.model('Case');
	Cases.find({ _id: provider_id}, function(err, cases) {
	if (err)
	  res.send(JSON.stringify({}));
	else
	  res.send(JSON.stringify(cases[0]));
	});
}

exports.get_pending_cp_data = function(req, res) {
	var provider_id = req.params.id;
	var Cases = mongoose.model('Case');
	Cases.find({ status:'open', 'provider.id': provider_id, owner:"doctor"}, function(err, cases) {
	if (err)
	  res.send(JSON.stringify({}));
	else
	  res.send(JSON.stringify(cases));
	});
}

exports.get_pending_doc_data = function(req, res) {
	var provider_id = req.params.id;
	var Cases = mongoose.model('Case');
	Cases.find({ status:'open', 'doctor.id': provider_id, owner:"cp"}, function(err, cases) {
	if (err)
	  res.send(JSON.stringify({}));
	else
	  res.send(JSON.stringify(cases));
	});
}

exports.get_closed_cp_data = function(req, res) {
	var provider_id = req.params.id;
	var Cases = mongoose.model('Case');
	Cases.find({ status:'close', 'provider.id': provider_id}, function(err, cases) {
	if (err)
	  res.send(JSON.stringify({}));
	else
	  res.send(JSON.stringify(cases));
	});
}
exports.get_existing_patient_data = function(req, res) {
	var provider_id = req.params.patientid;
	var Cases = mongoose.model('Case');
	Cases.find({'patientid.id': provider_id}, function(err, cases) {
	if (err)
	  res.send(JSON.stringify({}));
	else
	  res.send(JSON.stringify(cases));
	});
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
	case_data.doctor = {};
	case_data.doctor['id'] = 'DT-01';
	case_data.doctor['name'] = 'Dr Vikram Mehta';
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
