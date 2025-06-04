package MapObjects;

import UserInterfaces.MapView;
import UserInterfaces.ViewObject;
import org.junit.jupiter.api.Test;

public abstract class MapObject {

    protected ViewObject view;
    protected String type;


    public MapObject(String type) {
        this.view = new MapView();
        setType(type);
        this.setBackgroundColor("");
        this.setColor("");
    }

    public MapObject(String type, String backgroundColor, String color) {
        this.view = new MapView();
        setType(type);
        this.setBackgroundColor(backgroundColor);
        this.setColor(color);
    }

    public String getBackgroundColor() {
        return view.getBackgroundColor();
    }

    public void setBackgroundColor(String backgroundColor) {
        this.view.setBackgroundColor(backgroundColor);
    }

    public String getColor() {
        return this.view.getColor();
    }

    public void setColor(String color) {
        this.view.setColor(color);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return this.view.getImage();
    }

    public void setImage(String image) {
        this.view.setImage(image);
    }

    public ViewObject getView() {
        return view;
    }

    public void setView(ViewObject view) {
        this.view = view;
    }

}
