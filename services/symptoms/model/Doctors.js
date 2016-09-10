const mongoose = require('mongoose');

const Schema = mongoose.Schema;

var doctorsSchema = new Schema({
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
  });
module.exports = mongoose.model('Docotors', doctorsSchema);