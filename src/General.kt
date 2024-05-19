import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

object General {

    fun writeDurationData(date: String) {
        val ldtDate = LocalDateTime.parse(date.replace(" ", "T"), DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        println("часы: ${ldtDate.hour}, ${ldtDate.minute}, ${ldtDate.second}, ${ldtDate.month}, ${ldtDate.dayOfMonth}, ${ldtDate.year}, ")
        val nowDate = LocalDateTime.now()
        val nowDateStart = LocalDateTime.MIN
        println(nowDateStart)
        val duration = Duration.between(ldtDate, nowDate)
        val days = duration.toDays()
        val hours = duration.toHours() % 24
        val minutes = duration.toMinutes() % 60
        println("${days}, ${hours}, ${minutes}")
    }

    fun generatePassword(phrase: String){
        var newStr = ""
        phrase.forEach { c ->
            newStr += when(c.toLowerCase()) {
                'а' -> "@"
                'б' -> "6"
                'в' -> "|3"
                'г' -> getRand("r", getRand("g", "G"))
                'д' -> getRand("d", "D")
                'е' -> getRand("e", "E")
                'ё' -> "3`"
                'ж' -> getRand("#", "Zh")
                'з' -> "3"
                'и' -> "1"
                'й' -> "1`"
                'к' -> getRand("k", "K")
                'л' -> getRand("l", "L")
                'м' -> getRand("m", "M")
                'н' -> getRand("|-|", getRand("n", "H"))
                'о' -> "0"
                'п' -> "n"
                'р' -> getRand("p", "P")
                'с' -> getRand("c", "C")
                'т' -> getRand("t", "T")
                'у' -> getRand("y", "Y")
                'ф' -> getRand("<|>", "pH")
                'х' -> getRand("><", getRand("x", "X"))
                'ц' -> getRand("u,", "U,")
                'ч' -> getRand("4", "Ch")
                'ш' -> getRand("w", "W")
                'щ' -> getRand("w,", "W,")
                'ь' -> "|o"
                'ы' -> "b|"
                'ъ' -> "^6"
                'э' -> "3"
                'ю' -> "|-0"
                'я' -> "9|"
                else -> c
            }
        }
        val regex = Regex("(?=.*[A-ZА-Я])(?=.*[a-zа-я])(?=.*\\d)(?=.*\\s)(?=.*\\p{Punct}).+")
        val rez = regex.containsMatchIn(newStr)
        if (rez) println("${newStr} - подходит")
        else println("${newStr} - не подходит")
    }

    private fun getRand(s1: String, s2: String): String {
        val rand = Random.nextBoolean()
        if (rand) return s1
        else return s2
    }

    fun getPassSHA512(pass: String): String{
        return try {
            val md: MessageDigest = MessageDigest.getInstance("SHA-512")
            val mesdig = md.digest(pass.toByteArray())
            val newStr = BigInteger(1, mesdig)
            var hash = newStr.toString(16)
            while (hash.length < 128) {
                hash = "0$hash"
            }
            hash
        }
        catch (e: NoSuchAlgorithmException){
            return e.message.toString()
        }
    }

    fun String.toSHA512(): String {
        return try {
            val md = MessageDigest.getInstance("SHA-512")
            val mesdig = md.digest(toByteArray())
            val newStr = BigInteger(1, mesdig)
            return String.format("%0128x", newStr)
        } catch (e: NoSuchAlgorithmException) {
            return e.message.toString()
        }
    }

}