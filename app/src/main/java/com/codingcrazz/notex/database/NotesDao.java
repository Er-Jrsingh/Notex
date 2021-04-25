package com.codingcrazz.notex.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

//      This Is Also Called An "Operation In DAO"
@Dao
public interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNode(NoteEntity noteEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<NoteEntity> noteEntities);

    @Delete
    void deleteNote(NoteEntity noteEntity);

    //    id is Parameter Name , this can by any valid name but both must match , this is not column name
//    WHERE id is column name so that it must match the field name in Entity Class

    @Query("SELECT * FROM notes WHERE  id=:id")
    NoteEntity getNoteById(int id);

    @Query("SELECT * FROM notes ORDER BY date DESC")
    LiveData<List<NoteEntity>> getAllNotes();

    @Query("DELETE FROM notes")
    int deleteAllNotes();

    @Query("SELECT COUNT(*) FROM notes")
    int getCount();

}
