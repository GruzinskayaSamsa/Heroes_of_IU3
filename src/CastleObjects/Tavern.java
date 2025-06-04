package CastleObjects;

import BattleObjects.ArmyObject;
import BattleObjects.Peasant;
import MapObjects.HeroObject;
import UserInterfaces.User;
import UserInterfaces.ViewObject;

public class Tavern extends CastleMapObject {

    private User user;


    public Tavern(User user) {
        super("");
        this.setImage("\uD83C\uDF7B");
        this.setColor(ViewObject.YELLOW);

        this.user = user;
    }


    @Override
    public void doThings() {
        super.doThings();
        if (this.user.getMoney() >= 1000 && this.user.getNumberOfHeroes() < 5) {
            int max_id = 0;
            for (int i = 0; i < this.user.getNumberOfHeroes(); i++)
                max_id = Math.max(max_id, this.user.getHeroByNumber(i).getId());
            user.addHero(new HeroObject("0", max_id + 1, new ArmyObject(Peasant.class, 1, 1), this.user));
            user.addMoney(-1000);
        }
    }
}
