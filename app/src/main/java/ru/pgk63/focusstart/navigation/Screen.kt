package ru.pgk63.focusstart.navigation

sealed class Screen(val route:String) {
    object MainScreen:Screen("main_screen")
}