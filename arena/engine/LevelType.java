package arena.engine;

public enum LevelType {
    EASY("Easy", "Initial: 3 Goblins"),
    MEDIUM("Medium", "Initial: 1 Goblin, 1 Wolf; Backup: 2 Wolves"),
    HARD("Hard", "Initial: 2 Goblins; Backup: 1 Goblin, 2 Wolves");

    private final String name;
    private final String description;

    LevelType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
}
