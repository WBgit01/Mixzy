package org.mobileapp.bravo.threee.activityone;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Module_page extends AppCompatActivity {

    TextView txtComment;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_page);


        txtComment = findViewById(R.id.commentSec);
        btnSend = findViewById(R.id.sendBtn);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = txtComment.getText().toString().trim();
                if (!comment.isEmpty()) {
                    new AlertDialog.Builder(Module_page.this)
                            .setTitle("Message Sent!")
                            .setMessage("Thank you for your feedback!")
                            .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                            .create()
                            .show();
                } else {
                    Toast.makeText(Module_page.this, "Enter your comment", Toast.LENGTH_SHORT).show();
                }
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

    private void goBack() {

        Intent homeIntent = new Intent(Module_page.this, Home_page.class);
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
