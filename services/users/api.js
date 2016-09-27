var express = require('express');
var bodyparser = require('body-parser');

const doctors = require('./controller/Doctors')
const patients = require('./controller/Patients')
const careproviders = require('./controller/CareProviders')


module.exports = function() {
	var api = express.Router();
	api.use(bodyparser.json());

	api.get('/doctors/', doctors.get);
	api.get('/doctors/:doctorId', doctors.get);

	api.get('/patients/', patients.get);
  api.get('/patients/:patientId', patients.get);

	api.get('/careproviders/', careproviders.get);
  api.get('/careproviders/:providerId', careproviders.get);

  return api;
}
