class Student(
    val rollNo: Int,
    var name: String,
    var course: String,
    private var percentage: Double
) {

    fun acceptData() {
        print("Enter name: ")
        name = readln()
        print("Enter course: ")
        course = readln()
        print("Enter percentage: ")
        percentage = readln().toDouble()
    }

    fun displayDetails() {
        println("-------------------------")
        println("Roll No    : $rollNo")
        println("Name       : $name")
        println("Course     : $course")
        println("Percentage : $percentage%")
        println("-------------------------")
    }
}

fun main() {
    val s1 = Student(1, "Ravi", "B.Tech CSE", 82.5)
    val s2 = Student(2, "Anu", "B.Tech IT", 91.0)

    println("Student 1 (before update):")
    s1.displayDetails()

    println("Update details of Student 1:")
    s1.acceptData()
    println("Student 1 (after update):")
    s1.displayDetails()

    println("\nStudent 2 (before update):")
    s2.displayDetails()

    println("Update details of Student 2:")
    s2.acceptData()
    println("Student 2 (after update):")
    s2.displayDetails()
}