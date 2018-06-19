db.createCollection("Events", {
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: ["eID", "name", "description", "maxParticipators", "minAge", "totalLikes", "totalParticipators", "startDate", "endDate", "created", "lastEdited", "type", "state", "category", "participators", "creator", "location"],
            properties: {
                eID: {
                    bsonType: "string",
                    description: "must be a string and is automatically generated"
                },
                name: {
                    bsonType: "string",
                    description: "must be a string and is required"
                },
                description: {
                    bsonType: "string",
                    description: "must be a string and is required"
                },
                maxParticipators: {
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
                totalLikes: {
                    bsonType: "int",
                    minimum: 0,
                    maximum: 2000000000,
                    exclusiveMaximum: false,
                    description: "must be an integer in [ 0, 2000000000 ] and is required"
                },
                totalParticipators: {
                    bsonType: "int",
                    minimum: 0,
                    maximum: 2000000000,
                    exclusiveMaximum: false,
                    description: "must be an integer in [ 0, 2000000000 ] and is required"
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
                type: {
                    enum: ["Public", "NoList", "Private"],
                    description: "can only be one of the enum values and is required"
                },
                state: {
                    enum: ["Unconfirmed", "Confirmed", "CalledOff"],
                    description: "can only be one of the enum values and is required"
                },
                category: {
                    enum: ["Sportsevent", "Festival", "Concert", "Party", "Activity", "Other"],
                    description: "can only be one of the enum values and is required"
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
                    description: "must be a location { lat: double, lon: double } and is required"
                },
                participators: {
                    bsonType: "array",
                    items: {
                        bsonType: "object",
                        required: ["uID", "firstName", "lastName", "profilePicture"],
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
                            }
                        },
                        additionalProperties: false,
                        description: "must be a MinimalUser { uID: String, firstName: String, lastName: String, profilePicture: String } and is required"
                    }
                },
                creator: {
                    bsonType: "object",
                    required: ["uID", "firstName", "lastName", "profilePicture"],
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
                        }
                    },
                    additionalProperties: false,
                    description: "must be a MinimalUser { uID: String, firstName: String, lastName: String, profilePicture: String } and is required"
                }
            },
            additionalProperties: false
        }
    }
})