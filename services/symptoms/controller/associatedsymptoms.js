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
exports.post = function(req, res) {
	var data = req.body;
	
	var test = data.test;

	//console.log(data);
	//console.log(data.clinical);
	var ids =  []
	if (typeof data.clinical != 'undefined') {
		for (i = 0; i < data.clinical.length; i++) { 
    		ids.push(data.clinical[i].id);
		}
	}
	if (typeof data.test != 'undefined') {
		for (i = 0; i < data.test.length; i++) { 
    		ids.push(data.test[i].id);
		}
	}
	var uniqIds = new Set(ids);
	console.log(uniqIds);

	var Rules = mongoose.model('AssociationRules');
	Rules.find({}, function(err, rule) {
    	if (err) 
      		res.send(JSON.stringify("ERROR"));
    	else {
      		var newSymIds = associated(uniqIds, rule);
      		var Symptoms = mongoose.model('Symptoms');
			Symptoms.find({ 'id' : {$in: newSymIds}})
			.exec( function (err, data){})
			.then(function (data){
				console.log(JSON.stringify(data));
				res.send(JSON.stringify(data));
			})
		}	
	});
}

function associated(ids, rule, res) {
	console.log("associated symptons");
	console.log(ids);
	var newIds = [];
	for (i = 0; i < rule.length; i++) { 
    	var ruleIds = new Set(rule[i].left);
    	//console.log(ruleIds);
    	if (containsAllEx(ids, ruleIds) == true) {
    		newIds.push.apply(newIds, rule[i].right);
    	}
	}
	var newIdSet = new Set(newIds);
	var result = []
	newIdSet.forEach(function(value) {
  		if(ids.has(value) == false)
  			result.push(value)
	});
	return result;
}
function containsAllEx(id1, id2) {
	//console.log("foo");
	//console.log(id1);
	//console.log(id2);
	
	var myArr = Array.from(id2);
	var intersection = new Set(myArr.filter(x => id1.has(x)));
	//console.log(intersection);

	return intersection.size == id2.size;
}
