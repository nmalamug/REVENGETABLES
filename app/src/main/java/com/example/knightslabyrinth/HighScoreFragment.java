package com.example.knightslabyrinth;

import android.graphics.Typeface;
import android.os.Build;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.knightslabyrinth.databinding.FragmentHighScoreBinding;

import java.util.List;

// HighScoreFragment displays the high scores of the game.
public class HighScoreFragment extends Fragment{
    private FragmentHighScoreBinding binding;
    private ScoreBoard scoreBoard;
    MediaPlayer buttonClick;


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
        buttonClick = MediaPlayer.create(getContext(), R.raw.buttonclick);
        scoreBoard = new ScoreBoard(getContext());
        displayHighScores();

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsFragment.getAudioSetting()) {
                    buttonClick.start();
                }
                NavHostFragment.findNavController(HighScoreFragment.this)
                        .navigate(R.id.action_HighScore_to_Home);
            }
        });
        buttonClick.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
            }
        });
    }

    private void displayHighScores() {
        List<String> highScores = scoreBoard.getHighScores();
        LinearLayout linearLayoutHighScores = binding.linearLayoutHighScores;
        Typeface type = ResourcesCompat.getFont(getContext(), R.font.press_start_2p);

        for (String highScore : highScores) {
            TextView textView = new TextView(getContext());
            textView.setText(highScore);
            textView.setTextSize(24);
            textView.setTypeface(type);
            linearLayoutHighScores.addView(textView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
