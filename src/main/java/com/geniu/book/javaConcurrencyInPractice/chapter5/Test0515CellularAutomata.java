package com.geniu.book.javaConcurrencyInPractice.chapter5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 通过 CyclicBarrier 协调细胞自动衍生系统中的计算
 *
 * @Author: zhongshibo
 * @Date: 2021/2/22 12:50
 */
public class Test0515CellularAutomata {

    private final Board mainBoard;

    private final CyclicBarrier barrier;

    private final Worker[] workers;

    /**
     * 构造函数
     *
     * @param board
     */
    public Test0515CellularAutomata(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count, new Runnable() {
            @Override
            public void run() {
                mainBoard.commitNewValues();
            }
        });
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++) {
            workers[i] = new Worker(mainBoard.getSubBoard(count, i));
        }
    }

    /**
     * 一个任务
     */
    private class Worker implements Runnable {
        private final Board board;

        public Worker(Board board) {
            this.board = board;
        }

        @Override
        public void run() {
            while (!board.hasConverged()) {
                for (int x = 0; x < board.getMaxX(); x++) {
                    for (int y = 0; y < board.getMaxY(); y++) {
                        board.setNewValue(x, y, computeValue(x, y));
                    }
                }
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    return;
                } catch (BrokenBarrierException e) {
                    return;
                }
            }
        }
    }

    private Object computeValue(int x, int y) {
        return null;
    }

    /**
     * start方法 启动 CPU 个线程执行任务
     */
    public void start() {
        for (int i = 0; i < workers.length; i++) {
            new Thread(workers[i]).start();
        }
        mainBoard.waitForConvergence();
    }
}


