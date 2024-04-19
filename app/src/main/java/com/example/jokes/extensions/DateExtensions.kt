package com.example.jokes.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.convertTimeStamp(): String {
    val date = Date(this)
    val format = SimpleDateFormat("yyyy.MM.dd hh:mm:ss", Locale.getDefault())
    return format.format(date)
}