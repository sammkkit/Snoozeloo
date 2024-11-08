package com.example.snoozeloo.data

data class Alarm(
    var name : String = "",
    var hour : Int = 0,
    var minute : Int = 0,
    var toggle : Boolean = false,
    var meridiem : String = "AM",
)
val mockAlarms = listOf(
    Alarm(
        name = "Wake Up",
        hour = 7,
        minute = 0,
        toggle = true,
        meridiem = "AM"
    ),
    Alarm(
        name = "Workout",
        hour = 6,
        minute = 0,
        toggle = false,
        meridiem = "AM"
    ),
    Alarm(
        name = "Meeting",
        hour = 9,
        minute = 30,
        toggle = true,
        meridiem = "AM"
    )
)
