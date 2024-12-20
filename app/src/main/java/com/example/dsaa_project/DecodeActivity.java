package com.example.dsaa_project;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

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



        ArrayList<String> options = new ArrayList<>();
        options.add("prefix");
        options.add("infix");
        options.add("postfix");
        this.spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                options
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);






        Button decode = findViewById(R.id.decode_button);
        decode.setOnClickListener(v -> decode());
    }


    private Spinner spinner;
    private void decode(){
        String selectedValue = spinner.getSelectedItem().toString();


        EditText inputBox = findViewById(R.id.input_text_box);
        String input = inputBox.getText().toString();

        String answer = " ";
        switch (selectedValue){
            case "prefix":
                try { answer = String.valueOf(Decoder.decodePrefix(input));}
                catch (Exception e){ answer = "Syntax error"; }
                break;
            case "infix":
                answer = "infix";
                break;
            case "postfix":
                answer = "postfix";
                break;
        }

        TextView answereBox = findViewById(R.id.answere_text);
        answereBox.setText("the answer is \n" + answer);
    }





}