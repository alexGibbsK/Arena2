package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/**
 * Created by java-1-07 on 01.03.2017.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random r = new Random();
        //ID для каждого файтера
        int fighterId = 1;
        int arenaId = 0;
        //Кол-во бойцов
        int fCount = 1400000;
        //Кол-во арен
        int countOfArenas = 10;
        double percentMageOrFighter = 0.5;

        List<Fighter> list = Collections.synchronizedList(new ArrayList<Fighter>());
        List<Thread> arenaList = new ArrayList<Thread>();



        //Создание списков
        getFighterList(r, list, fighterId, fCount, percentMageOrFighter);

        fighterListPrint(list);

        createArenaList(arenaId, countOfArenas, list, arenaList);

        while (list.size() != 1) {
            Thread.currentThread().sleep(10);
        }

        Thread.sleep(1000);
        System.out.println("\nList size: " + list.size() + "\nWINNER: " + list.get(0));



    }

     //Начало битвы на аренах
     //Создание списка арен
    private static void createArenaList(int arenaId, int countOfArenas, List<Fighter> list, List<Thread> arenaList) {
        for (int i = 0; i < countOfArenas; i++) {
            Thread a = new Thread(new Arena(list, ++arenaId));
            arenaList.add(a);
            a.start();
        }
    }

    private static void fighterListPrint(List<Fighter> list) {
        System.out.println("Fighters List: ");
        for (Fighter f :
                list) {
            System.out.println(f.toString());
        }
    }


    //Создание списка файтеров
    private static void getFighterList(Random r, List<Fighter> list, int id, int fCount, double percent) {
        for (int i = 0; i < fCount; i++) {
            if(r.nextDouble() > percent){
                list.add(new Mage(r.nextInt(100) + 1, r.nextInt(100) + 1, r.nextInt(100) + 1, id++));
            }else
                list.add(new Fighter(r.nextInt(100) + 1, r.nextInt(100) + 1, r.nextInt(100) + 1, id++));
        }
    }
}
