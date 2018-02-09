package com.touhidapps.texttospeechandroid;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    EditText editText;
    Button buttonClear, buttonTalk;

    private TextToSpeech textToSpeech;
    private boolean textToSpeechInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        textToSpeech = new TextToSpeech(getApplicationContext(), this);

    }


    private void initUI() {
        editText = (EditText) findViewById(R.id.editText);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonTalk = (Button) findViewById(R.id.buttonTalk);

        buttonClear.setOnClickListener(this);
        buttonTalk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonClear:
                editText.setText("");
                break;

            case R.id.buttonTalk:
                sayText(editText.getText().toString());
                break;
        }
    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            int r = textToSpeech.setLanguage(Locale.US);

            if (r == TextToSpeech.LANG_MISSING_DATA || r == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "Un-supported language", Toast.LENGTH_SHORT).show();
            } else {
                textToSpeechInit = true;
            }
        } else {
            Toast.makeText(this, "Text To Speech init Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void sayText(String speech) {
        if (!textToSpeechInit) {
            Toast.makeText(this, "Text To Speech wasn't initialized", Toast.LENGTH_SHORT).show();
        } else {
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null, "touhid");
        }
    }

}
