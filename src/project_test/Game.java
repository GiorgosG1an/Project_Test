/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_test;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.Serializable;

/**
 *
 * @author Giorgos Giannopoulos
 * @author Ioannis Giannopoulos
 */
public class Game {
    // private Refugee _player;
    private List<Refugee> players;
    // private GiverEntity ngo_bank = new GiverEntity("NGO Bank", 10000);
    private GiverEntity ngo_bank;
    // private MoneyReceiver ngo_bank;
    private ReceiverEntity mafia_bank;
    private ReceiverEntity nobody;
    private Board gameBoard;

    private Square square;

    List<Integer> paySquares = Arrays.asList(1, 3, 6, 9, 13, 16, 21, 26, 37);
    List<Integer> rollSquares = Arrays.asList(2, 9, 12, 16, 17, 22, 28, 31, 33);
    List<Integer> staySquares = Arrays.asList(8, 11, 14, 19, 24, 27, 32, 34);
    List<Integer> goToSquares = Arrays.asList(4, 5, 15, 18, 23, 25, 29, 30, 33, 35, 38);
    List<Integer> receiveSquares = Arrays.asList(20);
    List<Integer> winSquares =  Arrays.asList(36,39);
    List<Integer> deadSquares = Arrays.asList(10);

    public void setNgoBank() {
        ngo_bank = new GiverEntity("NGO Bank", 10000);
    }

    public String getNgoBank() {
        return (ngo_bank.toString());
    }

    public void setMafiaBank() {
        mafia_bank = new ReceiverEntity("Mafia Bank", 0);
    }

    public String getMafiaBank() {
        return (mafia_bank.toString());
    }

    public void setNobodyReceives(){
        nobody = new ReceiverEntity("Nobody", 0);
    }

    

