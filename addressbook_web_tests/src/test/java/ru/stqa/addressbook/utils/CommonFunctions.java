package ru.stqa.addressbook.utils;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;
import net.datafaker.Faker;
import java.util.Locale;

public class CommonFunctions {

    public static String randomString(int n) {
        Random rnd = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append((char) (rnd.nextInt(26) + 'a'));
        }
        return result.toString();

    }

    public static String getRandomImagePath(String dir) {
        var fileNames = new File(dir).list();
        if (fileNames == null || fileNames.length == 0) {
            throw new IllegalStateException("No files found in directory: " + dir);
        }
        var rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
        return Paths.get(dir, fileNames[index]).toString();
    }

    private static final Faker faker = new Faker(new Locale("en-US"));
        public static String randomFirstName() {
            return faker.name().firstName();
        }

        public static String randomLastName() {
            return faker.name().lastName();
        }

        public static String randomMiddleName() {
            return faker.name().firstName();
        }

        public static String randomNickname() {
            return faker.name().username();
        }

        public static String randomCompany() {
            return faker.company().name();
        }

        public static String randomTitle() {
            return faker.job().title();
        }

        public static String randomAddress() {
            return faker.address().fullAddress();
        }

        public static String randomHomePhone() {
            return faker.phoneNumber().phoneNumber();
        }

        public static String randomMobilePhone() {
            return faker.phoneNumber().cellPhone();
        }

        public static String randomWorkPhone() {
            return faker.phoneNumber().phoneNumber();
        }

        public static String randomFax() {
            return faker.phoneNumber().phoneNumber();
        }

        public static String randomEmail() {
            return faker.internet().emailAddress();
        }

        public static String randomHomePage() {
            return faker.internet().url();
        }

        public static String randomHeader() {
            return faker.lorem().sentence();
        }

        public static String randomFooter() {
            return faker.lorem().sentence();
        }


}
