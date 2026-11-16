package main

import "fmt"

func squares(ch chan string) {
	fmt.Println("Squares goroutine started")
	result := ""
	for i := 1; i <= 5; i++ {
		result = result + fmt.Sprint(i*i) + " "
	}
	fmt.Println("Squares goroutine finished")
	ch <- "Squares: " + result
}

func cubes(ch chan string) {
	fmt.Println("Cubes goroutine started")
	result := ""
	for i := 1; i <= 5; i++ {
		result = result + fmt.Sprint(i*i*i) + " "
	}
	fmt.Println("Cubes goroutine finished")
	ch <- "Cubes: " + result
}

func fibonacci(ch chan string) {
	fmt.Println("Fibonacci goroutine started")
	a := 0
	b := 1
	result := ""
	for i := 1; i <= 8; i++ {
		result = result + fmt.Sprint(a) + " "
		a, b = b, a+b
	}
	fmt.Println("Fibonacci goroutine finished")
	ch <- "Fibonacci: " + result
}

func main() {
	ch := make(chan string)

	go squares(ch)
	go cubes(ch)
	go fibonacci(ch)

	for i := 0; i < 3; i++ {
		fmt.Println(<-ch)
	}
}
