package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.jokedisplayer.DisplayJokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends AppCompatActivity implements AsyncTaskListener {
    InterstitialAd mInterstitialAd;
    private String joke;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(false);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                displayJoke();
            }
        });

        requestNewInterstitial();

    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    public void tellJoke(View view) {
        mProgressDialog.show();
        EndPointAsyncTask endPointAsyncTask = new EndPointAsyncTask();
        endPointAsyncTask.asyncTaskListener = this;
        endPointAsyncTask.execute();
    }

    private void displayJoke() {
        if (joke != null && !joke.isEmpty()) {
            Intent jokeIntent = new Intent(this, DisplayJokeActivity.class);
            jokeIntent.putExtra("joke", joke);
            startActivity(jokeIntent);
        } else {
            Toast.makeText(this, R.string.error_msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onTaskFinished(String data) {
        mProgressDialog.dismiss();
        this.joke = data;
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            displayJoke();
        }
    }
}
