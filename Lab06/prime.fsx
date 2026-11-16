let isPrime n =
    let rec check d =
        if d * d > n then true
        elif n % d = 0 then false
        else check (d + 1)
    n > 1 && check 2

let tests = [2; 17; 20; 1; 97; 100]

tests
|> List.iter (fun n ->
    let result = if isPrime n then "Prime" else "Not Prime"
    printfn "%d -> %s" n result)