package model;

import java.util.ArrayList;
import java.util.List;

public class Choice {
    private String id;
    private String action;
    private List<Dialogue> dialogues;
    private List<Choice> subchoices;
    private List<Effect> effects;
    private int checkpoint;

    public Choice(String id) {
        this.id = id;
        this.dialogues = new ArrayList<>();
        this.subchoices = new ArrayList<>();
        this.effects = new ArrayList<>();
    }

    public String getId() {
        return id;
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

    public List<Choice> getSubchoices() {  // Correct method name
        return subchoices;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public int getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(int checkpoint) {
        this.checkpoint = checkpoint;
    }
}
