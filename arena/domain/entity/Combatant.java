package arena.domain.entity;

import arena.domain.action.Action;
import arena.domain.effect.StatusEffect;
import arena.engine.BattleContext;
import arena.ui.GameUI;
import java.util.ArrayList;
import java.util.List;

public abstract class Combatant {
    private final String name;
    private int hp;
    private final int maxHp;
    private final int baseAttack;
    private final int baseDefense;
    private final int speed;
    private final List<StatusEffect> statusEffects;

    protected Combatant(String name, int maxHp, int baseAttack, int baseDefense, int speed) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.speed = speed;
        this.statusEffects = new ArrayList<>();
    }

    public void takeDamage(int damage) {
        int modified = damage;
        for (StatusEffect effect : statusEffects) {
            modified = effect.modifyIncomingDamage(modified);
        }
        hp = Math.max(0, hp - modified);
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void applyEffects() {
        for (StatusEffect effect : statusEffects) {
            effect.apply(this);
        }
    }

    public boolean canAct() {
        for (StatusEffect effect : statusEffects) {
            if (effect.preventAction()) return false;
        }
        return true;
    }

    public void addStatusEffect(StatusEffect effect) {
        statusEffects.add(effect);
    }

    public int getEffectiveAttack() {
        int attack = baseAttack;
        for (StatusEffect effect : statusEffects) {
            attack = effect.modifyAttack(attack);
        }
        return attack;
    }

    public int getEffectiveDefense() {
        int defense = baseDefense;
        for (StatusEffect effect : statusEffects) {
            defense = effect.modifyDefense(defense);
        }
        return defense;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getSpeed() {
        return speed;
    }

    public List<StatusEffect> getStatusEffects() {
        return statusEffects;
    }

    public void reduceSpecialCooldown() { /* no-op by default */};

    public abstract void triggerSpecial(BattleContext context);

    public abstract Action chooseAction(BattleContext context, GameUI ui);
}
