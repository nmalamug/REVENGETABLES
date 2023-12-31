package com.example.knightslabyrinth;
import android.graphics.PointF;
import android.media.MediaPlayer;
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

public class GameScreenFragment extends Fragment implements GameScreenAPI {
    // Native methods
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

    private int score;
    private int maxLives = 7;
    private int currLives;
    private long numticks = 0;
    boolean Paused = false;

    MediaPlayer GameOverSound;
    static MediaPlayer GameMusic;
    MediaPlayer buttonClick;
    private int knightAbilityActive;
    private int difficulty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGameScreenBinding.inflate(inflater, container, false);
        //Calls stuff that needs to be called on game start
        gameStart();
        return binding.getRoot();
    }

    //Code to start and end the game3

    private void gameStart(){
        difficulty = MainActivity.settings.getDifficulty();
        if(difficulty == 0){
            maxLives = 7;
        }else if(difficulty == 1){
            maxLives = 5;
        }else{
            maxLives = 3;
        }
        currLives = maxLives;
        binding.lifeView.setMaxLives(maxLives);
    }
    private void gameEnd(){
        //Store the most recent score here.
        //binding.knightWrapper.killKnight();
        //Make deleting things work - Memory leak??
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GameMusic = MediaPlayer.create(getContext(), R.raw.gamescreen);
        buttonClick = MediaPlayer.create(getContext(), R.raw.buttonclick);
        GameOverSound = MediaPlayer.create(getContext(), R.raw.gameover);
        GameMusic.setLooping(true);
        GameMusic.setVolume(0.2f, 0.2f);
        GameOverSound.setVolume(0.5f, 0.5f);
        if (SettingsFragment.getAudioSetting()) {
            GameMusic.start();
        }
        //Setup the Game
        //gameStart();
        // Set reference to GameScreenFragment in LifeView
        binding.lifeView.setGameScreenFragment(this);

        //Code for updating game ticks
        //Tick time currently 20 ms
        runTicks = () -> {
            if (!Paused) {
                gameTick();
            }
            handler.postDelayed(runTicks, 20);
        };
        handler.postDelayed(runTicks, 20);

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameMusic.stop();
                GameMusic.release();
                GameMusic = null;
                if (SettingsFragment.getAudioSetting()) {
                    buttonClick.start();
                }
                NavHostFragment.findNavController(GameScreenFragment.this)
                        .navigate(R.id.action_GameScreenFragment_to_HomeScreenFragment);
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
        GameOverSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
            }
        });

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
        if (GameMusic != null) {
            GameMusic.stop();
            GameMusic.release();
            GameMusic = null;
        }
        gameEnd();
    }

    public void gameTick() {
        numticks++;

        //Move the knight and get position/radius
        binding.knightWrapper.moveKnight();
        knightPosition = binding.knightWrapper.getKnightPosition();
        knightRadius = binding.knightWrapper.getRadius();
        knightSpeed = binding.knightWrapper.getSpeed();

        // Check lives lost then delete monsters if they reach castle, update score
        if (binding.monsterView.getNormKicked() > 0) {
            score += 50 * (difficulty+1) * binding.monsterView.getNormKicked();
        }
        else if (binding.monsterView.getHopKicked() > 0) {
            score += 150 * (difficulty+1) * binding.monsterView.getHopKicked();
        }
        else if (binding.monsterView.getDiagKicked() > 0) {
            score += 300 * (difficulty+1) * binding.monsterView.getDiagKicked();
        }
        noLives = binding.monsterView.deleteMonsters();
        currLives = currLives - noLives;
        binding.lifeView.getLivesLost(noLives, currLives, maxLives);
        if (binding.lifeView.updateScore(numticks)) {
            score += 5 * (difficulty+1);
        }

        binding.textView.setText(String.valueOf(score));

        //Spawn and move the monsters
        //For some reason, you have to give the window width and height every time.
        binding.monsterView.setWindowWidth(binding.monsterView.getWidth());
        binding.monsterView.setWindowHeight(binding.monsterView.getHeight());
        binding.monsterView.spawnMonsters(numticks);

        // Update monster positions in the UI
        //binding.monsterView.setKnightPosition(knightPosition);
        knightAbilityActive = binding.knightWrapper.getAbilityActive();
        binding.monsterView.moveMonsters(knightPosition, knightRadius, knightSpeed, knightAbilityActive);
        if(currLives <= 0){
            GameMusic.stop();
            GameMusic.release();
            GameMusic = null;
            if (SettingsFragment.getAudioSetting()) {
                GameOverSound.start();
            }
            MainActivity.settings.setLastScore(score);
            NavHostFragment.findNavController(GameScreenFragment.this)
                    .navigate(R.id.action_GameScreenFragment_to_LoseScreenFragment);
        }
    }


    private void endGame(int currentGameScore) {
        // Save the score to the scoreboard
        ScoreBoard scoreBoard = new ScoreBoard(getContext());
        scoreBoard.saveScore("Player", currentGameScore);
        GameMusic.stop();
        GameMusic.release();
        GameMusic = null;
        if (SettingsFragment.getAudioSetting()) {
            GameOverSound.start();
        }
        // Navigate to the LoseScreenFragment
        NavHostFragment.findNavController(GameScreenFragment.this)
                .navigate(R.id.action_GameScreenFragment_to_LoseScreenFragment);
    }
}
