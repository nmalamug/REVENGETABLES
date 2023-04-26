package com.example.knightslabyrinth;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;

import com.example.knightslabyrinth.databinding.FragmentGameScreenBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameScreenFragment extends Fragment {
    // Native methods
    public native String getNativeMessage();
    public native void getNewTick();
    public native long createMonster(float x, float y, float speed);
    public native void updateMonster(long monsterPtr, float deltaTime, float objectiveX, float objectiveY);
    public native float getMonsterX(long monsterPtr);
    public native float getMonsterY(long monsterPtr);

    // Binding and handler
    private FragmentGameScreenBinding binding;

    //Variables for handler in game ticks
    private Handler handler = new Handler();
    private Runnable runTicks;

    // Monster management
    private List<Long> monsterPtrs = new ArrayList<>();
    private Random random = new Random();

    //Code to start and end the game
    private void gameEnd(){
        //binding.knightWrapper.killKnight();
        //Make deleting things work - Memory leak??
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGameScreenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Code for updating game ticks
        //Tick time currently 20 ms
        runTicks = () -> {
            gameTick();
            handler.postDelayed(runTicks, 20);
        };
        handler.postDelayed(runTicks, 20);

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
        binding.knightWrapper.setOnTouchListener(new View.OnTouchListener() {
            private int mActivePointerId;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Joscie's mActive Pointer Stuff
                mActivePointerId = motionEvent.getPointerId(0);
                int pointerIndex = motionEvent.findPointerIndex(mActivePointerId);

                float x = motionEvent.getX(pointerIndex);
                float y = motionEvent.getY(pointerIndex);

                binding.knightWrapper.setTarget(x, y);
                return true;
            }
        });

        // Set reference to GameScreenFragment in MonsterView
        binding.monsterView.setGameScreenFragment(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        handler.removeCallbacks(runTicks);
        gameEnd();
    }

    public void gameTick() {
        getNewTick();
        binding.knightWrapper.moveKnight();

        // Spawn monsters randomly
        if (random.nextInt(100) < 5) { // 5% chance to spawn a monster each tick
            float x = random.nextFloat() * binding.monsterView.getWidth();
            float y = 0;
            float speed = random.nextFloat() * 10 + 5; // Random speed between 5 and 15
            long monsterPtr = createMonster(x, y, speed);
            monsterPtrs.add(monsterPtr);
        }

        // Update monsters
        float objectiveX = binding.monsterView.getWidth() / 2;
        float objectiveY = binding.monsterView.getHeight();
        for (long monsterPtr : monsterPtrs) {
            updateMonster(monsterPtr, 0.02f, objectiveX, objectiveY);
        }
        // Use getMonsterX(monsterPtr) and getMonsterY(monsterPtr) to get the updated monster positions
        // and update the monster positions in the UI

        // Update monster positions in the UI
        binding.monsterView.setMonsterPtrs(monsterPtrs);
    }

}
