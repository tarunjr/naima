const mongoose = require('mongoose');

const Schema = mongoose.Schema;

var symptomsSchema = new Schema({
  	id:  String,
    type: String,
  	info: {
    	title: String,
      	name: String,
      	format: String
  	}
  });
module.exports = mongoose.model('Symptoms', symptomsSchema);



