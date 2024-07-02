package com.example.courseapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    //Logical grid for the game
    String[][] board = new String[3][3];
    //X for true, O for false
    boolean turn = false;
    String player = "", winner = "";
    //Counters for the quantity of Xs and Os
    int xCounter = 0, oCounter = 0;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> {
            startBoard();
            startButtons();
        });
    }

    //Fill the board matrix with _
    private void startBoard(){
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                board[row][col] = "_";
    }

    //Change the player in each turn
    private void playTurn(int row, int column){
        turn = !turn;
        player = turn ? "X" : "O";
        board[row][column] = player;
    }

    private void startButtons(){
        int buttonId;
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){

                //Look for every button named after its position on the matrix
                buttonId = getResources().getIdentifier("button" + row + col, "id", getPackageName());
                Button selectedButton = findViewById(buttonId);
                selectedButton.setText("");
                selectedButton.setEnabled(true);

                startButton.setText(R.string.startGameButton);
                turn = false;
                winner = "";
                xCounter = 0;
                oCounter = 0;

                final int r = row;
                final int c = col;

                selectedButton.setOnClickListener(v -> {
                    if (selectedButton.getText().toString().isEmpty() && winner.isEmpty()){
                        playTurn(r, c);
                        selectedButton.setText(player);
                        startButton.setText(R.string.restartGameButton);
                        lookForWinner();
                    }
                });
            }
        }
    }

    private void lookForWinner(){

        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++)
                checkMove(row, col);
            if(!winner.isEmpty()) break;
            xCounter = 0;
            oCounter = 0;
        }

        if (winner.isEmpty()){
            for (int col = 0; col < 3; col++){
                for (int row = 0; row < 3; row++)
                    checkMove(row, col);
                if(!winner.isEmpty()) break;
                xCounter = 0;
                oCounter = 0;
            }
        }

        if(winner.isEmpty()){
            int row;
            for (int col = 0; col < 3; col++){
                row = col;
                checkMove(row, col);
            }
            xCounter = 0;
            oCounter = 0;
        }

        if(!winner.isEmpty()){
            Toast.makeText(getApplicationContext(), winner + " wins", Toast.LENGTH_LONG).show();
        }

    }

    private void checkMove(int row, int col){
        String value = board[row][col];

        if(!value.isEmpty()){
            if(value.equals("X")) xCounter++;
            if(value.equals("O")) oCounter++;
        }

        if (xCounter == 3) winner = "X";
        if (oCounter == 3) winner = "O";
    }
}