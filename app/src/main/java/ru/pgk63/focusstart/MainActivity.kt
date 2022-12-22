package ru.pgk63.focusstart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.pgk63.focusstart.navigation.LocalNavController
import ru.pgk63.focusstart.navigation.LocalNavHostController
import ru.pgk63.focusstart.navigation.Screen
import ru.pgk63.focusstart.ui.screens.mainScreen.MainScreen
import ru.pgk63.focusstart.ui.theme.FocusStartTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            FocusStartTheme {
                CompositionLocalProvider(
                    LocalNavController provides navController,
                    LocalNavHostController provides navController
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MainScreen.route
                    ){
                        composable(Screen.MainScreen.route){
                            MainScreen()
                        }
                    }
                }
            }
        }
    }
}
