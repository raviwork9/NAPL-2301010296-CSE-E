#r "nuget: Microsoft.Data.Sqlite"
open System
open Microsoft.Data.Sqlite

type Status = Active | Graduated

type Student = { Id: int; Name: string; Gpa: float; Status: Status }

let statusToString s = match s with Active -> "Active" | Graduated -> "Graduated"
let statusOfString s = match s with "Graduated" -> Graduated | _ -> Active


let grade gpa =
    match gpa with
    | g when g >= 8.0 -> "A"
    | g when g >= 6.0 -> "B"
    | _ -> "C"

let honorRoll students = students |> List.filter (fun s -> s.Gpa >= 3.5)
let names students = students |> List.map (fun s -> s.Name)
let averageGpa students = students |> List.averageBy (fun s -> s.Gpa)


let connStr = "Data Source=students.db"

let run (sql: string) (ps: (string * obj) list) = async {
    use conn = new SqliteConnection(connStr)
    do! conn.OpenAsync() |> Async.AwaitTask
    use cmd = conn.CreateCommand()
    cmd.CommandText <- sql
    for (k, v) in ps do cmd.Parameters.AddWithValue(k, v) |> ignore
    let! _ = cmd.ExecuteNonQueryAsync() |> Async.AwaitTask
    return () }


let addStudent s =
    run "INSERT INTO Students (Name, Gpa, Status) VALUES ($n, $g, $s)"
        [ "$n", box s.Name; "$g", box s.Gpa; "$s", box (statusToString s.Status) ]


let updateGpa id gpa =
    run "UPDATE Students SET Gpa = $g WHERE Id = $id" [ "$g", box gpa; "$id", box id ]


let deleteStudent id =
    run "DELETE FROM Students WHERE Id = $id" [ "$id", box id ]


let getAll () = async {
    use conn = new SqliteConnection(connStr)
    do! conn.OpenAsync() |> Async.AwaitTask
    use cmd = conn.CreateCommand()
    cmd.CommandText <- "SELECT Id, Name, Gpa, Status FROM Students"
    use! r = cmd.ExecuteReaderAsync() |> Async.AwaitTask
    let items = ResizeArray<Student>()
    while r.Read() do
        items.Add { Id = r.GetInt32 0; Name = r.GetString 1; Gpa = r.GetDouble 2; Status = statusOfString (r.GetString 3) }
    return List.ofSeq items }


IO.File.Delete "students.db"

async {
    do! run "CREATE TABLE Students (Id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Gpa REAL, Status TEXT)" []

    do! addStudent { Id = 0; Name = "Aarav"; Gpa = 8; Status = Active }
    do! addStudent { Id = 0; Name = "Priya"; Gpa = 7.5; Status = Active }
    do! addStudent { Id = 0; Name = "Rohan"; Gpa = 4; Status = Active }

    let! all = getAll ()
    printfn "--- All students ---"
    all |> List.iter (fun s -> printfn "%d %s %.1f %s" s.Id s.Name s.Gpa (grade s.Gpa))

    do! updateGpa 2 3.6
    do! deleteStudent 3

    let! all = getAll ()
    printfn "--- After update and delete ---"
    printfn "Honor roll: %A" (all |> honorRoll |> names)
    printfn "Average GPA: %.2f" (averageGpa all)
}
|> Async.RunSynchronously
