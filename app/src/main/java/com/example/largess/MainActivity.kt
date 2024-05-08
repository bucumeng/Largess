package com.example.largess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.largess.screen.AddScreen
import com.example.largess.screen.LoginScreen
import com.example.largess.screen.PhotoAddScreen
import com.example.largess.screen.SigninScreen
import com.example.largess.screen.home.HomeScreen
import com.example.largess.ui.theme.LargessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LargessTheme {
                // A surface container using the 'background' color from the theme
                    App()
            }
        }
    }
}

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf("login") }
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                navController = navController,
                onLoginClick = {
                    currentScreen = "home"
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onSignUpClick = {
                    currentScreen = "register"
                    navController.navigate("register") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("register") {
            SigninScreen(navController = navController) {
                currentScreen = "login"
                navController.navigate("login") {
                    popUpTo("register") { inclusive = true }
                }
            }
        }
        composable("home") {
            HomeScreen(
                navController = navController,
                onAddClick = {
                    currentScreen = "add"
                    navController.navigate("add") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
        composable("add") {
            AddScreen(navController = navController,
                onAddClick = {
                    currentScreen = "add"
                    navController.navigate("addPhoto")
                },
                onAddPhotoClick = {
                    currentScreen = "addPhoto"
                    navController.navigate("addPhoto") {
                        popUpTo("add") { inclusive = true }
                    }

                }
            )
        }
        composable("addPhoto"){
            PhotoAddScreen(navController = navController,
            ) {

            }
        }
    }
}

