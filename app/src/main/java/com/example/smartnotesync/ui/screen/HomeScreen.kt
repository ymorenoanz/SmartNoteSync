package com.example.smartnotesync.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.smartnotesync.ui.viewmodel.NoteViewModel

@Composable
fun HomeScreen(viewModel: NoteViewModel) {
    val notes by viewModel.notes.collectAsState()

    LazyColumn {
        items(notes) { note ->
            Text(text = note.content)
        }
    }

}