    public void setBoard() {
        gameBoard = new Board();
        try {
            File file = new File("src\\project_test\\refugeoly-squares.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                int number = Integer.parseInt(scanner.nextLine().trim());
                String text = scanner.nextLine().trim();
                String description = scanner.nextLine().trim();

                // Προσθέστε το τετράγωνο στο ταμπλό
                square = new Square(number, text, description, gameBoard);


                // switch based on square number add the actions to be performed by the player

                gameBoard.addSquare(square);

                /*
                  if (square.getNumber() == 7) {
                        System.out.println("DEBUG: Square number is 7");
                        ExtraLiveAction extraLiveAction = new ExtraLiveAction();
                        square.addAction(extraLiveAction);
                        //IsDeadAction dead = new IsDeadAction();
                        //square.addAction(dead);
                  
                    }
                 */

                // Διαβάστε την κενή γραμμή μεταξύ των τετραγώνων
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setPlayers(int numberOfPlayers) {
        if (numberOfPlayers < 1 || numberOfPlayers > 4) {
            System.out.println("Invalid number of players. Please choose a number between 1 and 4.");
            return;
        }

        players = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < numberOfPlayers; i++) {

            System.out.print("Enter name for player " + (i + 1) + ": ");
            String playerName = scanner.nextLine();

            Refugee player = new Refugee(playerName, 10000, 0, 0, false, false, false, false);
            players.add(player);

        }
        
        
    }

    public void playGame() {

        setNgoBank();
        System.out.println(getNgoBank());
        setMafiaBank();
        System.out.println(getMafiaBank());
        setNobodyReceives();
        setBoard();

        int numberOfPlayers;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Enter the number of players (1 - 4): ");
            numberOfPlayers = scanner.nextInt();

            if (numberOfPlayers < 1 || numberOfPlayers > 4) {
                System.out.println("Invalid number of players. Please choose a number between 1 and 4.");
            }
        } while (numberOfPlayers < 1 || numberOfPlayers > 4);
        scanner.nextLine(); //read the next line
        
        // Set up players
        setPlayers(numberOfPlayers);

        ExtraLiveAction extraLiveAction = new ExtraLiveAction();
        StayAction stay = new StayAction();

        int current_square = 0;
        int i = 0;
        int round;
        boolean play_again = true;
        // for square 26 where the user choose option B and stays in the same square for two rounds instead of 1 round that's the normal
        int doubleStay = 0; 
        String userInput;
        while (!isGameOver(numberOfPlayers)) {
            round = i + 1;
            System.out.println("\t\tRound " + round);
            for (Refugee player : players) {

                if (!player.isDead() && !player.getHasWon()) {
                    
                    System.out.println("\n" + player.getName() + "'s turn: ");
                    
                    if (!player.isStayed()) {
                        
                        System.out.println("Press r to roll the dice");
                        System.out.println("Press s to save the game");
                        System.out.println("Press l to load a game");
                        
                        System.out.println("Enter your input: ");
                        //Scanner scanner = new Scanner(System.in);
                        userInput = scanner.nextLine();
                        //System.out.println(userInput);
                        
                        if (userInput.equalsIgnoreCase("s")) {
                            
                            saveGame(); // Call the saveGame method
                            System.out.println("Game saved successfully!");
                            userInput = "r";
                            //continue; // Skip the rest of the player's turn
                        }

                        if (userInput.equalsIgnoreCase("l")) {
                            loadGame();  // Call the loadGame method
                            // Continue the game from the loaded state
                            
                            continue;
                        }

                        if(userInput.equalsIgnoreCase("r")){

                            // standard dice for the player turn
                            
                            RollDiceAction diceAction = new RollDiceAction();
                            
                            // go to square
                            
                           
                            // valid move, player is moving to the new square
                            
                               
                            diceAction.act(player);
                            if(!diceAction.isLegal()){
                                // the move is not legal, player exceed's square 39 so stay on the same square and break so he doesn't perform any other actions
                                break;
                            }
                            else{
                                //player move is legal
                                GoToAction goToAction = new GoToAction(diceAction.diceval);
                                goToAction.act(player);
                                play_again = true;
                            
                            
                                while(play_again){
                                    gameBoard.getSquare(player.getCurrentSquare()); // get text of the square and number
                                    square.clearActions();
                                    play_again = false; //set play_again false and set it true if player get's to roll the dice again
                                    current_square = player.getCurrentSquare();

                                    if (paySquares.contains(current_square)) {

                                        //handle the square 26 actions that have two options after paying the amount
                                        if(current_square == 26){
                                            PayMoneyAction payMoney = new PayMoneyAction(player, mafia_bank, 1000);
                                            square.addAction(payMoney);
                                            System.out.println("Choose between\nOption A: Pay $1500 to Mafia Bank and roll dice");
                                            System.out.println("Option B: Don’t pay and stay 2 turns");
                                            System.out.println("Enter A or B: ");
                                            //userInput = scanner.nextLine();

                                            boolean validInput = false;
                                            do {
                                                
                                                userInput = scanner.nextLine();
                                                
                                                if (userInput.equalsIgnoreCase("A")) {
                                                    // User chose option A
                                                    PayMoneyAction payMoney2 = new PayMoneyAction(player, mafia_bank, 1500);
                                                    square.addAction(payMoney2);
                                                    square.addAction(diceAction);
                                                    square.addAction(goToAction);
                                                    play_again = true; // So the player gets the new actions of the box
                                                    validInput = true; // Valid input, exit the loop
                                                } else if (userInput.equalsIgnoreCase("B")) {
                                                    // User chose option B
                                                    square.addAction(stay);
                                                    doubleStay = 1; //stay for 2 rounds
                                                    validInput = true; // Valid input, exit the loop
                                                } else {
                                                    // User entered something other than A or B
                                                    System.out.println("Invalid input. Please enter either A or B.\n");
                                                    
                                                }
                                            } while (!validInput);
                                        
                                        }
                                        // handle square 21 where you have lost 1500$ due to theft
                                        else if (current_square == 21) {
                                            PayMoneyAction payMoney = new PayMoneyAction(player, nobody, 1500);
                                            square.addAction(payMoney);
                                            
                                        }
                                        else if(current_square == 1){
                                            //the money goes to nobody, not in the Mafia Bank
                                            PayMoneyAction payMoney = new PayMoneyAction(player, nobody, 100);
                                            square.addAction(payMoney);
                                        }
                                        else{
                                            String textForPay = gameBoard.getText(current_square);

                                            // Finding the positions of "Pay" and "$" in the message
                                            int payIndex = textForPay.indexOf("Pay");
                                            int dollarIndex = textForPay.indexOf("$");
                                            //System.out.println("Pay index = " + payIndex);
                                            //System.out.println("Dollar index = " + dollarIndex);
                                            if (payIndex != -1 && dollarIndex != -1) {
                                                String amountStr = "0";

                                                // Check if the dollar sign appears after amount
                                                if (dollarIndex - payIndex > 4) {
                                                    // Extracting the amount substring
                                                    amountStr = textForPay.substring(payIndex + "Pay".length(),
                                                            dollarIndex).trim();
                                                } else {

                                                    int endIndex = dollarIndex + 4; // Assuming the maximum amount length is 4 digits

                                                    endIndex = Math.min(endIndex, textForPay.length()); // Ensure not to go beyond the
                                                                                                        // string length

                                                    // Extracting the amount substring when the dollar sign comes before amount
                                                    amountStr = textForPay.substring(dollarIndex + 1, endIndex).trim();

                                                }

                                                // Parsing the amount to an integer
                                                try {
                                                    int amount = Integer.parseInt(amountStr);

                                                    // Creating PayMoneyAction with the extracted amount
                                                    //System.out.println("Text for pay is " + textForPay);
                                                    PayMoneyAction payMoney = new PayMoneyAction(player, mafia_bank, amount);
                                                    square.addAction(payMoney);
                                                } catch (NumberFormatException e) {
                                                    // Handle the case where the amount is not a valid integer
                                                    System.out.println("Error: Unable to parse amount from the text");
                                                }
                                            } else {
                                                // Handle the case where "Pay" or "$" is not found in the expected positions
                                                System.out.println("Error: Amount not found in the text");
                                            }
                                        }
                                    }

                                    

                                    if (rollSquares.contains(current_square)) {
                                        
                                        square.addAction(diceAction);

                                        square.addAction(goToAction);
                                        play_again = true; //so the player get's the new actions of the box
                                    }
                                    if (staySquares.contains(current_square)) {

                                        square.addAction(stay);
                                    }

                                    if (goToSquares.contains(current_square)) {
                                        //manual go to square 5 if player is on square 15
                                        //Text in the refugeoly-squares.txt is : Border Control 2. Back to Border Control 1
                                        //It's missing the square number
                                        if (current_square == 15){ 
                                            player.setCurrentSquare(5);
                                                square.addAction(goToAction);
                                        }
                                        else{ //other squares with Go to square have in the text the destination square
                                            String textForGoTo = gameBoard.getText(current_square);

                                            // Finding the positions of the opening and closing parentheses
                                            int openParenthesisIndex = textForGoTo.indexOf("(");
                                            int closeParenthesisIndex = textForGoTo.indexOf(")");

                                            // System.out.println("Text for go to is: " + textForGoTo);

                                            if (openParenthesisIndex != -1 && closeParenthesisIndex != -1) {
                                                //System.out.println("openParenthesisIndex: " + openParenthesisIndex);
                                                //System.out.println("closeParenthesisIndex: " + closeParenthesisIndex);

                                                

                                                // Parsing the content to check if it's a number or a box number
                                                try {
                                                    if(closeParenthesisIndex - openParenthesisIndex < 4){
                                                        // Extracting the content inside the parentheses
                                                        String contentInsideParentheses = textForGoTo
                                                            .substring(openParenthesisIndex + 1, closeParenthesisIndex).trim();

                                                        //System.out.println("Content inside parentheses: " +
                                                        //    contentInsideParentheses);
                                                        int destination = Integer.parseInt(contentInsideParentheses);

                                                        // Setting the new position for the player
                                                        player.setCurrentSquare(destination);
                                                        square.addAction(goToAction);
                                                    }
                                                    else{
                                                        // the text inside the parenthesis is (box number) 
                                                        // so we get rid off box string (3) + 1 for the space 
                                                        int startIndex = openParenthesisIndex + 4;
                                                        // Extracting the content inside the parentheses
                                                        String contentInsideParentheses = textForGoTo
                                                            .substring(startIndex, closeParenthesisIndex).trim();

                                                        //System.out.println("Content inside parentheses: " +
                                                        //  contentInsideParentheses);

                                                        int destination = Integer.parseInt(contentInsideParentheses);
                                                        player.setCurrentSquare(destination);
                                                        square.addAction(goToAction);
                                                    }
                                                    
                                                } catch (NumberFormatException e) {
                                                    
                                                    // If parsing as an integer fails, treat it as a box number
                                                    System.out.println("Error parsing the integer in the parenthesis");
                                                    
                                                }
                                            } else {

                                                // Handle the case where "(" or ")" is not found in the expected positions
                                                System.out.println("Error: Destination number not found in the text");
                                            }
                                        }


                                    }

                                    if (receiveSquares.contains(current_square)) {
                                        ReceiveMoneyAction receiveMoney = new ReceiveMoneyAction(ngo_bank, player, 1000);
                                        square.addAction(receiveMoney);
                                    }

                                    if (current_square == 7) {

                                        square.addAction(extraLiveAction);
                                    }

                                    if (winSquares.contains(current_square)){
                                        System.out.println("You did it!!!\nYou won the game!!!");
                                        player.setHasWon(true);
                                        
                                    }

                                    if (deadSquares.contains(current_square)){
                                        if(player.hasExtraLife()){
                                            System.out.println(player.getName()+ " is saved because he has an extra life");
                                            player.setExtraLife(false);
                                        }
                                        else{
                                            
                                            IsDeadAction dead = new IsDeadAction();
                                            
                                            square.addAction(dead);

                                        }
                                    }

                                    square.act(player);
                                }
                            }
                            
                        }

                        System.out.println(player.getCurrentSquare());
                        System.out.println(player.toString());
                        System.out.println(getNgoBank());
                        System.out.println(getMafiaBank());
                        System.out.println("---------------------------------------------------------------");
                        
                    } 
                    else if  (player.isStayed()) {
                        System.out.println("Player " + player.getName()
                                + " has lost his turn in the previous round and so he is not playing right now");
                        // handle the square 26 option B when user stays for 2 turns in the same square
                        if(doubleStay <= 0){
                            player.setStayed(false); // set the player to play next round
                            doubleStay = 0;
                        }
                        else{
                            doubleStay -= 1;
                        }
                        
                        
                    }
                    
                }
            }

            ++i;
        }
        System.out.println("\n\n---------------------Game Finished---------------------");

        // Print the player status if he won or not
        for(Refugee player : players){
            if(player.getHasWon()){
                System.out.println("Player: " + player.getName() + " managed to survive and found a new home and spend " + player.getExpenses() + "$ in his journey");
            }
            else{
                System.out.println("Player: " + player.getName() + " didn't managed to survive");
            }
        }
        System.out.println("\nThank you for playing Refugeoly!!!");

        scanner.close();
    }

    public boolean isGameOver(int numberOfPlayers) {
        int i = 0;
        for(Refugee player : players){
            if(player.isDead() || player.getHasWon()){
                ++i;
            }
        } 
        if(i == numberOfPlayers){
            return true;
        }
        else{ 
            return false;
        }
    }


    public void saveGame() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("savedGame.ser"))) {
            // Serialize the game state
            GameData gameData = new GameData(players, ngo_bank.getMoney(), mafia_bank.getMoney(), gameBoard);
            outputStream.writeObject(gameData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadGame() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("savedGame.ser"))) {
            // Deserialize the game state
            GameData loadedGameData = (GameData) inputStream.readObject();

            // Update the game state based on the loaded data
            players = loadedGameData.getPlayers();
            ngo_bank.setMoney(loadedGameData.getNgoBank());
            mafia_bank.setMoney(loadedGameData.getMafiaBank());
            

            
            System.out.println("Game loaded successfully!");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
