package com.geniu.concurrent.priority.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 分级队列调度
 */
public class MultiLevelScheduler {

    /**
     * High-priority
     */

    private PriorityQueue<Task> urgents;

    private ArrayList<LinkedList<Task>> multLevelQueues;

    /**
     * Levels of Scheduler
     */

    private int level = 3;

    public MultiLevelScheduler() {
        this.init();
    }

    public MultiLevelScheduler(int level) {
        this.level = level;
        this.init();
    }

    private void init() {
        urgents = new PriorityQueue<>();
        multLevelQueues = new ArrayList<>();
        for (int i = 0; i < this.level; i++) {
            multLevelQueues.add(new LinkedList<Task>());
        }
    }

    @FunctionalInterface
    interface IYieldFunction {
        void yield();
    }

    @FunctionalInterface
    interface ITask {
        void run(IYieldFunction yieldFunction);
    }

    class Task implements Comparable<Task> {
        int level = -1;
        ITask task;
        int priority;
        private boolean yield;

        public Task(ITask task, int priority) {
            this.task = task;
            this.priority = priority;
        }

        @Override
        public int compareTo(Task o) {
            return this.priority - o.priority;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public void run(IYieldFunction f) {
            this.task.run(f);
        }

        public void setYield(boolean yield) {
            this.yield = yield;
        }

        public boolean isYield() {
            return yield;
        }
    }


    /**
     * 根据 priority 判断是紧急任务还是普通任务，存入对应的队列中
     *
     * @param itask
     * @param priority
     */
    public void submit(ITask itask, int priority) {
        Task task = new Task(itask, priority);
        if (priority >= 100) {
            this.multLevelQueues.get(0).add(task);
            task.setLevel(0);

        } else {
            this.urgents.add(task);
        }
    }


    public void submit(ITask t) {
        this.submit(t, 100);
    }

    private Task next() {
        if (this.urgents.size() > 0) {
            return this.urgents.remove();
        } else {
            for (int i = 0; i < this.level; i++) {
                LinkedList<Task> queue = this.multLevelQueues.get(i);
                if (queue.size() > 0) {
                    return queue.remove();
                }
            }
        }
        return null;
    }

    private void runNext() {
        Task nextTask = this.next();
        if (nextTask == null) {
            return;
        }
        if (nextTask.isYield()) {
            return;
        }

        nextTask.run(() -> {
            if (nextTask.level == -1) {
                // high-priority forbid yield
                return;
            }
            nextTask.setYield(true);
            if (nextTask.level < this.level - 1) {
                multLevelQueues.get(nextTask.level + 1).add(nextTask);
                nextTask.setLevel(nextTask.level + 1);
            } else {
                multLevelQueues.get(nextTask.level).add(nextTask);
            }

            this.runNext();
        });
        this.runNext();
    }

    public void start() throws InterruptedException {
        this.runNext();
    }


    public static void main(String[] argv) throws InterruptedException {
        MultiLevelScheduler scheduler = new MultiLevelScheduler();

        scheduler.submit((IYieldFunction yield) -> {
            System.out.println("A1");
            yield.yield();
            System.out.println("A2");
        });

        scheduler.submit((IYieldFunction yield) -> {
            System.out.println("B");
        });

        scheduler.submit((IYieldFunction f) -> {
            System.out.println("C");
        }, 1);

        scheduler.submit((IYieldFunction yield) -> {
            System.out.println("Urgent");
        }, 10);

        scheduler.submit((IYieldFunction yield) -> {
            System.out.println("Most Urgent");
        }, 0);
        scheduler.start();
    }

}
