package com.kevin.ifood;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class DessertItemsFragment extends Fragment {

    DatabaseReference ref;
    ArrayList<String> items = new ArrayList<>();
    ArrayAdapter<String> listViewAdapter;
    ListView listView;
    //String[] items = {"Baileys Cheesecake", "Chocolate Fudge Brownie", "Selection of Ice-cream", "Apple Pie"};

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dessert_items, container, false);
        listView = v.findViewById(R.id.list);
        listViewAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(listViewAdapter);
        ref = FirebaseDatabase.getInstance().getReference().child("Desserts");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "You've added the item to your basket",Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}