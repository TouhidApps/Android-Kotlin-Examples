package com.touhidapps.androidthreadexample;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class HandlerActivity extends AppCompatActivity {

    Thread thread;
    Handler handler;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        handler = new MyHandler();  // handler object create before thread start to avoid nullpointer exception of handler used inside thread

        thread = new Thread(new MyThread());
        thread.start();


    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            progressBar.setProgress(msg.arg1);
        }
    }

    class MyThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {

                Message message = Message.obtain(); // creates reusable object
                message.arg1 = i;
                handler.sendMessage(message); // message sender

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

    }


}
