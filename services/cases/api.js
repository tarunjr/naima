var express = require('express');
var bodyparser = require('body-parser');

const cases = require('./controller/cases');

module.exports = function() {
	var api = express.Router();
	api.use(bodyparser.json());

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

  return api;
}
