package com.rk.mynote.screens

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rk.mynote.R
import com.rk.mynote.ui.theme.MyNoteTheme

@Composable
fun SplashScreen(navController: NavHostController, destination: String) {
    Handler(Looper.getMainLooper()).postDelayed({
        navController.navigate(destination)
    }, 3000)
    Log.d("SplashScreen", "SplashScreen")
    navController.popBackStack()
    MyNoteTheme {
        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.note),
                contentDescription = "Splash Image",
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFE0E0E0),
                                Color(0xFF757575)
                            ),
                            startY = 0f,
                            endY = 500f
                        )
                    )
                    .fillMaxSize()
                    .padding(16.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}