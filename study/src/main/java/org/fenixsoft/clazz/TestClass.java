package org.fenixsoft.clazz;

public class TestClass {

    public static String str = "ecy test";

    class InnerClass {
        private int y;
    }

    private int m;

    @Deprecated
    public int inc() throws NullPointerException, StackOverflowError, IndexOutOfBoundsException {
        return m + 1;
    }

    public int exception() {
        int i;
        try {
            i = 1;
            return i;
        } catch (NullPointerException e) {
            i = 2;
            return i;
        } finally {
            i = 3;
        }
    }

}
