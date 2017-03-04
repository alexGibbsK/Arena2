package com.company;

/**
 * Created by ПК on 04.03.2017.
 */
public class Mage extends Fighter {
    int intel;
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
        int damage = r.nextInt(this.intel) + 1;
        if (r.nextDouble() < ((double) this.per / 100)) {
            f1.hp -= (damage * 2);
            System.out.println((char) 27 + "[31mCRIT " + this.name + this.id + " casts fireball on " + f1.getName() + f1.id + " for: " + damage * 2 + " HP" + (char) 27 + "[0m");
        }
        //Реализация уворота
        else if (r.nextDouble() < ((double) f1.getDex() / 100)) {
            f1.hp -= (damage * 0.2);
            System.out.println((char) 27 + "[34mDODGE " + this.name + this.id + " casts fireball on " + f1.getName()+ f1.id + " for: " + (int) (damage * 0.2) + " HP" + (char) 27 + "[0m");
        }
        //Реализация обычного удара
        else {
            f1.hp -= damage;
            System.out.println("NORMAL " + this.name + this.id + " casts fireball on " + f1.getName() + f1.id + " for: " + damage + " HP");
        }
        return f1;
    }

    @Override
    public String toString() {
        return "Mage" + this.id +
                " intel=" + intel +
                " dex =" + this.dex + " per=" +this.per;
    }
}
