package com.kevin.ifood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button submitButton;
    static EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = findViewById(R.id.submit);
        editEmail = findViewById(R.id.editEmail);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editEmail.getText().toString().contains("@")) {
                    // GO TO NEXT ACTIVITY
                    Intent m = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(m);
                } else {
                    Toast.makeText(getApplicationContext(), "Must enter a valid email address", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}