package com.rk.PasswordManager.screens.Account

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivityResultRegistryOwner.current
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rk.PasswordManager.model.AccountData
import com.rk.PasswordManager.ui.theme.MyNoteTheme
import com.rk.PasswordManager.viewModel.AccountViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home(navController: NavController, function: (id: Int) -> Unit) {
    val viewModel: AccountViewModel = hiltViewModel()
    var simpleNotes by remember { mutableStateOf<List<AccountData>>(emptyList()) }

    // State to manage the visibility of the bottom sheet
    var isBottomSheetVisible by remember { mutableStateOf(false) }

    val bottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    // Handle back press to close the bottom sheet if it's open
    BackHandler(enabled = bottomSheetState.isVisible) {
        coroutineScope.launch { bottomSheetState.hide() }
    }

    // Function to toggle the bottom sheet
    val toggleBottomSheet: () -> Unit = {
        if (bottomSheetState.isVisible) {
            coroutineScope.launch { bottomSheetState.hide() }
        } else {
            coroutineScope.launch { bottomSheetState.show() }
        }
    }

    viewModel.getAccount().observeForever { observedAccount ->
        if (observedAccount.isNotEmpty()) {
            simpleNotes = observedAccount
        }
    }
    LaunchedEffect(isBottomSheetVisible) {
        if (isBottomSheetVisible) {
            bottomSheetState.show()
            isBottomSheetVisible = false
        }
    }

    MyNoteTheme {
        BottomSheetScaffold(
            scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState),

            sheetContent = {
                BottomSheetContent(viewModel = viewModel,current)
            },
            content = {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = toggleBottomSheet,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = "Add")
                        }
                    },
                    topBar = {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Password Manager",
                                fontSize = 20.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth().padding(top = 50.dp, start = 15.dp, bottom = 25.dp)
                            )
                            Box(
                                modifier = Modifier.heightIn(2.dp).background(Color.Black)
                            )
                        }
                    },
                    floatingActionButtonPosition = FabPosition.End,
                    content = { paddingValues ->
                        LazyColumn(
                            modifier = Modifier.padding(paddingValues).background(Color.White),
                            content = {
                                if (simpleNotes.isNotEmpty()) {
                                    items(simpleNotes) { note ->
                                        AccountItemList(account = note, function = function)
                                    }
                                }
                            }
                        )
                    }
                )
            }
        )
    }
}
