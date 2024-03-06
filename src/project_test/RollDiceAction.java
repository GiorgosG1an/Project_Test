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

import java.util.Random;

public class RollDiceAction extends Action{
    private boolean checkMove = true;
    /**
     * if player exceeds square 39 return false otherwise return true
     */
    public boolean isLegal(){
        return checkMove;
    }

    public void setCheckMove(boolean checkMove){
        this.checkMove = checkMove;
    }

    @Override
    void act(Refugee refugee){
        Random random = new Random();
        int diceValue = random.nextInt(6) + 1;
        diceval = diceValue;
        System.out.println("Player " + refugee.getName() + 
                    " rolled a dice and got: " + 
                    diceval);
        System.out.println(refugee.getCurrentSquare() + diceval);
        if(refugee.getCurrentSquare() + diceval <= 39){
           
            if(refugee.getCurrentSquare() == 22){
                // in square 22 player goes back by the value of the dice
                refugee.setCurrentSquare(refugee.getCurrentSquare() - diceval);
            }
            else{
                refugee.setCurrentSquare(refugee.getCurrentSquare() + diceval);
            }
            
           
            setCheckMove(true);
        }
        else{
            System.out.println("Player passed square 39, he stays in his current square");
            
            setCheckMove(false);
            
        }
        
        
    }
}
