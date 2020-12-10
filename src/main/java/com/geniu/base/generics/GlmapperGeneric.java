package com.geniu.base.generics;

/**
 * T 通配符
 *
 * @Auther: zhongshibo
 * @Date: 2020/12/10 10:33
 */
public class GlmapperGeneric<T> {

    private T t;

    public void setT(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void noSpecifyType() {
        GlmapperGeneric glmapperGeneric = new GlmapperGeneric();
        glmapperGeneric.setT("test");

        String test = (String) glmapperGeneric.getT();
        System.out.println(test);
    }

    public void specifyType() {
        GlmapperGeneric<String> glmapperGeneric = new GlmapperGeneric();
        glmapperGeneric.setT("test specify");

        String test = glmapperGeneric.getT();
        System.out.println(test);
    }

    public static void main(String[] args) {
        GlmapperGeneric glmapperGeneric = new GlmapperGeneric();
        glmapperGeneric.noSpecifyType();
        glmapperGeneric.specifyType();
    }
}
