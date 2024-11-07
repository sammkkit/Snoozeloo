package com.example.snoozeloo.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snoozeloo.R
import com.example.snoozeloo.components.AlarmItem
import com.example.snoozeloo.data.Alarm
import com.example.snoozeloo.data.mockAlarms

@Composable
fun AlarmListScreen(

){
    var AlarmList by remember { mutableStateOf(listOf<Alarm>()) }
    AlarmList = mockAlarms
    Column(
        modifier = Modifier.fillMaxSize()
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
        )
        LazyColumn {
            items(AlarmList){alarm->
                AlarmItem(modifier = Modifier,alarm = alarm)
            }
        }
    }
}
@Composable
@Preview(showBackground = true)
fun AlarmListScreenPreview(){
    AlarmListScreen()
}