package com.codingcrazz.notex.utils;

import com.codingcrazz.notex.database.NoteEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleDataProvider {
    private static final String SAMPLE_TEXT_1 = "A Simple Note";
    private static final String SAMPLE_TEXT_2 = "A Text With a\nLine";
    private static final String SAMPLE_TEXT_3 = "India is often referred to as a subcontinent of Southern Asia owing to its distinct and vast landmass. Surrounded by sea on three sides, the majestic Himalayas separates it from the rest of mainland Asia. India is famous for its ancient history, varied landscapes and diverse culture.";

    private static Date getDate(int diffAmount) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MILLISECOND, diffAmount);
        return calendar.getTime();
    }

    public static List<NoteEntity> getSampleData() {
        List<NoteEntity> notesList = new ArrayList<>();
        notesList.add(new NoteEntity(getDate(0), SAMPLE_TEXT_1));
        notesList.add(new NoteEntity(getDate(-1), SAMPLE_TEXT_2));
        notesList.add(new NoteEntity(getDate(-2), SAMPLE_TEXT_3));

        return notesList;
    }
}
