package com.kevin.ifood;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FoodItemsFragment extends Fragment {

    /*
     * Global variables
     */
    DatabaseReference ref;
    ListView listView;
    ArrayAdapter<String> listViewAdapter;
    ArrayList<String> items = new ArrayList<>();
    //String[] items = {"Margareta Pizza", "8oz Sirloin Steak", "Chicken Curry", "Beef Curry", "Lamb Chops", "BBQ Ribs", "Pasta Carbonara"};

    /**
     * Creates the UI layout
     * @param inflater inflates layout
     * @param container layout container
     * @param savedInstanceState Bundle requirement
     * @return view
     */
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_food_items, container, false);
        listView = v.findViewById(R.id.list);
        listViewAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(listViewAdapter);

        /*
         * Firebase Database Implementations
         * Add, Change, Remove, Move, Cancel items in DB
         */
        ref = FirebaseDatabase.getInstance().getReference().child("Main Course");
        ref.addChildEventListener(new ChildEventListener() {
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                items.add(value);
                listViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*
         * Produce Toast notification when item is selected
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "You've added the item to your basket", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}