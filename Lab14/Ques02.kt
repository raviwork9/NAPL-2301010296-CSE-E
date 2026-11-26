fun main() {

    println("===== 1. Nullable vs Non-nullable =====")
    var nullableName: String? = "Ravi"
    var normalName: String = "Kotlin"
    println("Nullable   : $nullableName")
    println("Non-nullable: $normalName")
    nullableName = null
    println("Nullable after setting null: $nullableName")
    println("Non-nullable String can never be null, the compiler gives an error if we try.")

    println("\n===== 2. Safe call operator (?.) =====")
    val city: String? = "Gurugram"
    val noCity: String? = null
    println("Length of city   : ${city?.length}")
    println("Length of noCity : ${noCity?.length}")
    println("Uppercase city   : ${city?.uppercase()}")
    println("Uppercase noCity : ${noCity?.uppercase()}")

    println("\n===== 3. Elvis operator (?:) =====")
    val userName: String? = null
    val shownName = userName ?: "Guest"
    println("Name shown: $shownName")
    val length = noCity?.length ?: 0
    println("Length with default: $length")

    println("\n===== 4. Not-null assertion (!!) =====")
    val value: String? = null
    try {
        val size = value!!.length
        println("Size: $size")
    } catch (e: NullPointerException) {
        println("Caught NullPointerException: value was null")
    }
}