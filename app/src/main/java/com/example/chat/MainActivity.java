package com.example.chat;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {

    private static final int SLEEP_TIME = 1000;
    Thread thread;
    static ClientThread clientThread;
    static LinearLayout ll;
    static LinearLayout.LayoutParams params;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = (LinearLayout) findViewById(R.id.ll);
        clientThread = new ClientThread();
        thread = new Thread(clientThread);
        context=this;
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        thread.start();
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new MessageListener().execute();
        clientThread.setFinished(true);

    }


    public static void createTextView(String string) {

        TextView textView = new TextView(context);
        textView.setText(string);
        ll.addView(textView, params);
    }

}







