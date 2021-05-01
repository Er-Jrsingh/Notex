package com.codingcrazz.notex.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.codingcrazz.notex.database.AppRepository;
import com.codingcrazz.notex.database.NoteEntity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EditorViewModel extends AndroidViewModel {

    public MutableLiveData<NoteEntity> mLiveNote = new MutableLiveData<>();
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    private AppRepository mRepository;

    public EditorViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
    }

    public void loadNote(int noteId) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                NoteEntity noteEntity = mRepository.loadNote(noteId);
                mLiveNote.postValue(noteEntity);
            }
        });
    }
}
