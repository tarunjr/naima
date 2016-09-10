const symptoms = require('../controller/symptoms');
const cases = require('../controller/cases');

module.exports = function (app, model) {
	app.get('/symptoms', symptoms.get);
	app.get('/symptoms/:symptomId/info', symptoms.getInfo);
	app.get('/symptoms/standard/', symptoms.getStandard);
	app.get('/symptoms/clinical', symptoms.getClinical);
	app.get('/symptoms/diagnostic', symptoms.getDiagnostic);
  	app.get('/cases', cases.get);
}