package ru.cypix.service;

import ru.cypix.domain.candy.base.ICandy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class CandyService extends CandyServiceBase {

    private final BlockingQueue<ICandyEater> candyEatersQueue;
    private final HashMap<Integer, CandyQueue> candyQueues;
    private final ExecutorService executorService;
    private boolean isEating = false;

    public CandyService(ICandyEater[] candyEaters) {
        super(candyEaters);
        candyEatersQueue = new LinkedBlockingQueue<>(Arrays.asList(candyEaters));
        candyQueues = new HashMap<>();
        executorService = Executors.newFixedThreadPool(candyEaters.length);
    }

    @Override
    public void addCandy(ICandy candy) {

        System.out.println("Thread [" + Thread.currentThread().getName() + "] new candy = [" + candy + "]");

        final int candyFlavour = candy.getCandyFlavour();
        CandyQueue candyQueue = candyQueues.get(candyFlavour);

        if (candyQueue == null) {
            candyQueue = new CandyQueue();
            candyQueues.put(candyFlavour, candyQueue);
        }

        candyQueue.add(candy);
    }

    @Override
    public void startEating() {

        isEating = true;

        new Thread(() -> {

            while (isEating) {

                // Берем с каждой очереди по одной конфетке
                candyQueues.values().forEach(candyQueue -> {

                    executorService.submit(new EatCandyTask(candyQueue));

                });

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // лог ошибки при паузе
                }
            }

        }).start();
    }

    @Override
    public void stopEating() {
        isEating = false;
    }

    private class EatCandyTask implements Runnable
    {
        private final CandyQueue candyQueue;

        EatCandyTask(CandyQueue candyQueue) {
            this.candyQueue = candyQueue;
        }

        public void run() {
            try {

                ICandy candy = candyQueue.poll();
                if (candy != null) {
                    ICandyEater candyEater = candyEatersQueue.take();
                    candyEater.eat(candy);
                    candyEatersQueue.put(candyEater);
                    candyQueue.unlock();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
