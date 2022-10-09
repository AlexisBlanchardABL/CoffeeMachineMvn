package org.co;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MakerImplTest {


    @Test
    void transformer() {
        Maker maker = new MakerImpl();
        //Drink maker will make one orange juice
        Order juice = new Order(Drink.JUICE, 0, 1d, false);
        //Drink maker will make an extra hot coffee with no sugar
        Order hotCoffee = new Order(Drink.COFFEE, 0, 1d, true);
        //Drink maker will make an extra hot chocolate with one sugar and a stick
        Order hotChocolate = new Order(Drink.CHOCOLATE,1, 2d, true );
        //The drink maker will make an extra hot tea with two sugar and a stick
        Order hotThea = new Order(Drink.THE, 2, 1d, true);
        Order Thea = new Order(Drink.THE, 2, 1d, false);


        assertEquals(maker.transformer(juice), "O::");
        assertEquals(maker.transformer(hotCoffee), "Ch::");
        assertEquals(maker.transformer(hotChocolate), "Hh:1:0");
        assertEquals(maker.transformer(hotThea), "Th:2:0");
        assertEquals(maker.transformer(Thea), "T:2:0");

        assertEquals(maker.getAmountReport().size(), 4);
        assertEquals(maker.getAmountReport().get(Drink.JUICE.getType()),1);
        assertEquals(maker.getAmountReport().get(Drink.COFFEE.getType()),1);
        assertEquals(maker.getAmountReport().get(Drink.CHOCOLATE.getType()),2);
        assertEquals(maker.getAmountReport().get(Drink.THE.getType()),2);

    }
}