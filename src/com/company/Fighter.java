package com.company;

/**
 * Created by java-1-07 on 01.03.2017.
 */
public class Fighter {
    //Сила
    int str;
    //Ловкость
    int dex;
    //Восприятие
    int per;
    int hp = 100;
    int id;


    public Fighter(int str, int dex, int per, int id) {
        int sum = str + dex + per;
        double max = 50;

        this.id = id;
        this.str = (int) ((max / sum) * str);
        if (this.str < 1) this.str++;
        this.dex = (int) ((max / sum) * dex);
        this.per = (int) ((max / sum) * per);
        this.hp = 100;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStr() {
        return str;
    }

    public int getDex() {
        return dex;
    }

    public int getPer() {
        return per;
    }

    @Override
    public String toString() {
        return "Fighter" + id +
                " str=" + str +
                ", dex=" + dex +
                ", per=" + per +
                ", hp=" + hp;
    }
}
