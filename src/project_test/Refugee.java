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
public class Refugee implements MoneyGiver, MoneyReceiver, Serializable{
    private String name;
    private int money;
    private int expenses;
    private int currentSquare;
    private boolean hasExtraLife;
    private boolean isStayed;
    private boolean isDead;
    private boolean hasWon;

    //constructor
    public Refugee(String name, int money, int expenses, int currentSquare, boolean hasExtraLife, boolean isStayed, boolean isDead, boolean hasWon){
        this.name = name;
        this.money = money;
        this.expenses = expenses;
        this.currentSquare = currentSquare;
        this.hasExtraLife = hasExtraLife;
        this.isStayed = isStayed;
        this.isDead = isDead;
        this.hasWon = hasWon;
    }
    
    //set and get money
    public void setMoney(int money){
        this.money = money;
    }
    
    public int getMoney(){
        return money;
    }
    
    //get name
    public String getName(){
        return name;
    }
    
    //set and get expenses
    public void setExpenses(int expenses){
        this.expenses = expenses;
    }
    
    public int getExpenses(){
        return(expenses);
    }
    
    public int getCurrentSquare(){
        return currentSquare;
    }
    
    public void setCurrentSquare(int currentSquare){
        this.currentSquare = currentSquare;
    }
    
    // Getter and setter for extra life
    public boolean hasExtraLife() {
        return hasExtraLife;
    }

    public void setExtraLife(boolean hasExtraLife) {
        this.hasExtraLife = hasExtraLife;
    }

    //stay in the same square for a round
    public boolean isStayed() {
        return isStayed;
    }

    public void setStayed(boolean isStayed) {
        this.isStayed = isStayed;
    }
   
    public boolean isDead(){
        return isDead;
    }

    public void setIsDead(boolean isDead){
        this.isDead = isDead;
    }

    public void setHasWon(boolean hasWon){
        this.hasWon = hasWon;
    }

    public boolean getHasWon(){
        return hasWon;
    }


    @Override
    public String toString(){
        return("Player: "+ getName()+ " has " + getMoney()+ "$\n"
                + "His total expenses are " + getExpenses() + "$"
                + " and has extra life: " + hasExtraLife()) ;
    }

    @Override
    public void giveMoney(int amount) throws NoMoneyException {
        if (money >= amount) {
            money -= amount;
        } 
        else {
            setIsDead(true);
            throw new NoMoneyException("Not enough money to give.\n"
                    + "You are out of the game!!!");

        }
    }

    @Override
    public void receiveMoney(int amount) {
         money += amount;
    }
    
}
