var mongoose = require('mongoose');

module.exports = function() {
  // set up the db layer
  var db = mongoose.connection;
  db.on('error', console.error);
  db.once('open', function() {
    require('./models');
  });

  mongoose.connect('mongodb://localhost:27017/test');
}
