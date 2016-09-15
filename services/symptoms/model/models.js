var mongoose = require('mongoose');

module.exports = function(){
  var db = mongoose.connection;
  db.on('error', console.error);
  db.once('open', function() {
    console.log('connected to mongod');
  });

  // set up the db layer
  mongoose.connect('mongodb://localhost/test');

  var Symptom =  mongoose.model('Symptom', require('./Symptoms'), 'symptoms');

  var Case = mongoose.model('Case',require('./Cases'),'cases');

  var Condition = mongoose.model('Condition',require('./Conditions'),'conditions');

  var Patient = mongoose.model('Patient',require('./Patients'),'patients');

  var Doctor = mongoose.model('Doctor',require('./Doctors'), 'doctors');

  var AssociationRule = mongoose.model('AssociationRule',require('./AssociationRules'),'associationrules');

  return {
    Symptom:Symptom,
    Case: Case,
    Patient: Patient,
    Doctor: Doctor,
    AssociationRule: AssociationRule
  }
};
