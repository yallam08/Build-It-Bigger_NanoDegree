package com.udacity.gradle.builditbigger;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class EndPointAsyncTaskTest implements AsyncTaskListener {
    private CountDownLatch countDownLatch;
    private String joke;

    @Test
    public void getJokeAsyncTaskTest() throws Throwable {
        countDownLatch = new CountDownLatch(1);

        EndPointAsyncTask endPointAsyncTask = new EndPointAsyncTask();
        endPointAsyncTask.asyncTaskListener = this;
        endPointAsyncTask.execute();

        countDownLatch.await(5, TimeUnit.SECONDS);

        assertNotNull("Null joke", joke);
        assertFalse("Empty joke", joke.isEmpty());
    }

    @Override
    public void onTaskFinished(String data) {
        this.joke = data;
        countDownLatch.countDown();
    }
}
