var mongoose = require('mongoose');
var express = require('express');
var bodyParser = require('body-parser')

var app = express()

var db = mongoose.connection;

app.use(bodyParser.json())

db.on('error', console.error);
db.once('open', function() {
  var Symptoms = require('./model/Symptoms.js');
  var Cases = require('./model/Cases.js');
  var Conditions = require('./model/Conditions.js');
  var Patients = require('./model/Patients.js');
  var Doctors = require('./model/Doctors.js');
  var Rules = require('./model/AssociationRules.js');
});

mongoose.connect('mongodb://localhost/test');

var SymptomRoutes = require('./routes/routes.js')(app);
/*
app.get('/symptoms', function (req, res) {
  Symptoms.findOne({id: 'STD-02'}, function(err, symptom) {
    if (err)
      res.send(JSON.stringify({}));
    else
      res.send(JSON.stringify(symptom));
  });
});
*/
app.listen(3000)
