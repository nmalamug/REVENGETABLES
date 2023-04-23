package com.example.knightslabyrinth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.knightslabyrinth.databinding.FragmentGameScreenBinding;

public class GameScreenFragment extends Fragment {
    public native String getNativeMessage();
    private FragmentGameScreenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGameScreenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(GameScreenFragment.this)
                        .navigate(R.id.action_GameScreenFragment_to_HomeScreenFragment);
            }
        });
        String nativeMessage = getNativeMessage();
        binding.textView.setText(nativeMessage);

        // Set up touch listener for the KnightView
        binding.knightView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();

                    // Move the knight to the touched position
                    binding.knightView.moveKnight(x, y);
                }
                return true;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
