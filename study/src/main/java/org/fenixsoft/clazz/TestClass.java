package org.fenixsoft.clazz;

public class TestClass {
    private int m;

    public int inc() {
        return m + 1;
    }

    public int exception() {
        int i;
        try {
            i = 1;
            throw new RuntimeException();
        } catch (NullPointerException e) {
            i = 2;
            return i;
        } finally {
            i = 3;
        }
    }

    public static void main(String[] args) {
        int l = new TestClass().exception();
        System.out.println(l);
    }

}
