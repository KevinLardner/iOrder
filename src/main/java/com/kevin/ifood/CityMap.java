package com.kevin.ifood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

public class CityMap extends AppCompatActivity implements OnMapReadyCallback {

    String city = "";
    MapFragment mf;
    GoogleMap map;

    public void setCity(String city)
    {
        this.city = city;
    }

    public void showMap()
    {
        mf = (MapFragment) getFragmentManager().findFragmentById(R.id.the_map);
        if (mf == null) {
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("city", city);
            startActivity(intent);
        } else {
            mf.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {    // map is loaded but not laid out yet
        this.map = map;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_basket){
            Intent b = new Intent(CityMap.this, Cart.class);
            startActivity(b);
        }

        if(item.getItemId() == R.id.action_food){
            Intent m = new Intent(CityMap.this, MenuActivity.class);
            startActivity(m);
        }

        if(item.getItemId() == R.id.action_reviews){
            /*FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.menu_frame, new DrinksMenuFragment()).commit();*/
        }

        if(item.getItemId() == R.id.action_maps){
            Intent m = new Intent(CityMap.this, CityMap.class);
            startActivity(m);

            //FragmentManager fm = getFragmentManager();
            //fm.beginTransaction().replace(R.id.menu_frame, new MapFragment()).commit();
        }

        if(item.getItemId() == R.id.action_assistance){
            Toast.makeText(CityMap.this, "A member of staff will be with you shortly", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}