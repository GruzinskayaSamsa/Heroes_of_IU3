package BattleObjects;

import BattleObjects.Battlable;
import BattleObjects.BattleMapObject;
import MapObjects.HeroObject;
import com.company.QuasiConsole;

import java.util.ArrayList;

public class Battle {

    private Battlable leftHero;
    private Battlable rightHero;

    private static final int width = 20;
    private static final int height = 10;

    private ArrayList<BattleMapObject> battleField;

    private int step;
    private boolean isEnd;
    private int winner;

    private int position;

    public Battle() {
        this.resetBattle(-1);
    }

    public void setLeftHero(Battlable leftHero) {
        this.leftHero = leftHero;

        for (int i = 0; i < leftHero.getArmy().getSize(); i++) {
            this.battleField.set(i * this.width, new BattleMapObject(leftHero.getArmy().get(i)));
        }
    }

    public void setRightHero(Battlable rightHero) {
        this.rightHero = rightHero;
        for (int i = 0; i < rightHero.getArmy().getSize(); i++) {
            this.battleField.set(this.width - 1 + i * this.width, new BattleMapObject(rightHero.getArmy().get(i)));
        }
    }

    public void print() {
        switch (this.step) {
            case (0):
                QuasiConsole.clearConsole();
                for (int k = 0; k < this.width + 2; k++) {
                    System.out.print("\u001B[31m" + "\uD83D\uDD25" + "\u001B[0m");
                }
                System.out.println();
                for (int i = 0; i < this.height; i++) {
                    System.out.print("\u001B[31m" + "\uD83D\uDD25" + "\u001B[0m");
                    for (int j = 0; j < this.width; j++) {
                        System.out.print(this.battleField.get(j + i * this.width).getImage());
                    }
                    System.out.print("\u001B[31m" + "\uD83D\uDD25" + "\u001B[0m");
                    System.out.println();
                }
                for (int k = 0; k < this.width + 2; k++) {
                    System.out.print("\u001B[31m" + "\uD83D\uDD25" + "\u001B[0m");
                }
                System.out.println();
                this.step += 1;
                break;
            case 1:
                //QuasiConsole.clearConsole();
                System.out.println(leftHero.getArmy().getForce() + " vs " + rightHero.getArmy().getForce());
                this.step += 1;
                break;
            case 2:
                //QuasiConsole.clearConsole();
                System.out.println(Math.max(this.leftHero.getArmy().getForce(), this.rightHero.getArmy().getForce()));
                this.isEnd = true;
                this.winner = this.leftHero.getArmy().getForce() >= this.rightHero.getArmy().getForce() ? 0 : 1;
                break;
        }
    }

    public void resetBattle(int position) {
        this.leftHero = null;
        this.rightHero = null;
        this.isEnd = false;
        this.step = 0;
        this.position = position;

        this.battleField = new ArrayList<BattleMapObject>();
        for (int i = 0; i < this.width * this.height; i++) {
            this.battleField.add(new BattleMapObject());
        }
    }

    public boolean isEnd() {
        return this.isEnd;
    }

    public int getWinner() {
        return this.winner;
    }

    public int getPosition() {
        return this.position;
    }

}
