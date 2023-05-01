package com.example.knightslabyrinth;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.knightslabyrinth.databinding.FragmentHomeScreenBinding;

public class HomeScreenFragment extends Fragment {
    static MediaPlayer mediaPlayer;
    MediaPlayer buttonClick;

    private FragmentHomeScreenBinding binding;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentHomeScreenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.menuscreen);
        buttonClick = MediaPlayer.create(getContext(), R.raw.buttonclick);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(0, 0.2f);
        if (SettingsFragment.getAudioSetting()) {
            mediaPlayer.start();
        }
        binding.buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                if (SettingsFragment.getAudioSetting()) {
                    buttonClick.start();
                }
                NavHostFragment.findNavController(HomeScreenFragment.this)
                        .navigate(R.id.action_play_button);
            }
        });
        binding.buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                if (SettingsFragment.getAudioSetting()) {
                    buttonClick.start();
                }
                NavHostFragment.findNavController(HomeScreenFragment.this)
                        .navigate(R.id.action_settings_button);
            }
        });
        binding.buttonManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                if (SettingsFragment.getAudioSetting()) {
                    buttonClick.start();
                }
                NavHostFragment.findNavController(HomeScreenFragment.this)
                        .navigate(R.id.action_manual_button);
            }
        });
        binding.buttonHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                if (SettingsFragment.getAudioSetting()) {
                    buttonClick.start();
                }
                NavHostFragment.findNavController(HomeScreenFragment.this)
                        .navigate(R.id.action_Home_HighScore);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}