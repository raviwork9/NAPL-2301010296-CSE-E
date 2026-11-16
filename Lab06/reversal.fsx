open System
open System.Text.RegularExpressions

let reverse (s: string) =
    String(s.ToCharArray() |> Array.rev)

let isPalindrome (s: string) =
    let c = s.ToLower()
    c = reverse c

let transform (s: string) =
    match s with
    | _ when s <> "" && Seq.forall Char.IsLetter s -> s.ToUpper()
    | _ when s <> "" && Seq.forall Char.IsDigit s -> string (int s * 2)
    | _ -> s

let isValidEmail (email: string) =
    Regex.IsMatch(email, @"^[\w.+-]+@[\w-]+(\.[\w-]+)*\.[A-Za-z]{2,}$")

printfn "--- Reverse and Palindrome ---"
["madam"; "hello"; "Level"]
|> List.iter (fun s ->
    printfn "%s -> reversed: %s, palindrome: %b" s (reverse s) (isPalindrome s))

printfn "--- Transform ---"
["hello"; "123"; "abc123"]
|> List.iter (fun s -> printfn "%s -> %s" s (transform s))

printfn "--- Email Validation ---"
["ravi@gmail.com"; "test.user@mail.co.in"; "abc@"; "hello.com"]
|> List.iter (fun e -> printfn "%s -> valid: %b" e (isValidEmail e))