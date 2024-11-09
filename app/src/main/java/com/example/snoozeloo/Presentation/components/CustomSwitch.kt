package com.example.snoozeloo.Presentation.components

import android.text.BoringLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snoozeloo.R

@Composable
fun pxToSp(px: Int): TextUnit {
    val density = LocalDensity.current.density
    return (px / density).sp
}

@Composable
fun pxToDp(px: Int): Dp {
    val density = LocalDensity.current.density
    return (px / density).dp
}
@Composable
fun CustomSwitch(
    modifier: Modifier,
    checked :Boolean,
    onCheckedChange : (Boolean)->Unit,
){
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        thumbContent = {
            Box(
                modifier = Modifier
                    .size(pxToDp(20)) // Size of the thumb
                    .background(
                        color = if (checked) Color.White else Color.White.copy(0.12f),
                        shape = CircleShape
                    )
            )
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White,
            checkedTrackColor = colorResource(R.color.purple),
            uncheckedThumbColor = Color.White,
            uncheckedTrackColor = colorResource(R.color.light_purple),
            uncheckedBorderColor = Color.Transparent
        ),
        modifier = modifier
    )
}
@Composable
@Preview(showBackground = true)
fun prev(){
    var checked by remember { mutableStateOf(false) }
    CustomSwitch(
        modifier = Modifier,
        checked = checked,
        onCheckedChange = {
            checked = it
        }
    )
}