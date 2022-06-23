package com.binaryclone.vedh.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.binaryclone.vedh.MainActivity;
import com.binaryclone.vedh.R;
import com.binaryclone.vedh.utility.CommonUtility;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    final Random random = new Random();

    private static final int MY_DATA_CHECK_CODE = 0;

    final CommonUtility utility = new CommonUtility();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        Button homebutton = (Button) findViewById(R.id.homebutton);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });

        Button col1 = (Button) findViewById(R.id.col1);
        Button col2 = (Button) findViewById(R.id.col2);
        Button checkButton = (Button) findViewById(R.id.chkval);
        Spinner spinner = (Spinner) findViewById(R.id.levels);

        TextView answer = (TextView) findViewById(R.id.answer);
        answer.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28f);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (answer != null && answer.getText() != null && !answer.getText().toString().isEmpty()) {
                        int ans = Integer.parseInt(answer.getText().toString());
                        int first = Integer.parseInt(col1.getText().toString());
                        int second = Integer.parseInt(col2.getText().toString());
                        AssetFileDescriptor afd = null;
                        if (ans == first + second) {
                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    onItemSelected(spinner, spinner.getSelectedView(), spinner.getSelectedItemPosition(), spinner.getSelectedItemId());
                                    answer.setText("");
                                }
                            });
                        } else {
                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    answer.setText("");
                                }
                            });
                        }
                    } else {
                        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.enter_answer);
                        mediaPlayer.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.levels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        Button col1 = (Button) findViewById(R.id.col1);
        Button col2 = (Button) findViewById(R.id.col2);

        col1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28f);
        col2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28f);

        switch (position) {
            case 0:
                col1.setText(String.valueOf(random.nextInt(50)));
                col2.setText(String.valueOf(random.nextInt(50)));

                break;
            case 1:
                col1.setText(String.valueOf(random.nextInt(100)));
                col2.setText(String.valueOf(random.nextInt(100)));

                break;
            case 2:
                col1.setText(String.valueOf(random.nextInt(1000)));
                col2.setText(String.valueOf(random.nextInt(1000)));

                break;
        }
        utility.generateSpeech(col1, col2, getApplicationContext(), " plus ");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
