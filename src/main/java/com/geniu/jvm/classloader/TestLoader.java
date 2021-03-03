package com.geniu.jvm.classloader;

/**
 * @Author: zhongshibo
 * @Date: 2021/3/3 08:35
 */
public class TestLoader {

    public static void main(String[] args) {
        printParentOfClassLoader();
        printBootClassLoaderPaths();
        printExtPaths();
        printAppClassLoaderPath();
    }

    /**
     * 打印类加载器的 parent 属性（ClassLoader.java 类中的parent属性）
     */
    public static void printParentOfClassLoader() {
        ClassLoader systemLoader = TestLoader.class.getClassLoader();
        System.out.println("系统类加载器：" + systemLoader);
        ClassLoader extLoader = systemLoader.getParent();
        System.out.println("系统类加载器的parent（扩展类加载器）：" + extLoader);
        ClassLoader bootClassLoader = extLoader.getParent();
        System.out.println("扩展类加载器的parent（启动类加载器）：" + bootClassLoader);
    }

    /**
     * 启动类加载器 加载
     */
    public static void printBootClassLoaderPaths() {
        // 可以查看 Launcher.java 类中的加载路径
        String var0 = System.getProperty("sun.boot.class.path");
        System.out.println("启动类加载器加载路径：" + var0);
    }

    public static void printExtPaths() {
        String var0 = System.getProperty("java.ext.dirs");
        System.out.println("扩展类加载器加载路径：" + var0);

    }

    public static void printAppClassLoaderPath() {
        String var0 = System.getProperty("java.class.path");
        System.out.println("系统类加载器加载路径：" + var0);
    }
}
