package model;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
    private String story;
    private List<Dialogue> dialogues;
    private List<Choice> choices;

    public Scenario() {
        this.dialogues = new ArrayList<>();
        this.choices = new ArrayList<>();
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public List<Dialogue> getDialogues() {
        return dialogues;
    }

    public List<Choice> getChoices() {
        return choices;
    }
}
