package ru.cypix.service;

import ru.cypix.domain.candy.base.ICandy;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class CandyQueue {

    private final Queue<ICandy> queue;
    private final ReentrantLock locker = new ReentrantLock();

    public CandyQueue() {
        queue = new ArrayDeque<>();
    }

    public void add(ICandy candy) {
        queue.add(candy);
    }

    public ICandy poll() {
        if (locker.tryLock()) {
            return queue.poll();
        }
        return null;
    }

    public void unlock() {
        locker.unlock();
    }
}
