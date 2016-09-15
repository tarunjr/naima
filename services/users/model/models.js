var mongoose = require('mongoose');

module.exports = function(){
  var db = mongoose.connection;
  db.on('error', console.error);
  db.once('open', function() {
    console.log('connected to mongod');
  });

  // set up the db layer
  mongoose.connect('mongodb://localhost/users');

  var Patient = mongoose.model('Patient',require('./Patients'),'patients');

  var Doctor = mongoose.model('Doctor',require('./Doctors'), 'doctors');

  var CareProvider = mongoose.model('CareProvider',require('./CareProviders'), 'careproviders');

  return {
    Patient: Patient,
    Doctor: Doctor,
    CareProvider: CareProvider
  }
};
