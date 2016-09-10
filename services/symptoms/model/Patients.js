const mongoose = require('mongoose');

const Schema = mongoose.Schema;

var patientsSchema = new Schema({
  	name: String,
  	id:  String,
  	address: {
    	locality: String,
      	subDistrict: String,
      	district: String
  	}
  });
module.exports = mongoose.model('Patients', patientsSchema);