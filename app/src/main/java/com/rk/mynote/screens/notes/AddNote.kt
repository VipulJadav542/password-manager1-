package com.rk.mynote.screens.notes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rk.mynote.R
import com.rk.mynote.model.Notes
import com.rk.mynote.ui.theme.MyNoteTheme
import com.rk.mynote.viewModel.NoteViewModel
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteAdd(navController: NavController) {
    val currentDate = LocalDate.now()
    val viewModel: NoteViewModel = hiltViewModel()
//
//    val (title, setTitle) = rememberSaveable { mutableStateOf("") }
//    val (content, setContent) = rememberSaveable { mutableStateOf("") }

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
                            navController.popBackStack()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.back),
                                contentDescription = "new"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            navController.popBackStack()
                            viewModel.addNoteAsync(
                                Notes(
                                    0,
                                    viewModel.title,
                                    viewModel.content,
                                    currentDate.toString(),
                                    currentDate.toString(),
                                    R.drawable.pin
                                )
                            )
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.unpin),
                                contentDescription = "new"
                            )
                        }
                        IconButton(onClick = {
                            navController.popBackStack()
                            viewModel.addNoteAsync(
                                Notes(
                                    0,
                                    viewModel.title,
                                    viewModel.content,
                                    currentDate.toString(),
                                    currentDate.toString(),
                                    R.drawable.unpin
                                )
                            )
                        }) {
                            Icon(
                                painter = painterResource(
                                    id = R.drawable.yes
                                ),
                                contentDescription = "new"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            }
        ) { value ->
            Row(
                modifier = Modifier
                    .padding(value)
                    .fillMaxSize()
            ) {
                NoteForm1(viewModel)
            }
        }
    }
}