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
public class StayAction extends Action {
    


    @Override
    void act(Refugee refugee) {
        if(!refugee.isStayed()){
            
            refugee.setStayed(true);
            System.out.println(refugee.getName()
            + " is staying on the current square" 
            + " for a turn");
        }
        else{
            refugee.setStayed(false);
            

        }
    }

    
}
