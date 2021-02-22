package ru.example.aeon.core

import java.text.SimpleDateFormat
import java.util.Calendar.*

object Utils {

    fun getDate(time: Long, dateFormat: String = BASE_DATE_FORMAT): String? {
        val calendar = getInstance()
        calendar.timeInMillis = time
        return SimpleDateFormat(dateFormat).format(calendar.time)
    }

}