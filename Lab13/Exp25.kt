import kotlin.math.abs

fun getBonusPercent(deptCode: String, years: Int): Int {
    return when {
        deptCode == "IT" && years > 5 -> 20
        deptCode == "HR" && years > 3 -> 15
        deptCode == "FIN" && years > 4 -> 12
        else -> 10
    }
}

fun showBonus(name: String, deptCode: String, years: Int, salary: Double) {
    val percent = getBonusPercent(deptCode, years)
    val bonus = salary * percent / 100
    println("$name | $deptCode | $years yrs | Salary: $salary | Bonus: $percent% = $bonus")
}

fun main() {

    println("Hello Kotlin!!!")

    println("\n--- Employee Bonus ---")
    showBonus("Ravi", "IT", 6, 50000.0)
    showBonus("Anu", "HR", 4, 40000.0)
    showBonus("Karan", "FIN", 2, 45000.0)
    showBonus("Meena", "IT", 3, 60000.0)

    println("\n--- Digit Average ---")
    print("Enter an integer: ")
    val number = abs(readln().toInt())

    var temp = number
    var sum = 0
    var count = 0

    if (temp == 0) {
        count = 1
    }

    while (temp > 0) {
        sum += temp % 10
        temp /= 10
        count++
    }

    val average = sum.toDouble() / count
    println("Sum of digits = $sum")
    println("Number of digits = $count")
    println("Average of digits = $average")
}