package com.example.snoozeloo.Presentation.Screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snoozeloo.Presentation.MainViewModel
import com.example.snoozeloo.R
import com.example.snoozeloo.Presentation.components.AlarmItem
import com.example.snoozeloo.data.Alarm
import com.example.snoozeloo.data.mockAlarms
import kotlinx.coroutines.delay

@Composable
fun AlarmListScreen(
    mainViewModel: MainViewModel,
    context: Context,
    onPlusClick:()->Unit = {}
){
//    var AlarmList by remember { mutableStateOf(listOf<Alarm>()) }
    val AlarmList by mainViewModel.alarmList.collectAsState()
//    AlarmList = mockAlarms
    var clickCount by remember { mutableStateOf(0) }
    val clickThreshold = 3 // Number of clicks to trigger the action
    val timeLimitMs = 500L // Time limit for clicks (e.g., 500ms)

    LaunchedEffect(clickCount) {
        if (clickCount == clickThreshold) {
            mainViewModel.deleteAllAlarms()
            clickCount = 0
        } else {
            delay(timeLimitMs)
            clickCount = 0
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(top = 32.dp)
                .padding(16.dp)
        ) {
            Text(
                text = "Your Alarms",
                color = Color(0xFF0D0F19),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                    fontWeight = FontWeight(500),
                    fontSize = 28.sp,
                    lineHeight = 29.sp,
                ),
                modifier = Modifier.padding(bottom = 24.dp)
                    .clickable {
                        clickCount++
                    }

            )
            if(AlarmList.isEmpty()){
                Box(
                    modifier = Modifier.fillMaxSize(),
                ){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.clock_icon),
                            contentDescription = "Clock Icon",
                            modifier = Modifier.size(90.dp).padding(bottom = 30.dp),
                            colorFilter = ColorFilter.tint(colorResource(R.color.purple)) // Apply tint using ColorFilter
                        )
                        Text(
                            text = "It's empty! Add the first alarm so you\n" +
                                    "don't miss an important moment!",
                            color = Color(0xFF0D0F19),
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                lineHeight = 29.sp,
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )
                    }
                }
            }else {
                LazyColumn {
                    items(AlarmList) { alarm ->
                        AlarmItem(modifier = Modifier, alarm = alarm, onAlarmToggle = {
                            Log.d("AlarmListScreen", "Alarm toggled $it: ${alarm.name}")
                            if(it){
                                mainViewModel.ScheduleAlarm(alarm)
                            }else{
                                mainViewModel.CancelAlarm(alarm)
                            }
                        })
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = {
                onPlusClick.invoke()
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
                .clip(CircleShape)
            ,
            containerColor = colorResource(R.color.purple)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Alarm",
                tint = Color.White
            )
        }
    }
}
