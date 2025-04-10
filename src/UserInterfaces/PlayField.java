package UserInterfaces;


import BattleObjects.ArmyObject;
import MapObjects.*;
import BattleObjects.Goblin;
import com.company.QuasiConsole;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class PlayField {

    private Integer height, width;
    private ArrayList<PassiveMapObject> data;


    public PlayField(JSONObject object, User user, User enemyUser) {
        this.height = (Integer) ((JSONObject) object.get("field")).optInt("height");
        this.width = (Integer) ((JSONObject) object.get("field")).optInt("width");
        this.data = new ArrayList<PassiveMapObject>();

        String landscape = (String) ((JSONObject) object.get("field")).optString("data");
        for (int i = 0; i < landscape.length(); i++) {
            data.add(new LandscapeObject(Character.toString(landscape.charAt(i))));
        }


        for (int i = 0; i < object.getJSONArray("treasures").length(); i++) {
            data.set(object.getJSONArray("treasures").getJSONObject(i).getInt("position"),
                    new TreasureObject(object.getJSONArray("treasures").getJSONObject(i).getString("type"),
                            "",
                            ViewObject.YELLOW));
        }

        for (int i = 0; i < object.getJSONArray("enemies").length(); i++) {
            data.set(object.getJSONArray("enemies").getJSONObject(i).getInt("position"),
                    new EnemyObject("",
                            Goblin.class,
                            object.getJSONArray("enemies").getJSONObject(i).getInt("number")));
        }

        this.generateCastles(user, enemyUser);


    }

    public PlayField() {}


    public void print() {
        QuasiConsole.clearConsole();
        try {
            for (int k = 0; k < this.width+2; k++) {
                System.out.print("\u001B[31m" + "\uD83D\uDD25" + "\u001B[0m");
            }
            System.out.println();

            for (int i = 0; i < this.height; i++) {
                System.out.print("\u001B[31m" + "\uD83D\uDD25" + "\u001B[0m");
                for (int j = 0; j < this.width; j++) {
                    System.out.print(data.get(i * this.width + j).getImage());
                }
                System.out.println("\u001B[31m" + "\uD83D\uDD25" + "\u001B[0m");
            }

            for (int k = 0; k < this.width+2; k++) {
                System.out.print("\u001B[31m" + "\uD83D\uDD25" + "\u001B[0m");
            }
            System.out.println();
        } catch (java.lang.IndexOutOfBoundsException e){
            System.out.println("\nOther pies of your field was eaten by Abyss\n");
        }
    }


    public void init(int width, int height, boolean landscape, boolean enemies, boolean treasures, User user, User enemyUser) {
        this.height = height;
        this.width = width;
        this.data = new ArrayList<PassiveMapObject>();
        for (int i=0;i<this.height*this.width; i++){
            data.add(new LandscapeObject("0"));   // change
        }

        if (landscape) {
            this.generateLandscape();
        }
        if (enemies) {
            this.generateEnemies();
        }
        if (treasures) {
            this.generateTreasure();
        }

        this.generateRoutes();
        this.generateCastles(user, enemyUser);
    }


    private void generateRoutes() {
        for (int i=0; i<(this.height/2); i++) {
            this.data.set(i+i*this.width, new LandscapeObject("9"));
            this.data.set(this.height*this.width-i-i*this.width-1, new LandscapeObject("9"));
        }

        if (this.width > this.height) {
            if (this.height % 2 == 0) {
                for (int i = (this.height / 2); i < this.width / 2; i++) {
                    this.data.set(i + (this.height / 2) * this.width, new LandscapeObject("9"));
                    this.data.set(this.width - i + (this.height / 2) * this.width - 1, new LandscapeObject("9"));
                }
            } else {
                for (int i = (this.height / 2); i < this.width / 2+1; i++) {
                    this.data.set(i + (this.height / 2) * this.width, new LandscapeObject("9"));
                    this.data.set(this.width - i + (this.height / 2) * this.width - 1, new LandscapeObject("9"));
                }
            }
        } else {
            for (int i = this.width; i<this.height; i++) {
                this.data.set(this.width-1 + i * this.width, new LandscapeObject("9"));
                this.data.set((this.height - i) * this.width, new LandscapeObject("9"));
            }
        }
    }


    private void generateLandscape() {
        int n = (int) (this.height*this.width*0.3);

        Random rand = new Random();
        int place, i = 0;
        ArrayList<Integer> mask = new ArrayList<Integer>();
        while (i<n) {
            place = rand.nextInt(this.data.size());
            if (this.data.get(place).getType() != "0") continue;

            this.data.set(place, new LandscapeObject(Integer.toString(rand.nextInt(3)+1)));
            mask.add(place);
            i++;
        }
    }


    private void generateTreasure() {
        int n = (int) (this.height*this.width*0.05);

        Random rand = new Random();
        int place, i = 0;
        ArrayList<Integer> mask = new ArrayList<Integer>();
        while (i<n) {
            place = rand.nextInt(this.data.size());
            if (this.data.get(place).getType() != "0") continue;

            this.data.set(place, new TreasureObject(Integer.toString(rand.nextInt(2)+1), "", ViewObject.YELLOW));
            mask.add(place);
            i++;
        }
    }


    private void generateEnemies() {
        int n = (int) (this.height*this.width*0.05);

        Random rand = new Random();
        int place, i = 0;
        ArrayList<Integer> mask = new ArrayList<Integer>();
        while (i<n) {
            place = rand.nextInt(this.data.size());
            if (this.data.get(place).getType() != "0") continue;

            this.data.set(place, new EnemyObject("0", Goblin.class, rand.nextInt(1000)+1 ));
            mask.add(place);
            i++;
        }
    }


    private void generateCastles(User user, User enemyUser) {
        if (user.getNumber() == 0) {
            this.data.set(0, new CastleObject("0", "", user));
            this.data.set(this.data.size() - 1, new CastleObject("1", "", enemyUser));
        } else {
            this.data.set(this.data.size() - 1, new CastleObject("0", "", user));
            this.data.set(0, new CastleObject("1", "", enemyUser));
        }
    }

    public PassiveMapObject setHero(HeroObject hero, int x, int y) {
        int oldPosition = this.getHeroPosition(hero.getId());
        if (oldPosition >= 0) this.deleteHero(hero.getId());
        try {
            if (((PassiveMapObject) this.data.get(x + y * this.width)).heroSetable()) {
                if (((PassiveMapObject) this.data.get(x + y * this.width)).setHero(hero))
                    return ((PassiveMapObject) this.data.get(x + y * this.width));
                return null;
            } else if (oldPosition >= 0) {
                setHero(hero, oldPosition%this.width, oldPosition/this.width);
            }
        } catch (NullPointerException e) {
            if (((PassiveMapObject) this.data.get(x + y * this.width)).setHero(hero))
                return ((PassiveMapObject) this.data.get(x + y * this.width));
            return null;
        }
        catch (Exception e) {
            System.out.println(e.getClass().toString());
        }
        return null;
    }

    public int getHeroPosition(int id) {
        for (int i = 0; i < this.data.size(); i++) {
            try {
                if (this.data.get(i).getHero().getId() == id)
                    return i;
            } catch (Exception e) {

            }
        }
        return -1;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public void deleteHero(int id) {
        int position = this.getHeroPosition(id);
        if (position >= 0) {
            if (Objects.equals(this.data.get(position).getClass(), TreasureObject.class)) {
                this.data.set(position, new LandscapeObject("0"));
            }
            this.data.get(position).resetHero();
        }
    }

    public PassiveMapObject getTerritory(int x, int y) {
        return data.get(x + y * this.width);
    }

    public PassiveMapObject getTerritoryByPosition(int position) {
        return data.get(position);
    }

    public void setTerritory(int x, int y, PassiveMapObject territory) {
        this.data.set(x + y * this.width, territory);
    }

    public String getLandscapeTypes() {
        String types = new String();

        for (int i = 0; i < this.data.size(); i++) {
            if (Objects.equals(this.data.get(i).getClass(), LandscapeObject.class)) {
                types += this.data.get(i).getType();
            } else {
                types += "0";
            }
        }

        return types;
    }

    public JSONArray getTreasures() {
        JSONArray treasures = new JSONArray();

        for (int i = 0; i < this.data.size(); i++) {
            if (Objects.equals(this.data.get(i).getClass(), TreasureObject.class)) {
                treasures.put(new JSONObject()
                                            .put("type", this.data.get(i).getType())
                                            .put("position", i));
            }
        }
        return treasures;
    }

    public JSONArray getEnemies() {
        JSONArray enemies = new JSONArray();

        for (int i = 0; i < this.data.size(); i++) {
            if (Objects.equals(this.data.get(i).getClass(), EnemyObject.class)) {
                enemies.put(new JSONObject()
                        .put("position", i)
                        .put("number", ((EnemyObject) this.data.get(i)).getNumber())
                        .put("units", ((EnemyObject) this.data.get(i)).getArmy().getUnitsJSON()));
            }
        }
        return enemies;
    }

}
