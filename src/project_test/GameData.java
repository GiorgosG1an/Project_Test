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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameData implements Serializable {
    
    private List<Refugee> players;
    private int ngoBank;
    private int mafiaBank;
    private Board gameBoard;

    /**
     * 
     * @param players in a list
     * @param ngoBank money
     * @param mafiaBank money
     * @param gameBoard board
     */
    public GameData(List<Refugee> players, int ngoBank, int mafiaBank, Board gameBoard) {
        this.players = players;
        this.ngoBank = ngoBank;
        this.mafiaBank = mafiaBank;
        this.gameBoard = gameBoard;
    }

    public List<Refugee> getPlayers() {
        return players;
    }

    public int getNgoBank() {
        return ngoBank;
    }

    public int getMafiaBank() {
        return mafiaBank;
    }

    public Board getGameBoard() {
        return gameBoard;
    }
}

