package com.rudy.utilityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Tic_tac_toeActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button[] buttons = new Button[9];
    private Button resetGame;

    private int playerOneScoreCount, playerTwoScoreCount, roundCount;
    boolean activePlayer;

    //p1 => 0
    //p2 => 1
    //empty => 2
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //horizontal winner
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // vertical winner
            {0, 4, 8}, {2, 4, 6}  // cross winner
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        playerStatus = (TextView) findViewById(R.id.playerStatus);

        resetGame = (Button) findViewById(R.id.btnResetGame);

        //get all teh buttons initilize
        for (int i = 0; i < buttons.length; i++) {
            String buttonID = "btn_" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = (Button) findViewById(resourceID);
            buttons[i].setOnClickListener(this);

        }

        roundCount = 0;
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        activePlayer = true;



        Button btnHome = (Button) findViewById(R.id.btn_Home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        Log.i("Test","Button is Click");
        //if players tries to click a tile that has been picked
        if (!((Button) v).getText().toString().equals("")) {
            Toast.makeText(this,"Already Selected",Toast.LENGTH_SHORT).show();
            return;
        }
        String buttonID = v.getResources().getResourceEntryName(v.getId()); //get button id name
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length() - 1, buttonID.length())); // get the last indext of the id


        if (activePlayer) {
            ((Button) v).setText("X");
            ((Button) v).setTextColor(Color.parseColor("#FA0404"));
            //pointer get the position for the gameSte and makes that position 0 which is the player 1 position
            gameState[gameStatePointer] = 0;



        } else {
            ((Button) v).setText("0");
            ((Button) v).setTextColor(Color.parseColor("#FA0404"));
            //pointer get the position for the gameSte and makes that position 1 which is the player 2 position
            gameState[gameStatePointer] = 1;

        }

        roundCount++;

        if (checkWinner()){

            if (activePlayer){
                playerOneScoreCount++;
                updatePlayerScore();
                Toast.makeText(this,"Player One Won",Toast.LENGTH_SHORT).show();
                //tread to make the app not start right away but to wait 2 seconds


            }
            else {
                playerTwoScoreCount++;
                updatePlayerScore();
                Toast.makeText(this,"Player Two Won",Toast.LENGTH_SHORT).show();


            }
        }

        else if (roundCount == 9){
            // playAgain();
            //Toast.makeText(this,"No Winner",Toast.LENGTH_LONG).show();
            //make message text visible when no winner
            TextView noWinner = (TextView) findViewById(R.id.noWinner);
            noWinner.setVisibility(View.VISIBLE);
        }
        //switch between active and no active player
        else {
            activePlayer = !activePlayer;
        }

        if (playerOneScoreCount > playerTwoScoreCount){
            playerStatus.setText("Player One Is Winning");
        }
        else if (playerTwoScoreCount > playerOneScoreCount){
            playerStatus.setText("Player Two Is Winning");
        }
        else {
            playerStatus.setText("");
        }

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                playerStatus.setText("");
                updatePlayerScore();
            }
        });// end onClick

        Button b = findViewById(R.id.btnPlayAgain);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
            }
        });
    }//END ON CLICK LISTERNER

    public boolean checkWinner() {
        boolean winnerResult = false;

        //loops trough the wining position to find a match
        //checking whether the gameState is 0 for user 1 for user2 or 2 as default val
        for (int[] winningPosition : winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] != 2) {


                winnerResult = true;

            }//end if
        }// end for
        return winnerResult;
    }// end check winner

    public void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));

    }

    public void playAgain(){
        roundCount = 0;
        activePlayer = true;
        for(int i =0; i < buttons.length;i++){
            gameState[i] = 2;
            buttons[i].setText("");
        }
        TextView noWinner = (TextView) findViewById(R.id.noWinner);
        noWinner.setVisibility(View.INVISIBLE);

    }//endPlayAgain




}// END OF CLASS ACTIVITY