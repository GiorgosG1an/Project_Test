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
import java.io.Serializable;

public class Board implements Serializable{
    private ArrayList<Square> squares;
    
    public Board() {
        this.squares = new ArrayList<>();
    }
    
    public void addSquare(Square square) {
        squares.add(square);
    }

    public String getText(int number){ //method for getting the amount to pay
        for (Square square : squares) {
            if (square.getNumber() == number) {
                
                //System.out.println("Square text: "+square.getText());
                return square.getText();
            }
        }
        return "null";
    }
    
    public void getSquare(int number) {
        for (Square square : squares) {
            if (square.getNumber() == number) {
                System.out.println("Square Number: "+square.getNumber());
                System.out.println("Square text: "+square.getText());
                //return square;
            }
        }
        //return null;
    }
}
