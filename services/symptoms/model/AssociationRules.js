const mongoose = require('mongoose');

const Schema = mongoose.Schema;

var rulesSchema = new Schema({
  	left: [String],
    right: [String],
    confidence: Number
  });
module.exports = mongoose.model('AssociationRules', rulesSchema);