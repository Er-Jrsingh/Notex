package com.codingcrazz.notex.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.codingcrazz.notex.database.NoteEntity;
import com.codingcrazz.notex.utils.SampleDataProvider;

import java.util.List;

public class ListActivityViewModel extends AndroidViewModel {

    public List<NoteEntity> mNotesList= SampleDataProvider.getSampleData();

    public ListActivityViewModel(@NonNull Application application) {
        super(application);
    }
}
