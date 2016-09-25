const mongoose = require('mongoose');
const Schema = mongoose.Schema;

var DoctorSchema = {
  	name: String,
  	id:  String,
  	address: {
    	locality: String,
      	subDistrict: String,
      	district: String
  	},
  	speciality: [{
  		id: String,
  		name: String
  	}]
  }

module.exports = new mongoose.Schema(DoctorSchema);
module.exports.DoctorSchema = DoctorSchema;
