/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_test;

/**
 *
 * @author Giorgos Giannopoulos
 * @author Ioannis Giannopoulos
 */
public class ReceiveMoneyAction extends Action {
    private GiverEntity giver;
    private Refugee receiver;
    private int amount;

    // Constructor
    public ReceiveMoneyAction(GiverEntity giver, Refugee receiver, int amount) {
        this.giver = giver;
        this.receiver = receiver;
        this.amount = amount;
    }

    @Override
    void act(Refugee refugee) {
        // Perform the receive money action
        try {
            giver.giveMoney(amount); // The giver gives money
            receiver.receiveMoney(amount); // The receiver receives money
            System.out.println(receiver.getName() + " received " + amount + "$ from " + giver.getName());
        } catch (NoMoneyException e) {
            System.out.println(e.getMessage());
        }
    }
}
