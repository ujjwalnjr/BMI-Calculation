package com.ujjwal.calculatebmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView display, category;
    private EditText txt_height, txt_weight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.result);
        category = findViewById(R.id.tv_category);
        txt_height = findViewById(R.id.et_height);
        txt_weight = findViewById(R.id.et_weight);
    }

    public void calculateBMI(View view) {
        String height_value = txt_height.getText().toString();
        String weight_value = txt_weight.getText().toString();

        if (height_value.isEmpty()) {
            txt_height.setError("Height cannot be null");
        } else if (weight_value.isEmpty()) {
            txt_weight.setError("Weight cannot be null");
        } else {
            double heightInCm = Double.parseDouble(height_value);
            double weight = Double.parseDouble(weight_value);
            double heightInM = heightInCm / 100;
            Person psn = new Person(heightInM,weight);
            double BMI = psn.getWeight() / (psn.getHeight()*psn.getHeight());
            DecimalFormat convert = new DecimalFormat("#.0");
            display.setText("Your BMI is: " + convert.format(BMI));

            if (BMI < 18.5) {
                category.setText("Underweight");
                (Toast.makeText(this, "UnderWeight", Toast.LENGTH_LONG)).show();
            } else if (BMI >= 18.5 && BMI <= 24.9) {
                category.setText("Normal Weight");
                (Toast.makeText(this, "Normal Weight", Toast.LENGTH_LONG)).show();
            } else if (BMI >= 25 && BMI <= 29.9) {
                category.setText("Overweight");
                (Toast.makeText(this, "Over Weight", Toast.LENGTH_LONG)).show();
            } else {
                category.setText("Obesity");
                (Toast.makeText(this, "Obesity", Toast.LENGTH_LONG)).show();
            }
        }
    }
}
