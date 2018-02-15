package com.kevin.ifood;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    String city = "";
    MapFragment mf;
    GoogleMap map;
    Location location;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        toolbar = findViewById(R.id.toolbar);
        MapsActivity.this.setSupportActionBar(toolbar);

        Intent I = getIntent();
        city = I.getStringExtra("city");
        mf = (MapFragment) getFragmentManager().findFragmentById(R.id.the_map);
        mf.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) { // map is loaded but not laid out yet
        this.map = map;

        double lat, lon;
        float zoom = 10;

        /*
         * declarations for json file contents
         */
        double[] contents;
        contents = getJson(city);
        lat = contents[0];
        lon = contents[1];

        map.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title(""));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), zoom));

        /*
         * code to retrieve current location
         */
        if (city.equals("Current Location")) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                map.setMyLocationEnabled(true);

                map.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                    public boolean onMyLocationButtonClick() {
                        return false;
                    }
                });
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        }
    }

    /*
     * Checks for permission to receive current location
     */
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case MY_PERMISSION_FINE_LOCATION:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        map.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "This app requires location permission to be granted", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*
         * Inflate Menu
         */
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    public double[] getJson(String json) {

        double[] contents = new double[2];
        /*
         * import json file
         */
        try {
            InputStream is = getAssets().open("cities.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            /*
             * declaration of JSON objects adn Array used to read data
             */
            JSONObject jsonObj = new JSONObject(json);
            JSONArray cities = jsonObj.getJSONArray("cities");

            for(int i=0; i<cities.length(); i++)
            {
                JSONObject cityName = cities.getJSONObject(i);
                String id = cityName.getString("city");

                /*
                 * parse through json file contents
                 */
                if (id.equals(city)){
                    String latitude = cityName.getString("lat");
                    double lat = Double.parseDouble(latitude);
                    String longitude = cityName.getString("lon");
                    double lon = Double.parseDouble(longitude);

                    /*
                     * retrieve lat and long
                     */
                    contents[0] = lat;
                    contents[1] = lon;

                    break;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return contents; //return longitute and latitude co-ord.
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_basket){
            Intent b = new Intent(MapsActivity.this, Cart.class);
            startActivity(b);
        }

        if(item.getItemId() == R.id.action_food){
            Intent m = new Intent(MapsActivity.this, MenuActivity.class);
            startActivity(m);
        }

        if(item.getItemId() == R.id.action_reviews){
            /*FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.menu_frame, new DrinksMenuFragment()).commit();*/
        }

        if(item.getItemId() == R.id.action_maps){
            Intent m = new Intent(MapsActivity.this, CityMap.class);
            startActivity(m);

            //FragmentManager fm = getFragmentManager();
            //fm.beginTransaction().replace(R.id.menu_frame, new MapFragment()).commit();
        }

        if(item.getItemId() == R.id.action_assistance){
            Toast.makeText(MapsActivity.this, "A member of staff will be with you shortly", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}