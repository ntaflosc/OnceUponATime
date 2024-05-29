package model;

import java.util.ArrayList;
import java.util.List;

public class Choice {
    private String id;
    private List<Choice> subChoices;
    private List<Dialogue> dialogues;
    private int checkpoint;
    private String action; // Added action property
    private List<Effect> effects; // Added effects property

    public Choice(String id) {
        this.id = id;
        subChoices = new ArrayList<>();
        dialogues = new ArrayList<>();
        this.effects = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Choice> getSubChoices() {
        return subChoices;
    }

    public void setSubChoices(List<Choice> subChoices) {
        this.subChoices = subChoices;
    }

    public List<Dialogue> getDialogues() {
        return dialogues;
    }

    public void setDialogues(List<Dialogue> dialogues) {
        this.dialogues = dialogues;
    }

    public int getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(int checkpoint) {
        this.checkpoint = checkpoint;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }
}

