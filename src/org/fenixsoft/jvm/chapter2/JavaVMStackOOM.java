package org.fenixsoft.jvm.chapter2;

/**
 * Java本地方法栈内存溢出
 * VM Args：-Xss2M （这时候不妨设大些，请在32位系统下运行）
 * -Xss2M 每个线程的虚拟机栈大小
 */
public class JavaVMStackOOM {

    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
