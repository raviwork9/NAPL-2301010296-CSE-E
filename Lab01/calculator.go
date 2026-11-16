package main

import "fmt"

func main() {
	var a, b float64
	var op string

	fmt.Print("Enter first number: ")
	fmt.Scan(&a)

	fmt.Print("Enter operator (+, -, *): ")
	fmt.Scan(&op)

	fmt.Print("Enter second number: ")
	fmt.Scan(&b)

	if op == "+" {
		fmt.Println("Result:", a+b)
	} else if op == "-" {
		fmt.Println("Result:", a-b)
	} else if op == "*" {
		fmt.Println("Result:", a*b)
	} else {
		fmt.Println("Invalid operator!")
	}
}
