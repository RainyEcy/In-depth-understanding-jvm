package org.fenixsoft.jvm.chapter4;

public class Bar {
    int a = 1;
    static int b = 2;

    public int sum(int c) {
        return a + b + c;
    }

    /**
     * 汇编模式打印日志
     * -XX:+UnlockDiagnosticVMOptions
     * -XX:+PrintAssembly
     * -Xcomp
     * -XX:CompileCommand=dontunline,*Bar.sum
     * -XX:CompileCommand=compileonly,*Bar.sum
     */
    /**
     * -XX:+UnlockDiagnosticVMOptions
     * -XX:+PrintAssembly
     * -XX:+TraceClassLoading
     * -XX:+LogCompilation
     * -XX:LogFile=logfile.log
     */
    public static void main(String[] args) {
        new Bar().sum(3);
    }
}
