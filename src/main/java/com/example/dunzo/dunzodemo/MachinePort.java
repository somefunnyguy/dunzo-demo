package com.example.dunzo.dunzodemo;

public class MachinePort extends Thread {
    private MachinePortDomainType STATE = MachinePortDomainType.READY;

    @Override
    public void run() {
        System.out.println("Running Port, CurrentState: " + this.getState());
    }

    public MachinePortDomainType getSTATE() {
        return STATE;
    }

    public void setSTATE(MachinePortDomainType STATE) {
        this.STATE = STATE;
    }

    public boolean makeDrink(Drink drink, Inventory inventory) {
        this.setSTATE(MachinePortDomainType.WORKING);
        boolean b = inventory.extractIngredients(drink.DRINK_NAME,drink.REQUIREMENTS);
        this.setSTATE(MachinePortDomainType.READY);
        return b;
    }
}
