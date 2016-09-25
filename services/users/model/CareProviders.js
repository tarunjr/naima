const mongoose = require('mongoose');

var CareProviderSchema = {
  	name: String,
  	id:  String,
  	address: {
    	locality: String,
      	subDistrict: String,
      	district: String
  	}
}

module.exports = new mongoose.Schema(CareProviderSchema);
module.exports.CareProviderSchema = CareProviderSchema;
