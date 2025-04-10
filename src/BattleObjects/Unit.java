package BattleObjects;

public abstract class Unit {

    protected int hp, damage;
    protected int damageRadius;
    protected int number;
    protected String name;
    protected String image;

    public void init(int number){
        this.number = number;
    }

    public int getHp() {
        return hp;
    }

    public int getDamage() {
        return damage;
    }

    public int getDamageRadius() {
        return damageRadius;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
