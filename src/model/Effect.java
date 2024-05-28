package model;

import java.util.List;

public class Effect {
    private List<String> items;
    private Double reputation;
    private int checkpoint;

    // Getters
    public List<String> getItems() {
        return items;
    }

    public Double getReputation() {
        return reputation;
    }

    public int getCheckpoint() {
        return checkpoint;
    }

    // Setters
    public void setItems(List<String> items) {
        this.items = items;
    }

    public void setReputation(Double reputation) {
        this.reputation = reputation;
    }

    public void setCheckpoint(int checkpoint) {
        this.checkpoint = checkpoint;
    }
}
