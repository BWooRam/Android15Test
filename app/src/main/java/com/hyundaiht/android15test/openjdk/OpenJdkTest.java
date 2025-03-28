package com.hyundaiht.android15test.openjdk;

import android.util.Log;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.stream.IntStream;

public class OpenJdkTest {
    public void testStringFormatFail(Object text) {
        String.format("%0$s", text);
    }

    public void testStringFormatSuccess(Object text) {
        String.format("%1$s", text);
    }

    public void testArraysAsListToArrayFail() {
        String[] arr = (String[]) Arrays.asList("a", "b").toArray();
    }

    public void testArraysAsListToArraySuccess() {
        String[] arr = (String[]) Arrays.asList("a", "b").toArray();
    }

    public void testLocaleCodes() {
        Locale oldHebrew = new Locale("iw"); // 구식 히브리어
        Locale newHebrew = new Locale("he"); // 표준 히브리어

        Locale oldIndonesian = new Locale("in"); // 구식 인도네시아어
        Locale newIndonesian = new Locale("id"); // 표준 인도네시아어

        Locale oldYiddish = new Locale("ji"); // 구식 이디시어
        Locale newYiddish = new Locale("yi"); // 표준 이디시어

        Log.d("LocaleTest", "iw → " + oldHebrew.getLanguage() + " (should be 'iw')");
        Log.d("LocaleTest", "he → " + newHebrew.getLanguage() + " (should be 'he')");

        Log.d("LocaleTest", "in → " + oldIndonesian.getLanguage() + " (should be 'in')");
        Log.d("LocaleTest", "id → " + newIndonesian.getLanguage() + " (should be 'id')");

        Log.d("LocaleTest", "ji → " + oldYiddish.getLanguage() + " (should be 'ji')");
        Log.d("LocaleTest", "yi → " + newYiddish.getLanguage() + " (should be 'yi')");
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
