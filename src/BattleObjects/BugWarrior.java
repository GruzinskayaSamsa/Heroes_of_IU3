package BattleObjects;

import UserInterfaces.ViewObject;

public class BugWarrior extends Unit{

    public BugWarrior() {
        this.hp = 3;
        this.damage = 5;
        this.damageRadius = 2;
        this.name = "BugWarrior";
        this.image = ViewObject.RED +  "\uD83E\uDD82" + ViewObject.RESET;
    }

}
