package com.codingcrazz.notex;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.codingcrazz.notex.utils.Constants;
import com.codingcrazz.notex.viewmodels.EditorViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EditorActivity extends AppCompatActivity {

    private EditorViewModel mViewModel;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edit_note_text)
    TextView mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        initViewMode();
    }

    private void initViewMode() {

        mViewModel = ViewModelProviders.of(this)
                .get(EditorViewModel.class);

        mViewModel.mLiveNote.observe(this, (noteEntity) -> {
            if (noteEntity != null) {
                mEditText.setText(noteEntity.getText());
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            setTitle("New Note");
        } else {
            setTitle("Edit Note");
            int noteId = bundle.getInt(Constants.NOTE_ID_KEY);
            mViewModel.loadNote(noteId);
        }
    }
}