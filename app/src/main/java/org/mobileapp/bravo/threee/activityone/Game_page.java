package org.mobileapp.bravo.threee.activityone;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Game_page extends AppCompatActivity {

    private EditText gInput;
    private ImageButton clearBtn, submitBtn;
    private TextView resMsg;
    private ImageView gImg;

    private String[] itemsToGuess = {"Apple", "Airplane", "Dog", "Banana", "Laptop", "Pumpkin", "Capybara"};
    private int[] itemImages = {R.drawable.apple, R.drawable.plane, R.drawable.dog, R.drawable.banana, R.drawable.laptop, R.drawable.pumpkin, R.drawable.capybara};
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);


        gInput = findViewById(R.id.guessInput);
        clearBtn = findViewById(R.id.clearBtn);
        submitBtn = findViewById(R.id.submitBtn);
        resMsg = findViewById(R.id.resultMsg);
        gImg = findViewById(R.id.guessImg);


        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInput();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer();
            }
        });


        displayCurrentItem();


        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                goBack();
            }
        });


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void clearInput() {
        gInput.setText("");
        resMsg.setText("");
    }

    private void displayCurrentItem() {
        String currentItem = itemsToGuess[currentIndex];
        gImg.setImageResource(itemImages[currentIndex]);
        resMsg.setText("");
        gInput.setText("");
    }

    private void submitAnswer() {
        String userGuess = gInput.getText().toString().trim();

        if (userGuess.isEmpty()) {
            Toast.makeText(Game_page.this, "Please enter a guess!", Toast.LENGTH_SHORT).show();
        } else if (userGuess.equalsIgnoreCase(itemsToGuess[currentIndex])) {

            resMsg.setText("Correct!");
            resMsg.setTextColor(ContextCompat.getColor(Game_page.this, android.R.color.holo_green_dark));

            currentIndex++;
            if (currentIndex < itemsToGuess.length) {
                displayCurrentItem();
            } else {
                resMsg.setText("You answered all correctly.\n Game Over!");
                resMsg.setTextColor(ContextCompat.getColor(Game_page.this, android.R.color.holo_blue_dark));
                submitBtn.setEnabled(false);
                clearBtn.setEnabled(false);
            }
        } else {
            resMsg.setText("Try again!");
            resMsg.setTextColor(ContextCompat.getColor(Game_page.this, android.R.color.holo_red_dark));
        }
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

    private void goBack() {

        Intent homeIntent = new Intent(Game_page.this, Home_page.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear stack
        startActivity(homeIntent);
        finish();
    }
}
