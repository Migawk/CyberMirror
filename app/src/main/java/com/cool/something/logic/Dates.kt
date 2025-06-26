package com.cool.something.logic

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


data class Day (
    val weekDay: String,
    val month: String,
    val monthDay: Number,
)

fun getDay(calendar: Calendar): Day {
    val dayOfWeekNumber = calendar.get(Calendar.DAY_OF_WEEK)
    val monthDay = calendar.get(Calendar.DAY_OF_MONTH)
    val monthNumber = calendar.get(Calendar.MONTH)

    val dayOfWeek = when (dayOfWeekNumber) {
        1 -> "Sunday"
        2 -> "Monday"
        3 -> "Tuesday"
        4 -> "Wednesday"
        5 -> "Thursday"
        6 -> "Friday"
        7 -> "Saturday"
        else -> "Unknown"
    }

    val month = when (monthNumber) {
        0 -> "January"
        1 -> "February"
        2 -> "March"
        3 -> "April"
        4 -> "May"
        5 -> "June"
        6 -> "July"
        7 -> "August"
        8 -> "September"
        9 -> "October"
        10 -> "November"
        11 -> "December"
        else -> ""
    }

    return Day (
        weekDay = dayOfWeek,
        month = month,
        monthDay = monthDay
    )
}

val dateFormat = SimpleDateFormat("yyyy-MM-dd")
fun parseDate(dateString: String): Calendar {
    val date: Date = dateFormat.parse(dateString) ?: Date(10000000)

    // Create a Calendar instance and set the date
    val calendar: Calendar = Calendar.getInstance()
    calendar.time = date

    return calendar
}