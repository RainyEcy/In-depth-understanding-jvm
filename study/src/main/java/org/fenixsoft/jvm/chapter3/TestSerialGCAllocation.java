package org.fenixsoft.jvm.chapter3;

/**
 * VM参数：-XX:+UseSerialGC
 */
public class TestSerialGCAllocation {

    private static final int _1MB = 1024 * 1024;

    /**
     * 触发Minor GC
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -verbose:gc  等同于 -XX:+PrintGC 等同于 -Xlog:gc
     */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];  // 出现一次Minor GC
    }

    /**
     * 大对象直接进入老年代
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshold=3145728 大对象内存限制，超过该内存直接进入老年代 只支持Serial 和 Parnew
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB];  //直接分配在老年代中
    }

    /**
     * 长期存活的对象进入老年代
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
     * -XX:+PrintTenuringDistribution
     */
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4];  // 什么时候进入老年代决定于XX:MaxTenuringThreshold设置
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }

    /**
     * 动态对象年龄判定 Survivor年龄小于等于某年龄的所有对象大小和占据过半则年龄大者入老年代
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
     * -XX:+PrintTenuringDistribution
     */
    public static void testTenuringThreshold2() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4];   // allocation1+allocation2大于survivo空间一半
        allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }

    /**
     * 均采用SerialGC 非region布局好测试
     * 1.8默认Paraller Scaveng + Paraller Old
     * 11+ 默认 G1
     */
    public static void main(String[] args) {
        // 触发Minor GC
//        TestSerialGCAllocation.testAllocation();
        // 大对象直接进入老年代
//        TestSerialGCAllocation.testPretenureSizeThreshold();
        // 长期存活的对象进入老年代
//        TestSerialGCAllocation.testTenuringThreshold();
        // 动态对象年龄判定 Survivor年龄小于等于某年龄的所有对象大小和占据过半则年龄大者入老年代
//        TestSerialGCAllocation.testTenuringThreshold2();
        // 空间分配担保 - 开关已过期 JDK Update 24规则已更改 老年代剩余空间 小于新生代对象总大小 或者 小于历次晋升总大小，则直接Full gc
//        TestSerialGCAllocation.testHandlePromotion();
    }

    /**
     * 空间分配担保
     * VM参数：-Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:-HandlePromotionFailure
     * -XX:-HandlePromotionFailure 老年代剩余空间小于历次Minor GC晋升的老年代对象时，是否冒险继续进行Minor GC
     */
    @Deprecated
    public static void testHandlePromotion() {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null;
        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2 * _1MB];
    }

}
