package domain;

public enum Decision {
    HIT("Hit"), STAND("Stand"), SPLIT("Split"), DOUBLE("Double");

    private String name;

    Decision(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
