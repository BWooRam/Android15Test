package com.hyundaiht.android15test.openjdk;

import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.IntStream;

public class OpenJdkTest {
    public String testStringFormatIndexFail(Object text) {
        return String.format("%0$s", text);
    }

    public String testStringFormatIndexSuccess(Object text) {
        return String.format("%1$s", text);
    }

    public String testStringFormatFlagFail1() {
        return String.format("%+-5d%n", 42);
    }

    public String testStringFormatFlagFail2() {
        return String.format("%0-5d%n", 42);
    }

    public String testStringFormatFlagFail3() {
        return String.format("%+ d%n", 42);
    }

    public String testStringFormatFlagFail4() {
        return String.format("%#05x%n", 42);
    }

    public String testStringFormatPrecisionSuccess(Object text) {
        return String.format("%-5.2f", 3.14159);
    }

    public String testStringFormatSuccess(Object text) {
        return String.format("%d", 3.14);
    }

    public String[] testArraysAsListToArrayFail() {
        return (String[]) Arrays.asList("a", "b").toArray();
    }

    public String[] testArraysAsListToArraySuccess() {
        return Arrays.asList("a", "b").toArray(new String[0]);
    }

    public String testLocaleCodesFail() {
        Locale hebrew = new Locale("iw");
        Locale yiddish = new Locale("ji");
        Locale indonesian = new Locale("in");

        return "히브리어: " + hebrew.getLanguage() + "이디시어: " + yiddish.getLanguage() + "인도네시아어: " + indonesian.getLanguage();
    }

    public String testLocaleCodesSuccess() {
        Locale hebrew = new Locale("he");
        Locale yiddish = new Locale("yi");
        Locale indonesian = new Locale("id");

        return "히브리어: " + hebrew.getLanguage() + "이디시어: " + yiddish.getLanguage() + "인도네시아어: " + indonesian.getLanguage();
    }

    public void testRandomSequence() {
        long seed = 12345L;

        Random random1 = new Random(seed);
        Random random2 = new Random(seed);

        System.out.println("Using nextInt():");
        for (int i = 0; i < 5; i++) {
            System.out.println("nextInt: " + random1.nextInt());
        }

        System.out.println("\nUsing ints().limit(5):");
        IntStream stream = random2.ints().limit(5);
        stream.forEach(value -> System.out.println("ints(): " + value));
    }
}
