const avro = require('avsc');

var caseAvroSchema = {
    "name": "Case",
    "namespace": "org.naima.avro",
    "type": "record",
    "fields": [
      {"name": "id", "type": "string"},
      {
            "name": "patient",
            "type": {
                    "type" : "record",
                    "name" : "patientRecord",
                    "fields" : [
                        {"name": "locality", "type": "string"},
                        {"name": "subdistrict", "type": "string"},
                        {"name": "district", "type": "string"}
                    ]
             }
        }
    ]
  }

module.exports = avro.parse(caseAvroSchema);
