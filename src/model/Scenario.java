package model;

import java.util.List;

public class Scenario {
    private String location;
    private int checkpoint;
    private String story;
    private List<Dialogue> dialogues;
    private List<Choice> choices;

    // Getters
    public String getLocation() {
        return location;
    }

    public int getCheckpoint() {
        return checkpoint;
    }

    public String getStory() {
        return story;
    }

    public List<Dialogue> getDialogues() {
        return dialogues;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    // Setters
    public void setLocation(String location) {
        this.location = location;
    }

    public void setCheckpoint(int checkpoint) {
        this.checkpoint = checkpoint;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public void setDialogues(List<Dialogue> dialogues) {
        this.dialogues = dialogues;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
