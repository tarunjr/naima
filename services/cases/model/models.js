var mongoose = require('mongoose');

module.exports = function(){
  var db = mongoose.connection;
  db.on('error', console.error);
  db.once('open', function() {
    console.log('connected to mongod');
  });

  // set up the db layer
  mongoose.connect('mongodb://localhost/cases');

  var Case = mongoose.model('Case',require('./Cases'),'cases');

  return {
    Case: Case
  }
};
