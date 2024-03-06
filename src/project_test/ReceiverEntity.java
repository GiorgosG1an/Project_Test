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
import java.io.Serializable;
public class ReceiverEntity implements MoneyReceiver, Serializable {
    private String name;
    private int money;
    
    //constructor
    public ReceiverEntity(String name, int money){
        this.name = name;
        this.money = money;
    }
    
    public String getName(){
        return name;
    }
    
    public int getMoney(){
        return money;
    }
    
    public void setMoney(int money){
        this.money = money;
    }
   
    
    @Override
    public void receiveMoney(int amount){
        money += amount;
    }
    
    @Override
    public String toString(){
        return (getName() + " has " + getMoney() +
                "$");
    }
    
    
}
