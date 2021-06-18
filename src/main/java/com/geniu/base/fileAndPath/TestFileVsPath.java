package com.geniu.base.fileAndPath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 对比 File.java 和 Path.java
 *
 * @Author: zhongshibo
 * @Date: 2021/6/18 11:35
 */
public class TestFileVsPath {

    public static void main(String[] args) {
        testFile();
        testPath();
    }

    /**
     * 缺点：
     * 1、错误处理不好，不知道内部包的详细错误是什么，只有一个结论false
     * 2、元数据 支持很差。元数据包含：权限、文件所有者、安全属性
     * 3、方法缩放和性能有问题，如果一个目录有很多文件，遍历可能会有问题——内存资源问题
     */
    public static void testFile() {
        File file = new File("baeldung/tutorial.txt");
        file.delete();

        try {
            boolean result = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean fileExists = file.exists();
        boolean fileIsFile = file.isFile();
        boolean fileIsDir = file.isDirectory();
        boolean fileReadable = file.canRead();
        boolean fileWritable = file.canWrite();
        boolean fileExecutable = file.canExecute();
        boolean fileHidden = file.isHidden();

        String absolutePathStr = file.getAbsolutePath();
        try {
            String canonicalPathStr = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 相对的：
     * 1、错误处理更好，有详细的错误信息，比如没有该文件，或者没有权限等等。
     */
    public static void testPath() {
        Path path = Paths.get("baeldung/tutorial.txt");
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean pathExists = Files.exists(path);
        boolean pathIsFile = Files.isRegularFile(path);
        boolean pathIsDir = Files.isDirectory(path);
        boolean pathReadable = Files.isReadable(path);
        boolean pathWritable = Files.isWritable(path);
        boolean pathExecutable = Files.isExecutable(path);
        try {
            boolean pathHidden = Files.isHidden(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path absolutePath = path.toAbsolutePath();
        try {
            Path canonicalPath = path.toRealPath().normalize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
