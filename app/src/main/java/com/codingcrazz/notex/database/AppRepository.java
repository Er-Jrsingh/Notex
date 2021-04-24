package com.codingcrazz.notex.database;

import com.codingcrazz.notex.utils.SampleDataProvider;

import java.util.List;

public class AppRepository {

    public static final AppRepository ourInstance = new AppRepository();
    public List<NoteEntity> mNotesList;

    private AppRepository() {
        mNotesList = SampleDataProvider.getSampleData();
    }

    public static AppRepository getInstance() {
        return ourInstance;
    }
}
