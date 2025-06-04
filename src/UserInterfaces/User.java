package UserInterfaces;

import MapObjects.CastleObject;
import MapObjects.HeroObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class User {


    private ArrayList<HeroObject> heroes;
    private ArrayList<CastleObject> castles;
    private String color;

    private String name;
    private int number;

    private int money;


    public User(String name, int number, String color) {
        this.name = name;
        this.number = number;
        this.color = color;

        this.heroes = new ArrayList<HeroObject>();
        this.castles = new ArrayList<CastleObject>();
        this.money = 0;
    }

    public void addMoney(int number) {
        this.money += number;
    }

    public int getMoney() {
        return this.money;
    }

    public void addHero(HeroObject hero) {
        this.heroes.add(hero);
    }

    public boolean removeHeroByID(int id) {
        for (int i = 0; i < this.heroes.size(); i++){
            if (this.heroes.get(i).getId() == id){
                this.heroes.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean removeHeroByNumber(int number) {
        return Objects.nonNull(this.heroes.remove(number));
    }

    public HeroObject getHeroByID(int id) {
        for (int i = 0; i < this.heroes.size(); i++){
            if (this.heroes.get(i).getId() == id)
                return this.heroes.get(i);
        }
        return null;
    }

    public HeroObject getHeroByNumber(int n) {
        return this.heroes.get(n);
    }

    public void addCastle(CastleObject caste) {
        this.castles.add(caste);
    }

    public void removeCastle() {
        this.castles.remove(0);
    }

    public void removeCastle(int index) {
        this.castles.remove(index);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumberOfHeroes() {
        return this.heroes.size();
    }

    public int getNumber() {
        return number;
    }

    public CastleObject getCastle() {
        return this.castles.get(0);
    }

    public JSONObject getUserJSON() {
        JSONObject user = new JSONObject()
                .put("castle", new JSONObject())
                .put("armies", new JSONArray());

        if (this.castles.size() > 0)
            user.getJSONObject("castle").put("units", this.castles.get(0).getArmyJSON());

        for (int i = 0; i < this.heroes.size(); i++) {
            user.getJSONArray("armies").put(this.heroes.get(i).getJSON());
        }

        return user;
    }
}
