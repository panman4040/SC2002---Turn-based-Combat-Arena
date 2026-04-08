package arena.domain.entity;

public class Warrior extends Player {
    public Warrior() {
        // ShieldBash requires a target, so it's created at action time, not construction time.
        // Pass null here; the actual ShieldBash is created in chooseAction() / UI.
        super("Warrior", 260, 40, 20, 30, null);
    }
}

