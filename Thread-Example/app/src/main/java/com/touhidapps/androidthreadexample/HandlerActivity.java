package com.touhidapps.androidthreadexample;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HandlerActivity extends AppCompatActivity {

    Handler handler;
    Thread thread;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        progressBar = findViewById(R.id.progressBar);


        handler = new MyHandler();  // handler object create before thread start to avoid null pointer exception of handler used inside thread

        thread = new Thread(new MyThread());
        thread.start();


        // async task
        MyAsyncTask asyncTask = new MyAsyncTask();
        asyncTask.execute("https://www.tutorialspoint.com/images/tp-logo-diamond.png");

    }

    class MyHandler extends Handler {
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

    } // MyThread


    URL imageUrl = null;
    InputStream is = null;
    Bitmap bmImg = null;
    ImageView imageView = null;
    ProgressDialog p;

    // async task
    private class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = new ProgressDialog(HandlerActivity.this);
            p.setMessage("Please wait...It is downloading");
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                imageUrl = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                is = conn.getInputStream();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                bmImg = BitmapFactory.decodeStream(is, null, options);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 100; i++) {
                            onProgressUpdate(i);
                        }
                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmImg;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // show in spinner, access UI elements
            Log.d("ttt", "onProgressUpdate: " + values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (imageView != null) {
                p.hide();
                imageView.setImageBitmap(bitmap);
            } else {
                p.show();
            }
        }
    }


}
