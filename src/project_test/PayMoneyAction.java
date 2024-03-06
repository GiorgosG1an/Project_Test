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

public class PayMoneyAction extends Action {
    
    //private MoneyGiver payer;
    private Refugee payer;
    private ReceiverEntity payee;
    private int amount;
    
    public PayMoneyAction(Refugee payer, ReceiverEntity payee, int amount) {
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
    }
    
    @Override
    void act(Refugee refugee) {
        try {
            payer.giveMoney(amount);
            
            payer.setExpenses(payer.getExpenses() + amount);
            
            payee.receiveMoney(amount);

                System.out.println(payer.getName()+ " paid $" 
                + amount + " to " 
                + payee.getName());
                
        } catch (NoMoneyException e) {
            System.out.println(e.getMessage());
        }
    }
}
