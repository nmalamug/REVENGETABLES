package com.example.knightslabyrinth;
import android.graphics.PointF;
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

public class GameScreenFragment extends Fragment {
    // Native methods
    public native String getNativeMessage();
    public native void getNewTick();


    // Binding and handler
    private FragmentGameScreenBinding binding;

    //Variables for handler in game ticks
    private Handler handler = new Handler();
    private Runnable runTicks;

    //Knight and monster management
    private PointF knightPosition;
    private int knightRadius;
    private float knightSpeed;
    private int noLives;

    private int maxLives = 7;
    private int currLives = maxLives;// changes based on difficulty
    // private int lives = maxLives - noLives;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGameScreenBinding.inflate(inflater, container, false);
        //Calls stuff that needs to be called on game start.


        return binding.getRoot();
    }

    //Code to start and end the game3
    /*
    private void gameStart(){

    }
    private void gameEnd(){
        //binding.knightWrapper.killKnight();
        //Make deleting things work - Memory leak??
    }
    */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Setup the Game
        //gameStart();

        // Set reference to GameScreenFragment in LifeView
        binding.lifeView.setGameScreenFragment(this);

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
                // Joscie's mActive Pointer Stuff
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
        //gameEnd();
    }

    public void gameTick() {
        getNewTick();
        //Move the knight and get position/radius
        binding.knightWrapper.moveKnight();
        knightPosition = binding.knightWrapper.getKnightPosition();
        knightRadius = binding.knightWrapper.getRadius();
        knightSpeed = binding.knightWrapper.getSpeed();

        // Delete monsters if they reach castle
        noLives = binding.monsterView.deleteMonsters();
        currLives = currLives-noLives;
        binding.lifeView.getLivesLost(noLives, currLives, maxLives);

        //Spawn and move the monsters
        //For some reason, you have to give the window width and height every time.
        binding.monsterView.setWindowWidth(binding.monsterView.getWidth());
        binding.monsterView.setWindowHeight(binding.monsterView.getHeight());
        binding.monsterView.spawnMonsters();

        // Update monster positions in the UI
        binding.monsterView.setKnightPosition(knightPosition);
        binding.monsterView.moveMonsters(knightPosition, knightRadius, knightSpeed);

    }

}
