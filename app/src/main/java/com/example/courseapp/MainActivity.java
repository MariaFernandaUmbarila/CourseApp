package com.example.courseapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String[][] board = new String[3][3];
    boolean turn = false;
    String player = "", winner = "";
    int xCounter = 0, oCounter = 0;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBoard();
            }
        });
    }

    private void startBoard(){
        for (int row = 0; row <= 2; row++)
            for (int col = 0; col <= 2; col++)
                board[row][col] = "_";
    }

    private void playTurn(int row, int column){
        turn = !turn;
        player = turn ? "X" : "O";
        board[row][column] = player;
    }

    private void startButtons(){
        int buttonId;
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){

                buttonId = getResources().getIdentifier("button" + row + col, "id", getPackageName());
                Button selectedButton = (Button) findViewById(buttonId);
                selectedButton.setText("");
                selectedButton.setEnabled(true);

                startButton.setText("Start Game");
            }
        }
    }
}