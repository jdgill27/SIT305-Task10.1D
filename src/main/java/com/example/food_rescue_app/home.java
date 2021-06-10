package com.example.food_rescue_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    RecyclerView rv;
    DatabaseReference database;
    Adapter adapter;
    List<Food_Item> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rv = findViewById(R.id.rv);
        final DrawerLayout drawerLayout = findViewById(R.id.draw_layout);
        rv.clearOnScrollListeners();
        database = FirebaseDatabase.getInstance().getReference().child("Food_Item").child("Food_Item");
        rv.setLayoutManager(new LinearLayoutManager(home.this));

        NavigationView nv = findViewById(R.id.nav_bar);
        nv.setNavigationItemSelectedListener(this);


        findViewById(R.id.draw_draw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        list.clear();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Food_Item food_item = dataSnapshot.getValue(Food_Item.class);
                    list.add(food_item);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        adapter = new Adapter(home.this, list);
        rv.setAdapter(adapter);
    }


    public void Add_Food_Item(View view) {
        Intent intent = new Intent(this, add_food_item.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.home:
                break;
            case R.id.myList:
                Intent intent = new Intent(this, myList.class);
                startActivity(intent);
                break;
            case R.id.payment:
                Intent intent2 = new Intent(this, payment.class);
                startActivity(intent2);
                break;
            default:
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }
}