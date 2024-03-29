package com.example.flashcat.Model;

import java.time.LocalDateTime;

public class Flashcard {
    private int ID_Flashcard;
    private String term;
    private String definition;
    private String example;
    private String sound;
    private boolean status;
    private LocalDateTime update_day;
    private int ID_Deck;

    public Flashcard() {
    }

    public Flashcard(int ID_Flashcard, String term, String definition, String example, String sound, boolean status, LocalDateTime update_day, int ID_Deck) {
        this.ID_Flashcard = ID_Flashcard;
        this.term = term;
        this.definition = definition;
        this.example = example;
        this.sound = sound;
        this.status = status;
        this.update_day = update_day;
        this.ID_Deck = ID_Deck;
    }

    public int getID_Flashcard() {
        return ID_Flashcard;
    }

    public void setID_Flashcard(int ID_Flashcard) {
        this.ID_Flashcard = ID_Flashcard;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getUpdate_day() {
        return update_day;
    }

    public void setUpdate_day(LocalDateTime update_day) {
        this.update_day = update_day;
    }

    public int getID_Deck() {
        return ID_Deck;
    }

    public void setID_Deck(int ID_Deck) {
        this.ID_Deck = ID_Deck;
    }
}
