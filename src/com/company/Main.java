package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {270, 260, 250, 240, 200};
    public static int[] heroesDamage = {25, 15, 20, 30, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Archer", "Medic"};
    public static int roundCounter = 1;


    public static void main(String[] args) {
        printStatistics();
        while (!isGameOver()){
            round();
        }

    }

    public static void printStatistics(){
        System.out.println("-------------------------");
        System.out.println("Round: " + roundCounter);
        roundCounter++;
        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + "health: " + heroesHealth[i]);
        }
        System.out.println("-------------------------");
    }

    public static void heroesHeal(){
        System.out.println("---------------------");
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesHealth[4] > 0){
                int randomValue = new Random().nextInt(50);
                heroesHealth[i] = heroesHealth[i] + randomValue;
                return;
            }

        }
    }

    public static void  bossHits(){
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[i] > 0){
                if (heroesHealth[i] - bossDamage < 0){
                    heroesHealth[i] = 0;
                }else{
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }

        }
    }

    public static void heroesHits(){
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0){
                if (heroesAttackType[i] == bossDefenceType){
                    int randomValue = new Random().nextInt(10);
                    heroesDamage[i] = heroesDamage[i] - randomValue;
                }else{
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }else{
                if (bossHealth - heroesDamage[i] < 0){
                    bossHealth = 0;
                }else{
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }

    public static boolean isGameOver(){
        if (bossHealth <= 0){
            System.out.println("Heroes won!!!");
            return true;
        }

        boolean allHeroesDead = true;
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[i] > 0){
                allHeroesDead = false;
                break;
            }
        }

        if (allHeroesDead){
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void changeDefenceType(){
        int randomIndex = new Random().nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choose: " + bossDefenceType);
    }

    public static void round(){
        // Проверяем жив ли наш босс
        if (bossHealth > 0){
            //Изменяет неуязвимость к тому или иному типу атаки
            changeDefenceType();
            // Босс наносит урон
            bossHits();
        }
        // Герои наносят урон
        heroesHits();
        // Распечатываем статистику раунда
        printStatistics();
        // Медик лечит
        heroesHeal();
    }
}
