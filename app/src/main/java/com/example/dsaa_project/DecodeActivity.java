package com.example.dsaa_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import classes.Decoder;

public class DecodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_decode);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button decode = findViewById(R.id.decode_button);
        decode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decode();
            }
        });
    }

    private void decode(){
        EditText inputBox = findViewById(R.id.input_text_box);
        String input = inputBox.getText().toString();
        String answere;
        try {
            answere = String.valueOf(Decoder.decodePrefix(input));
        } catch (Exception e){
            answere = "Syntax error";
        }
        TextView answereBox = findViewById(R.id.answere_text);
        answereBox.setText("the answer is \n" + answere);
    }





}