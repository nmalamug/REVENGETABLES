package com.example.knightslabyrinth;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.knightslabyrinth.databinding.FragmentHighScoreBinding;

import java.util.List;

public class HighScoreFragment extends Fragment{
    private FragmentHighScoreBinding binding;
    private ScoreBoard scoreBoard;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentHighScoreBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        scoreBoard = new ScoreBoard(getContext());
        displayHighScores();

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HighScoreFragment.this)
                        .navigate(R.id.action_HighScore_to_Home);
            }
        });
    }

    private void displayHighScores() {
        List<String> highScores = scoreBoard.getHighScores();
        LinearLayout linearLayoutHighScores = binding.linearLayoutHighScores;

        for (String highScore : highScores) {
            TextView textView = new TextView(getContext());
            textView.setText(highScore);
            textView.setTextSize(24);
            linearLayoutHighScores.addView(textView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
