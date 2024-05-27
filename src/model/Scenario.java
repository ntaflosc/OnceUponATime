package model;

import java.util.List;

public class Scenario {
    private String story;
    private List<Choice> choices;

    // Getters and Setters
    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
