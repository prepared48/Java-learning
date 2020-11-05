package com.geniu.string;

/**
 * 通过查看反编译后的代码，知道：switch只支持整数形式的表
 * 其他比如char字符型、String字符串形式都是转换成整数形式进行比较
 * <p>
 * 总结：当 switch 括号内的变量类型为 String 并且此变量为外部参数时，必须先进行 null
 * 判断。
 *
 * @author shibo_zhong
 * @date 2020/11/03
 */
public class switchDemoString {
    public static void main(String[] args) {
        String str = null;
        if (str == null) {
            return;
        }
        switch (str) {
            case "hello":
                System.out.println("hello");
                break;
            case "world":
                System.out.println("world");
                break;
            case "null":
                System.out.println("null");
                break;
            default:
                break;
        }
    }
}
