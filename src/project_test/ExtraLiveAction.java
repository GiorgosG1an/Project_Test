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
public class ExtraLiveAction extends Action {

    private boolean extraLive;

    public ExtraLiveAction() {
        this.extraLive = false;
    }

    @Override
    void act(Refugee refugee) {
        if (!refugee.hasExtraLife() && refugee.getCurrentSquare() == 7) {
        
            // Perform action to give an extra life

            refugee.setExtraLife(true);
            //receivedExtraLife = true;
            System.out.println(refugee.getName()
                    + " gained an extra life");
        }
    }

    // Reset the flag when needed
    //public void reset() {
     //   receivedExtraLife = false;
    //}

}
