package com.rk.mynote

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rk.mynote.screens.GoogleSignInButton
import com.rk.mynote.screens.notes.NoteAdd
import com.rk.mynote.screens.notes.NoteDetails
import com.rk.mynote.screens.notes.NoteList
import com.rk.mynote.screens.SplashScreen
import com.rk.mynote.screens.isUserSignedIn
import com.rk.mynote.viewModel.NoteViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: NoteViewModelFactory

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(LocalContext.current)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App(current: Context) {
    val navController = rememberNavController()
    val initialDestination = if (isUserSignedIn()) {
        "NoteList"
    } else {
        "GoogleSignInButton"
    }
    NavHost(navController = navController, startDestination = "SpalceScreen") {
        composable(route = "SpalceScreen") {
            SplashScreen(navController, destination = initialDestination)

        }
        composable(route = "GoogleSignInButton")
        {
            GoogleSignInButton(navController, context = current)
        }
        composable(route = "NoteList")
        {
            NoteList(navController = navController) {
                navController.navigate("NoteDetails/$it")
            }
        }
        composable(route = "NoteDetails/{email}", arguments = listOf(
            navArgument("email") {
                type = NavType.StringType
            }
        ))
        {
            val email = it.arguments!!.getString("email")
            NoteDetails(navController, email)
        }

        composable(route = "NoteAdd")
        {
            NoteAdd(navController)
        }
    }
}











