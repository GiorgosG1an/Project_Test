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

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Square implements Serializable{
    private int number;
    private String text;
    private String description;
    private Board board;
    private List<Action> actions;
    private Refugee currentPlayer;
    
    public Square(int number, String text, String description, Board board){
        this.number = number;
        this.text = text;
        this.description = description;
        this.board = board;
        actions = new ArrayList<>();
    }
    
    public int getNumber(){
        return number;
    }
    
    public String getText(){
        return text;
    }
    
    public String getDescription(){
        return description;
    }
    
    public Board getBoard() {
        return board;
    }
    
    public void addAction(Action action) {
        actions.add(action);
    }

    public void clearActions() {
        actions.clear();
    }
    
    public void setCurrentPlayer(Refugee player) {
        this.currentPlayer = player;
    }
    
    public void act(Refugee player) {
        
        
            //System.out.println("Actions applied for Square " + number);
            for (Action action : actions) {

                System.out.println("performing actions for player: " + player.getName());
                action.act(player); 
        }
    }
    
}
