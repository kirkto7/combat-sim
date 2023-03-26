package Entities;

import Doables.Attack;

public class PlayerCharacter extends Entity{

    public PlayerCharacter(int[] stats, int baseHP, int speed, int totalLevels, int[] ABILITY_SCORES, int totalHP, int speed1, Attack.DmgType[] resistances, Attack.DmgType[] immunities) {
        super(stats, baseHP, speed, totalLevels, resistances, immunities);
    }

}
