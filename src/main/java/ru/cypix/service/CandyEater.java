package ru.cypix.service;

import ru.cypix.domain.candy.base.ICandy;

import java.util.Random;

public class CandyEater implements ICandyEater {

    public void eat(ICandy candy) throws Exception {
        System.out.println("Thread [" + Thread.currentThread().getName() + "] eating candy = [" + candy + "]");
        Random rn = new Random();
        Thread.sleep(rn.nextInt(10) + 5);
        System.out.println("Thread [" + Thread.currentThread().getName() + "] candy eaten = [" + candy + "]");
    }
}
