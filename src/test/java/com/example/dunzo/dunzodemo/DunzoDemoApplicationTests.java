package com.example.dunzo.dunzodemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

class DunzoDemoApplicationTests {
    public CoffeeMachine coffeeMachine;

    @Test
    public void shouldFailWithEmptyInventory() throws Exception{
        coffeeMachine = new CoffeeMachine(2);
        CompletableFuture<Boolean> order1 = coffeeMachine.makeDrink(new Latte());
        CompletableFuture<Boolean> order2 = coffeeMachine.makeDrink(new Latte());

        Assertions.assertEquals(false,order1.get());
        Assertions.assertEquals(false,order2.get());

    }

    @Test
    public void shouldPassWithFullInventoryForLatte() throws Exception{
        coffeeMachine = new CoffeeMachine(2);
        coffeeMachine.refillMachine(TestUtil.getFullInventoryForLatte());
        CompletableFuture<Boolean> order1 = coffeeMachine.makeDrink(new Latte());
        CompletableFuture<Boolean> order2 = coffeeMachine.makeDrink(new Latte());

        Assertions.assertEquals(true,order1.get());
        Assertions.assertEquals(true,order2.get());

    }

    @Test
    public void shouldRejectOneOrderWithFullInventory_DueToOnly2Ports() throws Exception{
        coffeeMachine = new CoffeeMachine(2);
        coffeeMachine.refillMachine(TestUtil.getFullInventory());
        CompletableFuture<Boolean> order1 = coffeeMachine.makeDrink(new Latte());
        CompletableFuture<Boolean> order2 = coffeeMachine.makeDrink(new LemonTea());
        Thread.sleep(1000);
        CompletableFuture<Boolean> order3 = coffeeMachine.makeDrink(new GreenTea());

        List<Boolean> responses = Arrays.asList(order1.get(), order2.get(), order3.get());
        List<Boolean> falseList = responses.stream().filter(b -> b == false).collect(Collectors.toList());
        Assertions.assertEquals(1,falseList.size());
    }

    @Test
    public void shouldNotRejectWithFullInventory_DueToOnly3Ports() throws Exception{
        coffeeMachine = new CoffeeMachine(3);
        coffeeMachine.refillMachine(TestUtil.getFullInventory());
        CompletableFuture<Boolean> order1 = coffeeMachine.makeDrink(new Latte());
        CompletableFuture<Boolean> order2 = coffeeMachine.makeDrink(new LemonTea());
        CompletableFuture<Boolean> order3 = coffeeMachine.makeDrink(new GreenTea());

        List<Boolean> responses = Arrays.asList(order1.get(), order2.get(), order3.get());
        List<Boolean> falseList = responses.stream().filter(b -> b == false).collect(Collectors.toList());
        Assertions.assertEquals(0,falseList.size());
    }

    @Test
    public void shouldFailDueToMissingIngredient() throws Exception{
        coffeeMachine = new CoffeeMachine(2);
        coffeeMachine.refillMachine(TestUtil.getFullInventoryForLatte());
        CompletableFuture<Boolean> order1 = coffeeMachine.makeDrink(new Latte());
        CompletableFuture<Boolean> order2 = coffeeMachine.makeDrink(new GreenTea());

        Assertions.assertEquals(true,order1.get());
        Assertions.assertEquals(false,order2.get());

    }

    @Test
    public void shouldHaveOneFailureWithHalfInventory() throws Exception{
        coffeeMachine = new CoffeeMachine(2);
        coffeeMachine.refillMachine(TestUtil.getHalfInventoryForLatte());
        CompletableFuture<Boolean> order1 = coffeeMachine.makeDrink(new Latte());
        CompletableFuture<Boolean> order2 = coffeeMachine.makeDrink(new Latte());

        List<Boolean> responses = Arrays.asList(order1.get(), order2.get());
        List<Boolean> falseList = responses.stream().filter(b -> b == false).collect(Collectors.toList());
        Assertions.assertEquals(falseList.size(),1);
    }

    @Test
    public void shouldRejectTheSecondOrderSpecifically() throws Exception{
        coffeeMachine = new CoffeeMachine(2);
        coffeeMachine.refillMachine(TestUtil.getHalfInventoryForLatte());
        CompletableFuture<Boolean> order1 = coffeeMachine.makeDrink(new Latte());
        boolean response1 = order1.get();
        CompletableFuture<Boolean> order2 = coffeeMachine.makeDrink(new Latte());
        boolean response2 = order2.get();
        Assertions.assertEquals(true,response1);
        Assertions.assertEquals(false,response2);
    }

    @Test
    public void shouldReturnAllIngredientsInLowQuantityList() throws Exception{
        coffeeMachine = new CoffeeMachine(2);
        List<String> lowQuantityList = coffeeMachine.getLowQuantityIngredients();
        Assertions.assertEquals(CoffeeMachine.ALL_INGREDIENTS_LIST.size(),lowQuantityList.size());
    }

    @Test
    public void shouldReturn2IngredientsInLowQuantityList() throws Exception{
        coffeeMachine = new CoffeeMachine(2);
        coffeeMachine.refillMachine(TestUtil.getFullInventoryForLatte());
        List<String> lowQuantityList = coffeeMachine.getLowQuantityIngredients();
        Assertions.assertEquals(2,lowQuantityList.size());
    }

}
