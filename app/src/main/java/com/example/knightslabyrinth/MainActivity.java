package com.example.knightslabyrinth;

import android.media.AudioManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.knightslabyrinth.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private Handler handler = new Handler();
    private Runnable showToastRunnable;
    public static SettingsManager settings = new SettingsManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        // Load the C++ library
        System.loadLibrary("knightslabyrinth");
        SettingsFragment.setAudioSetting(true);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public void onPause() {
        super.onPause();
        //Pause your player
        if (GameScreenFragment.GameMusic != null) {
            GameScreenFragment.GameMusic.pause();
        }
        if (HomeScreenFragment.mediaPlayer != null) {
            HomeScreenFragment.mediaPlayer.pause();
        }
        //do more stuff
    }
    @Override
    public void onResume() {
        super.onResume();
        //Play again
        if (GameScreenFragment.GameMusic != null) {
            GameScreenFragment.GameMusic.start();
        }
        if (HomeScreenFragment.mediaPlayer != null) {
            HomeScreenFragment.mediaPlayer.start();
        }
        //do more stuff
    }
}
