package ru.netology

const val MINUTE = 60
const val HOUR = 60 * 60
const val DAY = 24 * 60 * 60
const val TWO_DAYS = 2 * 24 * 60 * 60
const val THREE_DAYS = 3 * 24 * 60 * 60

enum class TimeType { Minutes, Hours }

fun main() {
    val randomSeconds = (0..24 * 60 * 60).random()
    val randomSeconds2 = (24 * 60 * 60..4 * 24 * 60 * 60).random()
    println("$randomSeconds секунд")
    val result = agoToText(randomSeconds)
    println(result)
    println("$randomSeconds2 секунд")
    val result2 = agoToText(randomSeconds2)
    print(result2)
}

fun agoToText(seconds: Int): String {
    val result = when {
        seconds < MINUTE -> {
            "был(а) только что"
        }
        seconds in MINUTE until HOUR -> {
            val minutes = secondsToMinutesHours(seconds, TimeType.Minutes)
            "был(а) $minutes назад"
        }
        seconds in HOUR until DAY -> {
            val hours = secondsToMinutesHours(seconds, TimeType.Hours)
            "был(а) $hours назад"
        }
        seconds in DAY until TWO_DAYS -> {
            "был(а) сегодня"
        }
        seconds in TWO_DAYS until THREE_DAYS -> {
            "был(а) вчера"
        }
        seconds >= THREE_DAYS -> {
            "был(а) давно"
        }
        else -> {
            error("В функцию передано некорректное значение")
        }
    }
    return (result)
}

fun secondsToMinutesHours(seconds: Int, timeType: TimeType): String {
    val result = when (timeType) {
        TimeType.Minutes -> {
            val minutes = seconds / 60
            when {
                minutes in 11..20 -> "$minutes минут"
                minutes % 10 == 1 -> "$minutes минуту"
                minutes % 10 in 2..4 -> "$minutes минуты"
                else -> "$minutes минут"
            }
        }
        TimeType.Hours -> {
            val hours = seconds / 60 / 60
            when {
                hours in 11..20 -> "$hours часов"
                hours % 10 == 1 -> "$hours час"
                hours % 10 in 2..4 -> "$hours часа"
                else -> "$hours часов"
            }
        }
    }
    return (result)
}
