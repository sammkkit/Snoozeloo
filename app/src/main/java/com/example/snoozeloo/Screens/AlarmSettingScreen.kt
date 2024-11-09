package com.example.snoozeloo.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snoozeloo.R
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.ui.text.style.TextAlign
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.example.snoozeloo.components.AlarmTime

@Composable
fun AlarmSetting(
    onCancelClick :()->Unit = {},
    onSaveClick:()->Unit = {}
){
    var timeOfAlarm by remember { mutableStateOf(Pair(20, 0)) }
    var alarmName by remember { mutableStateOf("Work") }
    var saveColor = if (timeOfAlarm == Pair(0,0)){
        Color(0xFFE6E6E6)
    }else{
        Color(0xFF4664FF)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Cancel Icon Button
            IconButton(
                onClick = onCancelClick,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFE6E6E6))
                    .size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }

            // Save Button
            Button(
                onClick = onCancelClick,
                colors = ButtonDefaults.buttonColors(saveColor),
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .height(36.dp)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "Save",
                    color = Color.White,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                        fontWeight = FontWeight(600),
                        fontSize = 16.sp
                    )
                )
            }
        }
        AlarmTime(
            AlarmTime = timeOfAlarm,
            onTimeChanged = {
                timeOfAlarm = it
            }
        )

        Spacer(modifier = Modifier.height(20.dp))
        AlarmCard {
            alarmName = it
        }
    }
}
@Composable
fun AlarmCard(
    onClick: (String) -> Unit
) {
    var alarmName by remember { mutableStateOf("Work") }
    var isDialogOpen by remember { mutableStateOf(false) }

    // Handle dialog open state here
    if (isDialogOpen) {
        AlertDialog(
            onDismissRequest = { isDialogOpen = false },
            title = {  Text(
                text = "Alarm Name",
                color = Color(0xFF0D0F19),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                    fontWeight = FontWeight(700),
                    fontSize = 18.sp,
                )
            ) },
            text = {
                TextField(
                    value = alarmName,
                    onValueChange = { alarmName = it },
                    label = { Text("Alarm Name") }
                )
            },
            confirmButton = {
//                TextButton(onClick = {
//                    onClick(alarmName)  // Pass the edited alarm name
//                    isDialogOpen = false
//                }) {
//                    Text("Save")
//                }
                Button(
                    onClick = {
                        onClick(alarmName)
                    isDialogOpen = false
                    },
                    colors = ButtonDefaults.buttonColors( Color(0xFF4664FF)),
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .height(36.dp)
                        .padding(horizontal = 8.dp)
                ) {
                    Text(
                        text = "Save",
                        color = Color.White,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                            fontWeight = FontWeight(600),
                            fontSize = 16.sp
                        )
                    )
                }
            }
        )
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Alarm Name",
                color = Color(0xFF0D0F19),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                    fontWeight = FontWeight(700),
                    fontSize = 18.sp,
                ),
            )
            Spacer(modifier = Modifier.width(8.dp))
            EditableTextField(text = alarmName) {
                // Open the dialog on click
                isDialogOpen = true
            }
        }
    }
}

@Composable
fun EditableTextField(text: String, onEditClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable(onClick = onEditClick)
            .padding(16.dp)
    ) {
        Text(
            text = text,
            color = Color(0xFF858585),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                fontWeight = FontWeight(500),
                fontSize = 16.sp,
            ),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AlarmSettingPreview(){
    AlarmSetting()
}
