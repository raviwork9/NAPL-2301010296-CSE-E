open System.Diagnostics

let simulateTask (name: string) (delayMs: int) : Async<string> =
    async {
        printfn "[%s] started" name
        do! Async.Sleep delayMs
        printfn "[%s] finished after %d ms" name delayMs
        return sprintf "%s completed (%d ms)" name delayMs
    }

let sw = Stopwatch.StartNew()

printfn "Starting 3 tasks in parallel..."
printfn "-------------------------------"

let tasks =
    [ simulateTask "Task A" 3000
      simulateTask "Task B" 2000
      simulateTask "Task C" 1000 ]

let results =
    tasks
    |> Async.Parallel
    |> Async.RunSynchronously

sw.Stop()

printfn "-------------------------------"
printfn "All tasks done. Results:"
results |> Array.iter (printfn " - %s")

printfn ""
printfn "Total time: %d ms" sw.ElapsedMilliseconds
printfn "Sequential would take about 6000 ms."
printfn "Task C finished first even though it was started last,"
printfn "which shows the execution was non-sequential."