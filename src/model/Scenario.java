package model;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
    private List<Choice> choices;
    private List<Dialogue> dialogues;
    private int checkpoint;

    public Scenario() {
        this.choices = new ArrayList<>();
        this.dialogues = new ArrayList<>();
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
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

}

