package ru.cypix.service;

import ru.cypix.domain.candy.base.ICandy;

/**
 * Интерфейс пожирателя конфет, ест любые конфеты, но так как захочет.
 */
public interface ICandyEater {

    /**
     * Съесть конфету, может занять время
     * @param candy - конфета
     */
    void eat(ICandy candy) throws Exception;
}
