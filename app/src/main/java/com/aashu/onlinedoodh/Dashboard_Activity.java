package com.aashu.onlinedoodh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard_Activity extends AppCompatActivity implements Recyclerviewinterface {
    Button btn;
    RecyclerView recyclerView;
    RecyclerAdapter Adapter;
    CircleImageView circleImageView;

    @Override
    protected void onStart() {
        super.onStart();
        Adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView =(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Modalclass> options =
                new FirebaseRecyclerOptions.Builder<Modalclass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("doodhwala"), Modalclass.class)
                        .build();



        Adapter = new RecyclerAdapter(options, this);
        recyclerView.setAdapter(Adapter);




        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });





    }

    @Override
    public void Onitemclick(int position) {

        Intent intent = new Intent(Dashboard_Activity.this,Product_Activity.class);
        startActivity(intent);

    }
}