const symptoms = require('../controller/symptoms');
const cases = require('../controller/cases');

module.exports = function (app, model) {
	app.get('/symptoms', symptoms.get);
  	app.get('/cases', cases.get);
}