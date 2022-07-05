package org.fenixsoft.jvm.chapter2;

import java.util.HashSet;
import java.util.Set;

/**
 * JDK7之前的常量存放在方法区内存，后来存放在元空间跟随操作系统内存大小
 * VM Args：jdk7 -XX:PermSize=6M -XX:MaxPermSize=6M
 * jdk8 -XX:MaxMetaspaceSize=6M -XX:MetaspaceSize=6M
 */
public class RuntimeConstantPoolOOM_1 {

    public static void main(String[] args) {
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<String>();
        // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
