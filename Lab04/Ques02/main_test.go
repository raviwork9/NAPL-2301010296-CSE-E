package main

import "testing"

func TestDivide(t *testing.T) {
	result, err := divide(10, 2)
	if err != nil || result != 5 {
		t.Errorf("expected 5, got %v", result)
	}

	_, err = divide(10, 0)
	if err == nil {
		t.Errorf("expected error for division by zero")
	}
}

func TestSquareRoot(t *testing.T) {
	result, err := squareRoot(16)
	if err != nil || result != 4 {
		t.Errorf("expected 4, got %v", result)
	}

	_, err = squareRoot(-9)
	if err == nil {
		t.Errorf("expected error for negative number")
	}
}

func TestCheckInput(t *testing.T) {
	text, err := checkInput("hello")
	if err != nil || text != "hello" {
		t.Errorf("expected hello, got %v", text)
	}

	_, err = checkInput("")
	if err == nil {
		t.Errorf("expected error for empty input")
	}
}
