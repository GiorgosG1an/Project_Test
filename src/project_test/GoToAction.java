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
// Κλάση για μετακίνηση σε άλλο τετράγωνο

class GoToAction extends Action {
    //private int targetSquare;

    public GoToAction(int diceval) {
        this.diceval = diceval; // Set diceval 
    }

    public void setDiceVal(int diceval){
        this.diceval = diceval;
    }
    @Override
    void act(Refugee refugee) {
        // Implementation of the move action
        
        //refugee.setCurrentSquare(refugee.getCurrentSquare() + diceval); //this method have moved to RollDiceAction
        System.out.println(refugee.getName() + " is moving to "
                + "square " 
                + refugee.getCurrentSquare() );
    }
}
