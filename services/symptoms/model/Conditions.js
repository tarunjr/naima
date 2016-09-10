const mongoose = require('mongoose');

const Schema = mongoose.Schema;

var conditionsSchema = new Schema({
  	id:  String,
    name: String,
  	speciality: {
    	id: String,
      	name: String
    },
    data: {
    	clinical: [{id:String, name:String}],
    	test: [{id:String, name:String}]
    }
  });
module.exports = mongoose.model('Conditions', conditionsSchema);
