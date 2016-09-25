var express = require('express');
var bodyparser = require('body-parser');

const symptoms = require('./controller/symptoms');
const cases = require('./controller/cases');
const conditions = require('./controller/conditions');
const associatedsymptons = require('./controller/associatedsymptoms')
const doctors = require('./controller/Doctors')
const patients = require('./controller/Patients')


module.exports = function() {
	var api = express.Router();
	api.use(bodyparser.json());

	api.get('/', function(req,res){
		res.send('v1 API: /symptoms,  /cases, /conditions, /associatedsymptons');
	});

  api.get('/symptoms', symptoms.get);
  api.get('/symptoms/:symptomId/info', symptoms.getInfo);
  api.get('/symptoms/standard/', symptoms.getStandard);
  api.get('/symptoms/clinical', symptoms.getClinical);
  api.get('/symptoms/diagnostic', symptoms.getDiagnostic);

	api.get('/associatedsymptons', associatedsymptons.get);
	api.post('/associatedsymptons',associatedsymptons.post);

  api.get('/cases/', cases.get_all_data);
  api.get('/cases/open/cp/:id', cases.get_open_cp_data);
  api.get('/cases/open/doc/:id', cases.get_open_doc_data);
  api.get('/cases/one/:id', cases.get_one_case);
  api.get('/cases/pending/cp/:id', cases.get_pending_cp_data);
  api.get('/cases/pending/doc/:id', cases.get_pending_doc_data);
  api.get('/cases/closed/cp/:id', cases.get_closed_cp_data);
  api.get('/cases/existing/:patientid', cases.get_existing_patient_data);
  api.post('/cases/one/:id',cases.put_case_data);
  api.post('/cases/new',cases.put_new_case_data);

  api.get('/conditions', conditions.get);
  api.get('/conditions/id/:conditionId', conditions.getInfo);
	api.get('/conditions/specialities/:specialityId', conditions.getBySpeciality);

	api.get('/doctors/', doctors.get);
  api.get('/doctors/id/:doctorId', doctors.getById);
	api.get('/doctors/specialities/:specialityId', doctors.getBySpeciality);

	api.get('/patients/', patients.get);
  api.get('/patients/id/:patientId', patients.getById);

  return api;
}
