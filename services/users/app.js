
var express = require('express');
var bodyParser = require('body-parser')

var db = require('./model/models')();
// setup the services layer
var app = express();
app.use('/api/v1/', require('./api.js')());

app.listen(3001);
console.log('Listetning on 3001');
