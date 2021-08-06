package com.example.dailynotes.Model.Notes;

import com.google.gson.annotations.SerializedName;

public class Notes_response {

    @SerializedName("noteID")
    private Integer noteID;

    @SerializedName("title")
    private String title;

    @SerializedName("time")
    private String time;

    @SerializedName("date")
    private String date;

    @SerializedName("note")
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getNoteID() {
        return noteID;
    }

    public void setNoteID(Integer noteID) {
        this.noteID = noteID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
