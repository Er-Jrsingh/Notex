package com.codingcrazz.notex;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codingcrazz.notex.database.NoteEntity;
import com.codingcrazz.notex.model.NotesAdapter;
import com.codingcrazz.notex.viewmodels.ListActivityViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private List<NoteEntity> mNotesList;
    private ListActivityViewModel mViewModel;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.notes_recyclerview)
    RecyclerView mRecyclerView;

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.fab_add_note)
    void OnFabClicked() {
        Intent intent = new Intent(MainActivity.this, EditorActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViewModel();
        ButterKnife.bind(this);
        initRecyclerView();

        mNotesList = mViewModel.mNotesList;
        showData();
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(ListActivityViewModel.class);
    }

    private void showData() {
        NotesAdapter notesAdapter = new NotesAdapter(this, mNotesList);
        mRecyclerView.setAdapter(notesAdapter);
    }

    private void initRecyclerView() {
        mRecyclerView.hasFixedSize();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }
}