package com.binaryclone.vedh.activity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.binaryclone.vedh.MainActivity;
import com.binaryclone.vedh.R;
import com.binaryclone.vedh.utility.CommonUtility;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SubActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    final CommonUtility utility = new CommonUtility();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity);

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
        answer.setTextSize(TypedValue.COMPLEX_UNIT_SP,28f);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                if (answer != null && answer.getText() != null && !answer.getText().toString().isEmpty()) {
                    int ans = Integer.parseInt(answer.getText().toString());
                    int first = Integer.parseInt(col1.getText().toString());
                    int second = Integer.parseInt(col2.getText().toString());
                    AssetFileDescriptor afd = null;
                    if (ans == first - second) {
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
                }
                else {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.enter_answer);
                    mediaPlayer.start();
                }
                }
                catch(Exception e){
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

        col1.setTextSize(TypedValue.COMPLEX_UNIT_SP,28f);
        col2.setTextSize(TypedValue.COMPLEX_UNIT_SP,28f);

        switch (position) {
            case 0:
                Integer first_0 = ThreadLocalRandom.current().nextInt(1,50);
                Integer second_0 = ThreadLocalRandom.current().nextInt(1,first_0+1);
                col1.setText(String.valueOf(first_0));
                col2.setText(String.valueOf(second_0));
                break;
            case 1:
                Integer first_1 = ThreadLocalRandom.current().nextInt(1,100);
                Integer second_1 = ThreadLocalRandom.current().nextInt(1,first_1+1);
                col1.setText(String.valueOf(first_1));
                col2.setText(String.valueOf(second_1));
                break;
            case 2:
                Integer first_2 = ThreadLocalRandom.current().nextInt(1,1000);
                Integer second_2 = ThreadLocalRandom.current().nextInt(1,first_2+1);
                col1.setText(String.valueOf(first_2));
                col2.setText(String.valueOf(second_2));
                break;
        }
        utility.generateSpeech(col1, col2, getApplicationContext(), " minus ");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
