package ch.bbw.pr.weather.model;

public class Measure {
    private String kind;
    private double value;

    public Measure(String kind, double value) {
        this.kind = kind;
        this.value = value;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
