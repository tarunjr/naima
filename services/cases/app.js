var express = require('express');
var bodyParser = require('body-parser')
var compression = require('compression');

// diagnostic message
console.log(process.env.MONGO_PORT_27017_TCP_ADDR);
console.log(process.env.MONGO_PORT_27017_TCP_PORT);

var db = require('./model/models')();
// setup the services layer
var app = express();
app.use('/api/v1/', require('./api.js')());
app.use(compression());

app.listen(8082);
console.log('Listetning on 8082');
