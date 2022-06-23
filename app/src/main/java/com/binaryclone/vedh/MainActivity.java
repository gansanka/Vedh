package com.binaryclone.vedh;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.binaryclone.vedh.activity.AddActivity;
import com.binaryclone.vedh.activity.MulActivity;
import com.binaryclone.vedh.activity.SubActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent add = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(add);
            }
        });

        Button subButton = (Button) findViewById(R.id.subtract);
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sub = new Intent(getApplicationContext(), SubActivity.class);
                startActivity(sub);
            }
        });

        Button mulButton = (Button) findViewById(R.id.multiply);
        mulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mul = new Intent(getApplicationContext(), MulActivity.class);
                startActivity(mul);
            }
        });
    }
}