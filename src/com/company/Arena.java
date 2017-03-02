package com.company;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Alex on 3/1/2017.
 */
public class Arena extends Thread {
    private final Object lock = new Object();
    List<Fighter> list = Collections.synchronizedList(new LinkedList<Fighter>());

    //Урон наносимый бойцом(рассчет идет во время удара)
    int damage;
    int id;
    Random r = new Random();

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
        while (list.size() > 0) {
            try {
                synchronized (list) {
                    //Вывод победителя и окончательная очистка листа
                    if (list.size() == 1) {
                        System.out.println("WINNER: " + list.get(0));
                        list.remove(0);
                        Thread.currentThread().interrupt();
                    } else {
                        //Старт боя с последующим добавлением победителя в конец листа
                        System.out.println("Arena #" + this.id);
                        list.add(fight(list.get(0), list.get(1)));
                        list.remove(0);
                        list.remove(0);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                Thread.currentThread().interrupt();
            } catch (NullPointerException e) {
                Thread.currentThread().interrupt();
            } finally {
                Thread.currentThread().interrupt();
            }


            if (Thread.currentThread().isAlive() && list.size() == 0) {
                Thread.currentThread().interrupt();
            }
        }
    }


    public Fighter fight(Fighter f1, Fighter f2) {
        System.out.println("\n" + f1.toString());
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

        System.out.println("Figter" + f1.id + " hp: " + f1.getHp());
        System.out.println("Figter" + f2.id + " hp: " + f2.getHp());
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

    //Реализация удара
    public void fighterHit(Fighter f1, Fighter f2) {
        damage = r.nextInt(f1.getStr()) + 1;
        //Реализация крита;
        if (r.nextDouble() < ((double) f1.getPer() / 100)) {
            f2.hp -= (damage * 2);
            System.out.println((char) 27 + "[31mCRIT Fighter" + f1.id + " hit Fighter" + f2.id + " for: " + damage * 2 + " HP" + (char) 27 + "[0m");
        }
        //Реализация уворота
        else if (r.nextDouble() < ((double) f2.getDex() / 100)) {
            f2.hp -= (damage * 0.2);
            System.out.println((char) 27 + "[34mDODGE Fighter" + f1.id + " hit Fighter" + f2.id + " for: " + (int) (damage * 0.2) + " HP" + (char) 27 + "[0m");
        }
        //Реализация обычного удара
        else {
            f2.hp -= damage;
            System.out.println("NORMAL Fighter" + f1.id + " hit Fighter" + f2.id + " for: " + damage + " HP");
        }

    }
}
