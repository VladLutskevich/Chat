package com.example.chat;

import android.os.AsyncTask;

import java.io.IOException;

public class MessageListener extends AsyncTask {
    String string;
    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            string = MainActivity.clientThread.getStringForce();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Object object) {
         super.onPostExecute(object);
         if(string != null) MainActivity.createTextView(string);
         new MessageListener().execute();
    }


}
