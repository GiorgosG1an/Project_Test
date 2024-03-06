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
public class IsDeadAction extends Action {

    @Override
    void act(Refugee refugee) {
        if (!refugee.isDead() && refugee.getCurrentSquare() == 10 && !refugee.hasExtraLife()) {
            // Player falls on square 10 without an extra life, mark as dead
            System.out.println(refugee.getName() + " is now dead!");
            refugee.setIsDead(true);
            //refugee.setStayed(true); // Optionally, set isStayed to true to indicate the player is no longer active
        }
    }
}
