package com.example.conversioncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * The denominator of the conversion calculation.
     */
    private double denominatorConversion;

    /**
     * The numerator of the conversion calculation.
     */
    private double numeratorConversion;

    /**
     * The unit of the output.
     */
    private String outputUnit;

    /**
     * vibrates device when clicked.
     */
    private Vibrator vibratorButton;

    /**
     * handles the conversion calculations and vibration of the device.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputNumEditText = (EditText) findViewById(R.id.inputConversion);
        final TextView outputNumTextView = (TextView) findViewById(R.id.outputConversion);
        inputNumEditText.setTextColor(Color.MAGENTA);
        outputNumTextView.setTextColor(Color.BLUE);

        vibratorButton = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        Button convertBtn = (Button) findViewById(R.id.convert);
        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lines 57 - 62: How To Make An Android Device Vibrate (Video) by Coding Demos on Youtube
                if(Build.VERSION.SDK_INT >= 26) {
                    //vibratorButton creates single vibration to device for 200 milliseconds
                    vibratorButton.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //else, just pass duration
                    vibratorButton.vibrate(200);
                }

                double inputNum = Double.parseDouble(inputNumEditText.getText().toString());
                double outputNum = inputNum * numeratorConversion / denominatorConversion;
                outputNumTextView.setText(outputNum + " " + outputUnit);
            }
        });

        //lines 54 - 61: Android Drop Down List Tutorial (Video) by Coding Demos on Youtube
        Spinner inputDropDownListSpin = (Spinner) findViewById(R.id.inputDropDownList);
        Spinner outputDropDownListSpin = (Spinner) findViewById(R.id.outputDropDownList);

        ArrayAdapter<String> inputAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.units));
        inputAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputDropDownListSpin.setAdapter(inputAdapter);
        outputDropDownListSpin.setAdapter(inputAdapter);

        inputDropDownListSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    //inches
                    case 0:
                        inputNumEditText.setHint("Length in inches");
                        denominatorConversion = 63360;
                        break;
                    //feet
                    case 1:
                        inputNumEditText.setHint("Length in feet");
                        denominatorConversion = 5280;
                        break;
                    //yards
                    case 2:
                        inputNumEditText.setHint("Length in yards");
                        denominatorConversion = 1760;
                        break;
                    //miles
                    case 3:
                        inputNumEditText.setHint("Length in miles");
                        denominatorConversion = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        outputDropDownListSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    //inches
                    case 0:
                        outputNumTextView.setText("Length in inches");
                        outputUnit = "inches";
                        numeratorConversion = 63360;
                        break;
                    //feet
                    case 1:
                        outputNumTextView.setText("Length in feet");
                        outputUnit = "feet";
                        numeratorConversion = 5280;
                        break;
                    //yards
                    case 2:
                        outputNumTextView.setText("Length in yards");
                        outputUnit = "yards";
                        numeratorConversion = 1760;
                        break;
                    //miles
                    case 3:
                        outputNumTextView.setText("Length in miles");
                        outputUnit = "miles";
                        numeratorConversion = 1;
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
