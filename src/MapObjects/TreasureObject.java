package MapObjects;

import MapObjects.MapObject;

import java.util.Objects;
import java.util.Random;

public class TreasureObject extends PassiveMapObject{

    private int money;

    public TreasureObject(String type, String backgroundColor, String color) {
        super(type, backgroundColor, color);
        this.price = 10;

        Random rand = new Random();
        this.money = rand.nextInt(999) + 1;
    }

    @Override
    public void setType(String type) {
        super.setType(type);
        switch (type) {
            case ("1"):
                this.setImage("\uD83D\uDCB0");
                break;
            default:
                this.setImage("\uD83C\uDF81");
                break;
        }
    }


    @Override
    public boolean setHero(HeroObject hero) {
        if (heroSetable()) {
            hero.getUser().addMoney(this.money);
            this.hero = hero;
            return true;
        }
        return false;
    }
}
