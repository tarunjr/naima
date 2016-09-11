const symptoms = require('../controller/symptoms');
const cases = require('../controller/cases');
const conditions = require('../controller/conditions');
const associatedsymptons = require('../controller/associatedsymptoms')

module.exports = function (app, model) {
	app.get('/symptoms', symptoms.get);
	app.get('/symptoms/:symptomId/info', symptoms.getInfo);
	app.get('/symptoms/standard/', symptoms.getStandard);
	app.get('/symptoms/clinical', symptoms.getClinical);
	app.get('/symptoms/diagnostic', symptoms.getDiagnostic);


	app.get('/cases/', cases.get_all_data);
	app.get('/cases/open/cp/:id', cases.get_open_cp_data);
	app.get('/cases/open/doc/:id', cases.get_open_doc_data);
	app.get('/cases/one/:id', cases.get_one_case);
	app.get('/cases/pending/cp/:id', cases.get_pending_cp_data);
	app.get('/cases/pending/doc/:id', cases.get_pending_doc_data);
	app.get('/cases/closed/cp/:id', cases.get_closed_cp_data);

	app.get('/cases/existing/:patientid', cases.get_existing_patient_data);

	app.post('/cases/one/:id',cases.put_case_data);
	app.post('/cases/new',cases.put_new_case_data);

  	app.get('/conditions', conditions.get);
	app.get('/conditions/:conditionId/info', conditions.getInfo);

	app.get('/associatedsymptons', associatedsymptons.get);
	app.post('/associatedsymptons',associatedsymptons.post);
}

