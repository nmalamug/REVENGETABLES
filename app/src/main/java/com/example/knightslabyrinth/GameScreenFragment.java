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

public class GameScreenFragment extends Fragment {
    public native String getNativeMessage();
    public native void getNewTick();

    private FragmentGameScreenBinding binding;

    //Variables for handler in game ticks
    private Handler handler = new Handler();
    private Runnable runTicks;

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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        handler.removeCallbacks(runTicks);
        gameEnd();
    }

    //Runs code to go to next game tick
    public void gameTick(){
        binding.knightWrapper.moveKnight();
    }

}
