package BattleObjects;

import UserInterfaces.ViewObject;

public class Rebel extends Unit{

    public Rebel() {
        this.hp = 14;
        this.damage = 16;
        this.damageRadius = 7;
        this.name = "Rebel";
        this.image = ViewObject.BLACK +  "\uD83D\uDC80" + ViewObject.RESET;
    }

}
