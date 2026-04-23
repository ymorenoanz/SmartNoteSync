package com.example.smartnotesync.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnotesync.domain.model.Note
import com.example.smartnotesync.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {


    val notes = repository.getAllNotes()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    fun addNote(content: String) {
        viewModelScope.launch {
            repository.insertNote(
                Note(
                    id = UUID.randomUUID().toString(),
                    content = content
                ))
        }
    }
}
