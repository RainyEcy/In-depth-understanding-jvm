package org.fenixsoft.jvm.chapter2;

/**
 * Java虚拟机栈内存溢出
 * VM Args：-Xss128k
 * -Xss2M 每个线程的虚拟机栈大小
 */
public class JavaVMStackSOF_1 {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF_1 oom = new JavaVMStackSOF_1();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
