package org.fenixsoft.clazz;

import java.io.*;
import java.util.concurrent.Executors;

public class JITTest {
    public static final int NUM = 15000;

    public static int doubleValue(int i) {
        for (int j = 0; j < 100000; j++) ;
        return i * 2;
    }

    public static long calsum() {

        long sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum += doubleValue(i);
        }
        return sum;
    }

    public static void main(String[] args) {
        for (int i = 0; i < NUM; i++) {
            calsum();
        }
    }
}
