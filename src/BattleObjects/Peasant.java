package BattleObjects;

import UserInterfaces.ViewObject;

public class Peasant extends Unit{

    public Peasant() {
        this.hp = 1;
        this.damage = 1;
        this.damageRadius = 3;
        this.name = "Peasant";
        this.image = ViewObject.GREEN +  "\uD83D\uDC77" + ViewObject.RESET;
    }

}
