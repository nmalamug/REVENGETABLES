package com.example.knightsslabyrinth;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.androidgamesdk.GameActivity;

public class MainActivity extends GameActivity {
    static {
        System.loadLibrary("knightsslabyrinth");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeToast("onCreate()");

    }
    private Handler handler = new Handler();
    private Runnable showToastRunnable;
    @Override
    protected void onStart() {
        super.onStart();
        makeToast("onStart()");
        showToastRunnable = () -> {
            makeToast("Hi");
            handler.postDelayed(showToastRunnable, 5000);
        };
        handler.postDelayed(showToastRunnable, 5000);
    }


    @Override
    protected void onResume() {
        super.onResume();
        makeToast("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        makeToast("onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        makeToast("onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        makeToast("onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        makeToast("onRestart()");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            hideSystemUi();
        }
    }

    private void hideSystemUi() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }

    public void makeToast(String str) {
        runOnUiThread(() -> Toast.makeText(this, str, Toast.LENGTH_LONG).show());
    }

    public static <T extends Number> boolean amIPositive(T num) {
        return num.doubleValue() > 0;
    }
}

