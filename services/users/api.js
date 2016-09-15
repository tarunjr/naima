var express = require('express');
var bodyparser = require('body-parser');

const doctors = require('./controller/Doctors')
const patients = require('./controller/Patients')
const careproviders = require('./controller/CareProviders')


module.exports = function() {
	var api = express.Router();
	api.use(bodyparser.json());

	api.get('/doctors/', doctors.get);
  api.get('/doctors/id/:doctorId', doctors.getById);
	api.get('/doctors/specialities/:specialityId', doctors.getBySpeciality);

	api.get('/patients/', patients.get);
  api.get('/patients/id/:patientId', patients.getById);

	api.get('/careproviders/', careproviders.get);
  api.get('/careproviders/id/:providerId', careproviders.getById);

  return api;
}
