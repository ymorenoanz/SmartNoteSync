package com.example.smartnotesync.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartnotesync.ui.viewmodel.NoteViewModel

@Composable
fun NoteScreen(viewModel: NoteViewModel = hiltViewModel()) {
    // Refactored to use state hoisting. The stateful NoteScreen now delegates UI rendering
    // to a stateless NoteScreenContent. This fixes the Preview issue because Previews
    // cannot instantiate ViewModels with dependencies.
    NoteScreenContent(
        onSaveNote = { content -> viewModel.addNote(content) }
    )
}

@Composable
fun NoteScreenContent(
    onSaveNote: (String) -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }

        Card(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )  {
                //Note textField
                OutlinedTextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    maxLines = Int.MAX_VALUE,
                    label = { Text("Enter text...") }
                )

                Button(onClick = { onSaveNote(text) }) {
                    Text(text = "Save note")
                }

            }
        }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    // Use the stateless content Composable for the Preview
    NoteScreenContent(onSaveNote = {})
}
