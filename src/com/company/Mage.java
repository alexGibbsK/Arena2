package com.company;

/**
 * Created by ПК on 04.03.2017.
 */
public class Mage extends Fighter {
    String name = "Mage";
    String hitName = " casts fireball on ";

    public String getHitName() {
        return hitName;
    }

    public String getName() {
        return name;
    }

    public Mage(int intel, int dex, int per, int id) {
        int sum = intel + dex + per;
        double max = 50;

        this.id = id;
        this.intel = (int) ((max / sum) * intel);
        if (this.intel < 1) this.str++;
        this.dex = (int) ((max / sum) * dex);
        this.per = (int) ((max / sum) * per);
        this.hp = 100;
    }

    public int getIntel() {
        return intel;
    }

    public void setIntel(int intel) {
        this.intel = intel;
    }

    @Override
    public Fighter doHit(Fighter f1) {
        return super.doHit(f1);
    }

}
