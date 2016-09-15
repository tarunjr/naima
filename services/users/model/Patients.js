const mongoose = require('mongoose');

var PatientInfo = {
  name: String,
  id:  String,
}
var PatientSchema = {
  	name: String,
  	id:  String,
  	address: {
    	locality: String,
      	subDistrict: String,
      	district: String
  	}
}

module.exports = new mongoose.Schema(PatientSchema);
module.exports.PatientSchema = PatientSchema;
