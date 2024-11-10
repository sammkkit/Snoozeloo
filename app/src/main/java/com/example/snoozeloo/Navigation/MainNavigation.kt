package com.example.snoozeloo.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.snoozeloo.Presentation.MainViewModel
import com.example.snoozeloo.Presentation.Screens.AlarmListScreen
import com.example.snoozeloo.Presentation.Screens.AlarmSetting
import org.koin.androidx.compose.koinViewModel
@Composable
fun MainNavigation(

) {
    val mainViewModel: MainViewModel = koinViewModel()
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.Home
    ) {
        composable<Destination.Home>{
           AlarmListScreen(
               mainViewModel = mainViewModel,
               onPlusClick = {
                   navController.navigate(Destination.AlarmSetting)
               }
           )
        }
        composable<Destination.AlarmSetting> {
            AlarmSetting(
                mainViewModel = mainViewModel,
                navController = navController
            )
        }
    }

}