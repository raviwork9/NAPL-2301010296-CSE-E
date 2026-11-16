#r "nuget: MongoDB.Driver"

open MongoDB.Bson
open MongoDB.Driver

let client = MongoClient("mongodb://localhost:27017")
let db = client.GetDatabase("college")
db.DropCollection("students")
let students = db.GetCollection<BsonDocument>("students")

let mk (id: int) (name: string) (age: int) =
    BsonDocument([ BsonElement("_id", BsonInt32 id)
                   BsonElement("name", BsonString name)
                   BsonElement("age", BsonInt32 age) ])

let show (title: string) =
    printfn "--- %s ---" title
    for d in students.Find(Builders<BsonDocument>.Filter.Empty).ToList() do
        printfn "%s" (d.ToString())

students.InsertOne(mk 1 "Ravi" 20)
students.InsertOne(mk 2 "Amit" 21)
show "After CREATE (Insert)"

show "READ"

students.UpdateOne(
    Builders<BsonDocument>.Filter.Eq("_id", 1),
    Builders<BsonDocument>.Update.Set("age", 22)) |> ignore
show "After UPDATE"

students.DeleteOne(Builders<BsonDocument>.Filter.Eq("_id", 2)) |> ignore
show "After DELETE"