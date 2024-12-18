package com.example.snoozeloo.Presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snoozeloo.R
import com.example.snoozeloo.data.Alarm
import com.example.snoozeloo.data.mockAlarms
import kotlinx.coroutines.delay
import java.time.LocalTime
import java.time.temporal.ChronoUnit

@Composable
fun AlarmItem(
    modifier: Modifier,
    alarm:Alarm,
    onAlarmToggle:(Boolean)->Unit={}
){
    var alarmHours by remember { mutableStateOf(alarm.hour) }
    var alarmMinutes by remember { mutableStateOf(alarm.minute) }
    var timeLeftText by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(alarm.toggle) }

    LaunchedEffect(alarm.toggle) {
        isChecked = alarm.toggle
    }

    LaunchedEffect(alarmHours, alarmMinutes) {
        while (true) {
            // Get the current time and calculate the time until alarm
            val currentTime = LocalTime.now()
            val alarmTime = LocalTime.of(alarmHours, alarmMinutes)

            val durationUntilAlarm = if (currentTime.isBefore(alarmTime)) {
                ChronoUnit.MINUTES.between(currentTime, alarmTime)
            } else {
                ChronoUnit.MINUTES.between(currentTime, alarmTime.plusHours(24))
            }

            // Convert the duration into hours and minutes
            var hoursLeft = (durationUntilAlarm / 60).toInt()
            var minutesLeft = (durationUntilAlarm % 60).toInt()
            if (minutesLeft < 0) {
                minutesLeft += 60
                hoursLeft -= 1
            }

            if (hoursLeft < 0) {
                hoursLeft += 24
            }
            timeLeftText = "${hoursLeft}h ${minutesLeft+1}min"

            // Wait for 1 minute before recalculating
            delay(60000L) // 60000ms = 1 minute
        }
    }

    Card(
        modifier = modifier.fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(10.dp))
        ,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF)
        )
    ) {
        Row(
            modifier= Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier= Modifier.fillMaxSize()
                    .weight(1f)
            ) {
                Text(
                    text = "${alarm.name}",
                    color = Color(0xFF0D0F19),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                        fontWeight = FontWeight(600),
                        fontSize = 16.sp,
                        lineHeight = 19.5.sp,
                    ),
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Row (
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = String.format("%02d:%02d", alarm?.hour ?: 0, alarm?.minute ?: 0),
                        color = Color(0xFF0D0F19),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                            fontWeight = FontWeight(500),
                            fontSize = 42.sp,
                            lineHeight = 51.2.sp,
                        ),
                        modifier = Modifier.padding(end = 6.dp)
                    )
                    Text(
                        text = "${alarm.meridiem}",
                        color = Color(0xFF0D0F19),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                            fontWeight = FontWeight(500),
                            fontSize = 24.sp,
                            lineHeight = 29.26.sp,
                        )
                        ,modifier = Modifier.padding(top = 8.dp,end = 8.dp)
                    )
                }
                Text(
                    text = if (isChecked){
                        "Alarm in $timeLeftText"
                    }else{
                        ""
                    },
                    color = Color(0xFF858585),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                        fontWeight = FontWeight(500),
                        fontSize = 14.sp,
                        lineHeight = 17.07.sp,
                    )
                )

            }
            CustomSwitch(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                    onAlarmToggle(it)
                },
                modifier = Modifier
            )
        }

    }
}
@Composable
@Preview
fun AlarmItemPrev(){
    AlarmItem(
        alarm = mockAlarms[0],
        modifier = Modifier
    )

}