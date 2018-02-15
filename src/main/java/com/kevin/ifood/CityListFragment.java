package com.kevin.ifood;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

public class CityListFragment extends Fragment {

    Toolbar toolbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_city_list, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar();

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //setHasOptionsMenu(true);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, Arrays.asList("Current Location", "Whetherspoons, Dublin", "O'Shea's Bar & Grill, Kerry", "Coppi's Restaurant, Belfast", "Eireanns Isle, Galway", "Greene's Restaurant, Cork", "The Yard Restaurant, Wexford"));
        ListView list = view.findViewById(R.id.listView);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View row, int index, long rowID) {
                ((CityMap)getActivity()).setCity((String)list.getItemAtPosition(index));
                ((CityMap)getActivity()).showMap();
            }
        });
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.my_menu, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_basket){
            Intent b = new Intent(getActivity(), Cart.class);
            startActivity(b);
        }

        if(item.getItemId() == R.id.action_food){
            Intent m = new Intent(getActivity(), MenuActivity.class);
            startActivity(m);
        }

        if(item.getItemId() == R.id.action_reviews){
            /*FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.menu_frame, new DrinksMenuFragment()).commit();*/
        }

        if(item.getItemId() == R.id.action_maps){
            Intent m = new Intent(getActivity(), CityMap.class);
            startActivity(m);

            //FragmentManager fm = getFragmentManager();
            //fm.beginTransaction().replace(R.id.menu_frame, new MapFragment()).commit();
        }

        if(item.getItemId() == R.id.action_assistance){
            Toast.makeText(getActivity(), "A member of staff will be with you shortly", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}