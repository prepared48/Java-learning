package com.geniu.tool;

/**
 * idea自动进行json转义，神奇的Inject language
 * <p>
 * 第一步：焦点定位到双引号里面，，使用alt+enter快捷键弹出inject language视图，并选中Inject language or reference
 * 第二步，按下enter，在列表中选中 json
 * 第三步，选择完后。鼠标焦点自动会定位在双引号里面，这个时候你再次使用alt+enter就可以看到，选中Edit JSON Fragment并回车，就可以看到编辑JSON文件的视图了。
 *
 * @Author: zhongshibo
 * @Date: 2021/1/4 09:59
 */
public class AutoJson {

    public static void main(String[] args) {
        String jsonString = "{\"name\": \"ZHANGSAN\", \"age\": 17}";
        if (jsonString == null) {

        }
    }
}
