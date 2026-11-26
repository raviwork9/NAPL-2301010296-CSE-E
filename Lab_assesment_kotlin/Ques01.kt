data class Course(
    val code: String,
    val title: String,
    val credits: Int,
    val instructor: String?
)

data class Student(
    val name: String,
    val email: String?,
    val courses: MutableList<Course> = mutableListOf()
)

fun Course.label(): String = "$code - $title ($credits credits)"

fun Student.enroll(course: Course) {
    courses.add(course)
}

fun Student.totalCredits(): Int = courses.sumOf { it.credits }

class Section {
    val items = mutableListOf<String>()

    fun item(text: String) {
        items.add(text)
    }
}

class ReportBuilder {
    private val output = StringBuilder()

    fun title(text: String) {
        output.appendLine("===== $text =====")
    }

    fun section(name: String, block: Section.() -> Unit) {
        val section = Section()
        section.block()
        output.appendLine("\n$name")
        for (line in section.items) {
            output.appendLine("  - $line")
        }
    }

    fun build(): String = output.toString()
}

fun report(block: ReportBuilder.() -> Unit): String {
    val builder = ReportBuilder()
    builder.block()
    return builder.build()
}

fun main() {

    val c1 = Course("CS101", "Kotlin Basics", 4, "Dr. Sharma")
    val c2 = Course("CS102", "Data Structures", 3, "Dr. Verma")
    val c3 = Course("CS103", "Web Development", 3, null)
    val c4 = Course("CS104", "Databases", 2, "Dr. Sharma")
    val allCourses = listOf(c1, c2, c3, c4)

    val ravi = Student("Ravi", "ravi@mail.com")
    val anu = Student("Anu", null)

    ravi.enroll(c1)
    ravi.enroll(c2)
    ravi.enroll(c4)
    anu.enroll(c1)
    anu.enroll(c3)
    val students = listOf(ravi, anu)

    val titles = allCourses.map { it.title }
    val bigCourses = allCourses.filter { it.credits >= 3 }
    val totalCredits = allCourses.map { it.credits }.reduce { a, b -> a + b }
    val byInstructor = allCourses.groupBy { it.instructor ?: "Unassigned" }

    val text = report {
        title("Course Registration Report")

        section("All Courses") {
            for (course in allCourses) {
                item(course.label())
            }
        }

        section("Course Titles (map)") {
            item(titles.joinToString(", "))
        }

        section("Courses with 3 or more credits (filter)") {
            for (course in bigCourses) {
                item(course.title)
            }
        }

        section("Total credits offered (reduce)") {
            item("$totalCredits credits")
        }

        section("Courses grouped by instructor (groupBy)") {
            for ((instructor, list) in byInstructor) {
                item("$instructor -> ${list.map { it.code }}")
            }
        }

        section("Students") {
            for (student in students) {
                val email = student.email ?: "email not provided"
                item("${student.name} | $email | ${student.courses.size} courses | ${student.totalCredits()} credits")
            }
        }
    }

    println(text)
}