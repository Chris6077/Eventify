db.createCollection("Events", {
   validator: {
      $jsonSchema: {
         bsonType: "object",
         required: [ "name", "creatorID", "state", "description", "maxParticipants", "minAge", "type", "category", "startDate", "endDate", "created", "lastEdited" ],
         properties: {
            name: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            creatorID: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            state: {
               enum: ["Unconfirmed", "Confirmed", "CalledOff"],
               description: "can only be one of the enum values and is required"
            },
            description: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            maxParticipants: {
               bsonType: "int",
               minimum: 1,
               maximum: 2000000000,
               exclusiveMaximum: false,
               description: "must be an integer in [ 1, 2000000000 ] and is required"
            },
            minAge: {
               bsonType: "int",
               minimum: 0,
               maximum: 150,
               exclusiveMaximum: false,
               description: "must be an integer in [ 0, 150 ] and is required"
            },
            type: {
               enum: ["Public", "NoList", "Private"],
               description: "can only be one of the enum values and is required"
            },
            category: {
               enum: ["Sportevent", "Festival", "Konzert", "Party", "Aktivität", "Sonstiges"],
               description: "can only be one of the enum values and is required"
            },
            startDate: {
               bsonType: "date",
               description: "must be a date and is required"
            },
            endDate: {
               bsonType: "date",
               description: "must be a date and is required"
            },
            created: {
               bsonType: "date",
               description: "must be a date and is required"
            },
            lastEdited: {
               bsonType: "date",
               description: "must be a date and is required"
            },
            location: {
               bsonType: "object",
               required: ["lat", "lon"],
               properties: {
                 lat: {
                  bsonType: "double",
                    description: "must be a double and is required"
                 },
                 lon: {
                  bsonType: "double",
                    description: "must be a double and is required"
                 }
               },
               additionalProperties: false,
               description: "must be a location { lat: int, lon: int } and is required"
            }
         },
         additionalProperties: false
      }
   }
})