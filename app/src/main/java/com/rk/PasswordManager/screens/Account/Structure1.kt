package com.rk.PasswordManager.screens.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rk.PasswordManager.viewModel.AccountViewModel

@Composable
fun Form1(viewModel: AccountViewModel) {
    var account by rememberSaveable { mutableStateOf(viewModel.account) }
    var username by rememberSaveable { mutableStateOf(viewModel.username) }
    var password by rememberSaveable { mutableStateOf(viewModel.password) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(1f)
            .background(Color.White)
    ) {
        OutlinedTextField(
            label = { Text("account name",color = Color(0xffbf6347)) },
            value = account,
            onValueChange = { account = it },
            placeholder = {
                Text(
                    "Account",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.ExtraLight,
                    fontSize = 20.sp,
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = MaterialTheme.typography.headlineMedium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
            ),
        )


        TextField(
            label = { Text("username",color = Color(0xffbf6347)) },
            value = username,
            onValueChange = { username = it },
            placeholder = {
                Text(
                    "username",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
            ),
        )
        TextField(
            label = { Text("Password",color = Color(0xffbf6347)) },
            value = password,
            onValueChange = { password = it },
            placeholder = {
                Text(
                    "password",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
            ),
        )

        // Update the view model with the latest entered data
        DisposableEffect(account, username, password) {
            viewModel.updateAccount(account)
            viewModel.updateUserName(username)
            viewModel.updatePassword(password)
            onDispose { }
        }
    }
}