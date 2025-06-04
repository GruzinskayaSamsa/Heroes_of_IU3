package UserInterfaces;

public class MapView extends ViewObject{

    public void setBackground(String background) {
        this.setImage(background + this.getImage() + "\u001B[0m");
    }

}
