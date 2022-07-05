package org.fenixsoft.jvm.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存超出
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * -Xms20m 堆最小值
 * -Xmx20m 堆最大值
 * -XX:+HeapDumpOnOutOfMemoryError 内存溢出时打印堆转储快照，以便分析
 */
public class HeapOOM {

    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}

