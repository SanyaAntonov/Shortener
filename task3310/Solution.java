package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
    }
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> ids = new HashSet<>();
        for (String str : strings) {
            ids.add(shortener.getId(str));
        }
        return ids;
    }
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();
        for (Long key : keys) {
            strings.add(shortener.getString(key));
        }
        return strings;
    }
    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName() + " : ");
        Set<String> strings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        Date dateBeforeGetIds = new Date();
        getIds(shortener, strings);
        Date dateAfterGetIds = new Date();
        long timeIds = dateAfterGetIds.getTime() - dateBeforeGetIds.getTime();
        Helper.printMessage("Время на метод getIds : " + timeIds);

        Date dateBeforeGetStrings = new Date();
        Set<String> testStrings = getStrings(shortener, getIds(shortener, strings));
        Date dateAfterGetStrings = new Date();
        long timeStrings = dateAfterGetStrings.getTime() - dateBeforeGetStrings.getTime();
        Helper.printMessage("Время на метод getStrings : " + timeStrings);

        if (testStrings.equals(strings))
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");
    }
}
