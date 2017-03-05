package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Alex on 3/1/2017.
 */
public class Arena implements Runnable {
    List<Fighter> list = new ArrayList<Fighter>();

    int id;
    Random r = new Random();
    Fighter f1;
    Fighter f2;

    //Свитч для реализации очередности ударов
    int fighterSwitch;

    Arena() {
        Fighter f1;
        Fighter f2;
        List list;

    }

    public Arena(List<Fighter> list, int id) {
        this.list = list;
        this.id = id;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                //synchronized (list) {
                fight();
               // }
            } catch (IndexOutOfBoundsException e) {
                Thread.currentThread().interrupt();
                System.out.println("Arena #" + this.id + "CLOSED");
            } catch (NullPointerException e) {
                Thread.currentThread().interrupt();
                System.out.println("Arena #" + this.id + "CLOSED");
            }
        }
    }


    //


    public void fight() {
        getFromList();

        System.out.println("Arena #" + this.id);
        System.out.println("\n" + f1.toString());
        System.out.println("VS");
        System.out.println(f2.toString() + "\n");

        //Реализация первого удара
        if (r.nextDouble() > 0.5) {
            f1.doHit(f2);
            fighterSwitch = 0;
        } else {
            f2.doHit(f1);
            fighterSwitch = 1;
        }

        //Последующий замес
        while (f1.getHp() > 0 && f2.getHp() > 0) {
            if (fighterSwitch == 0) {
                f2.doHit(f1);
                fighterSwitch = 1;
            } else if (fighterSwitch == 1) {
                f1.doHit(f2);
                fighterSwitch = 0;
            }
        }

        System.out.println("\n" + f1.getName() + f1.id + " hp: " + f1.getHp());
        System.out.println(f2.getName() + f2.id + " hp: " + f2.getHp());
        System.out.println("\n----------------------------\n");

        //Возвращение победителя в замесе обратно в лист
        returnToList();
        System.out.println("LIST SIZE: " + list.size());


    }

    private synchronized void returnToList() {
        if (f1.getHp() > 0) {
            f1.setHp(100);
            list.add(f1);
        } else {
            f2.setHp(100);
            list.add(f2);
        }
    }

    private synchronized void getFromList() {
        f1 = list.get(0);
        f2 = list.get(1);
        list.remove(0);
        list.remove(0);
    }
}
