package com.example.firebaseviewpager;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.firebaseviewpager.Adapter.MyAdapter;
import com.example.firebaseviewpager.Interface.IFirebaseLoadDone;
import com.example.firebaseviewpager.Model.Wall;
import com.example.firebaseviewpager.Transformer.DepthPageTransformer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IFirebaseLoadDone {
     ViewPager viewPager;
     MyAdapter adapter;
     DatabaseReference walls;
     IFirebaseLoadDone iFirebaseLoadDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       walls= FirebaseDatabase.getInstance().getReference("Movies");

        iFirebaseLoadDone=this;
        loadWall();
        viewPager =findViewById(R.id.viewpager);
        viewPager.setPageTransformer(true,new DepthPageTransformer());
    }

    private void loadWall() {
        walls.addListenerForSingleValueEvent(new ValueEventListener() {
            List<Wall> wallList=new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot wallSnapshot:dataSnapshot.getChildren()){
                    wallList.add(wallSnapshot.getValue(Wall.class));
                    iFirebaseLoadDone.onFirebaseLoadSuccess( wallList);

                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                iFirebaseLoadDone.onFirebaseLoasFailed(databaseError.getMessage());
            }
        });
    }

    @Override
    public void onFirebaseLoadSuccess(List<Wall> wallList) {
        adapter=new MyAdapter(this,wallList,getLayoutInflater());
        viewPager.setAdapter(adapter);
        
    }

    @Override
    public void onFirebaseLoasFailed(String message) {

    }
}
