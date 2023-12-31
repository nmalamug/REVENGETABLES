package com.example.knightslabyrinth;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.media.AudioManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.knightslabyrinth.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment  {

    private FragmentSettingsBinding binding;
    private static boolean AudioSetting = true;
    MediaPlayer buttonClick;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSettingsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonClick = MediaPlayer.create(getContext(), R.raw.buttonclick);
        if (MainActivity.settings.getKnight() == 1) {
            binding.radioDasher.setChecked(true);
        } else if (MainActivity.settings.getKnight() == 2) {
            binding.radioBomber.setChecked(true);
        }
        if (MainActivity.settings.getDifficulty() == 0) {
            binding.radioEasy.setChecked(true);
        } else if (MainActivity.settings.getDifficulty() == 1) {
            binding.radioMedium.setChecked(true);
        } else if (MainActivity.settings.getDifficulty() == 2) {
            binding.radioHard.setChecked(true);
        }
        if (AudioSetting) {
            binding.radioSoundOn.setChecked(true);
        } else {
            binding.radioSoundOff.setChecked(true);
        }
        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsFragment.getAudioSetting()) {
                    buttonClick.start();
                }
                NavHostFragment.findNavController(SettingsFragment.this)
                        .navigate(R.id.action_Settings_to_HomeScreenFragment);
            }
        });
        RadioGroup radioGroup = (RadioGroup) binding.knightSettings;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if (binding.radioDasher.isChecked()) {
                    MainActivity.settings.setKnight(1);
                } else if (binding.radioBomber.isChecked()) {
                    MainActivity.settings.setKnight(2);
                }
            }
        });

        RadioGroup radioGroupDiff = (RadioGroup) binding.diffSettings;
        radioGroupDiff.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if (binding.radioEasy.isChecked()) {
                    MainActivity.settings.setDifficulty(0);
                } else if (binding.radioMedium.isChecked()) {
                    MainActivity.settings.setDifficulty(1);
                } else if (binding.radioHard.isChecked()) {
                    MainActivity.settings.setDifficulty(2);
                }
            }
        });

        RadioGroup radioGroupAudio = (RadioGroup) binding.audioSettings;
        radioGroupAudio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if (binding.radioSoundOn.isChecked()) {
                    AudioSetting = true;
                } else if (binding.radioSoundOff.isChecked()) {
                    AudioSetting = false;
                }
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
    public static boolean getAudioSetting() {
        return AudioSetting;
    }
    public static void setAudioSetting(boolean bool) {
        AudioSetting = bool;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

