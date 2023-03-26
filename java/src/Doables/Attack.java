package Doables;
import Entities.Entity.*;
import Entities.*;

public abstract class Attack {

    public enum DmgType {
        BLUDGEONING, PIERCING, SLASHING,
        ACID, COLD, FIRE, FORCE, LIGHTNING, NECROTIC, POISON, PSYCHIC, RADIANT, THUNDER
    }

    public enum ActionType {
        ACTION, BONUS_ACTION, REACTION
    }

    private final int range;
    private final int dmgDice;
    private final int numDice;
    private final int dmgOverride;
    private final DmgType dmgType;
    private final ActionType actionType;
    private final boolean isHealing;
    private final AbilityScore modifier;

    public Attack(int range, int dmgDice, int numDice, int dmgOverride, DmgType dmgType, ActionType actionType, boolean isHealing, AbilityScore modifier) {
        this.range = range;
        this.dmgDice = dmgDice;
        this.numDice = numDice;
        this.dmgOverride = dmgOverride;
        this.dmgType = dmgType;
        this.actionType = actionType;
        this.isHealing = isHealing;
        this.modifier = modifier;
    }

    public abstract void attack(Entity target);

    public void dealDamage(Entity target) {
        int totalDamage = 0;
        if(dmgOverride > 0) {
            totalDamage = dmgOverride;
        } else {
            for (int i = 0; i < numDice; i++) {
                totalDamage += (int) (Math.random() * (dmgDice - 1)) + 1;
            }
        }
        totalDamage = isHealing ? totalDamage * -1 : totalDamage;
        target.takeDamage(totalDamage, dmgType);
    }

    public ActionType getActionType() {
        return actionType;
    }
}