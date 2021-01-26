package com.example.dunzo.dunzodemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CoffeeMachine {
    public final int N = 2;
    public final ArrayList<MachinePort> MACHINE_PORTS = new ArrayList<MachinePort>();
    public final Inventory INVENTORY = new Inventory();
    public final static List<String> ALL_INGREDIENTS_LIST = Arrays.asList("milk", "water", "latte_powder", "sugar", "lemon_tea_powder", "green_tea_powder");
    public static final int LOW_QUANTITY_THRESHOLD = 5;

    public CoffeeMachine() {
        for (int i = 0; i < N; i++) {
            MachinePort machinePort = new MachinePort();
            MACHINE_PORTS.add(machinePort);
            machinePort.start();
        }
    }

    public CoffeeMachine(int n) {
        for (int i = 0; i < n; i++) {
            MachinePort machinePort = new MachinePort();
            MACHINE_PORTS.add(machinePort);
            machinePort.start();
        }
    }

    public void refillMachine(List<Ingredient> ingredientList) {
        ingredientList.stream().forEach(ingredient -> {
            int curr = this.INVENTORY.map.containsKey(ingredient.getName()) ? this.INVENTORY.map.get(ingredient.getName()) : 0;
            this.INVENTORY.map.put(ingredient.getName(), curr + ingredient.getQuantity());
        });
    }

    /*
        To find out of stock ingredients
     */
    public List<String> getLowQuantityIngredients() {
        return CoffeeMachine.ALL_INGREDIENTS_LIST.stream().filter(s -> {
            if (!this.INVENTORY.map.containsKey(s)) {
                return true;
            } else {
                return this.INVENTORY.map.get(s) < LOW_QUANTITY_THRESHOLD;
            }
        }).collect(Collectors.toList());
    }

    public CompletableFuture<Boolean> makeDrink(Drink drink) {
        synchronized (this) {
            CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
                boolean found = false;
                for (MachinePort port : MACHINE_PORTS) {
                    if (port.getSTATE() == MachinePortDomainType.READY) {
                        boolean resp = port.makeDrink(drink, INVENTORY);
                        found = resp;
                        break;
                    }
                }
                return found;
            });
            return future;
        }
    }

    public static void main(String[] args) throws Exception {
        CoffeeMachine coffeeMachine = new CoffeeMachine(2);
        Latte latte = new Latte();
        coffeeMachine.refillMachine(Util.getFullInventory());
        CompletableFuture<Boolean> future1 = coffeeMachine.makeDrink(latte);
        CompletableFuture<Boolean> future2 = coffeeMachine.makeDrink(latte);
        System.out.println("---------------Orders given------------");
        Thread.sleep(2000);
        Boolean a = future1.get();
        Boolean b = future2.get();
        System.out.println(a);
        System.out.println(b);
    }
}

