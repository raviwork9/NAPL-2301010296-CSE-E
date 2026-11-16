open System

let readMarks subject =
    printf "Enter marks for %s: " subject
    Console.ReadLine() |> float

let m1 = readMarks "Subject 1"
let m2 = readMarks "Subject 2"
let m3 = readMarks "Subject 3"
let m4 = readMarks "Subject 4"

let percentage = (m1 + m2 + m3 + m4) / 4.0

let division =
    match percentage with
    | p when p >= 60.0 -> "First Division"
    | p when p >= 50.0 -> "Second Division"
    | p when p >= 40.0 -> "Third Division"
    | _ -> "Fail"

printfn "Percentage: %.2f%%" percentage
printfn "Result: %s" division