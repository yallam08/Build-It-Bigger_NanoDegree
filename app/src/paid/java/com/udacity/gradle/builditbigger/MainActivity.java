package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.jokedisplayer.DisplayJokeActivity;

public class MainActivity extends AppCompatActivity implements AsyncTaskListener {
    private String joke;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(false);
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
        displayJoke();
    }
}
