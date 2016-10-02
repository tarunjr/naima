var express = require('express');
var bodyparser = require('body-parser');

const cases = require('./controller/cases');

module.exports = function() {
	var api = express.Router();
	api.use(bodyparser.json());

  api.get('/cases/', cases.get);
  //api.get('/cases/open/cp/:id', cases.get_open_cp_data);
	//?status=open&owner=careprovider

	//api.get('/cases/open/doc/:id', cases.get_open_doc_data);
	//?status=open&owner=doctor&ownerId=docId

	//api.get('/cases/one/:id', cases.get_one_case);
	//:caseId

	//api.get('/cases/pending/cp/:id', cases.get_pending_cp_data);
	//?status=pending&owner=careprovider&ownerid=providerid

	//api.get('/cases/pending/doc/:id', cases.get_pending_doc_data);
	//?status=pending&owner=doctor&ownerid=doctorid

	//api.get('/cases/closed/cp/:id', cases.get_closed_cp_data);
	//?status=closed&owner=careprovider&ownerid=providerid

	//api.get('/cases/existing/:patientid', cases.get_existing_patient_data);
	//?patientid=pid

	api.post('/cases/one/:id',cases.put_case_data);
  api.post('/cases/new',cases.put_new_case_data);

  return api;
}
