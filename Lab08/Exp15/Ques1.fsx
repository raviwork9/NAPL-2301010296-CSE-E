#r "nuget: Microsoft.Data.Sqlite"

open Microsoft.Data.Sqlite

let conn = new SqliteConnection("Data Source=students.db")
conn.Open()

let run (sql: string) =
    use cmd = conn.CreateCommand()
    cmd.CommandText <- sql
    cmd.ExecuteNonQuery() |> ignore

let show (title: string) =
    printfn "--- %s ---" title
    use cmd = conn.CreateCommand()
    cmd.CommandText <- "SELECT Id, Name, Age FROM Student"
    use r = cmd.ExecuteReader()
    while r.Read() do
        printfn "Id=%d Name=%s Age=%d" (r.GetInt32 0) (r.GetString 1) (r.GetInt32 2)

run "DROP TABLE IF EXISTS Student"
run "CREATE TABLE Student (Id INTEGER PRIMARY KEY, Name TEXT, Age INTEGER)"

run "INSERT INTO Student VALUES (1, 'Ravi', 20)"
run "INSERT INTO Student VALUES (2, 'Amit', 21)"
show "After CREATE (Insert)"

show "READ"

run "UPDATE Student SET Age = 22 WHERE Id = 1"
show "After UPDATE"

run "DELETE FROM Student WHERE Id = 2"
show "After DELETE"

conn.Close()