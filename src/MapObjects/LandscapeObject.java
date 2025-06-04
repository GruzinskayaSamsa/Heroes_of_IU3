package MapObjects;

import MapObjects.MapObject;
import UserInterfaces.ViewObject;

import java.util.Objects;

public class LandscapeObject extends PassiveMapObject{

    public LandscapeObject(String type) {
        super(type);
    }

    @Override
    public void setType(String type) {
        super.setType(type);

        switch (type) {
            case ("0"):
                this.price = 10;
                this.setImage("❎");
                break;
            case ("1"):
                this.setImage(ViewObject.GREEN + "\uD83C\uDF33" + ViewObject.RESET);
                break;
            case ("2"):
                this.setImage(ViewObject.CYAN + "\uD83C\uDF00" + ViewObject.RESET);
                break;
            case ("3"):
                this.setImage(ViewObject.GREEN + "\uD83C\uDF34" + ViewObject.RESET);
                break;
            case ("8"):
                this.setImage(ViewObject.RED + "\uD83D\uDCA5" + ViewObject.RESET);
                this.price = 20;
                break;
            case ("9"):
                this.setImage(ViewObject.BLACK + "\uD83D\uDCC8" + ViewObject.RESET);
                this.price = 5;
                break;
            default:
                this.setImage(" ");
                break;
        }
    }


    @Override
    public boolean setHero(HeroObject hero) {
        return super.setHero(hero);
    }


    @Override
    public boolean heroSetable() {
        if (Objects.equals(this.type, "1") || Objects.equals(this.type, "2") || Objects.equals(this.type, "3"))
            return false;
        return super.heroSetable();
    }
}
