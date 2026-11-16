let list1 = [1; 2; 3; 4; 5; 6]
let list2 = [10; 15; 20; 25; 30]

printfn "List.map (square each element)"
printfn "list1: %A" (List.map (fun x -> x * x) list1)
printfn "list2: %A" (List.map (fun x -> x * x) list2)

printfn ""
printfn "List.filter (keep even numbers)"
printfn "list1: %A" (List.filter (fun x -> x % 2 = 0) list1)
printfn "list2: %A" (List.filter (fun x -> x % 2 = 0) list2)

printfn ""
printfn "List.fold (sum)"
printfn "list1: %d" (List.fold (fun acc x -> acc + x) 0 list1)
printfn "list2: %d" (List.fold (fun acc x -> acc + x) 0 list2)

printfn ""
printfn "List.fold (product)"
printfn "list1: %d" (List.fold (fun acc x -> acc * x) 1 list1)
printfn "list2: %d" (List.fold (fun acc x -> acc * x) 1 list2)

printfn ""
printfn "List.reduce (maximum)"
printfn "list1: %d" (List.reduce (fun a b -> if a > b then a else b) list1)
printfn "list2: %d" (List.reduce (fun a b -> if a > b then a else b) list2)