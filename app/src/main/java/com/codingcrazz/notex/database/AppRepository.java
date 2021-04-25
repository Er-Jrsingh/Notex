package com.codingcrazz.notex.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.codingcrazz.notex.utils.SampleDataProvider;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {

    public static AppRepository ourInstance;
    private AppDatabase mDatabase;
    public LiveData<List<NoteEntity>> mNotesList;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    private AppRepository(Context context) {
//        mNotesList = SampleDataProvider.getSampleData();
        mDatabase=AppDatabase.getInstance(context);
        mNotesList = getAllNotes();
    }

    public static AppRepository getInstance(Context context) {
        return ourInstance=new AppRepository(context);
    }

    public void addSampleData() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                 mDatabase.notesDao().insertAll(SampleDataProvider.getSampleData());
            }
        });
    }
    private LiveData<List<NoteEntity>> getAllNotes(){
        return mDatabase.notesDao().getAllNotes();
    }
}
