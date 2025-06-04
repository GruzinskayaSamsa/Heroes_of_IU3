package MapObjects;


import java.util.Objects;

public class PassiveMapObject extends MapObject {

    protected HeroObject hero;
    protected int price;

    public PassiveMapObject(String type) {
        super(type);
        this.hero = null;
    }

    public PassiveMapObject(String type, String backgroundColor, String color) {
        super(type, backgroundColor, color);
        this.hero = null;
    }

    public HeroObject getHero() {
        return hero;
    }

    public boolean setHero(HeroObject hero) {
        if (heroSetable()) {
            this.hero = hero;
            return true;
        }
        return false;
    }

    public boolean heroSetable() {
        if (Objects.isNull(this.hero)) {
            return true;
        }
        return false;
    }

    public void resetHero() {
        this.hero = null;
    }

    @Override
    public String getImage() {
        if (Objects.nonNull(this.hero)) {
            return this.hero.getImage();
        }
        return super.getImage();
    }

    public int getPrice() {
        return price;
    }
}
