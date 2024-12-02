package org.mobileapp.bravo.threee.activityone;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Home_page extends AppCompatActivity {

    private ImageButton Gamebtn, Calcbtn, Modulebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        Gamebtn = findViewById(R.id.Game);
        Calcbtn = findViewById(R.id.Calculator);
        Modulebtn = findViewById(R.id.Module);


        Gamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, Game_page.class);
                startActivity(intent);
            }
        });

        Calcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, Calculator_page.class);
                startActivity(intent);
            }
        });

        Modulebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, Module_page.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.abt:
                startActivity(new Intent(this, About_page.class));
                return true;

            case R.id.dvp:
                startActivity(new Intent(this, Developers_page.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setMessage("Do you want to exit the app?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Home_page.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
