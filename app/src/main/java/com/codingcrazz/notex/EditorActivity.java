package com.codingcrazz.notex;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.codingcrazz.notex.utils.Constants;
import com.codingcrazz.notex.viewmodels.EditorViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.codingcrazz.notex.utils.Constants.EDITING_KEY;


public class EditorActivity extends AppCompatActivity {

    private EditorViewModel mViewModel;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edit_note_text)
    TextView mEditText;
    private boolean aNewNote;
    private boolean isEditing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_check);
        CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            isEditing = savedInstanceState.getBoolean(EDITING_KEY);
        }

        initViewMode();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(EDITING_KEY, true);
        super.onSaveInstanceState(outState);
    }

    private void initViewMode() {

        mViewModel = ViewModelProviders.of(this)
                .get(EditorViewModel.class);

        mViewModel.mLiveNote.observe(this, (noteEntity) -> {
            if (noteEntity != null && !isEditing) {
                mEditText.setText(noteEntity.getText());
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            setTitle("New Note");
            aNewNote = true;
        } else {
            setTitle("Edit Note");
            int noteId = bundle.getInt(Constants.NOTE_ID_KEY);
            mViewModel.loadNote(noteId);
            aNewNote = false;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveAndExit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!aNewNote) {
            getMenuInflater().inflate(R.menu.menu_editor, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveAndExit();
            return true;
        } else if (item.getItemId() == R.id.action_delete_note) {
            deleteNote();
            finish();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteNote() {
        mViewModel.deleteNote();
    }

    private void saveAndExit() {
        mViewModel.saveAndExit(mEditText.getText().toString());
    }
}