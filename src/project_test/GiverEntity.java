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
public class GiverEntity implements MoneyGiver, Serializable{
    private String name;
    private int money;
    
    public GiverEntity(String name, int money){
        this.name = name;
        this.money = money;
    }
    
    public void setMoney(int money){
        this.money = money;
    }
    
    public int getMoney(){
        return money;
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public String toString(){
        return(getName()+ " has " + getMoney() + "$");
    }
    
    @Override
    public void giveMoney(int amount) throws NoMoneyException {
        if (money >= amount) {
            money -= amount;
        } 
        else {
            throw new NoMoneyException("Not enough money to give.\n"
                    + "You are out of the game!!!");
            
            
        }
    }
}
