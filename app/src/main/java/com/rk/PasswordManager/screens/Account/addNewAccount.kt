package com.rk.PasswordManager.screens.Account

import androidx.activity.result.ActivityResultRegistryOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rk.PasswordManager.model.AccountData
import com.rk.PasswordManager.viewModel.AccountViewModel

@Composable
fun BottomSheetContent(viewModel: AccountViewModel, current: ActivityResultRegistryOwner?) {
    var account by rememberSaveable { mutableStateOf(viewModel.account) }
    var username by rememberSaveable { mutableStateOf(viewModel.username) }
    var password by rememberSaveable { mutableStateOf(viewModel.password) }
    val isButtonEnabled = account.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()


    var maxInputLength by remember { mutableStateOf(10) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            OutlinedTextField(
                value = account,
                onValueChange = { account = it },
                placeholder = {
                    Text(
                        "Account Name",
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraLight
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textStyle = MaterialTheme.typography.bodySmall,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                ),
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                placeholder = {
                    Text(
                        "UserName/Email",
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraLight
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textStyle = MaterialTheme.typography.bodySmall,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                ),
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text(
                        "Password",
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraLight
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textStyle = MaterialTheme.typography.bodySmall,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                ),
            )
            Button(
                onClick = {
                    viewModel.addAccountAsync(
                        AccountData(
                            0,
                            viewModel.account,
                            viewModel.username,
                            viewModel.password
                        )
                    )
                    account = ""
                    username = ""
                    password = ""

                    // Show a toast message

                },
                enabled = isButtonEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            {
                Text(text = "Add New Account")
            }
        }
        DisposableEffect(account, username, password) {
            viewModel.updateAccount(account)
            viewModel.updateUserName(username)
            viewModel.updatePassword(password)
            onDispose { }
        }
    }
}