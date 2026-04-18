package com.example.smartnotesync.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NoteScreen() {
    Scaffold() {
        Box(modifier = Modifier.padding(it)) {

            //Note textField
            OutlinedTextField(
                value = "",
                onValueChange = {
                    TODO() },
                maxLines = Int.MAX_VALUE,
                label = {Text("Enter text...")},
                placeholder = {Text("")}
            )


            Button(onClick = { /*TODO*/ }) {
                Text(text = "Save note")
            }

        }
    }
}