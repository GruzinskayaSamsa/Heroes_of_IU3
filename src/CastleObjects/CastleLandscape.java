package CastleObjects;

import UserInterfaces.ViewObject;

public class CastleLandscape extends CastleMapObject{
    public CastleLandscape(String type) {
        super(type);
    }

    @Override
    public void setType(String type) {
        super.setType(type);

        switch (type) {
            case ("0"):
                this.setImage("❎");
                break;
            case ("1"):
                this.setImage(ViewObject.GREEN + "\uD83C\uDF33" + ViewObject.RESET);
                break;
            case ("2"):
                this.setImage(ViewObject.GREEN + "\uD83C\uDF34" + ViewObject.RESET);
                break;
            default:
                this.setImage(" ");
                break;
        }
    }
}
