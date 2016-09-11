var mongoose = require('mongoose');
var fs = require('fs');
var db = mongoose.connection;

db.on('error', console.error);
db.once('open', function() {
  var Symptoms = require('./model/Symptoms.js');
  var Cases = require('./model/Cases.js');
  var Conditions = require('./model/Conditions.js');
  var Patients = require('./model/Patients.js');
  var Doctors = require('./model/Doctors.js');
  var Rules = require('./model/AssociationRules.js');

  Cases.find({}, function(err, cases) {
    if (err)
      console.log(JSON.stringify({}));
    else {
      var data = exportCaseSymtomsList(cases);
      fs.writeFile("db_case_symptoms.json", data, function(err) {
      if(err) {
          return console.log(err);
      }
      console.log("The file was saved!");
})
    }
  });

});

mongoose.connect('mongodb://localhost/test');

function exportCaseSymtomsList (cases) {
  var all = []
  for (i = 0; i < cases.length; i++) { 
      var symptomIds = []
      
      var clinicalList = cases[i].data.clinical;
      for (j = 0; j < clinicalList.length; j++) { 
           symptomIds.push(clinicalList[j].id);
      } 
      all.push(symptomIds.join())
  }
  return all.join('\n');
}
