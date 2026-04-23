package com.example.smartnotesync.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.smartnotesync.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    // 🔹 Obtener todas las notas (reactivo)
    @Query("SELECT * FROM notes WHERE isDeleted = 0 ORDER BY lastModified DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    // 🔹 Obtener TODAS (incluyendo borradas, útil para sync)
    @Query("SELECT * FROM notes")
    fun getAllNotesOnce(): Flow<List<NoteEntity>>

    // 🔹 Obtener notas pendientes de sincronizar
    @Query("SELECT * FROM notes WHERE syncStatus != 'SYNCED'")
    fun getPendingNotes(): Flow<List<NoteEntity>>

    // 🔹 Insertar nota
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    // 🔹 Actualizar nota
    @Update
    suspend fun update(note: NoteEntity)

    // 🔹 Marcar como sincronizada
    @Query("UPDATE notes SET syncStatus = 'SYNCED' WHERE id = :id")
    suspend fun markAsSynced(id: String)

    // 🔹 Marcar error
    @Query("UPDATE notes SET syncStatus = 'ERROR' WHERE id = :id")
    suspend fun markAsError(id: String)

    // 🔹 Soft delete (NO borrar realmente)
    @Query("""
        UPDATE notes
        SET isDeleted = 1,
            syncStatus = 'PENDING',
            lastModified = :timestamp
        WHERE id = :id
    """)
    suspend fun softDelete(id: String, timestamp: Long)
}
