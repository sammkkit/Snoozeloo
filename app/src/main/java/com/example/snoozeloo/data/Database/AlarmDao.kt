package com.example.snoozeloo.data.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.snoozeloo.data.Alarm
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun AddNewAlarm(alarm: Alarm) : Long

    @Delete
    suspend fun DeleteAlarm(alarm: Alarm)

    @Query("SELECT * FROM alarms")
    fun GetAllAlarms() : Flow<List<Alarm>>

    @Query("UPDATE alarms SET toggle = 0 WHERE id = :alarmId")
    suspend fun UpdateAlarmToOff(alarmId: Int)

    @Query("UPDATE alarms SET toggle = 1 WHERE id = :alarmId")
    suspend fun UpdateAlarmToON(alarmId: Int)

    @Query("DELETE from alarms")
    suspend fun DeleteAllAlarms()

    @Query("SELECT * FROM alarms WHERE id = :alarmId")
    suspend fun GetAalarmById(alarmId: Int) : Alarm
}