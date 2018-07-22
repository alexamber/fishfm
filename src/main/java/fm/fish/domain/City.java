package fm.fish.domain;

public enum City {
    KYIV;

    public String get() {
        return name().toLowerCase();
    }
}
