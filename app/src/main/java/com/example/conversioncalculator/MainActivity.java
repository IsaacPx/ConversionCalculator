package com.example.conversioncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button convertBtn = (Button) findViewById(R.id.convert);
        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputNumEditText = (EditText) findViewById(R.id.inputConversion);
                TextView outputNumTextView = (TextView) findViewById(R.id.outputConversion);

                double inputNum = Double.parseDouble(inputNumEditText.getText().toString());
                double outputNum = inputNum * 2.54;
                outputNumTextView.setText(outputNum + " cm");
            }
        });
    }
}
