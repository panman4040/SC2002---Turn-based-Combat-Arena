package arena.engine;

public enum LevelType {
    EASY("Easy", "Should be easy peasy"),
    MEDIUM("Medium", "Cranking up the heat"),
    HARD("Hard", "Survival is not guaranteed");

    private final String name;
    private final String description;

    LevelType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
}
