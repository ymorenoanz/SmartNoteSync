package com.example.smartnotesync

import com.example.smartnotesync.domain.repository.NoteRepository
import com.example.smartnotesync.ui.viewmodel.NoteViewModel
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class NoteViewModelTest {
    private val repository: NoteRepository = mockk(relaxed = true)
    private lateinit var viewModel: NoteViewModel

    @Before
    fun setup() {
        viewModel = NoteViewModel(repository)
    }

    @Test
    fun `when addNote is called, repository insertNote is executed`() = runTest {
        val content = "Test note"

        viewModel.addNote(content)

        coVerify {
            repository.insertNote(match { it.content == content })
        }
    }

}