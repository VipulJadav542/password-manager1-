package com.rk.PasswordManager.screens.Account

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rk.PasswordManager.R
import com.rk.PasswordManager.model.AccountData
import com.rk.PasswordManager.ui.theme.MyNoteTheme
import com.rk.PasswordManager.viewModel.AccountViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetails(navController: NavController?, id: String?) {
    val viewModel: AccountViewModel = hiltViewModel()
    var account by remember { mutableStateOf<List<AccountData>>(emptyList()) }

//    val (account, setAccount) = remember { mutableStateOf("") }
//    val (username, setUsername) = remember { mutableStateOf("") }
//    val (pin, setPin) = remember { mutableIntStateOf(R.drawable.unpin) }


    LaunchedEffect(viewModel) {
        val observedaccount = viewModel.getAccountById(id!!.toLong())
        account = listOf(observedaccount)
        viewModel.updateAccount(observedaccount.accountName)
        viewModel.updateUserName(observedaccount.userName)
        viewModel.updatePassword(observedaccount.password)
    }
    MyNoteTheme {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = {
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController!!.popBackStack()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.back),
                                contentDescription = "new"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            navController!!.popBackStack()
                            viewModel.deleteAccountAsync(
                                AccountData(
                                    id!!.toInt(),
                                    viewModel.account,
                                    viewModel.username,
                                    viewModel.password
                                )
                            )
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.trash),
                                contentDescription = "new"
                            )
                        }
                        IconButton(onClick = {
                            navController!!.popBackStack()
                            viewModel.updateAccountAsync(
                                AccountData(
                                    id!!.toInt(),
                                    viewModel.account,
                                    viewModel.username,
                                    viewModel.password
                                )
                            )
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.yes),
                                contentDescription = "new"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            }
        ) { value ->
            if (account.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .padding(value)
                        .fillMaxSize()
                ) {
                    Form1(viewModel)
                }
            } else {
                Text(text = "No data available")
            }
        }
    }
}