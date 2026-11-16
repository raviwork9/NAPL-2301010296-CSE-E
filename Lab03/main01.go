package main

import "fmt"

func changeValue(p *int) {
	*p = 20
}

type Student struct {
	age int
}

func main() {
	a := 10
	p := &a

	fmt.Println("Address of a:", &a)
	fmt.Println("Value of a:", *p)

	fmt.Println("Before:", a)
	changeValue(&a)
	fmt.Println("After:", a)

	s := new(Student)
	s.age = 21

	fmt.Println("Student age:", s.age)

	s.age = 22
	fmt.Println("Updated student age:", s.age)
}
