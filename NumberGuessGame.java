//Task 2 - Number Guessing Game - by Jash Thakkar

//import necessary packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NumberGuessGame extends JFrame{ //Main class

    private JLabel promptLabel;
    private JLabel RoundLabel;
    private JTextField inputField;
    private JButton guessButton;
    private int randomNumber;
    private int attemptsLeft;
    private int round;
    private int totalScore;

    public NumberGuessGame(){
        setTitle("Number Guessing Game");//Title of Frame
        setSize(250, 200);//Size of Frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Close operation
        setLayout(new FlowLayout());//Layout

        promptLabel=new JLabel("Enter your guess: ");
        RoundLabel=new JLabel();
        inputField=new JTextField(10);
        guessButton=new JButton("Guess");

        //Adding Label, Textfield, Button to JFrame
        add(RoundLabel);
        add(promptLabel);
        add(inputField);
        add(guessButton);

        startNewRound();//Method to start new round
        
        guessButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                checkGuess();//Method to check if the guess is correct
            }
        });
    }

    void startNewRound(){ //Method to start new round
        attemptsLeft=5; //number of attempts for each round; can be changed as per requirement.
        round++;
        randomNumber=(int)(Math.random()*100)+1; //Generates a Random Number between 1 and 100 using Math.random()
        RoundLabel.setText("Round " + round + " - Attempts left: " + attemptsLeft);
    }

    void checkGuess(){
        String input = inputField.getText();//Getting input from user and converting it into String
        try{
            int guess = Integer.parseInt(input);
            attemptsLeft--;

            if(guess==randomNumber){
                int Score= attemptsLeft>0 ? attemptsLeft*10 : 10; //points given based on the number of attempts
                totalScore += Score;
                JOptionPane.showMessageDialog(this,"Congrats! You guessed the correct number.\n Round Score: " + Score + " Total Score: " + totalScore);
                if(round<3){ //allows 3 rounds; can be changed as per requirement.
                    startNewRound();
                }
                else{
                    JOptionPane.showMessageDialog(this,"Game Over. Total Score: " + totalScore);//Optionpane to show message
                    System.exit(0);
                }
            }
            else if(attemptsLeft>0){//if attempts are left
                RoundLabel.setText("Round " + round + " - Attempts left: " + attemptsLeft + "\n");
                JOptionPane.showMessageDialog(this,guess<randomNumber ? "Try a higher number." : "Try a lower number.");
            }
            else{
                JOptionPane.showMessageDialog(this,"You are out of attempts.\n The correct number was " + randomNumber + ". Moving to the next round.");
                if(round<3){ //allows 3 rounds; can be changed as per requirement.
                    startNewRound();
                }
                else{
                    JOptionPane.showMessageDialog(this,"Game Over. Total Score: " + totalScore);
                    System.exit(0);
                }
            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Please enter a valid number.");
        }
        inputField.setText("");//Used to clear any previous input to avoid input conflict
    }

    public static void main(String[] args) {
            NumberGuessGame game = new NumberGuessGame();//Objection creation
            game.setVisible(true);//Visibility of JFrame
    }
}
