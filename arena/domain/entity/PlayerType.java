package arena.domain.entity;

public enum PlayerType {
    // Defining stats
    WARRIOR("Warrior", "Guy that smacks and smashes!", 260, 40, 20, 30, "Shield Bash"),
    WIZARD("Wizard", "Gal that shoots people!", 200, 50, 10, 20, "Arcane Blast");

    // Attributes for display
    private final String name;
    private final String description;
    private final int maxHp;
    private final int baseAttack;
    private final int baseDefense;
    private final int speed;
    private final String specialSkill;

    PlayerType(String name, String description, int maxHp, int baseAttack, int baseDefense, 
        int speed, String specialSkill) {
        this.name = name;
        this.description = description;
        this.maxHp = maxHp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.speed = speed;
        this.specialSkill = specialSkill;
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getMaxHp() { return maxHp; }
    public int getBaseAttack() { return baseAttack; }
    public int getBaseDefense() { return baseDefense; }
    public int getSpeed() { return speed; }
    public String getSpecialSkill() { return specialSkill; }
}
