import General.toSHA512
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

fun main() {
/*
    var listProduct = ArrayList<ProductModel>()
    listProduct.add(ProductModel(true, "Тестовый заголовок 1", "Какое-то описание", 500, "Tennis"))
    listProduct.add(ProductModel(false, "Тестовый 2",  "Какое-то описание",600, "Tennis"))
    listProduct.add(ProductModel(true, "заголоsdfвок 3",  "Какое-то опsdfgисание",700, "Outdoor"))
    listProduct.add(ProductModel(true, "заголоsdfвок 3",  "Какое-то опsdfgисание",700, "Outdoor"))
    listProduct.add(ProductModel(true, "заголоsdfвок 3",  "Какое-то опsdfgисание",700, "Outdoor"))
    listProduct.add(ProductModel(true, "заголsdfовок 4",  "Какsdfое-sdfто описание",800, "Tennis"))

    println("Получить сумму корзины: ${listProduct.map { it.price!! }.sum()}")
    val distinctProduct = listProduct.distinct()
    distinctProduct.forEach { el ->
        println("${el.name}: ${listProduct.count { it == el }}")
    }
    listProduct.removeAll { it == (ProductModel(true, "заголоsdfвок 3",  "Какое-то опsdfgисание",700, "Outdoor")) }
    distinctProduct.forEach { el ->
        println("${el.name}: ${listProduct.count { it == el }}")
    }

    println(listProduct)*/
    val nowDateStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0)
    println()
    val dateNow = "2024-05-17 21:00:47+00"
    val ldt = LocalDateTime.parse(dateNow.replace(" ", "T"), DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    //Разница между началом текущего дня и полученной датой
    //Из 2 даты вычитаеся 1
    var duration = Duration.between(ldt, nowDateStart)
    //Если дата больше начала текущего дня, т.е.
    //начало тек. дня - дата < 0, то это сегодня
    println(duration.toDays())
    if(duration.isNegative){
        //Разница между датой и текущей датой
        duration = Duration.between(ldt, LocalDateTime.now())
        println("${duration.toHours()} ч. ${duration.toMinutes() % 60} мин. назад")
    } else {
        if (duration.toDays() < 1) {
            println("${ldt.hour}:${ldt.minute}")
        } else {
            println("${ldt.dayOfMonth} ${ldt.month.getDisplayName(TextStyle.FULL, Locale("ru"))} ${ldt.year}")
        }
    }



    /*println("Получение разницы между датами")
    val date = "2024-03-21 12:27:55.012529+00"
    General.writeDurationData(date)*/

    /*println("Генератор паролей")
    print("Введите фразу: ")
    val phrase: String = readln()
    General.generatePassword(phrase)*/

    /*print("Пароль: ")
    val pass = readln()
    println("Захэшированный: ")
    println("1." + General.getPassSHA512(pass))
    println("2." + pass.toSHA512())*/

    //Хэшируем пароль
    //println(getSha512("pass123"))
}

fun getSha512(pass: String): String {
    val md = MessageDigest.getInstance("SHA-512")
    val mesdig = md.digest(pass.toByteArray())
    val newStr = BigInteger(1, mesdig)
    return String.format("%0128x", newStr)
}