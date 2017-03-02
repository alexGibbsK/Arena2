package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by java-1-07 on 01.03.2017.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        Random r = new Random();
        //ID для каждого файтера
        int id = 1;
        //Кол-во арен
        int countOfArenas = 5;

        List<Fighter> list = new LinkedList<Fighter>();
        List<Arena> arenaList = new LinkedList<Arena>();

        //Создание списков
        getFighterList(r, list, id);
        getArenaList(list, arenaList, countOfArenas);

        //Вывод списка бойцов
        fighterListPrint(list);

        //Начало битвы
        startFightOnAllArenas(arenaList);

    }

    private static void startFightOnAllArenas(List<Arena> arenaList) {
        for (Arena a :
                arenaList) {
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

    //Создание списка арен
    private static void getArenaList(List<Fighter> list, List<Arena> arenaList, int count) {
        for (int i = 0; i < count; i++) {
            arenaList.add(new Arena(list));
        }
    }

    //Создание списка файтеров
    private static void getFighterList(Random r, List<Fighter> list, int id) {
        for (int i = 0; i < 2; i++) {
            list.add(new Fighter(r.nextInt(100) + 1, r.nextInt(100) + 1, r.nextInt(100) + 1, id++));
        }
    }
}
