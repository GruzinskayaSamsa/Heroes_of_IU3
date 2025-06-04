package BattleObjects;

import UserInterfaces.ViewObject;

public class Goblin extends Unit {

    public Goblin() {
        this.hp = 5;
        this.damage = 5;
        this.damageRadius = 5;
        this.name = "Goblin";
        this.image = ViewObject.GREEN +  "\uD83D\uDC7A" + ViewObject.RESET;
    }

}
