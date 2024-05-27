package model;

import java.util.List;

public class Dialogue {
    private String character;
    private List<String> texts;

    // Getters and Setters
    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public List<String> getTexts() {
        return texts;
    }

    public void setTexts(List<String> texts) {
        this.texts = texts;
    }
}
