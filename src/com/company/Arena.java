package com.company;

import java.util.Random;

/**
 * Created by Alex on 3/1/2017.
 */
public class Arena extends Thread{
    Fighter f1;
    Fighter f2;
    //Урон наносимый бойцом(рассчет идет во время удара)
    int damage;
    Random r = new Random();

    //Свитч для реализации очередности ударов
    int fighterSwitch;

    Arena(){
        Fighter f1;
        Fighter f2;
    }

    public Arena(Fighter f1, Fighter f2) {
        this.f1 = f1;
        this.f2 = f2;
    }


    public Fighter fight(Fighter f1, Fighter f2) {
        System.out.println(f1.toString());
        System.out.println("VS");
        System.out.println(f2.toString() + "\n");

        //Реализация первого удара
        if (r.nextDouble() > 0.5) {
            fighterHit(f1, f2);
            fighterSwitch = 0;
        } else {
            fighterHit(f2, f1);
            fighterSwitch = 1;
        }

        //Последующий замес
        while (f1.getHp() > 0 && f2.getHp() > 0) {
            if (fighterSwitch == 0) {
                fighterHit(f2, f1);
                fighterSwitch = 1;
            } else if (fighterSwitch == 1) {
                fighterHit(f1, f2);
                fighterSwitch = 0;
            }
        }

        System.out.println("Figter1 hp: " + f1.getHp());
        System.out.println("Figter2 hp: " + f2.getHp());
        System.out.println("\n----------------------------\n");

        //Возвращение победителя в замесе обратно в лист
        if (f1.getHp() > 0) {
            f1.setHp(100);
            return f1;
        } else {
            f2.setHp(100);
            return f2;
        }
    }

    //Реализация удара для Fighter 1
    public void fighterHit(Fighter f1, Fighter f2) {
        damage = r.nextInt(f1.getStr()) + 1;
        //Реализация крита;
        if (r.nextDouble() < ((double) f1.getPer() / 100)) {
            f2.hp -= (damage * 2);
            System.out.println((char) 27 + "[31mCRIT F1 hit F2 for: " + damage * 2 + " HP" + (char) 27 + "[0m");
        }
        //Реализация уворота
        else if (r.nextDouble() < ((double) f2.getDex() / 100)) {
            f2.hp -= (damage * 0.2);
            System.out.println((char) 27 + "[34mDODGE F1 hit F2 for: " + (int) (damage * 0.2) + " HP" + (char) 27 + "[0m");
        }
        //Реализация обычного удара
        else {
            f2.hp -= damage;
            System.out.println("NORMAL F1 hit F2 for: " + damage + " HP");
        }

    }

}
