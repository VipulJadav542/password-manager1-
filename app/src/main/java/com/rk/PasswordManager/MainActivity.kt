package com.rk.PasswordManager

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivityResultRegistryOwner
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rk.PasswordManager.screens.GoogleSignInButton
import com.rk.PasswordManager.screens.Account.AccountDetails
import com.rk.PasswordManager.screens.Account.Home
import com.rk.PasswordManager.screens.SplashScreen
import com.rk.PasswordManager.screens.isUserSignedIn
import com.rk.PasswordManager.screens.Account.BottomSheetContent
import com.rk.PasswordManager.viewModel.AccountViewModel
import com.rk.PasswordManager.viewModel.AccountViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: AccountViewModelFactory

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(LocalContext.current)
//            MyApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val viewModel: AccountViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()
    val scaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = rememberModalBottomSheetState())

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheetContent(viewModel, LocalActivityResultRegistryOwner.current)
        },
        sheetPeekHeight = 0.dp
    ) {
        LaunchedEffect(key1 = 1) {
            scope.launch {
                scaffoldState.bottomSheetState.expand()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App(current: Context) {
    val navController = rememberNavController()
    val initialDestination = if (isUserSignedIn()) {
        "Home"
    } else {
        "GoogleSignInButton"
    }
    NavHost(navController = navController, startDestination = "SplashScreen") {
        composable(route = "SplashScreen") {
            SplashScreen(navController, destination = initialDestination)
        }

        composable(route = "GoogleSignInButton")
        {
            GoogleSignInButton(navController, context = current)
        }

        composable(route = "AddNew")
        {
            MyApp()
        }

        composable(route = "Home")
        {
            Home(navController = navController) {
                navController.navigate("AccountDetails/$it")
            }
        }

        composable(route = "AccountDetails/{email}", arguments = listOf(
            navArgument("email") {
                type = NavType.StringType
            }
        ))
        {
            val email = it.arguments!!.getString("email")
            AccountDetails(navController, email)
        }
    }
}











