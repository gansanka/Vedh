package com.binaryclone.vedh.utility;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.widget.Button;

import java.util.Locale;


public class CommonUtility {

    TextToSpeech ts;

    public void generateSpeech(Button col1, Button col2, Context context, String operation) {
        String first = col1.getText().toString();
        String second = col2.getText().toString();
        String toSpeak = "what is " + first + operation + second;

        ts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                //Toast.makeText(context, String.valueOf(status), Toast.LENGTH_SHORT).show();
                if (status != TextToSpeech.ERROR) {
                    ts.setLanguage(Locale.US);
                    ts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });
    }
}
