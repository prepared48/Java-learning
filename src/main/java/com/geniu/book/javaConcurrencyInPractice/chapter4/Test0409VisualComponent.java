package com.geniu.book.javaConcurrencyInPractice.chapter4;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 将线程安全性委托给多个状态变量
 * CopyOnWriteArrayList 是线程安全的链表
 *
 * @Author: zhongshibo
 * @Date: 2021/2/19 22:24
 */
public class Test0409VisualComponent {

    private final List<KeyListener> keyListeners = new CopyOnWriteArrayList();

    private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList();

    public void addKeyListener(KeyListener listener) {
        keyListeners.add(listener);
    }

    public void addMouseListener(MouseListener listener) {
        mouseListeners.add(listener);
    }

    public void removeKeyListener(KeyListener listener) {
        keyListeners.remove(listener);
    }

    public void removeMouseListener(MouseListener listener) {
        mouseListeners.remove(listener);
    }
}
