package com.example.snoozeloo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snoozeloo.R

@Composable
fun AlarmTime(
    AlarmTime: Pair<Int, Int> = (Pair(0, 0)),
    onTimeChanged: (Pair<Int, Int>) -> Unit // Lambda to handle state change
){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
        ,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF)
        )
    ) {
        Row (
            modifier = Modifier.fillMaxWidth()
                .padding(24.dp)
            ,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier.weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFF6F6F6))
                    .fillMaxSize()
                ,
                contentAlignment = Alignment.Center
            ){
                TextField(
                    value = AlarmTime.first.toString().padStart(2, '0'),
                    onValueChange = {
                        onTimeChanged(AlarmTime.copy(first = it.toIntOrNull() ?: 0))
                    },
                    modifier = Modifier
                    ,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedTextColor = Color(0xFF858585),
                        focusedTextColor = Color(0xFF4664FF),
                        unfocusedContainerColor = Color(0xFFF6F6F6),
                        focusedContainerColor = Color(0xFFF6F6F6),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    textStyle =TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                        fontWeight = FontWeight(600),
                        fontSize = 58.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
            Text(":",
                color = Color(0xFF858585),
                fontSize = 54.sp,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp)
            )
            Box(
                modifier = Modifier.weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFF6F6F6))
                    .fillMaxSize()
                ,
                contentAlignment = Alignment.Center
            ){
                TextField(
                    value = AlarmTime.second.toString().padStart(2, '0'),
                    onValueChange = {
                        onTimeChanged(AlarmTime.copy(second = it.toIntOrNull() ?: 0))
                    },
                    modifier = Modifier
                    ,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedTextColor = Color(0xFF858585),
                        focusedTextColor = Color(0xFF4664FF),
                        unfocusedContainerColor = Color(0xFFF6F6F6),
                        focusedContainerColor = Color(0xFFF6F6F6),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    textStyle =TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                        fontWeight = FontWeight(600),
                        fontSize = 58.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}
@Preview
@Composable
fun Prere(){
    AlarmTime(
        onTimeChanged = {}
    )
}