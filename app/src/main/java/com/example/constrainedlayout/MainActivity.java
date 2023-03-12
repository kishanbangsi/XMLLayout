package com.example.constrainedlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup rg;
    EditText txtCost;
    Button btn;
    RadioButton rb;
    TextView tvTip;

    final int AMAZING = R.id.rb_amazing;
    final int GOOD = R.id.rb_good;
    final int OKAY = R.id.rb_okay;

    double costOfService = 0;
    double tipPercentage = 0;
    double tipAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // GET ID OF VIEWS
        rg = findViewById(R.id.radioGroup);
        txtCost = findViewById(R.id.input_view);
        btn = findViewById(R.id.btn_submit);
        tvTip = findViewById(R.id.tv_desc);
        rb = findViewById(OKAY);

        // SET LISTENER TO THE BUTTON
        btn.setOnClickListener(view -> {
            if(txtCost.getText().toString().equals("")) {
                txtCost.setError("Enter cost of service!");
                return;
            }

            int checkedId = rg.getCheckedRadioButtonId();
            if(checkedId < 0) {
                rb.setError("Select service type!");
                return;
            }

            try {
                costOfService = Double.parseDouble(txtCost.getText().toString());
            }
            catch(Exception ex) {
                txtCost.setError("Invalid cost of service!");
                return;
            }

            switch(checkedId) {
                case AMAZING:
                    tipPercentage = 0.20;
                    break;

                case GOOD:
                    tipPercentage = 0.18;
                    break;

                case OKAY:
                    tipPercentage = 0.15;
                    break;
            }

            tipAmount = costOfService * tipPercentage;
            tvTip.setText(Double.toString(tipAmount));
        });

        // SET LISTENER TO THE RADIO GROUP
        rg.setOnCheckedChangeListener((radioGroup, i) -> {
            switch(i) {
                case AMAZING:
                    Toast.makeText(MainActivity.this, "Amazing clicked!", Toast.LENGTH_SHORT).show();
                    break;

                case GOOD:
                    Toast.makeText(MainActivity.this, "Good clicked!", Toast.LENGTH_SHORT).show();
                    break;

                case OKAY:
                        Toast.makeText(MainActivity.this, "Okay clicked!", Toast.LENGTH_SHORT).show();
                        break;
            }
        });
    }
}