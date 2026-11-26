package main

import (
	"errors"
	"fmt"
)

type Item struct {
	ID       int
	Name     string
	Quantity int
	Price    float64
}
type Inv struct {
	Items []Item
}
type Stats struct {
	TotalValue float64
	ItemCount  int
}

func (inv *Inv) Add(item Item) error {
	for _, n := range inv.Items {
		if n.ID == item.ID {
			return errors.New("Item already exists")
		}
	}
	inv.Items = append(inv.Items, item)
	return nil
}
func (inv *Inv) Remove(id int) error {
	for i, item := range inv.Items {
		if item.ID == id {
			inv.Items = append(inv.Items[:i], inv.Items[i+1:]...)
			return nil
		}
	}
	return errors.New("Item not found")
}
func (inv *Inv) Update(id int, quantity int, price float64) error {
	for i := range inv.Items {
		if inv.Items[i].ID == id {
			inv.Items[i].Quantity = quantity
			inv.Items[i].Price = price
			return nil
		}
	}
	return errors.New("Item not found")
}
func (inv *Inv) Summary() Stats {
	items := make([]Item, len(inv.Items))
	copy(items, inv.Items)

	result := make(chan Stats)

	go func() {
		total := 0.0
		for _, item := range items {
			total += float64(item.Quantity) * item.Price
		}
		result <- Stats{TotalValue: total, ItemCount: len(items)}
	}()

	return <-result
}
func main() {
	inv := &Inv{}

	inv.Add(Item{ID: 1, Name: "football", Quantity: 10, Price: 100})
	inv.Add(Item{ID: 2, Name: "volley", Quantity: 20, Price: 50})
	inv.Add(Item{ID: 3, Name: "basketball", Quantity: 10, Price: 500})

	if err := inv.Add(Item{ID: 1, Name: "volley", Quantity: 1, Price: 100}); err != nil {
		fmt.Println("Error:", err)
	}

	if err := inv.Update(2, 5, 80); err != nil {
		fmt.Println("Error:", err)
	}

	if err := inv.Remove(99); err != nil {
		fmt.Println("Error:", err)
	}

	stats := inv.Summary()
	fmt.Println("Item count:", stats.ItemCount)
	fmt.Println("Total value:", stats.TotalValue)
}
