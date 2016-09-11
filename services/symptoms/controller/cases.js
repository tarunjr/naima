const mongoose = require('mongoose');

exports.get_open_cp_data = function(req, res) {
	var provider_id = req.params.id;
	var Cases = mongoose.model('Cases');
	console.log(provider_id)
	Cases.find({ status:'open', 'provider.id': provider_id, owner:"cp"}, function(err, cases) {
	if (err)
	  res.send(JSON.stringify({}));
	else
	  res.send(JSON.stringify(cases));
	});

}
exports.get_open_doc_data = function(req, res) {
	var provider_id = req.params.id;
	var Cases = mongoose.model('Cases');
	Cases.find({ status:'open', 'doctor.id': provider_id, owner:"doctor"}, function(err, cases) {
	if (err)
	  res.send(JSON.stringify({}));
	else
	  res.send(JSON.stringify(cases));
	});

}
exports.get_one_case = function(req, res) {
	var provider_id = req.params.id;
	var Cases = mongoose.model('Cases');
	Cases.find({ _id: provider_id}, function(err, cases) {
	if (err)
	  res.send(JSON.stringify({}));
	else
	  res.send(JSON.stringify(cases[0]));
	});

}
exports.get_pending_cp_data = function(req, res) {
	var provider_id = req.params.id;
	var Cases = mongoose.model('Cases');
	Cases.find({ status:'open', 'provider.id': provider_id, owner:"doctor"}, function(err, cases) {
	if (err)
	  res.send(JSON.stringify({}));
	else
	  res.send(JSON.stringify(cases));
	});
}
exports.get_pending_doc_data = function(req, res) {
	var provider_id = req.params.id;
	var Cases = mongoose.model('Cases');
	Cases.find({ status:'open', 'doctor.id': provider_id, owner:"cp"}, function(err, cases) {
	if (err)
	  res.send(JSON.stringify({}));
	else
	  res.send(JSON.stringify(cases));
	});
}
exports.get_closed_cp_data = function(req, res) {
	var provider_id = req.params.id;
	var Cases = mongoose.model('Cases');
	Cases.find({ status:'close', 'provider.id': provider_id}, function(err, cases) {
	if (err)
	  res.send(JSON.stringify({}));
	else
	  res.send(JSON.stringify(cases));
	});
}
exports.get_existing_patient_data = function(req, res) {
	var provider_id = req.params.patientid;
	var Cases = mongoose.model('Cases');
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
	var Cases = mongoose.model('Cases');
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
	case_data = { status : "open", owner :"doctor", data : data.data, provider: data.provider };

	var Conditions = mongoose.model('Conditions');
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
		var Doctors  =  mongoose.model('Doctors');
		// res.send("success");
		Doctors.find({"speciality.id" : maxid }, function(err, data){
			if (err)
			  res.send("failed");
			else{
				var Cases = mongoose.model('Cases');
				case_data.doctor = {};
				/*
				case_data.doctor['id'] = data[0].id;
				case_data.doctor['name'] = data[0].name;
				console.log("Doctor matached for user");
				*/
				Cases.create(case_data, function(err, data){
					if(err)
						res.send("failed");
					else {
						res.send("success");
						console.log("Insert success");
					}
				});

			}
		});

	});
	// Conditions.find({'data.clinical.id' : data.data.clinical[iCount].id, 'data.clinical.value' : data.data.clinical[iCount].value}, function(err, data){
	//
	// 	}
	// });

	// var Cases = mongoose.model('Cases');
	// Cases.create(case_data, function(err, data){
	// 	if(err)
	// 		res.send("failed");
	// 	else {
	// 		res.send("success");
	// 	}
	// });
}
