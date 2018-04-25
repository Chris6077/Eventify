db.createCollection("Users", {
   validator: {
      $jsonSchema: {
         bsonType: "object",
         required: [ "firstName", "lastName", "birthdate", "username", "email", "password", "profilePicture", "type", "created", "lastEdited", "numberOfCreated", "numberOfParticipated", "state" ],
         properties: {
            firstName: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            lastName: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            birthdate: {
               bsonType: "date",
               description: "must be a date and is required"
            },
            username: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            email: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            password: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            profilePicture: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            type: {
               enum: ["Administrator", "User"],
               description: "can only be one of the enum values and is required"
            },
            state: {
               enum: ["Activated", "Deactivated"],
               description: "can only be one of the enum values and is required"
            },
            created: {
               bsonType: "date",
               description: "must be a date and is required"
            },
            lastEdited: {
               bsonType: "date",
               description: "must be a date and is required"
            },
            numberOfCreated: {
               bsonType: "int",
               minimum: 0,
               maximum: 2000000000,
               exclusiveMaximum: false,
               description: "must be an integer in [ 0, 2000000000 ] and is required"
            },
            numberOfParticipated: {
               bsonType: "int",
               minimum: 0,
               maximum: 2000000000,
               exclusiveMaximum: false,
               description: "must be an integer in [ 0, 2000000000 ] and is required"
            }
         },
         additionalProperties: false
      }
   }
})