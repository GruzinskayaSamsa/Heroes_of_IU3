package BattleObjects;

import UserInterfaces.ViewObject;

public class Snake extends Unit{

    public Snake() {
        this.hp = 6;
        this.damage = 8;
        this.damageRadius = 2;
        this.name = "Snake";
        this.image = ViewObject.GREEN +  "\uD83D\uDC0D" + ViewObject.RESET;
    }

}
