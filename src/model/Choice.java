package model;

import java.util.List;

public class Choice {
    private String id;
    private String action;
    private List<Dialogue> dialogues;
    private List<Choice> subChoices;
    private List<Effect> effects;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Dialogue> getDialogues() {
        return dialogues;
    }

    public void setDialogues(List<Dialogue> dialogues) {
        this.dialogues = dialogues;
    }

    public List<Choice> getSubChoices() {
        return subChoices;
    }

    public void setSubChoices(List<Choice> subChoices) {
        this.subChoices = subChoices;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }
}
