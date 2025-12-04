package com.example.tutorlink_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tutorlink_app.ui.screens.SplashScreen
import com.example.tutorlink_app.ui.screens.TutorListScreen
import com.example.tutorlink_app.ui.theme.TutorLinkAppTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutorLinkAppTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {

                    // SPLASH SCREEN
                    composable("splash") {
                        SplashScreen()

                        // Auto navigate lepas 2 saat
                        LaunchedEffect(Unit) {
                            delay(2000)
                            navController.navigate("list") {
                                popUpTo("splash") { inclusive = true }
                            }
                        }
                    }

                    // LIST SCREEN
                    composable("list") {
                        TutorListScreen()
                    }
                }
            }
        }
    }
}

