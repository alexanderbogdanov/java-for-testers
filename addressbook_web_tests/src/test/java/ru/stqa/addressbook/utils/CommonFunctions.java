package ru.stqa.addressbook.utils;

import java.util.Random;

public class CommonFunctions {
    public static String randomString(int n) {
        Random rnd = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append((char) (rnd.nextInt(26) + 'a'));
        }
        return result.toString();
    }
}
