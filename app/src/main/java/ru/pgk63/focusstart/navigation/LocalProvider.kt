package ru.pgk63.focusstart.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController

val LocalNavController = compositionLocalOf<NavController> { error("no provider nav controller") }

val LocalNavHostController = compositionLocalOf<NavHostController> { error("no provider nav host controller") }