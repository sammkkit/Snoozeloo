package com.example.snoozeloo.data.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.snoozeloo.data.Alarm
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun AddNewAlarm(alarm: Alarm)

    @Query("SELECT * FROM alarms")
    fun GetAllAlarms() : Flow<List<Alarm>>
}