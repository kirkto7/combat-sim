package Entities;

import Doables.Attack.DmgType;

import java.util.HashMap;

public abstract class Entity {

    public enum AbilityScore { STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA }
    private final AbilityScore[] SCORE_SEQUENCE = {
            AbilityScore.STRENGTH, AbilityScore.DEXTERITY, AbilityScore.CONSTITUTION,
            AbilityScore.INTELLIGENCE, AbilityScore.WISDOM, AbilityScore.CHARISMA };
    private final HashMap<AbilityScore, Integer> ABILITY_SCORES = new HashMap<>();
    private final int SPEED;
    private int totalHP;
    private final int MAX_HP;
    private final DmgType[] RESISTANCES;
    private final DmgType[] IMMUNITIES;

    public Entity(int[] stats, int baseHP, int speed, int totalLevels, DmgType[] resistances, DmgType[] immunities) {
        for(int i = 0; i < SCORE_SEQUENCE.length; i++) {
            ABILITY_SCORES.put(SCORE_SEQUENCE[i], stats[i]);
        }
        RESISTANCES = resistances;
        IMMUNITIES = immunities;
        this.MAX_HP = baseHP + (ABILITY_SCORES.get(AbilityScore.CONSTITUTION) * totalLevels);
        totalHP = MAX_HP;
        this.SPEED = speed;
    }

    public void takeDamage(int damage, DmgType dmgType) {
       for(DmgType d : RESISTANCES) {
           if(dmgType == d) {
               damage /= 2;
               break;
           }
       }
       for(DmgType d : IMMUNITIES) {
           if(dmgType == d) {
               damage = 0;
               break;
           }
       }
       totalHP -= damage;
    }
}
