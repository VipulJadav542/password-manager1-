package com.rk.mynote.screens.notes

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
import com.rk.mynote.viewModel.NoteViewModel

@Composable
fun NoteForm1(viewModel: NoteViewModel) {
    var title by rememberSaveable { mutableStateOf(viewModel.title) }
    var content by rememberSaveable { mutableStateOf(viewModel.content) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(1f)
            .background(Color.White)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            placeholder = { Text("Title",
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.ExtraLight,
                fontSize = 28.sp
            ) },
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
            value = content,
            onValueChange = { content = it },
            placeholder = { Text("Content",
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.ExtraLight) },
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
        DisposableEffect(title, content) {
            viewModel.updateTitle(title)
            viewModel.updateContent(content)
            onDispose { }
        }
    }
}