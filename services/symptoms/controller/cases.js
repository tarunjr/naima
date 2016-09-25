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
	var provider_id = req.params.id;
	var Cases = mongoose.model('Case');
	console.log(provider_id)
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

	var Conditions = mongoose.model('Condition');
	symptomIds = [];
	for(iCount = 0; iCount < data.data.clinical.length; iCount++){
		symptomIds.push(data.data.clinical[iCount].id);
	}
	console.log(symptomIds);
	Conditions.find({ 'data.clinical.id' : {$in: symptomIds}})
	.exec( function (err, data){

	})
	.then(function (data){
		map = new Map();
		for(iCount = 0; iCount < data.length; iCount++){
			map.get(data[iCount].speciality.id) == undefined ? map.set(data[iCount].speciality.id, 1) : map.set(data[iCount].speciality.id, map.get(data[iCount].speciality.id) + 1);
		}
		console.log(map);
		maxid = "";
		maxval = 0;
		map.forEach( function(value, key){
			if(maxval < value ){
				maxval = value;
				maxid = key;
			}
		}, map);
		var Doctors  =  mongoose.model('Doctor');
		// res.send("success");
		Doctors.find({"speciality.id" : maxid }, function(err, data){
			if (err)
			  res.send("failed");
			else{
				var Cases = mongoose.model('Cases');
				var selectedDoctor = data[0];
				case_data.doctor = {};
				case_data.doctor['id'] = selectedDoctor.id;
				case_data.doctor['name'] = selectedDoctor.name;
				console.log("Doctor matached for user");
				Cases.create(case_data, function(err, data){
					if(err)
						res.send("failed");
					else {
						res.send(JSON.stringify(selectedDoctor));
						console.log(JSON.stringify(selectedDoctor));
					}
				});

			}
		});

	});
}
