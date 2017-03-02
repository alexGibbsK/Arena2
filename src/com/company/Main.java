package com.company;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by java-1-07 on 01.03.2017.
 */
public class Main {
    public static void main(String[] args){

        Random r = new Random();
        //ID для каждого файтера
        int fighterId = 1;
        int arenaId = 0;
        //Кол-во бойцов
        int fCount = 3000;
        //Кол-во арен
        int countOfArenas = 10;


        List<Fighter> list = Collections.synchronizedList(new LinkedList<Fighter>());
        List<Arena> arenaList = new LinkedList<Arena>();

        //Создание списков
        getFighterList(r, list, fighterId, fCount);
        getArenaList(list, arenaList, countOfArenas, arenaId);

        //Вывод списка бойцов
        fighterListPrint(list);

        //Начало битвы
            startFightOnAllArenas(arenaList);

        for (Fighter f :
                list) {
            System.out.println(f.toString());
        }

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
    private static void getArenaList(List<Fighter> list, List<Arena> arenaList, int count, int id) {
        for (int i = 0; i < count; i++) {
            arenaList.add(new Arena(list, ++id));
        }
    }

    //Создание списка файтеров
    private static void getFighterList(Random r, List<Fighter> list, int id, int fCount) {
        for (int i = 0; i < fCount; i++) {
            list.add(new Fighter(r.nextInt(100) + 1, r.nextInt(100) + 1, r.nextInt(100) + 1, id++));
        }
    }
}
