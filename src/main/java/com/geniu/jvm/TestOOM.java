package com.geniu.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 测试 OOM
 */
public class TestOOM {

    public static void main(String[] args) {
        List<OOMBean> cases = new ArrayList<OOMBean>();
        Random random = new Random();
        while (true) {
            OOMBean oomBean = new OOMBean();
            oomBean.setAge(random.nextInt(100));
            oomBean.setUserName("sldjfls三六九等开发了煞风景啊；四大佛教萨里的副驾驶的飞机啊水力发电");

            cases.add(oomBean);

        }


    }

}
