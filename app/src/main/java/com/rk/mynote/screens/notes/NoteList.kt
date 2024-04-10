package com.rk.mynote.screens.notes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rk.mynote.R
import com.rk.mynote.model.Notes
import com.rk.mynote.ui.theme.MyNoteTheme
import com.rk.mynote.viewModel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteList(navController: NavController, function: (id: Int) -> Unit) {

    val viewModel: NoteViewModel = hiltViewModel()
    var simpleNotes by remember { mutableStateOf<List<Notes>>(emptyList()) }
    var pinNotes by remember { mutableStateOf<List<Notes>>(emptyList()) }
    var searchText by remember { mutableStateOf("") }

    viewModel.getNotes().observeForever { observedNotes ->
        if (observedNotes.isNotEmpty()) {
            val unpinned = observedNotes.filter { it.pined_icon == R.drawable.unpin }
            val pinned = observedNotes.filter { it.pined_icon == R.drawable.pin }
            simpleNotes = unpinned
            pinNotes = pinned
        } else {
            simpleNotes = emptyList()
            pinNotes = emptyList()
        }
    }
    MyNoteTheme {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
//                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                OutlinedTextField(
                    value = searchText,
                    leadingIcon = {
                        Icon(
                            painterResource(id = R.drawable.search),
                            contentDescription = "emailIcon"
                        )
                    },
                    onValueChange = { searchText = it },
                    placeholder = { Text("Search") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate("NoteAdd")
                    },
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
//                    .verticalScroll(rememberScrollState())
            ) {
                if (pinNotes.isNotEmpty() or simpleNotes.isNotEmpty()) {
                    // Display grid for pinNotes if available
                    if (pinNotes.isNotEmpty()) {
                        Text(
                            text = "Pinned Notes",
                            fontFamily = FontFamily.Serif,
                            modifier = Modifier
                                .padding(10.dp, 10.dp, 0.dp, 0.dp)
                        )
                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Fixed(2),
                            verticalItemSpacing = 4.dp,
                            modifier = Modifier
                                .background(Color.White),
//                                .height(500.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            content = {
                                val filteredPinNotes = pinNotes.filter { note ->
                                    note.title.contains(searchText, ignoreCase = true) ||
                                            note.content.contains(searchText, ignoreCase = true)
                                }
                                items(filteredPinNotes) { note ->
                                    NoteItemList(note = note, function)
                                }
                            }
                        )
                    }

                    // Display grid for simpleNotes if available
                    if (simpleNotes.isNotEmpty()) {
                        Text(
                            text = "Others",
                            fontFamily = FontFamily.Serif,
                            modifier = Modifier
                                .padding(10.dp, 10.dp, 0.dp, 0.dp)
                        )
                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Fixed(2),
                            verticalItemSpacing = 4.dp,
                            modifier = Modifier
                                .background(Color.White),
//                                .height(500.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            content = {
                                val filteredNotes = simpleNotes.filter { note ->
                                    note.title.contains(searchText, ignoreCase = true) ||
                                            note.content.contains(searchText, ignoreCase = true)
                                }
                                items(filteredNotes) { note ->
                                    NoteItemList(note = note, function)
                                }
                            }
                        )
                    }
                } else {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "no notes found!!!",
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 28.sp,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }
    }
}