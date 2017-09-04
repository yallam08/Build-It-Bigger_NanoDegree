package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.example.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

class EndPointAsyncTask extends AsyncTask<Void, Void, String> {
    AsyncTaskListener asyncTaskListener = null;
    private static MyApi myApiService = null;

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://build-it-bigger-178916.appspot.com/_ah/api/");

            myApiService = builder.build();
        }


        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String data) {
        asyncTaskListener.onTaskFinished(data);
    }
}
