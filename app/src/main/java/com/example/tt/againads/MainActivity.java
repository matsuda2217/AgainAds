package com.example.tt.againads;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

public class MainActivity extends ActionBarActivity {
    InterstitialAd interstitialAd;
    AdRequest adRequest;
    String unitId ="a14f840b733d0dc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(unitId);

        adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("5554")
                .build();

        interstitialAd.loadAd(adRequest);
        Handler  handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "2s delay", Toast.LENGTH_SHORT).show();
                displayIntersitialAd();
            }
        },2000);
        displayIntersitialAd();
        Random r = new Random();
        if (r.nextInt(100)<30) {
            displayIntersitialAd();
        }
    }

    public void displayIntersitialAd() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == event.KEYCODE_BACK) {
            displayIntersitialAd();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Random r = new Random();
        if (r.nextInt(100)<30) {
            displayIntersitialAd();
        }
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
}
