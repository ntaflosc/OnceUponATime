package model;

public class Effect {
    private String item;
    private double rep;
    private int checkpoint;

    public Effect() {}

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getRep() {
        return rep;
    }

    public void setRep(double rep) {
        this.rep = rep;
    }

    public int getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(int checkpoint) {
        this.checkpoint = checkpoint;
    }
}
