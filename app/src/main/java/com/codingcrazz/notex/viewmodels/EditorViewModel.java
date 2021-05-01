package com.codingcrazz.notex.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.codingcrazz.notex.database.AppRepository;
import com.codingcrazz.notex.database.NoteEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EditorViewModel extends AndroidViewModel {

    public MutableLiveData<NoteEntity> mLiveNote = new MutableLiveData<NoteEntity>();
    private AppRepository mRepository;

    public EditorViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
    }
}
