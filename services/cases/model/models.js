var mongoose = require('mongoose');

module.exports = function(){
  var db = mongoose.connection;
  db.on('error', console.error);
  db.once('open', function() {
    console.log('connected to mongod');
  });

  // set up the db layer
  mongoose.connect('mongodb://'
                    + process.env.MONGO_PORT_27017_TCP_ADDR
                    + ':'
                    + process.env.MONGO_PORT_27017_TCP_PORT
                    + '/cases');

  var Case = mongoose.model('Case',require('./Cases'),'cases');

  return {
    Case: Case
  }
};
