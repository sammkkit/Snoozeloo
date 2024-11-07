package com.example.snoozeloo.data

data class Alarm(
    var name : String = "",
    var time : String = "",
    var toggle : Boolean = false,
    var meridiem : String = "AM",
)
val mockAlarms = listOf(
    Alarm(
        name = "Wake Up",
        time = "07:00",
        toggle = true,
        meridiem = "AM"
    ),
    Alarm(
        name = "Workout",
        time = "06:00",
        toggle = false,
        meridiem = "AM"
    ),
    Alarm(
        name = "Meeting",
        time = "09:30",
        toggle = true,
        meridiem = "AM"
    )
)
