package com.rk.PasswordManager.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.rk.PasswordManager.R


fun isUserSignedIn(): Boolean {
    return FirebaseAuth.getInstance().currentUser != null
}

@Composable
fun GoogleSignInButton(navController: NavHostController, context: Context) {
    val signInLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    if (account != null) {
                        navController.navigate("Home") {
                            // Pop the GoogleSignInButton composable from the back stack
                            popUpTo("GoogleSignInButton") { inclusive = true }
                        }
                        firebaseAuthWithGoogle(account.idToken!!)
                    }
                } catch (e: ApiException) {
                    Log.d("error", "Error getting")
                }
            }
        }

    Box(
        modifier = Modifier
            .fillMaxSize()
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to MyNotes!",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Sign in with Google to continue",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            ElevatedButton(onClick = { signInLauncher.launch(createSignInIntent(context)) }) {
                Image(painter = painterResource(id = R.drawable.google), contentDescription = "Google",
                    modifier = Modifier.padding(5.dp))
                Text("Sign in with Google",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W700,
                    )
            }
        }
    }
}


fun createSignInIntent(context: Context): Intent {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    val mGoogleSignInClient = GoogleSignIn.getClient(context, gso)
    return mGoogleSignInClient.signInIntent
}

private fun firebaseAuthWithGoogle(idToken: String) {
    val credential = GoogleAuthProvider.getCredential(idToken, null)
    FirebaseAuth.getInstance().signInWithCredential(credential)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
//                val user = FirebaseAuth.getInstance().currentUser
                Log.d("signing", "signing successfully")
                // ...
            } else {
                Log.d("signing", "something went wrong")
            }
        }
        .addOnFailureListener {
            Log.d("signing", "something went wrong")
        }
}