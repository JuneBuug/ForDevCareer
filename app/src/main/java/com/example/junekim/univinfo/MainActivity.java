package com.example.junekim.univinfo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import static com.google.android.gms.internal.zzs.TAG;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity{

    @ViewById
    TextView fb_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    protected void afterViews(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myRef = db.getReference("message");

        myRef.setValue("안녕 나는 준");


        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                fb_test.setText(value);
                Log.d(TAG,"Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG,"값을 읽는데 실패했습니다.");
            }
        });
    }
}
