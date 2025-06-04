package BattleObjects;

import UserInterfaces.ViewObject;

public class Wyvern extends Unit{

    public Wyvern() {
        this.hp = 10;
        this.damage = 10;
        this.damageRadius = 4;
        this.name = "Wyvern";
        this.image = ViewObject.CYAN +  "\uD83D\uDC09" + ViewObject.RESET;
    }

}
