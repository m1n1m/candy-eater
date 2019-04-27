package ru.cypix.service;

import org.junit.Before;
import org.junit.Test;
import ru.cypix.domain.candy.CherryCandy;
import ru.cypix.domain.candy.MintCandy;
import ru.cypix.domain.candy.OrangeCandy;

public class CandyServiceTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void addCandy() {

        ICandyEater[] candyEaters = new CandyEater[4];
        candyEaters[0] = new CandyEater();
        candyEaters[1] = new CandyEater();
        candyEaters[2] = new CandyEater();
        candyEaters[3] = new CandyEater();

        CandyService service = new CandyService(candyEaters);
        service.addCandy(new CherryCandy());
        service.startEating();
        service.addCandy(new MintCandy());
        service.addCandy(new OrangeCandy());

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}