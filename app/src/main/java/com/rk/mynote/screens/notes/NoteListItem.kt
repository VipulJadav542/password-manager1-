package com.rk.mynote.screens.notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rk.mynote.model.Notes

@Composable
fun NoteItemList(note: Notes, function: (id: Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                function(note.noteId)
            }
    ) {
        Row(
            modifier = Modifier.padding(8.dp)

        ) {

            Column(
                modifier = Modifier.weight(.1f)

            ) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
                )
                Text(
                    text = note.content,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(1.dp)
                )
                Text(
                    text = note.last_modified_date,
                    modifier = Modifier.align(Alignment.End),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}