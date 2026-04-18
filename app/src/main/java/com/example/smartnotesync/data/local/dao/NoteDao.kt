package com.example.smartnotesync.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.smartnotesync.domain.model.Note

@Dao
interface NoteDao {
/*    // 🔹 Obtener todas las notas (reactivo)
    @Query("SELECT * FROM notes WHERE isDeleted = 0 ORDER BY lastModified DESC")
    fun getAllNotes(): Flow<List<Note>>*/

    // 🔹 Obtener TODAS (incluyendo borradas, útil para sync)
    @Query("SELECT * FROM notes")
    suspend fun getAllNotesOnce(): List<Note>

    // 🔹 Obtener notas pendientes de sincronizar
    @Query("SELECT * FROM notes WHERE syncStatus != 'SYNCED'")
    suspend fun getPendingNotes(): List<Note>

    // 🔹 Insertar nota
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    // 🔹 Actualizar nota
    @Update
    suspend fun update(note: Note)

    // 🔹 Marcar como sincronizada
    @Query("UPDATE notes SET syncStatus = 'SYNCED' WHERE id = :id")
    suspend fun markAsSynced(id: String)

    // 🔹 Marcar error
    @Query("UPDATE notes SET syncStatus = 'ERROR' WHERE id = :id")
    suspend fun markAsError(id: String)

/*    // 🔹 Soft delete (NO borrar realmente)
    @Query("""
        UPDATE notes
        SET isDeleted = 1,
            syncStatus = 'PENDING',
            lastModified = :timestamp
        WHERE id = :id
    """)
    suspend fun softDelete(id: String, timestamp: Long)*/
}