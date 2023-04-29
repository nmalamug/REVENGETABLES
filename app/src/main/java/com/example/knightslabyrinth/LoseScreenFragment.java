package com.example.knightslabyrinth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;
public class LoseScreenFragment extends Fragment {


    private TextView textView2;
    private Button buttonMenu;
    private Button buttonGame;
    private LinearLayout linearLayoutHighScores;
    private ScoreBoard scoreBoard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lose_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView2 = view.findViewById(R.id.textView2);
        buttonMenu = view.findViewById(R.id.buttonMenu);
        buttonGame = view.findViewById(R.id.buttonGame);
        linearLayoutHighScores = view.findViewById(R.id.linearLayoutHighScores);

        scoreBoard = new ScoreBoard(getContext());
        // Save the current game's score
        int currentScore = MainActivity.settings.getLastScore();
        scoreBoard.saveScore("Player", currentScore);

        displayHighScores();

        textView2.setText("Current Game Score: " + String.valueOf(currentScore));
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoseScreenFragment.this)
                        .navigate(R.id.action_Lose_screen_to_Home);
            }
        });
        buttonGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoseScreenFragment.this)
                        .navigate(R.id.action_Lose_screen_to_Game);
            }
        });
    }

    private void displayHighScores() {
        List<String> highScores = scoreBoard.getHighScores();
        StringBuilder highScoresText = new StringBuilder("High Scores:\n");

        for (String highScore : highScores) {
            highScoresText.append(highScore).append("\n");
        }

        TextView textView = new TextView(getContext());
        textView.setText(highScoresText.toString());
        textView.setTextSize(24);
        linearLayoutHighScores.addView(textView);
    }

}
