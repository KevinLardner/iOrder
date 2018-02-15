package com.kevin.ifood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Cart extends AppCompatActivity {
    ListView listView;
    String[] items = {"Your Food items will be listed here! A receipt will also be sent to your email."};
    Button pay;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        listView = findViewById(R.id.list);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(Cart.this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(listViewAdapter);

        email = UserEmail.getEditEmail();
        pay = findViewById(R.id.payNow);


        pay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String to = email.getText().toString();
                String sub = "Your iFood Order";

                Intent email = new Intent();
                email.setAction(Intent.ACTION_SEND);
                email.setType("text/plain");//PLAIN_TEXT_TYPE
                email.putExtra(Intent.EXTRA_EMAIL, new String[] {to});
                email.putExtra(Intent.EXTRA_SUBJECT, sub);
                startActivity(Intent.createChooser(email, "Send Email"));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater myMenuInflater = getMenuInflater();
        myMenuInflater.inflate(R.menu.my_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_basket){
            Intent b = new Intent(Cart.this, Cart.class);
            startActivity(b);
        }

        if(item.getItemId() == R.id.action_food){
            Intent m = new Intent(Cart.this, MenuActivity.class);
            startActivity(m);
        }

        if(item.getItemId() == R.id.action_reviews){
            /*FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.menu_frame, new DrinksMenuFragment()).commit();*/
        }

        if(item.getItemId() == R.id.action_maps){
            Intent m = new Intent(Cart.this, CityMap.class);
            startActivity(m);

            //FragmentManager fm = getFragmentManager();
            //fm.beginTransaction().replace(R.id.menu_frame, new MapFragment()).commit();
        }

        if(item.getItemId() == R.id.action_assistance){
            Toast.makeText(Cart.this, "A member of staff will be with you shortly", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
