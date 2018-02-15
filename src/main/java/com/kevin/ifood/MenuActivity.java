package com.kevin.ifood;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.MapFragment;

public class MenuActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        Button food = findViewById(R.id.foodButton);
        Button drinks = findViewById(R.id.drinksButton);
        Button desserts = findViewById(R.id.dessertButton);
        Button snacks = findViewById(R.id.snacksButton);

        food.setOnClickListener(new View.OnClickListener()    {
            public void onClick(View v){
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.menu_frame, new FoodItemsFragment()).commit();
            }
        });

        drinks.setOnClickListener(new View.OnClickListener()    {
            public void onClick(View v){
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.menu_frame, new DrinksMenuFragment()).commit();
            }
        });

        snacks.setOnClickListener(new View.OnClickListener()    {
            public void onClick(View v){
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.menu_frame, new SnackItemsFragment()).commit();
            }
        });

        desserts.setOnClickListener(new View.OnClickListener()    {
            public void onClick(View v){
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.menu_frame, new DessertItemsFragment()).commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater myMenuInflater = getMenuInflater();
        myMenuInflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_basket){
            Intent b = new Intent(MenuActivity.this, Cart.class);
            startActivity(b);
        }

        if(item.getItemId() == R.id.action_food){
            Intent m = new Intent(MenuActivity.this, MenuActivity.class);
            startActivity(m);
        }

        if(item.getItemId() == R.id.action_reviews){
            /*FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.menu_frame, new DrinksMenuFragment()).commit();*/
        }

        if(item.getItemId() == R.id.action_maps){
            Intent m = new Intent(MenuActivity.this, CityMap.class);
            startActivity(m);

            //FragmentManager fm = getFragmentManager();
            //fm.beginTransaction().replace(R.id.menu_frame, new MapFragment()).commit();
        }

        if(item.getItemId() == R.id.action_assistance){
            Toast.makeText(MenuActivity.this, "A member of staff will be with you shortly", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}