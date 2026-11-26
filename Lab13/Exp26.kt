fun greet(name: String = "Student", greeting: String = "Hello") {
    println("$greeting, $name!")
}

fun showNumbers(vararg numbers: Int) {
    println("Count: ${numbers.size}")
    for (n in numbers) {
        println("Number: $n")
    }
}

fun applyToList(list: List<Int>, operation: (Int) -> Int): List<Int> {
    val result = mutableListOf<Int>()
    for (item in list) {
        result.add(operation(item))
    }
    return result
}

fun main() {

    println("===== 1. Default parameters =====")
    greet()
    greet("Ravi")
    greet("Ravi", "Welcome")

    println("\n===== 2. Named arguments =====")
    greet(greeting = "Good morning", name = "Anu")
    greet(greeting = "Hi")

    println("\n===== 3. Vararg =====")
    showNumbers(10, 20, 30)
    showNumbers(1, 2, 3, 4, 5)

    println("\n===== 4. Higher-order function =====")
    val numbers = listOf(1, 2, 3, 4, 5)
    println("Original list: $numbers")

    val squares = applyToList(numbers) { it * it }
    println("Squares: $squares")

    val doubled = applyToList(numbers) { it * 2 }
    println("Doubled: $doubled")

    val plusTen = applyToList(numbers) { n -> n + 10 }
    println("Plus 10: $plusTen")
}