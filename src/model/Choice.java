package model;

import java.util.List;

public class Choice {
    private String id;
    private String action;
    private List<Dialogue> dialogues;
    private List<Effect> effects;
    private List<Choice> subChoices;
    private int checkpoint;

    // Getters
    public String getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public List<Dialogue> getDialogues() {
        return dialogues;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public List<Choice> getSubChoices() {
        return subChoices;
    }

    public int getCheckpoint() {
        return checkpoint;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setDialogues(List<Dialogue> dialogues) {
        this.dialogues = dialogues;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public void setSubChoices(List<Choice> subChoices) {
        this.subChoices = subChoices;
    }

    public void setCheckpoint(int checkpoint) {
        this.checkpoint = checkpoint;
    }
}
