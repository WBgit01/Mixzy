package org.mobileapp.bravo.threee.activityone;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Calculator_page extends AppCompatActivity {

    private EditText inPrincipal, inInterest, inTime;
    private Button btnCalculate, btnClear;
    private TextView outResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_page);


        inPrincipal = findViewById(R.id.inPrincipal);
        inInterest = findViewById(R.id.inInterest);
        inTime = findViewById(R.id.inTime);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        outResult = findViewById(R.id.outResult);


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String principalStr = inPrincipal.getText().toString();
                String interestRateStr = inInterest.getText().toString();
                String timeStr = inTime.getText().toString();


                if (principalStr.isEmpty() || interestRateStr.isEmpty() || timeStr.isEmpty()) {
                    Toast.makeText(Calculator_page.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
                    return;
                }


                double principal = Double.parseDouble(principalStr);
                double interestRate = Double.parseDouble(interestRateStr) / 100; // Convert percentage to decimal
                double time = Double.parseDouble(timeStr);


                int n = 12;
                double amount = principal * Math.pow(1 + (interestRate / n), n * time);


                DecimalFormat currencyFormat = new DecimalFormat("₱###,###.00");


                outResult.setText("Amount After Interest: " + currencyFormat.format(amount));
            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInput();
            }
        });


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                goBack();
            }
        });
    }

    private void clearInput() {
        inPrincipal.setText("");
        inInterest.setText("");
        inTime.setText("");
        outResult.setText("To find out the answer, fill out the necessary information below.");
    }


    private void goBack() {
        Intent homeIntent = new Intent(Calculator_page.this, Home_page.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.abt:
                startActivity(new Intent(this, About_page.class));
                return true;

            case R.id.dvp:
                startActivity(new Intent(this, Developers_page.class));
                return true;

            case android.R.id.home:
                goBack();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}