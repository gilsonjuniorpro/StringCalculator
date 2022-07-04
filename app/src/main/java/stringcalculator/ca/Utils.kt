package stringcalculator.ca


class Utils {
    companion object {
        var exceptions = mutableListOf<Int>()

        fun add(numbers: String): Int {
            exceptions = mutableListOf()
            var result = 0

            if (numbers.isEmpty()) {
                return result
            }

            val delimitersPosition = numbers.indexesOf("//")
            delimitersPosition.forEach {
                delimit["${numbers[it + 2]}"] = ","
            }

            val array = convertDelimiter(numbers).split(",")

            array.forEach {
                result += isNumber(it)
            }

            return if(exceptions.size > 0){
                -1
            }else{
                result
            }
        }

        private fun isNumber(s: String?): Int {
            return if(s.isNullOrEmpty()){
                0
            }else{
                return try {
                    val value: Int = s.toInt()
                    if (value < 0) {
                        exceptions.add(value)
                        value
                    }else if (value > 1000) {
                        0
                    } else {
                        value
                    }
                } catch (e: NumberFormatException) {
                    0
                }
            }
        }
    }
}

private val delimit = mutableMapOf(
    "//" to ",",
    "\n" to ","
)

private fun convertDelimiter(numbers: String): String {
    var result = numbers
    delimit.forEach { (t, u) -> result = result.replace(t, u) }

    return result
}

private fun String?.indexesOf(substr: String, ignoreCase: Boolean = true): List<Int> {
    return this?.let {
        val regex = if (ignoreCase) Regex(substr, RegexOption.IGNORE_CASE) else Regex(substr)
        regex.findAll(this).map { it.range.first }.toList()
    } ?: emptyList()
}
