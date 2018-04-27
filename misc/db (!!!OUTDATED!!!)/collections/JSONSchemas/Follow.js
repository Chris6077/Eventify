{
   "bsonType": "object",
   "required": [ "User1ID", "User2ID", "created"],
   "properties": {
      "User1ID": {
         "bsonType": "string",
         "description": "must be a string and is required"
      },
      "User2ID": {
         "bsonType": "string",
         "description": "must be a string and is required"
      },
      "created": {
         "bsonType": "date",
         "description": "must be a date and is required"
      }
   },
   "additionalProperties": false
}