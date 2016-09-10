const mongoose = require('mongoose');

const Schema = mongoose.Schema;

var caseSchema = new Schema({
  	id:  String,
  });
module.exports = mongoose.model('Cases', caseSchema);