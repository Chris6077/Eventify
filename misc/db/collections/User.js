db.createCollection("Users", {
   validator: {
      $jsonSchema: {
         bsonType: "object",
         required: [ "uID", "firstName", "lastName", "birthdate", "email", "password", "profilePicture", "state", "type", "numberOfCreated", "numberOfParticipated", "ratings", "totalFollowers", "created", "lastEdited", "follows", "likes", "participatesIn", "createdEvents" ],
         properties: {
            uID: {
               bsonType: "string",
               description: "must be a string and is automatically generated"
            },
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
            state: {
               enum: ["Activated", "Deactivated"],
               description: "can only be one of the enum values and is required"
            },
            type: {
               enum: ["Administrator", "User"],
               description: "can only be one of the enum values and is required"
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
            },
            ratings: {
               bsonType: "array",
               items: {
                  bsonType: "int",
                  minimum: 1,
                  maximum: 10,
                  exclusiveMaximum: false,
                  description: "must be an integer in [ 1, 10 ] and is required"
               }
            },
            totalFollowers: {
               bsonType: "int",
               minimum: 0,
               maximum: 2000000000,
               exclusiveMaximum: false,
               description: "must be an integer in [ 0, 2000000000 ] and is required"
            },
            created: {
               bsonType: "date",
               description: "must be a date and is required"
            },
            lastEdited: {
               bsonType: "date",
               description: "must be a date and is required"
            },
            follows: {
               bsonType: "array",
               required: ["uID", "firstName", "lastName", "profilePicture", "type", "numberOfCreated", "numberOfParticipated", "rating", "totalFollowers", "created"],
               properties: {
                  uID: {
                     bsonType: "string",
                     description: "must be a string and is automatically generated"
                  },
                  firstName: {
                     bsonType: "string",
                     description: "must be a string and is required"
                  },
                  lastName: {
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
                  },
                  rating: {
                     bsonType: "Number",
                     description: "must be a Number and is required"
                  },
                  totalFollowers: {
                     bsonType: "int",
                     minimum: 0,
                     maximum: 2000000000,
                     exclusiveMaximum: false,
                     description: "must be an integer in [ 0, 2000000000 ] and is required"
                  },
                  created: {
                     bsonType: "date",
                     description: "must be a date and is required"
                  }
               },
               additionalProperties: false,
               description: "must be a SlimUser { uID: String, firstName: String, lastName: String, profilePicture: String } and is required"
            },
            likes: {
               bsonType: "array",
               required: ["eID", "name", "totalLikes", "maxParticipators", "totalParticipators", "category" ],
               properties: {
                  eID: {
                     bsonType: "string",
                     description: "must be a string and is automatically generated"
                  },
                  name: {
                     bsonType: "string",
                     description: "must be a string and is required"
                  },
                  totalLikes: {
                     bsonType: "int",
                     minimum: 0,
                     maximum: 2000000000,
                     exclusiveMaximum: false,
                     description: "must be an integer in [ 0, 2000000000 ] and is required"
                  },
                  maxParticipators: {
                     bsonType: "int",
                     minimum: 1,
                     maximum: 2000000000,
                     exclusiveMaximum: false,
                     description: "must be an integer in [ 1, 2000000000 ] and is required"
                  },
                  totalParticipators: {
                     bsonType: "int",
                     minimum: 0,
                     maximum: 2000000000,
                     exclusiveMaximum: false,
                     description: "must be an integer in [ 0, 2000000000 ] and is required"
                  },
                  category: {
                     enum: ["Sportsevent", "Festival", "Concert", "Party", "Activity", "Other"],
                     description: "can only be one of the enum values and is required"
                  }
               },
               additionalProperties: false,
               description: "must be a SlimEvent { eID: String, name: String, totalLikes: int, maxParticipators: int, totalParticipators: int, category: enum } and is required"
            },
            participatesIn: {
               bsonType: "array",
               required: ["eID", "name", "totalLikes", "maxParticipators", "totalParticipators", "category" ],
               properties: {
                  eID: {
                     bsonType: "string",
                     description: "must be a string and is automatically generated"
                  },
                  name: {
                     bsonType: "string",
                     description: "must be a string and is required"
                  },
                  totalLikes: {
                     bsonType: "int",
                     minimum: 0,
                     maximum: 2000000000,
                     exclusiveMaximum: false,
                     description: "must be an integer in [ 0, 2000000000 ] and is required"
                  },
                  maxParticipators: {
                     bsonType: "int",
                     minimum: 1,
                     maximum: 2000000000,
                     exclusiveMaximum: false,
                     description: "must be an integer in [ 1, 2000000000 ] and is required"
                  },
                  totalParticipators: {
                     bsonType: "int",
                     minimum: 0,
                     maximum: 2000000000,
                     exclusiveMaximum: false,
                     description: "must be an integer in [ 0, 2000000000 ] and is required"
                  },
                  category: {
                     enum: ["Sportsevent", "Festival", "Concert", "Party", "Activity", "Other"],
                     description: "can only be one of the enum values and is required"
                  }
               },
               additionalProperties: false,
               description: "must be a SlimEvent { eID: String, name: String, totalLikes: int, maxParticipators: int, totalParticipators: int, category: enum } and is required"
            },
            createdEvents: {
               bsonType: "array",
               required: ["eID", "name", "totalLikes", "maxParticipators", "totalParticipators", "category" ],
               properties: {
                  eID: {
                     bsonType: "string",
                     description: "must be a string and is automatically generated"
                  },
                  name: {
                     bsonType: "string",
                     description: "must be a string and is required"
                  },
                  totalLikes: {
                     bsonType: "int",
                     minimum: 0,
                     maximum: 2000000000,
                     exclusiveMaximum: false,
                     description: "must be an integer in [ 0, 2000000000 ] and is required"
                  },
                  maxParticipators: {
                     bsonType: "int",
                     minimum: 1,
                     maximum: 2000000000,
                     exclusiveMaximum: false,
                     description: "must be an integer in [ 1, 2000000000 ] and is required"
                  },
                  totalParticipators: {
                     bsonType: "int",
                     minimum: 0,
                     maximum: 2000000000,
                     exclusiveMaximum: false,
                     description: "must be an integer in [ 0, 2000000000 ] and is required"
                  },
                  category: {
                     enum: ["Sportsevent", "Festival", "Concert", "Party", "Activity", "Other"],
                     description: "can only be one of the enum values and is required"
                  }
               },
               additionalProperties: false,
               description: "must be a SlimEvent { eID: String, name: String, totalLikes: int, maxParticipators: int, totalParticipators: int, category: enum } and is required"
            }
         },
         additionalProperties: false
      }
   }
})