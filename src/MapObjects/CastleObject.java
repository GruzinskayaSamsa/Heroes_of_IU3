package MapObjects;

import BattleObjects.ArmyObject;
import BattleObjects.Battlable;
import CastleObjects.*;
import MapObjects.MapObject;
import UserInterfaces.User;
import UserInterfaces.ViewObject;
import com.company.QuasiConsole;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Random;

public class CastleObject extends PassiveMapObject implements Battlable {

    private User user;
    private ArmyObject army;

    //private int id;

    private ArrayList<CastleMapObject> buildings;
    private static final int size = 10;

    private static final int width = 4;
    private static final int height = 4;

    public CastleObject(String type, String backgroundColor, User user) {
        super(type, backgroundColor, user.getColor());
        this.user = user;
        this.army = new ArmyObject();

        this.user.addCastle(this);

        this.buildings = new ArrayList<CastleMapObject>(16);

        Random rand = new Random();
        for (int i = 0; i < this.height * this.width; i++) {
            this.buildings.add(new CastleLandscape(String.valueOf(rand.nextInt(2))));
        }
        this.buildings.set(this.width * 2 + 3, new Tavern(this.user));

        this.buildings.set(0, new Barn(this.user, this));
        this.buildings.set(1, new Hollow(this.user, this));
        this.buildings.set(2, new SnakeLake(this.user, this));
        this.buildings.set(3, new MountainNest(this.user, this));
        this.buildings.set(4, new RottingHills(this.user, this));

        this.buildings.set(5, new Stable(this.user, this));

    }

    @Override
    public void setType(String type) {
        super.setType(type);

        switch (type) {
            case ("0"):
                this.setImage("\uD83C\uDFF0");
                break;
            case ("1"):
                this.setImage("\uD83C\uDFF0");
                break;
            default:
                this.setImage("\uD83C\uDFF0");
                break;
        }
    }


    public JSONArray getArmyJSON() {
        return this.army.getUnitsJSON();
    }

    @Override
    public ArmyObject getArmy() {
        return this.army;
    }

    @Override
    public void setArmy(ArmyObject army) {
        this.army = army;
    }

    @Override
    public User getUser() {
        return this.user;
    }

    public CastleMapObject getBuilding(int position) {
        return this.buildings.get(position);
    }


    public void print() {
        QuasiConsole.clearConsole();
        try {
            for (int k = 0; k < this.width+2; k++) {
                System.out.print(ViewObject.BLACK + "\uD83C\uDFE2" + ViewObject.RESET);
            }
            System.out.println();

            for (int i = 0; i < this.height; i++) {
                System.out.print(ViewObject.BLACK + "\uD83C\uDFE2" + ViewObject.RESET);
                for (int j = 0; j < this.width; j++) {
                    System.out.print(buildings.get(i * this.width + j).getImage());
                }
                System.out.println(ViewObject.BLACK + "\uD83C\uDFE2" + ViewObject.RESET);
            }

            for (int k = 0; k < this.width+2; k++) {
                System.out.print(ViewObject.BLACK + "\uD83C\uDFE2" + ViewObject.RESET);
            }
            System.out.println();
        } catch (java.lang.IndexOutOfBoundsException e){
            System.out.println("\nOther pies of your field was eaten by Abyss\n");
        }
        System.out.println("Your money: " + this.user.getMoney());
    }
}
