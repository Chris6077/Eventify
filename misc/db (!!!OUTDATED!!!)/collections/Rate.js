db.createCollection("Ratings", {
   validator: {
      $jsonSchema: {
         bsonType: "object",
         required: [ "UserID", "EventID", "value", "created" ],
         properties: {
            UserID: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            EventID: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            value: {
               bsonType: "int",
               minimum: 0,
               maximum: 10,
               exclusiveMaximum: false,
               description: "must be an integer in [ 0, 10 ] and is required"
            },
            created: {
               bsonType: "date",
               description: "must be a date and is required"
            }
         },
         additionalProperties: false
      }
   }
})