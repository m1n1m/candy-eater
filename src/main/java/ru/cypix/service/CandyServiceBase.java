package ru.cypix.service;

import ru.cypix.domain.candy.base.ICandy;

/**
 * Сервис пожирания конфет, требует реализации
 */
public abstract class CandyServiceBase {

    /**
     * Сервис получает при инициализации массив доступных пожирателей конфет
     * @param candyEaters
     */
    public CandyServiceBase(ICandyEater[] candyEaters) {
    }

    /**
     * Добавить конфету на съедение
     * @param candy
     */
    public abstract void addCandy(ICandy candy);
    public abstract void startEating();
    public abstract void stopEating();
}