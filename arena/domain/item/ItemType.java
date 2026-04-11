package arena.domain.item;

public enum ItemType {
    POTION("Potion", "Heal 100 HP"),
    POWER_STONE("Power Stone", "Free extra use of your special skill"),
    SMOKE_BOMB("Smoke Bomb", "Immune to damage for 2 turns");

    private final String name;
    private final String description;

    ItemType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
}