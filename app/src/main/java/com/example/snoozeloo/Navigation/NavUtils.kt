package com.example.snoozeloo.Navigation

import kotlinx.serialization.Serializable

sealed class Destination {

    @Serializable
    data object Home:Destination()

    @Serializable
    data object AlarmSetting:Destination()

}