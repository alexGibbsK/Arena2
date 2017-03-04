package com.company;

import java.util.Random;

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
    Random r = new Random();


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

    public Fighter doHit(Fighter f1) {
        int damage = r.nextInt(this.str) + 1;
        if (r.nextDouble() < ((double) this.per / 100)) {
            f1.hp -= (damage * 2);
           System.out.println((char) 27 + "[31mCRIT Fighter" + this.id + " Hits Fighter" + f1.id + " for: " + damage * 2 + " HP" + (char) 27 + "[0m");
        }
        //Реализация уворота
        else if (r.nextDouble() < ((double) f1.getDex() / 100)) {
            f1.hp -= (damage * 0.2);
            System.out.println((char) 27 + "[34mDODGE Fighter" + this.id + " Hits Fighter" + f1.id + " for: " + (int) (damage * 0.2) + " HP" + (char) 27 + "[0m");
        }
        //Реализация обычного удара
        else {
            f1.hp -= damage;
            System.out.println("NORMAL Fighter" + this.id + " Hits Fighter" + f1.id + " for: " + damage + " HP");
        }
        return f1;
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
