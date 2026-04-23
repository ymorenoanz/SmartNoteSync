package com.example.smartnotesync.di

import com.example.smartnotesync.data.repository.NoteRepositoryImpl
import com.example.smartnotesync.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMoodRepository(
        impl: NoteRepositoryImpl
    ): NoteRepository
}