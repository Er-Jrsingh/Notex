package com.codingcrazz.notex.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.codingcrazz.notex.database.AppRepository;
import com.codingcrazz.notex.database.NoteEntity;
import com.codingcrazz.notex.utils.SampleDataProvider;

import java.util.List;

public class ListActivityViewModel extends AndroidViewModel {

    public LiveData<List<NoteEntity>> mNotesList;
    //    public List<NoteEntity> mNotesList= SampleDataProvider.getSampleData();
//    public List<NoteEntity> mNotesList;
    private AppRepository mRepository;

    public ListActivityViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mNotesList = mRepository.mNotesList;
    }

    public void addSampleData() {
        mRepository.addSampleData();
    }

    public void deleteAllData() {
        mRepository.deleteAllData();
    }
}