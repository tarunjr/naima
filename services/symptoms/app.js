var mongoose = require('mongoose');
var express = require('express');

var app = express()

var db = mongoose.connection;

db.on('error', console.error);
db.once('open', function() {
  var Symptoms = require('./model/Symptoms.js');
  var Cases = require('./model/Cases.js');
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