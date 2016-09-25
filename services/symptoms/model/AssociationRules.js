const mongoose = require('mongoose');

var AssociationRuleSchema = {
  	left: [String],
    right: [String],
    confidence: Number
  }

module.exports = new mongoose.Schema(AssociationRuleSchema);
module.exports.AssociationRuleSchema = AssociationRuleSchema;
