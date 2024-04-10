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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun NoteDetails(navController: NavController?, email: String?) {
    val currentDate = LocalDate.now()
    val viewModel: NoteViewModel = hiltViewModel()
    var notes by remember { mutableStateOf<List<Notes>>(emptyList()) }

//    val (title, setTitle) = remember { mutableStateOf("") }
//    val (content, setContent) = remember { mutableStateOf("") }
//    val (pin, setPin) = remember { mutableIntStateOf(R.drawable.unpin) }


    LaunchedEffect(viewModel) {
        val observedNotes = viewModel.getNoteById(email!!.toLong())
        notes = listOf(observedNotes)
        viewModel.updateTitle(observedNotes.title)
        viewModel.updateContent(observedNotes.content)
        viewModel.updatePin(observedNotes.pined_icon)
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
                            viewModel.deleteNoteAsync(
                                Notes(
                                    email!!.toInt(),
                                    viewModel.title,
                                    viewModel.content,
                                    notes[0].creation_date,
                                    currentDate.toString(),
                                    viewModel.pin
                                )
                            )
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.trash),
                                contentDescription = "new"
                            )
                        }
                        IconButton(onClick = {
                            if (viewModel.pin == R.drawable.pin) {
                                viewModel.updateNoteAsync(
                                    Notes(
                                        email!!.toInt(),
                                        viewModel.title,
                                        viewModel.content,
                                        notes[0].creation_date,
                                        currentDate.toString(),
                                        R.drawable.unpin
                                    )
                                )
                            } else {
                                viewModel.updateNoteAsync(
                                    Notes(
                                        email!!.toInt(),
                                        viewModel.title,
                                        viewModel.content,
                                        notes[0].creation_date,
                                        currentDate.toString(),
                                        R.drawable.pin
                                    )
                                )
                            }
                            navController!!.popBackStack()
                        }) {
                            Icon(
                                painter = painterResource(
                                    id = viewModel.pin
                                ),
                                contentDescription = "new"
                            )
                        }
                        IconButton(onClick = {
                            navController!!.popBackStack()
                            viewModel.updateNoteAsync(
                                Notes(
                                    email!!.toInt(),
                                    viewModel.title,
                                    viewModel.content,
                                    notes[0].creation_date,
                                    currentDate.toString(),
                                    viewModel.pin
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
            if (notes.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .padding(value)
                        .fillMaxSize()
                ) {
                    NoteForm1(viewModel)
                }
            } else {
                Text(text = "No data available")
            }
        }
    }
}