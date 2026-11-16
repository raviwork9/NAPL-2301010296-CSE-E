type Shape() =
    abstract member Area : unit -> float
    default this.Area() = 0.0

type Circle(radius: float) =
    inherit Shape()
    override this.Area() = System.Math.PI * radius * radius

type Rectangle(width: float, height: float) =
    inherit Shape()
    override this.Area() = width * height

let shape = Shape()
let circle = Circle(5.0)
let rectangle = Rectangle(4.0, 6.0)

printfn "Shape area: %.2f" (shape.Area())
printfn "Circle area: %.2f" (circle.Area())
printfn "Rectangle area: %.2f" (rectangle.Area())

let shapes : Shape list = [shape; circle; rectangle]
for s in shapes do
    printfn "Polymorphic call -> Area = %.2f" (s.Area())