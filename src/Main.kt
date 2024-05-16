import General.toSHA512
import java.sql.Timestamp
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println("Получение разницы между датами")
    val date = "2024-03-21 12:27:55.012529+00"
    General.writeDurationData(date)
    /*println("Генератор паролей")
    print("Введите фразу: ")
    val phrase: String = readln()
    General.generatePassword(phrase)*/
    print("Пароль: ")
    val pass = readln()
    println("Захэшированный: ")
    println("1." + General.getPassSHA512(pass))
    println("2." + pass.toSHA512())
}