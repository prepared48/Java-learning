package com.geniu.jvm;

import java.util.List;

public class TestJVM {

    ThreadLocal threadLocal = new ThreadLocal();

    public static void main(String[] args) {
//        TestJVM testJVM = new TestJVM();
//        testJVM.test1(new ArrayList<>());
        System.out.println();
    }

    public List<String> test1(List<String> stringList) {

        stringList.add("而对于 Java 虚拟机栈和本地方法栈，这里要稍微复杂一点。如果我们写一段程序不断的进行递归调用，而且没有退出条件，就会导致不断地进行压栈。类似这种情况，JVM 实际会抛出 StackOverFlowError；当然，如果 JVM 试图去扩展栈空间的的时候失败，则会抛出 OutOfMemoryError。");
        return test1(stringList);
    }

    public List<String> test2(List<String> stringList) {
        for (int i = 0; i < 1000000000; i++) {
            stringList.add("而对于 Java 虚拟机栈和本地方法栈，这里要稍微复杂一点。如果我们写一段程序不断的进行递归调用，而且没有退出条件，就会导致不断地进行压栈。类似这种情况，JVM 实际会抛出 StackOverFlowError；当然，如果 JVM 试图去扩展栈空间的的时候失败，则会抛出 OutOfMemoryError。");
        }
        return stringList;
    }

    public void lock() {

        jobCode();
    }

    private void jobCode() {
    }

}
