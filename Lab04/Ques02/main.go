package main

import (
	"fmt"
	"math"
)

func divide(a, b float64) (float64, error) {
	if b == 0 {
		return 0, fmt.Errorf("cannot divide %v by zero", a)
	}
	return a / b, nil
}

func squareRoot(n float64) (float64, error) {
	if n < 0 {
		return 0, fmt.Errorf("cannot take square root of negative number %v", n)
	}
	return math.Sqrt(n), nil
}

func checkInput(s string) (string, error) {
	if s == "" {
		return "", fmt.Errorf("input cannot be empty")
	}
	return s, nil
}

func main() {
	result, err := divide(10, 0)
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("Result:", result)
	}

	result, err = squareRoot(-4)
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("Result:", result)
	}

	text, err := checkInput("")
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("Input:", text)
	}
}
