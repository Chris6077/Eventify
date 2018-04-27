db.createCollection("Participations", {
   validator: {
      $jsonSchema: {
         bsonType: "object",
         required: [ "UserID", "EventID", "type", "created" ],
         properties: {
            UserID: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            EventID: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            type: {
               enum: ["Public", "Anonymous"],
               description: "can only be one of the enum values and is required"
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