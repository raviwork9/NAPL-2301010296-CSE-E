data class Student(val name: String, val percentage: Double, val city: String)

fun main() {

    println("===== PART 1: Collections =====")

    val fixedList = listOf("Apple", "Banana", "Cherry")
    val changeList = mutableListOf("Apple", "Banana", "Cherry")
    changeList.add("Mango")
    changeList.remove("Banana")
    println("Read-only List : $fixedList")
    println("Mutable List   : $changeList")
    println("Contains Mango?: ${changeList.contains("Mango")}")
    println("Item at index 1: ${changeList.get(1)}")

    val fixedSet = setOf(1, 2, 3, 3)
    val changeSet = mutableSetOf(1, 2, 3)
    changeSet.add(4)
    changeSet.add(2)
    changeSet.remove(1)
    println("\nRead-only Set  : $fixedSet")
    println("Mutable Set    : $changeSet")
    println("Contains 4?    : ${changeSet.contains(4)}")

    val fixedMap = mapOf(1 to "One", 2 to "Two")
    val changeMap = mutableMapOf(1 to "One", 2 to "Two")
    changeMap[3] = "Three"
    changeMap.remove(1)
    println("\nRead-only Map  : $fixedMap")
    println("Mutable Map    : $changeMap")
    println("Contains key 3?: ${changeMap.contains(3)}")
    println("Value of key 2 : ${changeMap.get(2)}")

    println("\n===== PART 2: map, filter, reduce =====")

    val numbers = listOf(1, 2, 3, 4, 5, 6)
    println("Original numbers : $numbers")

    val doubled = numbers.map { it * 2 }
    println("After map (x2)   : $doubled")

    val evens = doubled.filter { it % 4 == 0 }
    println("After filter (divisible by 4): $evens")

    val total = evens.reduce { sum, n -> sum + n }
    println("After reduce (sum): $total")

    val words = listOf("kotlin", "java", "python", "go")
    println("\nOriginal words   : $words")

    val upper = words.map { it.uppercase() }
    println("After map (upper): $upper")

    val longWords = upper.filter { it.length > 4 }
    println("After filter (length > 4): $longWords")

    val joined = words.reduce { a, b -> "$a-$b" }
    println("After reduce (join): $joined")

    println("\n===== PART 3: Sort and groupBy =====")

    val students = listOf(
        Student("Ravi", 82.5, "Delhi"),
        Student("Anu", 91.0, "Gurugram"),
        Student("Karan", 74.0, "Delhi"),
        Student("Meena", 88.0, "Gurugram")
    )

    println("Sorted by percentage (low to high):")
    students.sortedBy { it.percentage }.forEach { println("  ${it.name} - ${it.percentage}") }

    println("Sorted by percentage (high to low):")
    students.sortedByDescending { it.percentage }.forEach { println("  ${it.name} - ${it.percentage}") }

    val byCity = students.groupBy { it.city }
    println("\nGrouped by city:")
    for ((city, list) in byCity) {
        println("  $city -> ${list.map { it.name }}")
    }
}