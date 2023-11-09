package com.ui;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {
    @ColumnInfo(name = "message")
    private String message;
    @ColumnInfo(name = "timeSent")
    private String timeSent;
    @ColumnInfo(name = "isSentButton")
    private boolean isSentButton;

    // Default, no-argument constructor
    public ChatMessage() {
    }

    // Parameterized constructor
    public ChatMessage(String m, String t, boolean sent) {
        message = m;
        timeSent = t;
        isSentButton = sent;
    }

    // Setter for the message field
    public void setMessage(String message) {
        this.message = message;
    }

    // Setter for the timeSent field
    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    // Setter for the isSentButton field
    public void setSentButton(boolean sentButton) {
        isSentButton = sentButton;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public boolean isSentButton() {
        return isSentButton;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
}
